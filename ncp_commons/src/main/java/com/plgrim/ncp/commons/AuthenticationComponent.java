/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      ystam.kwon
 * @since       2015. 4. 17       
 */
package com.plgrim.ncp.commons;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.base.entities.datasource1.sys.*;
import com.plgrim.ncp.commons.result.AuthResult;
import com.plgrim.ncp.commons.result.SysAdminBukmkResult;

public interface AuthenticationComponent {

	//ID 및 비밀번호 체크, IP 체크이면 IP체크
	
	//getIOService().HttpServletRequest().getRemoteAddr()
	
	
	/**
	 * 아이디, 비밀번호 로 관리자정보를 조회한다.
	 * @param adminId
	 * @param adminPwd
	 * @return
	 */
	public SysAdmin selectRowSysAdmInfo( String adminId) throws Exception ;
	
	/**
	 * 관리자로그인 가능여부를 체크한다.
	 * @param adminId
	 * @param adminPwd
	 * @return
	 */
	public boolean isLoginCheck( String adminId, String adminPwd ) throws Exception ;
	
	/**
	 * EP에서 SSO 를 통한 관리자로그인 가능여부를 체크한다.
	 * @param adminId
	 * @param adminPwd
	 * @return
	 */
	public String isLoginCheckEPSSO( String shopId) throws Exception ;
	public SysAdmin selectBoLoginIdInfo( String adminId ) throws Exception ;
	public SysAdmin selectPoLoginIdInfo( String adminId ) throws Exception ;
	public void updateFailrCnt5Init( String adminId, String pw , String encMbrPw) throws Exception ;

	
	/**
	 * 관리자 권한정보를 조회한다.
	 * @param adminId
	 * @param adminPwd
	 * @return
	 */
	public Map<String,Object> selectAdmAuthUrlAct ( String adminId) throws Exception ;
	List<AuthResult> selectAdmDynamicAuthUrlAct ( String adminId) throws Exception ;

	
	/**
	 * 권한별 상단메뉴정보를 조회한다.
	 * @param systemPK
	 * @return
	 */
	public List<SysMenuExtend> getTopMenuList( String adminId ) throws Exception ;
	
	
	/**
	 * 권한그룹번호를 조회한다.
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public long getAdmAuthorGrpSn( String adminId) throws Exception;
	
	/**
	 * 현재 접근된 site_id를 넘겨준다.
	 * @return
	 * @throws Exception
	 */
	public String getBoSiteId() throws Exception;
	
	/**
	 * 로그인된 site id 를 설정한다.
	 * @param boSiteId
	 * @throws Exception
	 */
	public void setLoginBoSiteId( String boSiteId) throws Exception;
	
	/**
	 * 운영자 login 정보를 업데이트한다.
	 * @param adminId
	 * @throws Exception
	 */
	public void updateLogin(String adminId, String sessionId) throws Exception;
	
	/**
	 * 운영자 메뉴 접근시 log를 생성한다.
	 * @param url
	 * @throws Exception
	 */
	public void insertMenuUseLog(String url) throws Exception;
	
	/**
	 * 개인정보 동의여부 조회(Y or N )
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public String getStplatAgrYn( String adminId, String stplatCd) throws Exception;
	
	public String getStplatAgrYn( String adminId, String boSiteId, String stplatCd) throws Exception;
	
	/**
	 * 즐겨찾기 정보를 조회한다
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public List<SysAdminBukmkResult> selectAdminBookmarkList(String adminId) throws Exception;

	/**
	 * 권한 브랜드몰을 조회한다.
	 * @param sysAdmin
	 * @return
	 * @throws Exception
	 */
	public List<SysMall> selectListAuthAdmMall(SysAdmin sysAdmin) throws Exception;

	/**
	 * 권한 브랜드정보를 조회한다.
	 * @param sysAdmin
	 * @return
	 * @throws Exception
	 */
	public List<SysBrnd> selectAuthBrndList(SysAdmin sysAdmin) throws Exception;

	/**
	 * PO Brand ACL
	 * @param sysAdmin
	 * @return
	 * @throws Exception
	 */
	public List<SysBrnd> selectPoAuthBrndList(SysAdmin sysAdmin) throws Exception;

	/**
	 * 비밀번호 체크
	 * @param boSiteId
	 * @param adminId
	 * @param adminPwd
	 * @return
	 * @throws Exception
	 */
	public boolean isAdminPwdCheck(String adminId, String adminPwd ) throws Exception;
	
	/**
	 * full url 권한체크 메뉴 조회
	 * @throws Exception
	 */
	public List<AuthResult> selectAdmAuthCheckUrlAct() throws Exception ;
	
	/**
	 * 관리자 로그인 SMS 인증정보 merge
	 */
	public int mergeSysAdminCrtfc(SysAdminCrtfc sysAdminCrtfc);
	
	/**
	 * SMS 인증 필요여부
	 */
	public String getSmsAuthInfoCheck(String adminId);
	
	/**
	 * SMS 인증번호 발송
	 */
	public String sendSmsAuthNo(String adminId);
	
	/**
	 * SMS 인증 처리 및 일치 여부 반환
	 */
	public String getSmsAuthentication(String adminId, String smsAuthNo);

	/**
	 * PO 로그인시 휴대폰 최초 인증 여부 조회
	 * @param adminId
	 * @param adminPwd
	 * @throws Exception
	 */
	public SysAdmin isSmsFirstCrtfc(String adminId, String adminPwd ) throws Exception;

	/**
	 * 휴대폰 인증 실패 횟수 초기화
	 * @param adminId
	 * @throws Exception
	 */
	public void updateCrtfcFailCnt(String adminId) throws Exception;
}
