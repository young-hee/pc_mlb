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
package com.plgrim.ncp.commons.repository;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plgrim.ncp.base.entities.datasource1.sys.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.commons.result.AuthResult;

/**
 * 권한그룹 Repository
 * @author tam
 *
 */
@Slf4j
@Repository
public class AuthenticationRepository extends AbstractRepository {
	
	/**
	 * 로그인 체크
	 * @param boSiteId
	 * @param adminId
	 * @param adminPwdSha256
	 * @return
	 * @throws Exception
	 */
	public String isLoginCheck ( String boSiteId, String adminId, String adminPwdSha256) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("boSiteId", boSiteId);
		paramData.put("adminId", adminId);
		paramData.put("adminPwdSha256", adminPwdSha256);
		
		log.info( paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.bo.authertication.isLoginCheck", paramData);
	}
	/**
	 * EP에서 SSO 를 통한 관리자로그인 가능여부를 체크한다.
	 * @param boSiteId
	 * @param shopId
	 * @return
	 * @throws Exception
	 */
	public String isLoginCheckEPSSO ( String boSiteId, String shopId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("boSiteId", boSiteId);
		paramData.put("shopId", shopId);
		
		log.info( paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.bo.authertication.isLoginCheckEPSSO", paramData);
	}
	
	/**
	 * 관리자 정보를 조회한다.
	 * @param boSiteId
	 * @param adminId
	 * @param adminPwSha256
	 * @return
	 * @throws Exception
	 */
	public SysAdmin selectRowSysAdmInfo ( String boSiteId, String adminId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("boSiteId", boSiteId);
		paramData.put("adminId", adminId);
		return getSession1().selectOne("com.plgrim.ncp.bo.authertication.selectRowSysAdmInfo", paramData);
	}

	/**
	 * 담당 몰 정보 조회
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public List<SysMall> selectListAuthAdmMall(String adminId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("adminId", adminId);

		return getSession1().selectList("com.plgrim.ncp.bo.authertication.selectListAuthAdmMall", paramData);
	}

	/**
	 * MD 담당브랜드정보 조회
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public List<SysBrnd> selectListAuthAdmBrnd( String adminId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("adminId", adminId);
		
		return getSession1().selectList("com.plgrim.ncp.bo.authertication.selectListAuthAdmBrnd", paramData);
	}

	/**
	 * 업체 담당브랜드정보 조회
	 * @param comId
	 * @return
	 * @throws Exception
	 */
	public List<SysBrnd> selectListAuthComBrnd( String comId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("comId", comId);
		
		return getSession1().selectList("com.plgrim.ncp.bo.authertication.selectListAuthComBrnd", paramData);
	}
	
	/**
	 * 제휴대행사 담당브랜드정보 조회
	 * @param comId
	 * @return
	 * @throws Exception
	 */
	public List<SysBrnd> selectListAuthAffBrnd( String comId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("comId", comId);
		
		return getSession1().selectList("com.plgrim.ncp.bo.authertication.selectListAuthAffBrnd", paramData);
	}
	
	
	
	/**
	 * 관리자 메뉴 File 유형 및 권한정보를 조회한다.
	 * @param boSiteId
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public List<AuthResult> selectListAdmAuthUrlAct( String boSiteId, String adminId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("adminId", adminId);
		paramData.put("boSiteId", boSiteId);

		return getSession1().selectList("com.plgrim.ncp.bo.authertication.selectListAdmAuthUrlAct", paramData);
	}
	
	/**
	 * 관리자 메뉴 File 유형 및 권한정보를 조회한다. (동적메뉴)
	 * @param boSiteId
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public List<AuthResult> selectListAdmDynamicAuthUrlAct( String boSiteId, String adminId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("adminId", adminId);
		paramData.put("boSiteId", boSiteId);

		return getSession1().selectList("com.plgrim.ncp.bo.authertication.selectListAdmDynamicAuthUrlAct", paramData);
	}


	/**
	 * 로그인 실패 count 횟수
	 * @param adminId
	 * @throws Exception
	 */
	public void updateLoginFailrCnt( String adminId) throws Exception {
		
		getSession1().update("com.plgrim.ncp.bo.authertication.updateLoginFailrCnt", adminId);
	}
	
	/**
	 * 로그인 실패 카운터 초기화
	 * @param adminId
	 * @throws Exception
	 */
	public void updateLoginFailrCntInit( String adminId) throws Exception {
		
		getSession1().update("com.plgrim.ncp.bo.authertication.updateLoginFailrCntInit", adminId);
	}
	
	/**
	 * 로그인 5 번 실패 비밀번호 초기화
	 * @param adminId
	 * @throws Exception
	 */
	public void updateFailrCnt5Init( String adminId, String pw) throws Exception {
		SysAdmin sysAdmin = new SysAdmin();
		sysAdmin.setAdminId(adminId);
		sysAdmin.setPw(pw);
		sysAdmin.setPwIntzYn(MemberEnum.YES.toString());
		sysAdmin.setRegtrId("system");
		getSession1().update("com.plgrim.ncp.bo.authertication.updateFailrCnt5Init", sysAdmin);
	}

	/**
	 * 로그인 일시 업데이트
	 * @param adminId
	 * @throws Exception
	 */
	public void updateLastLoginDt(String adminId, String sessionId) throws Exception {
		Map<String,Object> paramData = new HashMap<String,Object>();
		paramData.put("adminId", adminId);
		paramData.put("sessionId", sessionId);
		getSession1().update("com.plgrim.ncp.bo.authertication.updateLastLoginDt", paramData);
	}
	
	
	/**
	 * 권한그룹별 메뉴정보 조회
	 * @param boSiteId
	 * @param authorGrpSn
	 * @return
	 * @throws Exception
	 */
	public List<SysMenuExtend> selectListAuthorGrpMenu( String boSiteId, long authorGrpSn) throws Exception {
		Map<String,Object> paramData = new HashMap<String,Object>();
		paramData.put("boSiteId", boSiteId);
		paramData.put("authorGrpSn", authorGrpSn);
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.bo.authertication.selectListAuthorGrpMenu", paramData);
	}
	
	
	/**
	 * 권한그룹정보 조회
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public List<SysAdminAuthorGrpMapng> selectListAdmAuthgrp( String adminId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("adminId", adminId);
		return getSession1().selectList("com.plgrim.ncp.bo.authertication.selectListAdmAuthgrp", paramData);
	}
	
	/**
	 * 메뉴정보 조회
	 * @param boSiteId
	 * @param menuUrl
	 * @return
	 * @throws Exception
	 */
	public SysMenu  selectRowMenuFromUrl( String boSiteId, String menuUrl ) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("boSiteId", boSiteId);
		paramData.put("menuUrl", menuUrl);
		return getSession1().selectOne("com.plgrim.ncp.bo.authertication.selectRowMenuFromUrl", paramData);
	}
	

	/**
	 * 개인정보 동의여부 조회(Y or N )
	 * @param adminId
	 * @param boSiteId
	 * @param stplatCd
	 * @return
	 * @throws Exception
	 */
	public String getStplatAgrYn( String adminId, String boSiteId, String stplatCd) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("adminId", adminId);
		paramData.put("boSiteId", boSiteId);
		paramData.put("stplatCd", stplatCd);
		
		return getSession1().selectOne("com.plgrim.ncp.bo.authertication.selectRowStplatAgrYn", paramData);
	}
	
	/**
	 * full url 권한체크 메뉴 조회
	 * @param boSiteId
	 * @return
	 */
	public List<AuthResult> selectAdmAuthCheckUrlAct(String boSiteId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("boSiteId", boSiteId);

		return getSession1().selectList("com.plgrim.ncp.bo.authertication.selectAdmAuthCheckUrlAct", paramData);
	}
	
	/**
	 * 관리자 로그인 SMS 인증정보 merge
	 */
	public int mergeSysAdminCrtfc(SysAdminCrtfc sysAdminCrtfc) {
		return getSession1().update("com.plgrim.ncp.bo.authertication.mergeSysAdminCrtfc", sysAdminCrtfc);
	}

	/**
	 * 휴대폰 인증 실패 횟수 초기화
	 * @param adminId
	 * @throws Exception
	 */
	public void updateCrtfcFailCnt(String adminId) throws Exception {
		getSession1().update("com.plgrim.ncp.bo.authertication.updateCrtfcFailCnt", adminId);
	}
}