/* Copyright (c) 2013 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 *                       
 */
package com.plgrim.ncp.commons.auth.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.*;
import com.plgrim.ncp.commons.repository.AuthenticationRepository;
import com.plgrim.ncp.commons.repository.SysAdmlogRepository;
import com.plgrim.ncp.commons.result.AuthResult;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * EditorService Implementation
 * @author Tam
 *
 */
@Service
@Slf4j
public class AuthenticationService extends AbstractService {

	@Autowired
    AuthenticationRepository authenticationRepository; 
	
	@Autowired
	SysAdmlogRepository sysAdmlogRepository;
	
	@Autowired
	InterfaceApiCommon interfaceApiCommon;
	
	/**
	 * 관리자 정보를 조회한다.
	 * @param boSiteId
	 * @param adminId
	 * @param adminPwSha256
	 * @return
	 * @throws Exception
	 */
	public SysAdmin selectRowSysAdmInfo ( String boSiteId, String adminId) throws Exception {
		
		return authenticationRepository.selectRowSysAdmInfo(boSiteId, adminId );
	}
	
	/**
	 * 담당 몰 정보 조회
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public List<SysMall> selectListAuthAdmMall( String adminId) throws Exception {
		return authenticationRepository.selectListAuthAdmMall( adminId);
	}

	/**
	 * MD 담당브랜드정보 조회
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public List<SysBrnd> selectListAuthAdmBrnd( String adminId) throws Exception {
		return authenticationRepository.selectListAuthAdmBrnd( adminId);
	}

	/**
	 * 업체 담당브랜드정보 조회
	 * @param comId
	 * @return
	 * @throws Exception
	 */
	public List<SysBrnd> selectListAuthComBrnd( String comId) throws Exception {
		return authenticationRepository.selectListAuthComBrnd( comId);
	}
	
	
	/**
	 * 관리자 메뉴 File 유형 및 권한정보를 조회한다.
	 * @param boSiteId
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public List<AuthResult> selectListAdmAuthUrlAct( String boSiteId, String adminId) throws Exception {
		return authenticationRepository.selectListAdmAuthUrlAct( boSiteId, adminId);
	}
	public List<AuthResult> selectListAdmDynamicAuthUrlAct( String boSiteId, String adminId) throws Exception {
		return authenticationRepository.selectListAdmDynamicAuthUrlAct( boSiteId, adminId);
	}

	
	
	/**
	 * 관리자로그인 가능여부를 체크한다.
	 * @param adminId
	 * @param adminPwd
	 * @return
	 */
	public boolean isLoginCheck( String boSiteId, String adminId, String adminPwd ) throws Exception {
		
		log.debug("==============================================================================================");
		boolean isResult = false;
		
		String isPw = authenticationRepository.isLoginCheck(boSiteId, adminId, getIdGenService().generateSHA256(adminPwd)) ;
		
		if( "1".equals(isPw)) {
			
			SysAdmin sysAdmin = selectRowSysAdmInfo(boSiteId , adminId);
			
			if( "Y".equals( sysAdmin.getLoginPsbIpChkYn() )){
				//허용가능 IP 체크
				if( ContextService.getCurrentRequest().getRemoteAddr().equals( sysAdmin.getLoginPsbIp())){
					isResult = true;
				}
			}else{
				isResult = true;
			}
			
		}else if( "2".equals(isPw)) {
			//비밀번호 틀림.
			authenticationRepository.updateLoginFailrCnt(adminId);
		}
		
		if( isResult ){
			//비밀번호 초기화
			authenticationRepository.updateLoginFailrCntInit(adminId);
		}
		
		return isResult;
		
	}
	/**
	 * EP에서 SSO 를 통한 관리자로그인 가능여부를 체크한다.
	 * @param boSiteId
	 * @param shopId
	 * @return
	 */
	public String isLoginCheckEPSSO( String boSiteId, String shopId) throws Exception {

		String adminId = authenticationRepository.isLoginCheckEPSSO(boSiteId, shopId) ;

		if(StringUtils.isNotEmpty(adminId)) {
			SysAdmin sysAdmin = selectRowSysAdmInfo(boSiteId , adminId);

			if( "Y".equals( sysAdmin.getLoginPsbIpChkYn() )){
				//허용가능 IP 체크
				if( ContextService.getCurrentRequest().getRemoteAddr().equals( sysAdmin.getLoginPsbIp())){

				}
			}else{

			}
		}else{
			//없는 아이디
		}
		return adminId;
	}

	public SysAdmin selectBoLoginIdInfo(String adminId) throws Exception {
		return authenticationRepository.selectRowSysAdmInfo("BO", adminId);
	}

	public SysAdmin selectPoLoginIdInfo(String adminId) throws Exception {
		return authenticationRepository.selectRowSysAdmInfo("PO", adminId);
	}

	public void updateFailrCnt5Init(String adminId, String pw) throws Exception {
		authenticationRepository.updateFailrCnt5Init(adminId, pw);
	}

	/**
	 * 로그인 히스트로 업데이트 
	 * @param adminId
	 * @throws Exception
	 */
	public void updateLogin(String adminId, String sessionId) throws Exception {
		
		//마지막 로그인 일자 업데이트
		authenticationRepository.updateLastLoginDt(adminId, sessionId);
	}
	
	/**
	 * 권한그룹 메뉴정보 조회
	 * @param boSiteId
	 * @param authorGrpSn
	 * @return
	 * @throws Exception
	 */
	public List<SysMenuExtend> selectListAuthorGrpMenu( String boSiteId, long authorGrpSn) throws Exception {
		return authenticationRepository.selectListAuthorGrpMenu( boSiteId,authorGrpSn);
	}
	
	
	/**
	 * 권한그룹코드를 조회한다.
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public long getAdmAuthorGrpSn( String adminId) throws Exception {
		
		List<SysAdminAuthorGrpMapng>  dataList = authenticationRepository.selectListAdmAuthgrp( adminId);
		
		Iterator<SysAdminAuthorGrpMapng> iterator = dataList.iterator();
		
		long authorGrpSn = 0;
		
		//운영자별 권한그룹은 1개이기 떄문에 if 문으로 처리.
		if(iterator.hasNext()) {
			SysAdminAuthorGrpMapng rowData = iterator.next();
			authorGrpSn = rowData.getAuthorGrpSn();
		}
		
		return authorGrpSn;
	}
	
	/**
	 * 메뉴정보 조회
	 * @param boSiteId
	 * @param menuUrl
	 * @return
	 * @throws Exception
	 */
	public SysMenu  selectRowMenuFromUrl( String boSiteId, String menuUrl ) throws Exception {
		
		return authenticationRepository.selectRowMenuFromUrl(boSiteId, menuUrl);
	}
	
	
	/**
	 * 개인정보 동의여부 조회(Y or N )
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public String getStplatAgrYn( String adminId, String boSiteId, String stplatCd) throws Exception {
		return authenticationRepository.getStplatAgrYn(adminId, boSiteId, stplatCd);
	}
	
	public List<SysBrnd> selectListAuthAffBrnd( String comId) throws Exception {
		return authenticationRepository.selectListAuthAffBrnd( comId);
	}
	
	/**
	 * 접근브랜드정보를 조회한다.
	 * (브랜드정보가 null이면 전체 브랜드 접근 가능)
	 * @param sysAdmin
	 * @return
	 * @throws Exception
	 */
	public List<SysBrnd> selectAuthBrndList(SysAdmin sysAdmin) throws Exception {
	    
		//자사 :   담당브랜드가 설정된 자사직원일 경우만 담당브랜드 데이타만  접근관리할 수 있다.  그외 자사직원은 제한이 없습니다. ( 담당브랜드가 설정된 사용자는 운영자 MD유형입니다.)
		//입점업체 :   자사 브랜드가 존재하는 업체를 말하며  자사브랜드 관련된 데이타만 접근관리 할 수 있다.  (자사브랜드 : 입점업체괸리에서 등록된 자사브랜드))

		/*MCOM	자사
		PARTMAL_COM	입점업체
		AFF_COM	제휴업체
		AFF_AGNC	제휴대행사
		AFF_AD	광고제휴사
		POTOGRF_COM	촬영업체
		CNSLR	상담원
		ETC	기타 */
		
		List<SysBrnd> returnList = new ArrayList<SysBrnd>();
		
		if( "MCOM".equals( sysAdmin.getAdminTpCd() ) ){ //자사
			
			//SYS_ADMIN_BRND
			returnList = selectListAuthAdmBrnd(sysAdmin.getAdminId());
			
		}else if( "PARTMAL_COM".equals( sysAdmin.getAdminTpCd() ) ){ //입점업체

			//COM_BRND
			returnList = selectListAuthComBrnd( sysAdmin.getComId());
			
		}else if( "AFF_AGNC".equals( sysAdmin.getAdminTpCd() ) ){ //제휴대행사

			//COM_BRND
			returnList = selectListAuthAffBrnd( sysAdmin.getComId());
		}
		
    	return (returnList.size() > 0)?returnList:null;
    }
	
	/**
	 * PO Brand ACL
	 * (브랜드정보가 null이면 해당 입점업체 브랜드 접근 불가)
	 * @param sysAdmin
	 * @return
	 * @throws Exception
	 */
	public List<SysBrnd> selectPoAuthBrndList(SysAdmin sysAdmin) throws Exception {
		List<SysBrnd> returnList = new ArrayList<SysBrnd>();
		log.debug("PO기준 ACL Start...");
		returnList = selectListAuthAdmBrnd(sysAdmin.getAdminId());
		log.debug("PO기준 ACL End...");
    	return (returnList.size() > 0)?returnList:null;
    }

	/**
	 * 비밀번호 체크
	 * @param boSiteId
	 * @param adminId
	 * @param adminPwd
	 * @return
	 * @throws Exception
	 */
	public boolean isAdminPwdCheck( String boSiteId, String adminId, String adminPwd ) throws Exception {
		
		boolean isResult = false;
		
		String isPw = authenticationRepository.isLoginCheck(boSiteId, adminId, getIdGenService().generateSHA256(adminPwd)) ;
		
		if( "1".equals(isPw)) {
			isResult = true;	
		}		
		return isResult;
		
	}
	
	/**
	 * full url 권한체크 메뉴 조회
	 * @param boSiteId
	 * @return
	 */
	public List<AuthResult> selectAdmAuthCheckUrlAct(String boSiteId) throws Exception {
		return authenticationRepository.selectAdmAuthCheckUrlAct( boSiteId);
	}
	
	/**
	 * 관리자 로그인 SMS 인증정보 merge
	 */
	public int mergeSysAdminCrtfc(SysAdminCrtfc sysAdminCrtfc) {
		return authenticationRepository.mergeSysAdminCrtfc(sysAdminCrtfc);
	}
	
	/**
	 * SMS 인증 필요여부
	 */
	public String getSmsAuthInfoCheck(String boSiteId, String adminId) {
		String rtnCd = "";

		try{
			SysAdmin sysAdmin = selectRowSysAdmInfo(boSiteId, adminId);
			
			if(sysAdmin != null) {
				/*
				//1.휴대폰번호 인증 필요 체크
				if(!"Y".equals(sysAdmin.getFirstCrtfcYn())) {
					rtnCd = CommonResponseCode.MB_00073_휴대폰번호_인증_필요.toMessage();
				} else {
					//2.계정잠김 체크
					if(sysAdmin.getAcmtlCrtfcFailrCount() >= 5) {
						rtnCd = CommonResponseCode.MB_00076_5회_이상_실패로_계정잠김.toMessage();
						return rtnCd;
					}
					//3.SMS 인증 필요기간 이내인 경우(1일)
					if("Y".equals(sysAdmin.getCrtfcNoAuthValidYn())) {
						rtnCd = CommonResponseCode.MB_00072_SMS_인증_불필요.toMessage();
						return rtnCd;
					}
					//4.SMS 인증번호 재전송 필요 체크
					if(!"Y".equals(sysAdmin.getCrtfcNoValidTimeYn())) {
						rtnCd = CommonResponseCode.MB_00079_SMS_인증_재전송_필요.toMessage();
					} else {
						rtnCd = CommonResponseCode.MB_00071_SMS_인증_필요.toMessage();
					}
				}
				*/
			} else {
				rtnCd = CommonResponseCode.MB_00077_ID_존재하지_않음.toMessage();
				return rtnCd;
			}
		} catch(Exception e){
			log.warn(CommonResponseCode.SC_50000_시스템_오류가_발생하였습니다.toMessage(), e);
			throw new RuntimeException(e);
		}
		
		return rtnCd;
	}
	
	/**
	 * SMS 인증번호 발송
	 */
	public String sendSmsAuthNo(String boSiteId, String adminId) {
		String rtnCd = "";

		try{
			SysAdmin sysAdmin = selectRowSysAdmInfo(boSiteId, adminId);
			
			if(sysAdmin != null) {
				String mobileNo = sysAdmin.getMobilAreaNo().trim() + sysAdmin.getMobilTlofNo().trim() + sysAdmin.getMobilTlofWthnNo().trim();
				if(StringUtils.isNotEmpty(mobileNo) && mobileNo.length() > 4) {
					//1.SMS 인증번호 발송
					String smsAuthNo = this.smsAuthNoGenarator(mobileNo);
					log.debug(">>>sendSmsAuthNo smsAuthNo : {}, {}", adminId, smsAuthNo);
					
					String msgKey = "DXM_SMS_AUTH_NO";
					ArrayList<String> params = new ArrayList<>();
					params.add(0, smsAuthNo);

					// TODO 문자 솔루션 적용

//					HumusonSDO humusonSDO = humusonCommonService.getSmsData(adminId, mobileNo, "DXM", null, AuthenticationService.class.getName() + ".sendSmsAuthNo", null, msgKey, params);
//					log.debug(">>>sendSmsAuthNo humusonSDO : {}", humusonSDO);
//
//					// Adapter Header 를 세팅한다.
//					AdapterHeader adapterHeader = new AdapterHeader();
//					adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
//					adapterHeader.setMallId("DXM");
//					adapterHeader.setLangCd("KOR");
//					adapterHeader.setDvcCd("PC");
//
//					String result = humusonCommonService.sendSmsMms(humusonSDO, adapterHeader);
					//응답코드(result)
					//00 : 성공
					//05 : 성공(실패건 있음)
					//09 : 성공(화이트리스트)
					//10 : 전체 실패
					//11 : JSON 형식 오류
					//90 : DB오류
					//99 : 시스템 오류
//					log.info(">>>sendSmsAuthNo adminId / result  : {} / {}", adminId, result);
					//2.SMS 전송 인증번호 저장(DB)
					SysAdminCrtfc sysAdminCrtfc = new SysAdminCrtfc();
					sysAdminCrtfc.setAdminId(adminId);
					sysAdminCrtfc.setRegtrId(adminId);
					sysAdminCrtfc.setUdterId(adminId);
					sysAdminCrtfc.setLastCrtfcNo(smsAuthNo);
					int resultCnt = authenticationRepository.mergeSysAdminCrtfc(sysAdminCrtfc);
					
					rtnCd = CommonResponseCode.MB_00080_SMS_인증번호_전송_성공.toMessage();
				} else {
					rtnCd = CommonResponseCode.MB_00078_휴대폰번호_존재하지_않음.toMessage();
				}
			} else {
				rtnCd = CommonResponseCode.MB_00077_ID_존재하지_않음.toMessage();
			}
		} catch(Exception e){
			log.warn(CommonResponseCode.SC_50000_시스템_오류가_발생하였습니다.toMessage(), e);
			throw new RuntimeException(e);
		}
		
		return rtnCd;
	}
	
	/**
	 * SMS 인증번호 생성 유틸(휴대폰번호 맨뒤 4자리와 현재시간을 곱해서 끝에서 4자리를 return)
	 */
	public String smsAuthNoGenarator(String mobileNo){
		String lastMobileNo = mobileNo.substring(mobileNo.length()-4, mobileNo.length());
		String rtnAuthNo = Long.toString((Integer.parseInt(lastMobileNo) * System.currentTimeMillis()));
		rtnAuthNo = rtnAuthNo.substring(rtnAuthNo.length()-4, rtnAuthNo.length());
		return rtnAuthNo;
	}
	
	/**
	 * SMS 인증 처리 및 일치 여부 반환
	 */
	public String getSmsAuthentication(String boSiteId, String adminId, String smsAuthNo) {
		String rtnCd = "";

		try{
			SysAdmin sysAdmin = selectRowSysAdmInfo(boSiteId, adminId);
			
			if(sysAdmin != null) {
				/*
				//1.휴대폰번호 인증 필요 체크
				if(!"Y".equals(sysAdmin.getFirstCrtfcYn())) {
					rtnCd = CommonResponseCode.MB_00073_휴대폰번호_인증_필요.toMessage();
				} else {
					//2.계정잠김 체크
					if(sysAdmin.getAcmtlCrtfcFailrCount() >= 5) {
						rtnCd = CommonResponseCode.MB_00076_5회_이상_실패로_계정잠김.toMessage();
						return rtnCd;
					}
					
					//3.SMS 인증번호 일치여부 체크 및 결과 저장(DB)
					SysAdminCrtfc sysAdminCrtfc = new SysAdminCrtfc();
					sysAdminCrtfc.setAdminId(adminId);
					sysAdminCrtfc.setRegtrId(adminId);
					sysAdminCrtfc.setUdterId(adminId);
					log.debug(">>>getSmsAuthentication exec adminId / smsAuthNo / LastCrtfcNo  : {} / {} / {}", adminId, smsAuthNo, sysAdmin.getLastCrtfcNo());
					
					if(smsAuthNo.equals(sysAdmin.getLastCrtfcNo())) {
						rtnCd = CommonResponseCode.MB_00074_인증번호_일치.toMessage();
						sysAdminCrtfc.setLastCrtfcSuccesYn("Y");
					} else {
						rtnCd = CommonResponseCode.MB_00075_인증번호_불일치.toMessage();
						sysAdminCrtfc.setLastCrtfcSuccesYn("N");
						log.info(">>>getSmsAuthentication fail adminId / smsAuthNo / LastCrtfcNo  : {} / {} / {}", adminId, smsAuthNo, sysAdmin.getLastCrtfcNo());
					}
					int resultCnt = authenticationRepository.mergeSysAdminCrtfc(sysAdminCrtfc);
				}
				*/
			} else {
				rtnCd = CommonResponseCode.MB_00077_ID_존재하지_않음.toMessage();
			}
		} catch(Exception e){
			log.warn(CommonResponseCode.SC_50000_시스템_오류가_발생하였습니다.toMessage(), e);
			throw new RuntimeException(e);
		}
		
		return rtnCd;
	}

	/**
	 * 휴대폰 인증 실패 횟수 초기화
	 * @param adminId
	 * @throws Exception
	 */
	public void updateCrtfcFailCnt(String adminId) throws Exception {
		authenticationRepository.updateCrtfcFailCnt(adminId);
	}
}