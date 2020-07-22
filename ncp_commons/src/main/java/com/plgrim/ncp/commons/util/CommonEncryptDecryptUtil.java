package com.plgrim.ncp.commons.util;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.entities.datasource3.clm.ClmBnkAcctNoEncrtKey;
import com.plgrim.ncp.base.entities.datasource3.com.ComBnkAcctNoEncrtKey;
import com.plgrim.ncp.commons.cryption.Base64Coder;
import com.plgrim.ncp.commons.cryption.SeedCipher;
import com.plgrim.ncp.commons.service.SysCommonService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CommonEncryptDecryptUtil {

	@Autowired 
	private SysCommonService sysCommonService;
	
	private SeedCipher seedChpher = new SeedCipher();
	
	/**
	 * 암복호화 대상 업무구분
	 */
	public enum BizType{
		  CLAME	     // 클레임
		, VENDOR	 // 협력업체
		, ORDER	     // 주문 
	}
	
	
    /**
     * 복호화
     * @param encryptedRefundAccount : 암호화된 데이터
     * @pramm maskOption : true:마스킹(디폴트), false:마스킹처리하지 않음. 
     * @param key : 암호화 된 데이터를 복호화 하기 위한 data 키 (암호화시 사용한 data 키)
     * @return 복호화 된 데이터
     * @throws Exception
     * 
     * 기존 환불계좌 암호화 로직과 다르게 데이터당 키가 1:1 관계에서 >> 키 단위로 여러 데이터를 복호화 가능하게 수정
     */
    public String getDecryptData(String encryptedRefundAccount, Boolean maskOption,String key, BizType bizType) throws Exception{
    	
    	String decryptText = "";
    	String dbKey = "";
    	
    	
    	try {
        
        	//00. 개인키 서버에서 개인키 추출
        	String personalKey = ""; 
        	dbKey = getPersonalKeyFromAnotherServer(key, bizType);
        	if(StringUtils.isNotEmpty(dbKey)) {
        		personalKey = dbKey;
        	}
        	
        	//01. 계좌번호 복호화
        	if(!"".equals(StringUtils.defaultIfEmpty(personalKey, "")) && !"".equals(StringUtils.defaultIfEmpty(encryptedRefundAccount, ""))) {
        		decryptText = seedChpher.decryptAsString(Base64Coder.decodeLines(encryptedRefundAccount), personalKey.getBytes(), "UTF-8");  
            }
        	
        	if(maskOption == null) {
        		maskOption = true;
        	}
        	
        	//마스킹 처리
        	if(maskOption) {
        		
        		String headText = "";
        		String tailText = "";
        		
        		//계좌번호 마스킹 정책  :  앞 5자리만 보여지고 나머지는 *처리 
        		//(자릿수의 50%이상은 Masking되아 한다. 신한은행이 최소 11자리므로 5자리까지 보여주고 나머지는 마스킹 처리함)
        		Pattern pattern = Pattern.compile("[1-9]"); 
                Matcher matcher = pattern.matcher(decryptText);
                
                ArrayList matchArray = new ArrayList();
                
                while (matcher.find()) {
                	matchArray.add(matcher.start());
                }
                
                if(matchArray != null) {
    	            int delimeter = (int) Math.floor(matchArray.size()/2);
    	            
    	            if(delimeter >  0) {  //전체 마스킹 처리 방지를 위해 0보다 클경우만 마스킹 처리 함
    		            headText = StringUtils.substring(decryptText, 0, (int) matchArray.get(delimeter));
    		            tailText = StringUtils.substring(decryptText, (int) matchArray.get(delimeter));
    		            
    		            tailText = tailText.replaceAll("[1-9]", "*");
    		            
    		    		decryptText = headText + tailText;
    	            }
                }
        	}
		}
		catch (Exception e) {
			log.warn("", e);
			decryptText = encryptedRefundAccount;
		}

    	
    	return decryptText;
    }
    

    /**
     * 환불계좌 암호화
     * @param plainedData : 평문 데이터
     * @param key : 암호화시 사용할 data 키 (ordNo,mbrNo,comId 등등) 
     * @return 암호화된 데이터
     * @throws Exception
     * 
     * 기존 환불계좌 암호화 로직과 다르게 데이터당 키가 1:1 관계에서 >> 키 단위로 여러 데이터를 복호화 가능하게 수정
     * 
     */
    public String getEncryptData(String plainedData, String key, BizType bizType) throws Exception{
    	
    	seedChpher = new SeedCipher();
    	
    	Random randomText = new Random();
    	String encryptText = "";
        
    	//00. 개인키 서버에서 개인키 추출
    	
    	String personalKey = "";
    	String dbKey = "";
    	
    	for(int i=0; i<16; i++) {
            if(randomText.nextBoolean()) {
            	personalKey = personalKey + (String.valueOf((char)((int)(randomText.nextInt(26))+97)).toUpperCase());
            } else {
            	personalKey = personalKey + ((randomText.nextInt(16)));
            }
        }
    	
    	personalKey = StringUtils.substring(personalKey, 0, 16);
    	
    	//insert/update 구분
    	dbKey = getPersonalKeyFromAnotherServer(key, bizType);
    	
    	//암호화된 코드값이 없으면 코드값 생성을 하고 고객 코드값 테이블에 insert
    	//암호화된 코드값이 있으면 그거로 암호화
        if(StringUtils.isEmpty(dbKey)) {
            //01-1. key 값으로 랜덤키(개인키)값이 저장된게 없다면 복호화때를 위해 개인키서버에 개인키 저장
            setPersonalKeyToAnotherServer(personalKey, key, bizType);
        }else{
        	//01-2. key 값으로 랜덤키(개인키)값이 저장되어있다면 저장된 랜덤키값으로 암호화를 한다.
        	personalKey = dbKey;
        }
        
      
    	
        //01-2. 계좌번호 암호화
        if(!"".equals(StringUtils.defaultIfEmpty(plainedData, ""))){
            encryptText = Base64Coder.encodeLines(seedChpher.encrypt(plainedData, personalKey.getBytes(), "UTF-8"));    
        }
    	
    	return encryptText;
    }

    /**
     * 개인키 셀렉트
     * @param maskValue - true:마스킹 처리함, false : 마스킹 하지 않음.
     * @param userId
     * @param orderNo
     * @return
     * @throws Exception 
     */
    private String getPersonalKeyFromAnotherServer(String key, BizType bizType) {
    	String rtnKey = "";
    	try {
    		if(bizType.equals(BizType.CLAME)) {
        		ClmBnkAcctNoEncrtKey clmKey = new ClmBnkAcctNoEncrtKey();
            	clmKey.setClmNo(key);
            	//clmKey = sysCommonService.getPrivateKeyForCryption(clmKey);
            	rtnKey = clmKey.getEncrtPsnlKey();
        	} else if(bizType.equals(BizType.VENDOR)) {
        		ComBnkAcctNoEncrtKey comKey = new ComBnkAcctNoEncrtKey();
        		comKey.setComId(key);
        		//comKey = sysCommonService.selectComBnkAcctNoEncrtKey(comKey);
            	rtnKey = comKey.getEncrtKey();
        	}
    	}catch(Exception ex) {
    		log.warn("", ex);
    	}
    	
    	return rtnKey;
    }
    
    
    /**
     * 개인키 저장
     * @param personalKey
     * @param userId
     * @param orderNo
     * @param userGb
     * @return
     */
    private boolean setPersonalKeyToAnotherServer(String personalKey, String key, BizType bizType) {
    	boolean returnResult = false;
    	try {
    		if(bizType.equals(BizType.CLAME)) {
    			ClmBnkAcctNoEncrtKey clmKey = new ClmBnkAcctNoEncrtKey();
            	clmKey.setClmNo(key);
            	clmKey.setEncrtPsnlKey(personalKey);
        		//sysCommonService.setPrivateKeyForCryption(clmKey);
    		} else if(bizType.equals(BizType.VENDOR)) {
    			ComBnkAcctNoEncrtKey comKey = new ComBnkAcctNoEncrtKey();
    			comKey.setComId(key);
    			comKey.setEncrtKey(personalKey);
        		//sysCommonService.insertComBnkAcctNoEncrtKey(comKey);
        	}
    		returnResult = true;
    	}catch(Exception ex) {
    		log.warn("", ex);
    		returnResult = false;
    	}
    	
    	return returnResult;
    }
    
}
