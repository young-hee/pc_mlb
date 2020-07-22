package com.plgrim.ncp.commons.util;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.support.SessionStatus;

import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMall;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMenuExtend;
import com.plgrim.ncp.commons.AuthenticationComponent;
import com.plgrim.ncp.commons.auth.BOSecurityUserDetail;
import com.plgrim.ncp.commons.result.AuthResult;
import com.plgrim.ncp.commons.result.SysAdminBukmkResult;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.enums.SecurityParam;

import lombok.extern.slf4j.Slf4j;

/**
 * BO security 관련 유틸리티 서비스
 *
 * <p>
 *
 * <ul>
 * </ul>.
 *
 * @author ys.tam
 * @since 2015. 4. 22
 */
@Slf4j
public final class BOSecurityUtil   {
	
	@Autowired
    static AuthenticationComponent authenticationComponent;

	@Autowired
	static private UserDetailsManager manager;

	/**
	 * BO 권한을 판단한다.
	 * 
	 * <p/>
	 * 
	 *
	 * @return true:[설명], false:[설명]
	 * @since 2015. 4. 9
	 */
	public static boolean hasRole() {
	  if(!isLogin()) return false;
	  SecurityContext ctx = SecurityContextHolder.getContext();
	  Authentication auth = null;
	  if(ctx != null) {
		  auth = ctx.getAuthentication();
	  }
	  boolean hasRole = false;
	  if(auth != null && auth != null) {
		  Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		  for (GrantedAuthority authority : authorities) {
		     hasRole = authority.getAuthority().equals(SecurityParam.ADMIN_ROLE.toString());
		     if (hasRole) {
		    	 break;
		     }else if(authority.getAuthority().equals(SecurityParam.GUEST_ROLE.toString()))
		     {
		    	 hasRole = true;
		    	 break;
		     }
		  }
	  }
	  return hasRole;
	}
	
	/**
	 *  스프링 시큐리티로 로그인된 사용자 세션정보를 얻는다.
	 * @return
	 */
	public static BOSecurityUserDetail getCurrentUserDetail ()  {
		if( !hasRole() ) return null;
		
		if(SecurityContextHolder.getContext().getAuthentication() == null) return null;
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) return null;

		return (BOSecurityUserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public static boolean securityIsNull(){
		boolean isNull = true;
		if(isBoSite()){
			if (getCurrentUserDetail() != null){
				if(getCurrentUserDetail().getSysAdmin() != null ){
					isNull = false; //null이 아니다.
				}
			}
		}else{
			if (getCurrentUserDetail() != null){
				if(getCurrentUserDetail().getPoSysAdmin() != null ){
					isNull = false; //null이 아니다.
				}
			}
		}
		return isNull;
	}

	/**
	 * 로그인 아이디를 얻는다.
	 * @return
	 */
	public static String getLoginId(){
		//log.info("isPoSite: {}", isPoSite());
		//log.info("isLogin: {}", isLogin());
		//log.info("SecurityContextHolder: {}", SecurityContextHolder.getContext());
		//log.info("SecurityContextHolder authenticatiion: {}", SecurityContextHolder.getContext() == null ? null : SecurityContextHolder.getContext().getAuthentication());
		//log.info("SecurityContextHolder authenticatiion.getPrincipal(): {}", SecurityContextHolder.getContext().getAuthentication()== null ? null : SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		//log.info("SecurityContextHolder authenticatiion.getCredentials(): {}", SecurityContextHolder.getContext().getAuthentication()== null ? null : SecurityContextHolder.getContext().getAuthentication().getCredentials());
		if(securityIsNull() == true) return null;
		if(!isLogin()) return "";
		if(isBoSite()){
			return getCurrentUserDetail().getSysAdmin().getAdminId();
		}else{
			return getCurrentUserDetail().getPoSysAdmin().getAdminId();
		}
	}

	/**
	 * 로그인 사용자명을 얻는다.
	 * @return
	 */
	public static String getLoginNm(){
		if(securityIsNull() == true) return null;
		if(!isLogin()) return "";
		if(isBoSite()){
			return getCurrentUserDetail().getSysAdmin().getAdminNm();
		}else{
			return getCurrentUserDetail().getPoSysAdmin().getAdminNm();
		}
	}

	public static String getAdminTpCd(){
		if(securityIsNull() == true) return null;
		if(!isLogin()) return "";
		if(isBoSite()){
			return getCurrentUserDetail().getSysAdmin().getAdminTpCd();
		}else{
			return getCurrentUserDetail().getPoSysAdmin().getAdminTpCd();
		}
	}

	/**
	 * MD 브랜드 정보를 얻는다.
	 * @return
	 */
	/*public static List<SysBrnd> getMdBrndList(){
		
		if( !hasRole() ) return null;
		
		if(isBoSite()){
			return getCurrentUserDetail().getMdBrndList();
		}else{
			return getCurrentUserDetail().getPoMdBrndList();
		}
	}*/
	
	/**
	 * 권한그룹별 접근 URL 및 기능
	 * @return
	 */
	public static Map<String, Object> getAuthUrlAct(){
		if(securityIsNull() == true) return null;
		if( !hasRole() ) return null;
		
		if(isBoSite()){
			return getCurrentUserDetail().getAuthUrlAct();
		}else{
			return getCurrentUserDetail().getPoAuthUrlAct();
		}
	}

	/**
	 * 권한그룹별 접근 URL 및 기능 (동적 메뉴)
	 * @return
	 */
	public static List<AuthResult> getDynamicAuthUrlAct(){
		if(securityIsNull() == true) return null;
		if( !hasRole() ) return null;

		if(isBoSite()){
			return getCurrentUserDetail().getDynamicAuthUrlAct();
		}else{
			return getCurrentUserDetail().getPoDynamicAuthUrlAct();
		}
	}

	/**
	 * 접근가능URL인지 판단한다.
	 * @param accessUrl
	 * @return
	 */
	public static boolean hasAccessUrl( String accessUrl ) {
		return hasAccessUrl( accessUrl, null);
	}
	
	/**
	 * 접근가능URL에 기능권한이 있는지 판단한다.
	 * @param accessUrl
	 * @param actAuth
	 * @return
	 */
	public static boolean hasAccessUrl( String accessUrl , String actAuth) {
		

		if(log.isDebugEnabled()){
			log.debug("accessUrl=>{}", accessUrl);
		}
		boolean hasAccessUrl = false;
		
		if( getAuthUrlAct() != null ) { 
			
			if( getAuthUrlAct().get(accessUrl) != null ){
				
				if( actAuth != null){
					
					Object  rowData = getAuthUrlAct().get(accessUrl);
					
					String  authorResrcNm = null;
					
					if (rowData instanceof java.lang.String ) {
						authorResrcNm = (String) rowData;
                    }else if (rowData instanceof com.plgrim.ncp.commons.result.AuthResult ) {
                    	authorResrcNm = ((AuthResult) rowData).getSysAuthor().getAuthorResrcNm();
                    }else{
                    	log.debug("accessUrl value class=>{}", rowData.getClass());
                    }
					
					//CVUDEM , 등록,보기,수정,삭제, 엑셀다운로드, 마스킹
					log.debug("authorResrcNm=>{}", authorResrcNm);
					
					if( authorResrcNm != null){
						if( (" "+ authorResrcNm).indexOf( actAuth ) > 0 ){
							hasAccessUrl = true;
						}
					}
					
				}else{
					hasAccessUrl = true;
				}
			
			}
		}
		
		return hasAccessUrl;
	}
	
	/**
	 * 접근가능URL에 기능권한이 있는지 판단한다. (동적 메뉴 체크)
	 * @param accessUrl
	 * @param actAuth
	 * @return
	 */
	public static boolean hasAccessDynamicUrl( String accessUrl) {

		if(log.isDebugEnabled()){
			//log.debug("hasAccessDynamicUrl=>{}", accessUrl);
		}
		boolean hasAccessUrl = false;
		List<AuthResult> menuList = null;
		AuthResult auth = null;
		String url = "";

		if( getDynamicAuthUrlAct() != null ) {
			menuList = getDynamicAuthUrlAct();
			for(int i=0; i<menuList.size(); i++){
				auth = (AuthResult)menuList.get(i);
				url = auth.getSysMenu().getMenuUrl();
				//log.debug("[정규표현식] db menu url/비교값 : "+url+"/"+accessUrl);
				if(url.indexOf("*")>-1 && !hasAccessUrl) {
					url = BOStringUtil.rplc(url,"**","[a-zA-Z0-9/._]+");
					url = BOStringUtil.rplc(url,"*","[^/]+");
					//log.debug("[정규표현식] url * rplc : "+url);
					hasAccessUrl = Pattern.matches(url, accessUrl);
					//log.debug("[정규표현식] 결과  : "+hasAccessUrl);
				}
			}
		}

		return hasAccessUrl;
	}

	/**
	 * 메뉴코드롤 접근가능여부 체크
	 * @param menuSn
	 * @param actAuth
	 * @return
	 */
	public static boolean hasAccessMenu( String menuSn , String actAuth) {
		
		boolean hasAccess = false;
		
		if( getAuthUrlAct() != null ) { 
			
			if( getAuthUrlAct().get(menuSn) != null ){
				
				if( actAuth != null){
					
					Object  rowData = getAuthUrlAct().get(menuSn);
					
					String  authorResrcNm = null;
					
					if (rowData instanceof java.lang.String ) {
						authorResrcNm = (String) rowData;
                    }else if (rowData instanceof com.plgrim.ncp.commons.result.AuthResult ) {
                    	authorResrcNm = ((AuthResult) rowData).getSysAuthor().getAuthorResrcNm();
                    }else{
                   		log.debug("accessUrl value class=>{}", rowData.getClass());
                    }
					
					//CVUDEM , 등록,보기,수정,삭제, 엑셀다운로드, 마스킹
					log.debug("authorResrcNm=>{}", authorResrcNm);
					
					if( authorResrcNm != null){
						if( (" "+ authorResrcNm).indexOf( actAuth ) > 0 ){
							hasAccess = true;
						}
					}
					
				}else{
					hasAccess = true;
				}
			
			}
		}
		
		return hasAccess;
	}
	
	/**
	 * 접근가능 권한 URL로 부터 Menu정보를 얻는다. 
	 * @param accessUrl
	 * @param actAuth
	 * @return menuSn or null
	 */
	public static Long getSysMenuFromHasAccessUrl( String accessUrl , String actAuth) {
		
		if( getAuthUrlAct() != null ) { 
			
			if( getAuthUrlAct().get(accessUrl) != null ){
				Object  rowData = getAuthUrlAct().get(accessUrl);
                if (rowData instanceof com.plgrim.ncp.commons.result.AuthResult ) {
                	
                	if( actAuth != null){
                		String authorResrcNm = ((AuthResult) rowData).getSysAuthor().getAuthorResrcNm();
                    	
                    	if( authorResrcNm != null){
    						if( (" "+ authorResrcNm).indexOf( actAuth ) > 0 ){
    							return ((AuthResult) rowData).getSysMenu().getMenuSn();
    						}
    					}
                	}else{
                		return ((AuthResult) rowData).getSysMenu().getMenuSn();
                	}
                	
                }
			}
		}
		return null;
	}
	
	/**
	 * 프로그램URL로 권한를 체크하여 권한이 존재시 메뉴코드를 넘겨준다.
	 * @param menuSn
	 * @param actAuth
	 * @return
	 */
	public static Long getSysMenuFromHasAccessMenu( String menuSn , String actAuth) {
		return getSysMenuFromHasAccessUrl( menuSn, actAuth);
	}
	
	
	/**
	 * 접근권한이 있는 accessUrl 로 메뉴코드를 조회한다.
	 * @param accessUrl
	 * @return
	 */
	public static Long getMenuSnFromAccessUrl( String accessUrl ) {
		return getSysMenuFromHasAccessUrl(accessUrl, null);
	}
	
	/**
	 * 로그인된 시스템 샤이트 ID를 넘겨준다.
	 * @return
	 */
	public static String getAccessSiteId(){
		if(securityIsNull() == true) return null;
		if(isBoSite()){
			return getCurrentUserDetail().getAccessSiteId();
		}else{
			return getCurrentUserDetail().getPoAccessSiteId();
		}
	}

	/**
	 * 로그인 시간을 넘겨준다.
	 * @return
	 */
	public static Date getLoginDt(){
		if(securityIsNull() == true) return null;
		if(isBoSite()){
			return getCurrentUserDetail().getLoginDt();
		}else{
			return getCurrentUserDetail().getPoLoginDt();
		}
	}

	/**
	 * 상단메뉴목록 넘겨준다.
	 * @return
	 */
	public  static List<SysMenuExtend> getTopMenuList(){
		if(securityIsNull() == true) return null;
		if( !hasRole() ) return null;
		
		if(isBoSite()){
			return getCurrentUserDetail().getTopMenuList();
		}else{
			return getCurrentUserDetail().getPoTopMenuList();
		}
	}

	/**
	 * CS 개인정보 동의여부 넘겨준다.
	 * @return
	 */
	public static String getStplatAgrYn(){
		if(securityIsNull() == true) return null;
		if(isBoSite()){
			return getCurrentUserDetail().getStplatAgrYn();
		}else{
			return getCurrentUserDetail().getPoStplatAgrYn();
		}
	}

	/**
	 * 즐겨찾기 목록를 넘겨준다.
	 * @return
	 */
	public static List<SysAdminBukmkResult> getBookmarkList() {
		if(securityIsNull() == true) return null;
		if(isBoSite()){
			return getCurrentUserDetail().getBookmarkList();
		}else{
			return getCurrentUserDetail().getPoBookmarkList();
		}
	}

	/**
	 * 접근가능 브랜드객체 목록를 얻는다.
	 * @return
	 */
	public static List<SysBrnd> getAuthBrndList(){
		if(securityIsNull() == true) return null;
		if( !hasRole() ) return null;

		if(isBoSite()){
			return getCurrentUserDetail().getAuthBrndList();
		}else{
			return getCurrentUserDetail().getPoAuthBrndList2();
		}
	}

	/**
	 * 접근가능 사용자 지정 브랜드객체 목록를 얻는다.
	 * @return
	 */
	public static List<SysBrnd> getPoAuthBrndList(){
		if(securityIsNull() == true) return null;
		if( !hasRole() ) return null;

		if(isBoSite()){
			return getCurrentUserDetail().getPoAuthBrndList();
		}else{
			return getCurrentUserDetail().getPoPoAuthBrndList2();
		}
	}

	public static int getPoAuthBrndCnt(){
		List<SysBrnd> list = null;
		int brndCnt = 0;

		if(securityIsNull() == true) return 0;
		if( !hasRole() ) return 0;

		if(isBoSite()){
			list = getCurrentUserDetail().getPoAuthBrndList();
			if(list != null)
				brndCnt = list.size();
		}else{
			list = getCurrentUserDetail().getPoPoAuthBrndList2();
			if(list != null)
				brndCnt = list.size();
		}
		return brndCnt;
	}

	/**
	 * 접근가능 브랜드코드 목록를 얻는다.
	 * @return
	 */
	public static List<String> getStringListFromAuthBrndList(){
		if( !hasRole() ) return null;
		
		List<String> resultList = new ArrayList<String>();
		
		if(getAuthBrndList() != null){
			Iterator<SysBrnd> iterator = getAuthBrndList().iterator();
			SysBrnd data = null;
			while(iterator.hasNext()) {
				data = iterator.next();
				resultList.add(data.getBrndId());
			}
		}

		return ( resultList.size() > 0)?resultList:null;
	}
	
	/**
	 * 로그인된 업체ID를 얻는다.
	 * @return
	 */
	public static String getComId(){
		if(securityIsNull() == true) return null;
		if( !hasRole() ) return null;

		if(isBoSite()){
			return getCurrentUserDetail().getSysAdmin().getComId();
		}else{
			return getCurrentUserDetail().getPoSysAdmin().getComId();
		}
	}

	/**
	 * 로그인된 매장ID를 얻는다.
	 * @return
	 */
	public static String getShopId(){
		if(securityIsNull() == true) return null;
		if( !hasRole() ) return null;

		if(isBoSite()){
			return getCurrentUserDetail().getSysAdmin().getShopId();
		}else{
			return getCurrentUserDetail().getPoSysAdmin().getShopId();
		}
	}

	/**
	 * 브랜드 데이타 제한 여부 
	 * true: 데이타 제한
	 * false: 데이타 제한 없음.
	 * @return
	 */
	public static boolean isAuthBrndLimit(){
		if(securityIsNull() == true) return false;
		if( !hasRole() ) return false;

		return (getAuthBrndList() != null)?true:false;
		
	}
	
	/**
	 * 현재 프로그램URL에 마스킹해제 권한를 체크하여 권한이 없을 경우 Y(마스킹) or N(마스킹해제).
	 * @return Y(마스킹) or N(마스킹해제)
	 */
	public static String checkMarkingFromAccessUrl(){
		
		String checkURI = ContextService.getCurrentRequest().getRequestURI();
		
		if( checkURI.toUpperCase().indexOf("EXCEL",checkURI.length() - 5 ) != -1 || checkURI.toUpperCase().indexOf(".JSON",checkURI.length() - 5 ) != -1 ){
			//처리프로세스 마스킹해제권한은 상위 프로그램 URL 마스킹해제 권한으로 사용한다.
			//처리프로세스 ( 확장자가 json 이거나 맨끝 URL Excel )
			try{
				checkURI = new URI(ContextService.getCurrentRequest().getHeader("REFERER")).getPath();
			}catch(Exception e){
				return "Y";
			}
		}
		
		log.debug("CHECKURI =>{}",checkURI);
		
		if( !isSelfUrlAuth( checkURI, "M") ) return "Y";
		else return "N";
	}
	
	/**
	 * 메뉴 마스킹해제 권한 체크 
	 * @param menuSn
	 * @return Y(마스킹) or N(마스킹해제)
	 */
	public static String checkMarkingFromMenuSn( String menuSn ){
		
		if( !hasAccessMenu( menuSn, "M") ) return "Y";
		else return "N";
	}
	
	//
	
	/**
	 * 현재 URI 권한(CRUD 이 있는지 체크.
	 */
	public static boolean isSelfUrlAuth( String actAuth) {
		return hasAccessUrl( ContextService.getCurrentRequest().getRequestURI(), actAuth);
	}
	
	/**
	 * 접근URL 에 버튼권한여부를 체크한다.
	 * @param accessUri
	 * @param actAuth
	 * @return
	 */
	public static boolean isSelfUrlAuth( String accessUri, String actAuth) {
		
		return hasAccessUrl( accessUri, actAuth);
	}
	
	/*
	public static void addBookmark(SysAdminBukmkResult addObj ) {
		getCurrentUserDetail().getBookmarkList().add(addObj);
	}
	
	public static void removeBookmark(long menuSn ) {
		
		Iterator<SysAdminBukmkResult> iterator = getBookmarkList().iterator();
		
		int index = 0;
		while(iterator.hasNext()) {
			SysAdminBukmkResult rowData = iterator.next();
			//sysAdminBukmk.menuSn
			if( rowData.getSysAdminBukmk().getMenuSn() == menuSn ){
				getCurrentUserDetail().getBookmarkList().remove(index);
			}
			index++;
		}
		
	}*/
	
	/**
	 * 중복 커밋 방지 시작
	 * @param 세션저장변수(유일해야함)
	 * @param 세션
	 * @return
	 */
	public static void startDoubleSubmitSave( String sessionObjName, HttpSession session) {
		session.setAttribute(sessionObjName, "Y");
	}

	/**
	 * 중복 커밋 방지 종료
	 * @param 세션저장변수(유일해야함)
	 * @param 상태
	 * @return
	 */
	public static void endDoubleSubmitCheck(String checkValue, SessionStatus status) {
		if (checkValue==null) {
			throw new RuntimeException();
		}else{
			status.setComplete();
		}
	}

	/**
	 * Bo url check
	 * @param
	 * @return bo 사이트 여부
	 */
	public static boolean isBoSite(){
		HttpServletRequest req = ContextService.getCurrentRequest();
		if(req == null) {
			return false;
		}
		String host = req.getHeader("host");
		if(host==null) host = req.getHeader("Host"); //request head
		//log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> host ==>" + host);
		//-Dboenvtype="po"
		String boenvtype = System.getProperty("boenvtype"); //weblogic startweblogic environment variable
		if(boenvtype==null)boenvtype="bo"; //default bo assign
		//log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> boenvtype ==>" + boenvtype);
		String checkURL = ContextService.getCurrentRequest().getRequestURL().toString().toLowerCase(); //web request
		//log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> checkURL ==>" + checkURL);
		boolean isBoSite = true;
		if(host.indexOf("withus.plgrimshop") > -1 || checkURL.indexOf("withus.plgrimshop") > -1 || boenvtype.equals("po") ) {
			isBoSite = false;
		}
		//withus.plgrimshop.com/shop
		//log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> isBoSite ==>" + isBoSite);
		return isBoSite;
	}

	public static boolean isPoSite(){
		return (isBoSite()?false:true);
	}

	public static boolean isLogin() { //로그인 여부
		HttpServletRequest req = ContextService.getCurrentRequest();
		if(req == null) {
			return false;
		}
		HttpSession session = req.getSession();
		boolean isLogin = false;
		if(session==null) {
		}else{
			isLogin = (session.getAttribute("SPRING_SECURITY_CONTEXT") == null?false:true); //로그인 되어 있으면 true 리턴
		}
		return isLogin;
	}

	public static void clearAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    public static void configureAuthentication(String role) {
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "user",
                role,
                authorities
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    //로그인 상태가 아니면 세션의 본인 값들을 모두 삭제 한다.
    public static void sessionDeleteAll() {
    	if(!isLogin()){
	    	HttpServletRequest request = ContextService.getCurrentRequest();
		    request.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");
			Enumeration<String> names = request.getSession().getAttributeNames();
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				request.getSession().removeAttribute(name);
			}
			log.info("[prepare] not login + session delete all successful...");
    	}
    }
    
    /**
	 * full url 권한체크 메뉴 조회
	 * @return
	 */
	public static List<AuthResult> getAuthCheckUrlAct(){
		if(securityIsNull() == true) return null;
		if( !hasRole() ) return null;

		if(isBoSite()){
			return getCurrentUserDetail().getAuthCheckUrlAct();
		}else{
			return getCurrentUserDetail().getPoAuthCheckUrlAct();
		}
	}
    
    /**
	 * 현재 메뉴가 full url 권한체크 메뉴인지 확인(SYS_MENU.FIRST_PGE_YN = 'Y' 조건 페이지)
	 * @param accessUrl
	 * @return
	 */
	public static boolean hasAuthCheckUrl(String accessUrl) {
		boolean authCheckUrl = false;
		List<AuthResult> menuList = null;
		AuthResult auth = null;
		
		if( getAuthCheckUrlAct() != null ) {
			menuList = getAuthCheckUrlAct();
			for(int i=0; i<menuList.size(); i++){
				auth = (AuthResult)menuList.get(i);
				if(accessUrl.equals(auth.getSysMenu().getMenuUrl())) {
					authCheckUrl = true;
					break;
				}
			}
		} else {
			authCheckUrl = true;
		}
		
		return authCheckUrl;
	}

	public static List<Map<String, String>> getAuthMallList() {
		if (securityIsNull() == true) return null;
		if (!hasRole()) return null;


		List<SysMall> sysMallList = getCurrentUserDetail().getAuthMallList();
		if (sysMallList == null) return null;

		Iterator<SysMall> iterator = sysMallList.iterator();
		List<Map<String, String>> mallList = new ArrayList<>();

		while (iterator.hasNext()) {
			SysMall data = iterator.next();
			Map<String, String> map = new HashMap<>();
			map.put("mallId", data.getMallId());
			map.put("mallNm", data.getMallNm());
			map.put("mallUrl", data.getMallUrl());

			mallList.add(map);
		}

		return mallList;
	}
	
	public static Map<String, String> getAuthMall() {
		Map<String, String> map = null;

		if (securityIsNull() != true && hasRole()) {
			List<SysMall> sysMallList = getCurrentUserDetail().getAuthMallList();
			if (sysMallList != null) {
				Iterator<SysMall> iterator = sysMallList.iterator();
				if(iterator.hasNext()) {
					SysMall data = iterator.next();
					map = new HashMap<>();
					
					map.put("mallId", data.getMallId());
					map.put("mallNm", data.getMallNm());
					map.put("mallUrl", data.getMallUrl());
				}
			}
		}		
		if(map == null) {
			//{mallId=DXM, mallNm=Discovery Expedition, mallUrl=http://www.discovery-expedition.co.kr}
			map = new HashMap<>();
			map.put("mallId", 	"DXM");
			map.put("mallNm", "Discovery Expedition");
			map.put("mallUrl", 	"http://www.discovery-expedition.co.kr");
		}


		return map;
	}
	
    public static String getAuthMallId() {
        return getAuthMall().get("mallId");
    }
}