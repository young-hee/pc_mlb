 /* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 3. 27       
 */
package com.plgrim.ncp.commons;

 import com.plgrim.ncp.base.abstracts.AbstractComponent;
 import com.plgrim.ncp.base.entities.datasource1.sys.*;
 import com.plgrim.ncp.commons.auth.service.AuthenticationService;
 import com.plgrim.ncp.commons.data.FormSysAdmDTO;
 import com.plgrim.ncp.commons.result.AdmResult;
 import com.plgrim.ncp.commons.result.AuthResult;
 import com.plgrim.ncp.commons.result.SysAdminBukmkResult;
 import com.plgrim.ncp.commons.service.SysAdmService;
 import com.plgrim.ncp.commons.service.SysAdminBukmkService;
 import com.plgrim.ncp.commons.service.SysAdmlogService;
 import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.IdGenService;
 import com.plgrim.ncp.framework.exception.NotFindConfigException;
 import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
 import lombok.extern.slf4j.Slf4j;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.core.context.SecurityContextHolder;
 import org.springframework.stereotype.Component;

 import javax.servlet.http.HttpServletRequest;
 import java.math.BigInteger;
 import java.util.*;

@Slf4j
@Component
public class AuthenticationComponentImpl extends AbstractComponent implements AuthenticationComponent {

	
	/*@Autowired(required=false)
	ServletContext servletContext;*/
	
	@Autowired
    AuthenticationService authenticationService; 
	
	@Autowired
	SysAdmService sysAdmService;
	
	@Autowired
	SysAdmlogService sysAdmlogService;
	
	@Autowired
	SysAdminBukmkService sysAdminBukmkService;
	
	@Autowired
	InterfaceApiCommon interfaceApiCommon;

	private String loginBoSiteId;
	
	public void setLoginBoSiteId( String boSiteId) throws Exception{
		this.loginBoSiteId = boSiteId;
	}
	
	/**
	 * 아이디, 비밀번호 로 관리자정보를 조회한다.
	 * @param adminId
	 * @return
	 */
	public SysAdmin selectRowSysAdmInfo( String adminId) throws Exception {
		return authenticationService.selectRowSysAdmInfo(getBoSiteId(), adminId);
	}
	
	/**
	 * 관리자로그인 가능여부를 체크한다.
	 * @param adminId
	 * @param adminPwd
	 * @return
	 */
	public boolean isLoginCheck( String adminId, String adminPwd ) throws Exception {
		return authenticationService.isLoginCheck(getBoSiteId(), adminId, adminPwd);
	}
	/**
	 * EP에서 SSO 를 통한 관리자로그인 가능여부를 체크한다.
	 * @param shopId
	 * @return
	 */
	public String isLoginCheckEPSSO( String shopId) throws Exception {
		return authenticationService.isLoginCheckEPSSO(getBoSiteId(), shopId);
	}
	public SysAdmin selectBoLoginIdInfo( String adminId ) throws Exception {
		return authenticationService.selectBoLoginIdInfo(adminId);
	}
	public SysAdmin selectPoLoginIdInfo( String adminId ) throws Exception {
		return authenticationService.selectPoLoginIdInfo(adminId);
	}
	public void updateFailrCnt5Init( String adminId, String pw, String encMbrPw ) throws Exception {
		
		authenticationService.updateFailrCnt5Init(adminId, encMbrPw);
		
		
		FormSysAdmDTO paramData = new FormSysAdmDTO();
		SysAdmin sysParam = new SysAdmin();
		sysParam.setAdminId(adminId);
		paramData.setSysAdmin(sysParam);
		AdmResult adminInfo = sysAdmService.selectRowAdminMaskNInfo(paramData);

		// TODO 이메일 솔루션 적용
//		//이메일발송...
//		if( getStringService().isEmail(  adminInfo.getSysAdmin().getEmail() ) ){
//
//			List<EmailSDO> parameterList = new ArrayList<EmailSDO>();
//
//			EmailSDO parameter1 = new EmailSDO();
//			parameter1.setAutocode("");
//			parameter1.setUserId( adminInfo.getSysAdmin().getAdminId()  );
//			parameter1.setAutotype("A11");
//			parameter1.setEmail( adminInfo.getSysAdmin().getEmail());
//			parameter1.setName( adminInfo.getSysAdmin().getAdminNm());
//
////					parameter1.setInsertdate( Calendar.getInstance().getTime());
//			parameter1.setInsertdate( this.interfaceApiCommon.getCurrentDate() );
//			parameter1.setSendtime(this.interfaceApiCommon.getCurrentDate());
//			parameter1.setSendyn("N");
//			parameter1.setOpentime("");
//			parameter1.setSenttime("");
//			parameter1.setCmpncode("");
//			parameter1.setFromaddress("");
//			parameter1.setFromname("");
//			parameter1.setTitle("로그인 5회 실패로 인한 운영자 비밀번호 초기화");
//			parameter1.setTag1(getDateService().parseString(Calendar.getInstance().getTime(), "yyyy-MM-dd HH:mm:ss"));
//			parameter1.setTag2(pw);
//			parameter1.setTag3("");
//			parameter1.setTag4("");
//			parameter1.setTag5("");
//			parameter1.setTag6("");
//			parameter1.setTag7("");
//			parameter1.setTag8("");
//			parameter1.setTag9("");
//			parameter1.setTag10("");
//			parameter1.setTag11("");
//			parameter1.setTag12("");
//			parameter1.setCallerId(InterfaceAdapterEnum.INTERFACE_ADAPTER_UPDATE_ADMIN_TEMP_PASSWORD.toString());
//			parameter1.setAdminId("sysadmin");
//
//			parameterList.add(parameter1);
//
//			// Adapter Header 를 세팅한다.
//			AdapterHeader adapterHeader = new AdapterHeader();
//			adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
//			adapterHeader.setMallId("DXM");
//			adapterHeader.setLangCd("KOR");
//			adapterHeader.setDvcCd("PC");
//
//			log.info("> junit test : {}", emailAdapter.sendEmail(parameterList, adapterHeader));
//		}
	}

	
	/**
	 * 관리자 권한정보를 조회한다.
	 * @param adminId
	 * @return
	 */
	public Map<String,Object> selectAdmAuthUrlAct ( String adminId) throws Exception {
		List<AuthResult> listAuthResult =  authenticationService.selectListAdmAuthUrlAct( getBoSiteId(), adminId);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		Iterator<AuthResult> iterator = listAuthResult.iterator();
		
		while(iterator.hasNext()) {
			AuthResult rowData = iterator.next();
			//resultMap.put( rowData.getSysMenu().getMenuUrl(), rowData.getSysAuthor().getAuthorResrcNm());
			resultMap.put( rowData.getSysMenu().getMenuUrl(), rowData);
			resultMap.put( ""+rowData.getSysMenu().getMenuSn(), rowData);
		}
		
		//main url 등록
		resultMap.put( getConfigService().getProperty(getIdGenService().getEnvValue("system.id")+".main.url") , "V");
		
		return resultMap;
	}
	
	/**
	 * 관리자 권한정보를 조회한다. (동적메뉴)
	 * @param adminId
	 * @return
	 */
	public List<AuthResult> selectAdmDynamicAuthUrlAct ( String adminId) throws Exception {
		return authenticationService.selectListAdmDynamicAuthUrlAct( getBoSiteId(), adminId);
	}


	/**
	 * 권한별 상단 메뉴정보를 조회한다.
	 * @param adminId
	 * @return
	 */
	public List<SysMenuExtend> getTopMenuList( String adminId ) throws Exception {	
		
		List<SysMenuExtend>  menuList = authenticationService.selectListAuthorGrpMenu( getBoSiteId(), getAdmAuthorGrpSn(adminId));
		
		SysMenuExtend menuTree = new SysMenuExtend();    // 더미 메뉴 Root
		 
        if (menuList != null) {
        	
        	SysMenuExtend menuNode = null;
        	
            for (SysMenuExtend menuViewResult : menuList) {
                
            	menuNode = findChildMenu(menuTree, menuViewResult.getUpperKey());
            	
            	if( menuNode != null){
            		if (menuNode.getRows() == null) {
                     	menuNode.setRows(new ArrayList<SysMenuExtend>());
                    }
                    menuNode.getRows().add(menuViewResult);
                }else{
                	
                }
        		
            }
        }

	    return menuTree.getRows();
	        
	}
	
	/**
	 * 권한그룹코드를 조회한다.
	 */
	public long getAdmAuthorGrpSn( String adminId) throws Exception {
		return authenticationService.getAdmAuthorGrpSn(adminId);
	}
	 
	
	/**
	 * bo site id 얻는다.
	 * @return
	 * @throws Exception
	 */
	public String getBoSiteId() {
		
		String boSiteId = null;
		
		if( this.loginBoSiteId != null){
			boSiteId = this.loginBoSiteId ;
		}else{
			log.debug("system.id={}", getIdGenService().getEnvValue("system.id"));
			
			String systemId = getIdGenService().getEnvValue("system.id");
			
			if(BOSecurityUtil.isPoSite()){
				systemId = "ncp_web_po";
			}

			if( "ncp_web_bo".equals(systemId) ){
				boSiteId =  "BO";
			}else if( "ncp_web_cs".equals(systemId) ){
				boSiteId = "CS";
			}else if( "ncp_web_po".equals(systemId) ){
				boSiteId = "PO";
			}else{
				throw new NotFindConfigException( null);
			}
		}

		return boSiteId;
		
	}
	
	/**
	 * 운영자 login 정보를 업데이트한다.
	 * @param adminId
	 * @throws Exception
	 */
	public void updateLogin(String adminId, String sessionId) throws Exception {
		authenticationService.updateLogin(adminId, sessionId);
		
		//로그인 로그 등록
		SysAdminLoginLog paramData = new SysAdminLoginLog();
		paramData.setAdminId(adminId);
		paramData.setBoSiteId(getBoSiteId());
		paramData.setLoginSuccesYn("Y");
		paramData.setConectIp( ContextService.getCurrentRequest().getRemoteAddr());
		sysAdmlogService.insertLoginLog(paramData);
	}
	
	/**
	 * 운영자 메뉴 접근시 log를 생성한다.
	 * @param url
	 * @throws Exception
	 */
	public void insertMenuUseLog(String url) throws Exception {
		
		//메뉴정보를 조회한다.
		SysMenu rowData = authenticationService.selectRowMenuFromUrl(getBoSiteId(), url);
		
		if( rowData != null && rowData.getMenuSn() > 0 && BOSecurityUtil.getLoginId() != null){			
			//FILE URL 로그 생성
			SysAdminUseLog fileMenuData = new SysAdminUseLog();
			fileMenuData.setAdminId(BOSecurityUtil.getLoginId());
			fileMenuData.setMenuSn( rowData.getMenuSn() );
			fileMenuData.setConectIp( ContextService.getCurrentRequest().getRemoteAddr());
			sysAdmlogService.insertMenuUseLog(fileMenuData);
		
			//메뉴 URL 로그 생성
			SysAdminUseLog menuData = new SysAdminUseLog();
			menuData.setAdminId(BOSecurityUtil.getLoginId());
			menuData.setMenuSn( rowData.getUpperMenuSn() );
			menuData.setConectIp( ContextService.getCurrentRequest().getRemoteAddr());
			sysAdmlogService.insertMenuUseLog(menuData);
		}
		
	}
	
	/**
	 * 개인정보 동의여부 조회(Y or N ) - 로그인시 사용
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public String getStplatAgrYn( String adminId, String stplatCd) throws Exception {
		
		return  ("Y".equals(authenticationService.getStplatAgrYn(adminId, getBoSiteId(), stplatCd)))?"Y":"N";
	}
	
	public String getStplatAgrYn( String adminId, String boSiteId, String stplatCd) throws Exception {
		
		return  ("Y".equals(authenticationService.getStplatAgrYn(adminId, boSiteId, stplatCd)))?"Y":"N";
	}
	
	/**
	 * 즐겨찾기 정보를 조회한다
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
    public List<SysAdminBukmkResult> selectAdminBookmarkList(String adminId) throws Exception {
	    return sysAdminBukmkService.selectAdminBookmarkList(adminId, getBoSiteId());
    }
    
    /**
	 * 권한 몰 정보를 조회한다.
	 * @param sysAdmin
	 * @return
	 * @throws Exception
	 */
    public List<SysMall> selectListAuthAdmMall(SysAdmin sysAdmin) throws Exception {
    	 return authenticationService.selectListAuthAdmMall(sysAdmin.getAdminId());
    }

    /**
	 * 권한 브랜드정보를 조회한다.
	 * @param sysAdmin
	 * @return
	 * @throws Exception
	 */
    public List<SysBrnd> selectAuthBrndList(SysAdmin sysAdmin) throws Exception {
    	 return authenticationService.selectAuthBrndList(sysAdmin);
    }

    /**
	 * PO Brand ACL
	 * @param sysAdmin
	 * @return
	 * @throws Exception
	 */
    public List<SysBrnd> selectPoAuthBrndList(SysAdmin sysAdmin) throws Exception {
    	 return authenticationService.selectPoAuthBrndList(sysAdmin);
    }

	/*#################################private function #######################################################################*/
	
	/**
	 * 하위Object 정의한다.
	 * @param treeRoot
	 * @param upperKey
	 * @return
	 */
	private SysMenuExtend findChildMenu(SysMenuExtend treeRoot, BigInteger upperKey) {
		SysMenuExtend returnNode = null;
		if (upperKey == null || upperKey.equals(treeRoot.getId())) {
        	returnNode = treeRoot;
        } else {
        	for (int i = 0; treeRoot.getRows() != null && i < treeRoot.getRows().size(); i++) {
        		returnNode = findChildMenu(treeRoot.getRows().get(i), upperKey);
            }
        }
		
        return returnNode;
    }
	
	/**
	 * 비밀번호 체크
	 * @param adminId
	 * @param adminPwd
	 * @return
	 * @throws Exception
	 */
	public boolean isAdminPwdCheck( String adminId, String adminPwd ) throws Exception {		
		return authenticationService.isAdminPwdCheck(getBoSiteId(), adminId, adminPwd);
	}
	
	/**
	 * full url 권한체크 메뉴 조회
	 * @return
	 */
	public List<AuthResult> selectAdmAuthCheckUrlAct() throws Exception {
		return authenticationService.selectAdmAuthCheckUrlAct(getBoSiteId());
	}
	
	/**
	 * 관리자 로그인 SMS 인증정보 merge
	 */
	public int mergeSysAdminCrtfc(SysAdminCrtfc sysAdminCrtfc) {
		return authenticationService.mergeSysAdminCrtfc(sysAdminCrtfc);
	}
	
	/**
	 * SMS 인증 필요여부
	 */
	public String getSmsAuthInfoCheck(String adminId) {
		return authenticationService.getSmsAuthInfoCheck(getBoSiteId(), adminId);
	}
	
	/**
	 * SMS 인증번호 발송
	 */
	public String sendSmsAuthNo(String adminId) {
		return authenticationService.sendSmsAuthNo(getBoSiteId(), adminId);
	}
	
	/**
	 * SMS 인증 처리 및 일치 여부 반환
	 */
	public String getSmsAuthentication(String adminId, String smsAuthNo) {
		return authenticationService.getSmsAuthentication("PO", adminId, smsAuthNo);
	}

	/**
	 * PO 로그인시 휴대폰 최초 인증 여부 조회
	 * @param adminId
	 * @param adminPwd
	 * @throws Exception
	 */
	public SysAdmin isSmsFirstCrtfc(String adminId, String adminPwd ) throws Exception {

		HttpServletRequest request = ContextService.getCurrentRequest();
		String smsFirstCrtfcYn = "";
		SysAdmin sysAdmin = null;

		// 패스워드 체크
		if (authenticationService.isLoginCheck("PO", adminId, adminPwd)) {

			// 관리자 정보 조회
			sysAdmin = authenticationService.selectRowSysAdmInfo("PO", adminId);

		} else {
			log.info("=================================================");
			log.info("log-in fail log...");
			log.info("=================================================");
			log.info("Module :: " + (BOSecurityUtil.isBoSite() ? "BO" : "PO"));
			log.info("isLogin :: " + BOSecurityUtil.isLogin());
			log.info("hasRole :: " + BOSecurityUtil.hasRole());
			log.info("getCurrentUserDetail :: " + BOSecurityUtil.getCurrentUserDetail());
			log.info("SecurityContextHolder: {}", SecurityContextHolder.getContext());
			log.info("SecurityContextHolder authenticatiion: {}", SecurityContextHolder.getContext() == null ? null : SecurityContextHolder.getContext().getAuthentication());
			log.info("SecurityContextHolder authenticatiion.getPrincipal(): {}", SecurityContextHolder.getContext().getAuthentication() == null ? null : SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			log.info("SecurityContextHolder authenticatiion.getCredentials(): {}", SecurityContextHolder.getContext().getAuthentication() == null ? null : SecurityContextHolder.getContext().getAuthentication().getCredentials());
			log.info("ID :: " + adminId);
			log.info("PWD :: " + adminPwd);

			//ID 존재 시 로그인 실패횟수 구하기
			sysAdmin = authenticationService.selectPoLoginIdInfo(adminId);
			Integer failCnt = 0;

			if (sysAdmin == null) {
				failCnt = 1;
			} else {
				failCnt = (Integer) sysAdmin.getLoginFailrCount();
				log.info("failCnt :: " + failCnt);
				if (failCnt == 5) {    //비밀번호 초기화
					String mbrPw = IdGenService.generateTempPassword();
					String encMbrPw = IdGenService.generateSHA256(mbrPw);
					updateFailrCnt5Init(adminId, mbrPw, encMbrPw);
					log.info("password initilized...");
				}
			}
			sysAdmin.setLoginFailrCount(failCnt);
		}
		return sysAdmin;
	}

	/**
	 * 휴대폰 인증 실패 횟수 초기화
	 * @param adminId
	 * @throws Exception
	 */
	public void updateCrtfcFailCnt(String adminId) throws Exception {
		authenticationService.updateCrtfcFailCnt(adminId);
	}
}