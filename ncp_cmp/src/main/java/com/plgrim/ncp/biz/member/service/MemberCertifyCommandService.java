package com.plgrim.ncp.biz.member.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.base.repository.mbr.MbrCrtfcRepository;
import com.plgrim.ncp.biz.member.data.Ipin;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.Pcc;
import com.plgrim.ncp.biz.member.repository.MemberCertifyCommandRepository;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

/**
 * 회원인증정보 command service
 */
@Service
@Slf4j
public class MemberCertifyCommandService extends AbstractService {
	 
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;
	
	@Autowired
	MemberCertifyCommandRepository memberCertifyCommandRepository;
	
	@Autowired
	MbrCrtfcRepository mbrCrtfcRepository;
	
	@Autowired
    InterfaceApiCommon interfaceApiCommon;
	

	/**
	 * 임직원 인증 후 회원정보 업데이트
	 * @param mbr
	 * @
	 */
	public void updateEmpInfo(Mbr mbr) {
		memberCertifyCommandRepository.updateEmpInfo(mbr);
	}
	
	public boolean inesrtMemberCrtfc(MemberFoDTO dto) {
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("MBR_NO", dto.getMbrCrtfc().getMbrNo());
		dto.getMbrCrtfc().setMbrCrtfcTurn(getIdGenService().generateDBOrder(sqlSession1, "MBR_CRTFC", "MBR_CRTFC_TURN", conditions, DatabaseType.ORACLE));
		int cnt = memberCertifyCommandRepository.insertMemberCrtfc(dto);
		if(cnt > 0){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * 임직원 인증 후 인증 완료여부 업데이트
	 * @param mbr
	 * @
	 */
	public void updateEmpEnfrcCrtfcYn(Mbr mbr) {
		memberCertifyCommandRepository.updateEmpEnfrcCrtfcYn(mbr);

		// 인증 완료한 회원의 사번과 같은 SSO_GRP_CD 계정으로 인증코드 발송한 이력이 있을 경우 인증 이력 삭제
		memberCertifyCommandRepository.deleteSsoEmpEnfrcCrtfc(mbr);
	}
	
	/**
	 * 아이핀 세팅
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param reqIpinNum [설명]
	 * @return Ipin [설명]
	 * @ the exception
	 * @since 2015. 4. 15
	 */
	public Ipin setIPIN(String reqIpinNum, String srv) {
		// IPIN 인중 부분
		log.info("=== srv : [" + srv + "]");

		Kisinfo.Check.IPIN2Client niceCheck = new  Kisinfo.Check.IPIN2Client();
		
		String sSiteCode = getConfigService().getProperty("ncp_base.ipin.sitecode");						// NICE로부터 부여받은 사이트 코드
		String sSitePassword = getConfigService().getProperty("ncp_base.ipin.sitepassword");		// NICE로부터 부여받은 사이트 패스워드
		String sReturnUrl = getConfigService().getProperty("ncp_base.ipin.returnurl");					// 성공시 이동될 URL
		
		int iReturn = niceCheck.fnRequest(sSiteCode, sSitePassword, reqIpinNum, sReturnUrl);
		
		String sEncData = "";
		
		if( iReturn == 0 )
		{
			sEncData = niceCheck.getCipherData();
		}

		Ipin ipin =new Ipin();
		ipin.setReqIpinInfo(sEncData);
		ipin.setRetIpinUrl(sReturnUrl);
		ipin.setReqIpinNum(reqIpinNum);
		ipin.setCertificationDiv(SessionEnum.IPIN.toString());
		return ipin;
	}
	
	/**
	 * 본인인증 세팅
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param reqPccNum [설명]
	 * @param day [설명]
	 * @param srv
	 * @param device
	 * @return Pcc [설명]
	 * @ the exception
	 * @since 2015. 4. 15
	 */
	public Pcc setPCC(String reqPccNum, String day, String srv, String device) {
		// 휴대폰 본인인증 부분
		log.info("=== srv : [" + srv + "]");
		
		NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();
		
		String sSiteCode = getConfigService().getProperty("ncp_base.cert.sitecode");					// NICE로부터 부여받은 사이트 코드
		String sSitePassword = getConfigService().getProperty("ncp_base.cert.sitepassword");		// NICE로부터 부여받은 사이트 패스워드
		
		String sRequestNumber = reqPccNum;
//		sRequestNumber = niceCheck.getRequestNO(sSiteCode);
	 	String sAuthType = "";										// 없으면 기본 선택화면, M: 핸드폰, C: 신용카드, X: 공인인증서
	 	String popgubun 	= "N";									// Y : 취소버튼 있음 / N : 취소버튼 없음
	 	String customize 	= "";										// 없으면 기본 웹페이지 / Mobile : 모바일페이지
	 	if(!DisplayEnum.DVC.PC.toString().equals(device)) {
	 		customize = "Mobile";
	 	}
	 	String sGender = "";											//없으면 기본 선택 값, 0 : 여자, 1 : 남자 
	
		// CheckPlus(본인인증) 처리 후, 결과 데이타를 리턴 받기위해 다음예제와 같이 http부터 입력합니다.
	 	// 리턴url은 인증 전 인증페이지를 호출하기 전 url과 동일해야 합니다. ex) 인증 전 url : http://www.~ 리턴 url : http://www.~
		String sReturnUrl = getConfigService().getProperty("ncp_base.cert.returnsuccessurl");		// 성공시 이동될 URL
		String sErrorUrl = getConfigService().getProperty("ncp_base.cert.returnfailurl");	// 실패시 이동될 URL
		
		// 입력될 plain 데이타를 만든다.
		String sPlainData = "7:REQ_SEQ" + sRequestNumber.getBytes().length + ":" + sRequestNumber +
										"8:SITECODE" + sSiteCode.getBytes().length + ":" + sSiteCode +
										"9:AUTH_TYPE" + sAuthType.getBytes().length + ":" + sAuthType +
										"7:RTN_URL" + sReturnUrl.getBytes().length + ":" + sReturnUrl +
										"7:ERR_URL" + sErrorUrl.getBytes().length + ":" + sErrorUrl +
										"11:POPUP_GUBUN" + popgubun.getBytes().length + ":" + popgubun +
										"9:CUSTOMIZE" + customize.getBytes().length + ":" + customize + 
										"6:GENDER" + sGender.getBytes().length + ":" + sGender
										;
		
		String sMessage = "";
		String sEncData = "";
		
		int iReturn = niceCheck.fnEncode(sSiteCode, sSitePassword, sPlainData);
		if( iReturn == 0 )
		{
			sEncData = niceCheck.getCipherData();
		}
		else {
			sMessage = this.niceEncodeMsg(iReturn);
		}
	
		Pcc pcc = new Pcc();
		pcc.setReqPccInfo(sEncData);
		pcc.setReqPccNum(reqPccNum);
		pcc.setRetPccUrl(sReturnUrl);
		pcc.setCertificationDiv(SessionEnum.PCC.toString());
		log.info("=== setPCC :: [" + sMessage + "]");
		
		return pcc;
	}
	
	/**
	 * 아이핀 인증 절차 및 성공시 값 세팅
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param reqIpinNum [설명]
	 * @param retInfo [설명]
	 * @return Ipin [설명]
	 * @ the exception
	 * @since 2015. 4. 15
	 */
	public Ipin getIPIN(String reqIpinNum, String retInfo) {
		
		Kisinfo.Check.IPIN2Client niceCheck = new  Kisinfo.Check.IPIN2Client();
		
		// 변수 --------------------------------------------------------------------------------
		
		String sSiteCode = getConfigService().getProperty("ncp_base.ipin.sitecode");						// NICE로부터 부여받은 사이트 코드
		String sSitePassword = getConfigService().getProperty("ncp_base.ipin.sitepassword");		// NICE로부터 부여받은 사이트 패스워드
		String sVNumber = "";					// 가상주민번호 (13자리이며, 숫자 또는 문자 포함)
		String sName = "";							// 이름
		String sDupInfo = "";						// 중복가입코드 (DI - 64 byt고유값)
		String sAgeCode = "";					// 연령대 코드 (개발 가이드 참조)
		String sGenderCode = "";				// 성별 코드 (개발 가이드 참조)
		String sBirthDate = "";					// 생년월일 (YYYYMMDD)
		String sNationalInfo = "";				// 내/외국인 정보
		String sCPRequestNum = "";			// CP 요청번호
		String sCoInfo1 = "";						// 연계정보 확인값 (CI - 88 byte 고유값)
		String sCIUpdate = "";					// CI 갱신정보
		String sMessage = "";

		//-----------------------------------------------------------------------------------
		
		// Parameter 수신 --------------------------------------------------------------------
		int iReturn = niceCheck.fnResponse(sSiteCode, sSitePassword, retInfo);
		
		if( iReturn == 1 ) 	// 인증성공
		{
			sVNumber = niceCheck.getVNumber();
			sName = niceCheck.getName();
			sDupInfo = niceCheck.getDupInfo();
			sAgeCode = niceCheck.getAgeCode();
			sGenderCode = niceCheck.getGenderCode();
			sBirthDate = niceCheck.getBirthDate();
			sNationalInfo = niceCheck.getNationalInfo();
			sCPRequestNum = niceCheck.getCPRequestNO();
			sCoInfo1 = niceCheck.getCoInfo1();
			sCIUpdate = niceCheck.getCIUpdate();
			
			log.info("=== sVNumber :: [" + sVNumber + "]");
			log.info("=== sName :: [" + sName + "]");
			log.info("=== sDupInfo :: [" + sDupInfo + "]");
			log.info("=== sAgeCode :: [" + sAgeCode + "]");
			log.info("=== sGenderCode :: [" + sGenderCode + "]");
			log.info("=== sBirthDate :: [" + sBirthDate + "]");
			log.info("=== sNationalInfo :: [" + sNationalInfo + "]");
			log.info("=== sCPRequestNum :: [" + sCPRequestNum + "]");
			log.info("=== sCoInfo1 :: [" + sCoInfo1 + "]");
			log.info("=== sCIUpdate :: [" + sCIUpdate + "]");

			sMessage = "정상 처리되었습니다.";
		} else {
			// 인증 결과 데이터 오류 - API Specification 참조
		}

		Ipin ipin = new Ipin();
		
		ipin.setReqnum(sCPRequestNum);
		ipin.setVdiscrno(sVNumber);
		ipin.setName(sName);
		ipin.setAge(sAgeCode);
		ipin.setSex(sGenderCode);
		ipin.setBirth(sBirthDate);
		ipin.setFgn(sNationalInfo);
		ipin.setDiscrhash(sDupInfo);
		ipin.setCiscrhash(sCoInfo1);
		ipin.setCiversion(sCIUpdate);
		ipin.setResult("Y");

		return ipin;
	}
	
	/**
	 * 본인인증 확인 절차 및 성공시 값 세팅
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param reqPccNum [설명]
	 * @param retInfo [설명]
	 * @return Pcc [설명]
	 * @ the exception
	 * @since 2015. 4. 15
	 */
	public Pcc getPCC(String reqPccNum, String retInfo, boolean successFlag) {
		
		NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();
		
	    String sEncodeData = requestReplace(retInfo, "encodeData");
		
	    // 변수 --------------------------------------------------------------------------------

		String sSiteCode = getConfigService().getProperty("ncp_base.cert.sitecode");					// NICE로부터 부여받은 사이트 코드
		String sSitePassword = getConfigService().getProperty("ncp_base.cert.sitepassword");		// NICE로부터 부여받은 사이트 패스워드
		String sCipherTime = "";			// 복호화한 시간
		String sRequestNumber = "";	// 요청 번호
		String sResponseNumber = "";	// 인증 고유번호
		String sAuthType = "";				// 인증 수단
		String sName = "";						// 성명
		String sUtf8Name = "";				// 성명(charset utf8)
		String sDupInfo = "";					// 중복가입 확인값 (DI_64 byte)
		String sConnInfo = "";				// 연계정보 확인값 (CI_88 byte)
		String sBirthDate = "";				// 생년월일(YYYYMMDD)
		String sGender = "";					// 성별
		String sNationalInfo = "";			// 내/외국인정보 (개발가이드 참조)
		String sMobileNo = "";				// 휴대폰번호
		String sMobileCo = "";				// 통신사
		String sMessage = "";
		String sPlainData = "";
		String sErrorCode = "";				// 인증 결과코드
		
		//-----------------------------------------------------------------------------------

		// Parameter 수신 --------------------------------------------------------------------
		int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

	    if( iReturn == 0 ) {
	        sPlainData = niceCheck.getPlainData();
	        sCipherTime = niceCheck.getCipherDateTime();
	        
	        // 데이타를 추출합니다.
	        java.util.HashMap<String, Object> mapResult = niceCheck.fnParse(sPlainData);
	        
	        if(successFlag) {
		        sRequestNumber		= (String)mapResult.get("REQ_SEQ");
		        sResponseNumber	= (String)mapResult.get("RES_SEQ");
		        sAuthType				= (String)mapResult.get("AUTH_TYPE");
		        sName						= (String)mapResult.get("NAME");
				sUtf8Name				= (String)mapResult.get("UTF8_NAME"); //charset utf8 사용시 주석 해제 후 사용
		        sBirthDate				= (String)mapResult.get("BIRTHDATE");
		        sGender					= (String)mapResult.get("GENDER");
		        sNationalInfo			= (String)mapResult.get("NATIONALINFO");
		        sDupInfo					= (String)mapResult.get("DI");
		        sConnInfo					= (String)mapResult.get("CI");
		        sMobileNo				= (String)mapResult.get("MOBILE_NO");
		        sMobileCo				= (String)mapResult.get("MOBILE_CO");
		        
		        log.info("=== sCipherTime :: [" + sCipherTime + "]");
		        log.info("=== sRequestNumber :: [" + sRequestNumber + "]");
		        log.info("=== sResponseNumber :: [" + sResponseNumber + "]");
		        log.info("=== sAuthType :: [" + sAuthType + "]");
		        log.info("=== sName :: [" + sName + "]");
		        log.info("=== sUtf8Name :: [" + sUtf8Name + "]");
		        log.info("=== sBirthDate :: [" + sBirthDate + "]");
		        log.info("=== sGender :: [" + sGender + "]");
		        log.info("=== sNationalInfo :: [" + sNationalInfo + "]");
		        log.info("=== sDupInfo :: [" + sDupInfo + "]");
		        log.info("=== sConnInfo :: [" + sConnInfo + "]");
		        log.info("=== sMobileNo :: [" + sMobileNo + "]");
		        log.info("=== sMobileCo :: [" + sMobileCo + "]");
		        
		        if(!sRequestNumber.equals(reqPccNum))
		        {
		            sMessage = "세션값이 다릅니다. 올바른 경로로 접근하시기 바랍니다.";
		            sResponseNumber = "";
		            sAuthType = "";
		        }
	        }
	        else {
	        	sRequestNumber		= (String)mapResult.get("REQ_SEQ");
	        	sErrorCode				= (String)mapResult.get("ERR_CODE");
	        	sAuthType				= (String)mapResult.get("AUTH_TYPE");
	        }
	    }
	    else {
	        sMessage = this.niceDecodeMsg(iReturn);
	    }

	    log.info("=== getPCC :: [" + sMessage + "]");
	    
	    Pcc pcc= new Pcc();
		try {
			sUtf8Name = URLDecoder.decode(sUtf8Name, "UTF-8");
			log.info("=== sUtf8Name :: [" + sUtf8Name + "]");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	        
		pcc.setName(sName);
		pcc.setBirYMD(sBirthDate);
		pcc.setSex(sGender);
		pcc.setFgnGbn(sNationalInfo);
		pcc.setDi(sDupInfo);
		pcc.setCi(sConnInfo);
		pcc.setCellNo(sMobileNo);
		pcc.setCellCorp(sMobileCo);
		pcc.setCertDate(sCipherTime);
		pcc.setResult("Y");
			
		return pcc;
			
	}

	private String requestReplace(String paramValue, String gubun) {
		String result = "";

		if (paramValue != null) {

			paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

			paramValue = paramValue.replaceAll("\\*", "");
			paramValue = paramValue.replaceAll("\\?", "");
			paramValue = paramValue.replaceAll("\\[", "");
			paramValue = paramValue.replaceAll("\\{", "");
			paramValue = paramValue.replaceAll("\\(", "");
			paramValue = paramValue.replaceAll("\\)", "");
			paramValue = paramValue.replaceAll("\\^", "");
			paramValue = paramValue.replaceAll("\\$", "");
			paramValue = paramValue.replaceAll("'", "");
			paramValue = paramValue.replaceAll("@", "");
			paramValue = paramValue.replaceAll("%", "");
			paramValue = paramValue.replaceAll(";", "");
			paramValue = paramValue.replaceAll(":", "");
			paramValue = paramValue.replaceAll("-", "");
			paramValue = paramValue.replaceAll("#", "");
			paramValue = paramValue.replaceAll("--", "");
			paramValue = paramValue.replaceAll("-", "");
			paramValue = paramValue.replaceAll(",", "");

			if(gubun != "encodeData"){
				paramValue = paramValue.replaceAll("\\+", "");
				paramValue = paramValue.replaceAll("/", "");
				paramValue = paramValue.replaceAll("=", "");
			}

			result = paramValue;

		}
		return result;
	}

	private String niceEncodeMsg(int iReturn) {
		String returnMessage = "";
		
		if( iReturn == -1)
		{
			returnMessage = "암호화 시스템 에러입니다.";
		}    
		else if( iReturn == -2)
		{
			returnMessage = "암호화 처리오류입니다.";
		}    
		else if( iReturn == -3)
		{
			returnMessage = "암호화 데이터 오류입니다.";
		}    
		else if( iReturn == -9)
		{
			returnMessage = "입력 데이터 오류입니다.";
		}    
		else
		{
			returnMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
		}
		
		return returnMessage;
	}
	
	private String niceDecodeMsg(int iReturn) {
		String returnMessage = "";
	
	    if( iReturn == -1)
	    {
	    	returnMessage = "복호화 시스템 에러입니다.";
	    }    
	    else if( iReturn == -4)
	    {
	    	returnMessage = "복호화 처리오류입니다.";
	    }    
	    else if( iReturn == -5)
	    {
	    	returnMessage = "복호화 해쉬 오류입니다.";
	    }    
	    else if( iReturn == -6)
	    {
	    	returnMessage = "복호화 데이터 오류입니다.";
	    }    
	    else if( iReturn == -9)
	    {
	    	returnMessage = "입력 데이터 오류입니다.";
	    }    
	    else if( iReturn == -12)
	    {
	    	returnMessage = "사이트 패스워드 오류입니다.";
	    }    
	    else
	    {
	    	returnMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
	    }
		
		return returnMessage;
	}
	
}
