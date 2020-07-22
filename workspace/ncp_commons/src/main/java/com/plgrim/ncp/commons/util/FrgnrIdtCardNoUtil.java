package com.plgrim.ncp.commons.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.entities.datasource3.clm.ClmBnkAcctNoEncrtKey;
import com.plgrim.ncp.commons.cryption.Base64Coder;
import com.plgrim.ncp.commons.cryption.SeedCipher;
import com.plgrim.ncp.commons.result.RefundAccountResult;
import com.plgrim.ncp.commons.service.SysCommonService;

@Component
public class FrgnrIdtCardNoUtil {

	@Autowired 
	private SysCommonService sysCommonService;
	
	private SeedCipher seedChpher = new SeedCipher();
	

    
    
    /**
     * 외국인 개인 식별번호 복호화
     * @param encryptedFrgnrIdtCardNo : 암호화된 식별번호
     * @pramm maskOption : true:마스킹(디폴트), false:마스킹처리하지 않음.
     * @param userId : 사용자 아이디 
     * @param orderNo : 주문번호
     * @return 복호화된 환불계좌
     * @throws Exception
     */
    public String getDecryptFrgnrIdtCardNo(String encryptedFrgnrIdtCardNo, Boolean maskOption,String ordNo) throws Exception{
    	
    	String decryptText = "";
    	ClmBnkAcctNoEncrtKey dbKey = null;
    	
    	//00. 개인키 서버에서 개인키 추출
    	String personalKey = ""; 
    	dbKey = getPersonalKeyFromAnotherServerForFrgnrId(ordNo);
    	if(dbKey != null){
    		personalKey = dbKey.getEncrtPsnlKey();
    	}
    	
    	//01. 계좌번호 복호화
    	if(!"".equals(StringUtils.defaultIfEmpty(personalKey, "")) && !"".equals(StringUtils.defaultIfEmpty(encryptedFrgnrIdtCardNo, ""))) {
    		decryptText = seedChpher.decryptAsString(Base64Coder.decodeLines(encryptedFrgnrIdtCardNo), personalKey.getBytes(), "UTF-8");  
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
    	
    	return decryptText;
    }
    

    /**
     * 외국인 개인 식별번호 암호화
     * @param plainedRefundAccount : 평문 외국인 식별번호
     * @param userId : 사용자 아이디
     * @param orderNo : 주문번호
     * @param userGb : 회원, 비회원 
     * @return 암호화된 환불계좌
     * @throws Exception
     */
    public String getEncryptFrgnrIdtCardNo(String FrgnrIdtCardNo,String ordNo) throws Exception{
    	
    	seedChpher = new SeedCipher();
    	
    	Random randomText = new Random();
    	String encryptText = "";
        
    	//00. 개인키 서버에서 개인키 추출
    	
    	String personalKey = "";
    	ClmBnkAcctNoEncrtKey dbKey = null;
    	
    	for(int i=0; i<16; i++) {
            if(randomText.nextBoolean()) {
            	personalKey = personalKey + (String.valueOf((char)((int)(randomText.nextInt(26))+97)).toUpperCase());
            } else {
            	personalKey = personalKey + ((randomText.nextInt(16)));
            }
        }
    	
    	personalKey = StringUtils.substring(personalKey, 0, 16);
    	
    	//insert/update 구분
    	dbKey = getPersonalKeyFromAnotherServerForFrgnrId(ordNo);
    	
    	//암호화된 코드값이 없으면 코드값 생성을 하고 고객 코드값 테이블에 insert
    	//암호화된 코드값이 있으면 세로 암호화 하여 코드값 테이블에 update
        if(dbKey == null) {
            //01-1. 개인키서버에 개인키 저장
            setPersonalKeyToAnotherServerForFrgnrId(personalKey, ordNo);
        }else{
        	//01-2. 개인키서버에 개인키 수정
            putPersonalKeyToAnotherServerForFrgnrId(personalKey, ordNo);
        }
        
      
    	
        //01-2. 계좌번호 암호화
        if(!"".equals(StringUtils.defaultIfEmpty(FrgnrIdtCardNo, ""))){
            encryptText = Base64Coder.encodeLines(seedChpher.encrypt(FrgnrIdtCardNo, personalKey.getBytes(), "UTF-8"));    
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
    private ClmBnkAcctNoEncrtKey getPersonalKeyFromAnotherServerForFrgnrId(String clmNo) throws Exception {
    	
    	ClmBnkAcctNoEncrtKey clmKey = new ClmBnkAcctNoEncrtKey();
    	
    	clmKey.setClmNo(clmNo);

    	//clmKey = sysCommonService.getPrivateKeyForCryptionFrgnrId(clmKey);
    	
    	return clmKey;
    }
    
    
    /**
     * 개인키 저장
     * @param personalKey
     * @param userId
     * @param orderNo
     * @param userGb
     * @return
     */
    private boolean setPersonalKeyToAnotherServerForFrgnrId(String personalKey, String clmNo) {
    	boolean returnResult = false;
    	try {
    		
    		ClmBnkAcctNoEncrtKey clmKey = new ClmBnkAcctNoEncrtKey();
        	clmKey.setClmNo(clmNo);
        	clmKey.setEncrtPsnlKey(personalKey);
        	
    		//sysCommonService.setPrivateKeyForCryptionFrgnrId(clmKey);
    		
    		returnResult = true;
    	}catch(Exception ex) {
    		returnResult = false;
    	}
    	
    	return returnResult;
    }
    
    /**
     * 개인키 수정
     * @param personalKey
     * @param userId
     * @param orderNo
     * @param userGb
     * @return
     */
    private boolean putPersonalKeyToAnotherServerForFrgnrId(String personalKey, String clmNo) {
    	boolean returnResult = false;
    	try {
    		
    		ClmBnkAcctNoEncrtKey clmKey = new ClmBnkAcctNoEncrtKey();
        	clmKey.setClmNo(clmNo);
        	clmKey.setEncrtPsnlKey(personalKey);
        	
    		//sysCommonService.putPrivateKeyForCryptionFrgnrId(clmKey);
    		
    		returnResult = true;
    	}catch(Exception ex) {
    		returnResult = false;
    	}
    	
    	return returnResult;
    }
}
