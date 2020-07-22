package com.plgrim.ncp.commons.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.UriAuthService;
import com.plgrim.ncp.framework.data.UriAuthData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UriAuthInterceptor extends HandlerInterceptorAdapter{
	
	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	/** The sql session1. */
	@Autowired
	@Qualifier("sessionTemplate1")
	private SqlSession sqlSession1;
	
	@Autowired
    private UriAuthService uriAuthService;	
	
	@Override
    public boolean preHandle(HttpServletRequest request, 
            HttpServletResponse response, Object handler) throws Exception {
		
		//log.info("============================================================================================");
		//log.info("                              UriAuthInterceptor / preHandle                                ");
		//log.info(" 1. uri unmapping : 404 error page ( by spring)                                             ");
		//log.info(" 2. BO site IP check : ");
		//log.info(" 3. URI connect control : ");
		//log.info("============================================================================================");
		boolean passYn = true;
		boolean hasAccessAuth = false;
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		String igNoreUrl = "!@#$%^&*()~";
		Boolean isBoSite = BOSecurityUtil.isBoSite();
		
		String reqUrl = ContextService.getCurrentRequest().getRequestURL().toString(); if(reqUrl==null)reqUrl=igNoreUrl;
		String reqUri = request.getRequestURI(); if(reqUri==null)reqUri=igNoreUrl;
		String siteId = "";
		String loginId = "";
		String requestUri = urlPathHelper.getOriginatingRequestUri(ContextService.getCurrentRequest()); if(requestUri==null)requestUri=igNoreUrl;
		String rootUri = (isBoSite?"/":igNoreUrl);
		String clientIp = request.getRemoteAddr();
		
		//0순위 보안 체크 : ip 접근 제한, BO 사이트만 체크 한다.
		UriAuthData uriInfo = new UriAuthData();
		uriInfo.setIp(clientIp);
		
		if(
		   reqUrl.indexOf(".css")>-1 				||
		   ( reqUrl.indexOf(".js")>-1 && !(reqUrl.indexOf(".json")>-1) ) ||		   
		   reqUrl.indexOf("/sc/auth/authCheck")>-1 	||
		   reqUrl.indexOf("/login;jsessionid=")>-1  ||
		   reqUrl.indexOf("/pologin;jsessionid=")>-1 ||
		   reqUrl.indexOf("/login?error=")>-1 		||
		   reqUrl.indexOf("/grid/colSelector")>-1 	||		   
		   reqUrl.indexOf(".png")>-1 				||
		   reqUrl.indexOf(".jpg")>-1 				||
		   reqUrl.indexOf(".jpeg")>-1 				||
		   reqUrl.indexOf(".gif")>-1 				||
		   reqUrl.indexOf(".ico")>-1 				||
		   reqUrl.indexOf(".eot")>-1 				||
		   reqUrl.indexOf(".woff")>-1 				||
		   reqUrl.indexOf(".ttf")>-1 				||
		   reqUrl.indexOf("vulnerability")>-1 		||
		   reqUri.equals(rootUri) 					|| //BO의 경우는 / 허용
		   reqUri.equals("/shop") 					|| /* po 접근 예외 */
		   reqUri.equals("/shop/") 					|| /* po 접근 예외 */
		   reqUri.equals("/index") 					||
		   reqUri.equals("/accessDenied") 			||
		   reqUri.equals("/po/sc/adm/lostPw") 		||
		   reqUri.equals("/po/sc/adm/lostPwEmailSent.json") ||
		   reqUri.equals("/po/poStplatAgree") 		||
		   reqUri.equals("/po/poStplatAgree.json") 	||
		   reqUri.equals("/csoStplatAgree") 		||
		   reqUri.equals("/csoStplatAgree.json") 	||		   
		   reqUri.equals("/sc/auth/authCheck.json") ||
		   reqUri.equals("/errors/invalidSession") 	||
		   reqUri.equals("/errors/expiredSession") 	||
		   reqUri.equals("/loginProcess") 			||
		   reqUri.equals("/login") 					||
		   reqUri.equals("/pologin")				||
		   reqUri.equals("/po/isSmsFirstCrtfc.json") 	||
		   reqUri.equals("/po/sc/adm/smsCrtfcNoti") ||
		   reqUri.equals("/po/common/adm/updateMyInfo.json") ||
		   reqUri.equals("/po/sc/adm/sendSmsAuthNo.json") ||
		   reqUri.equals("/po/sc/adm/getSmsAuthentication.json") ||
		   reqUri.equals("/main") 					||
		   reqUri.equals("/pomain")					||
		   reqUri.equals("/logout")					||
		   reqUri.equals("/epsso")					||
		   reqUri.equals("/logouRedirect")			||
		   reqUri.equals("/btmonitor")				||
		   reqUri.equals("/system/healthcheck")
		   ) {
			//log.info(request.getProtocol()+"/"+reqUri+" => resource skip...");
			passYn = true;
		}else{

			try {
			
				if(BOSecurityUtil.hasRole()){
					siteId = BOSecurityUtil.getAccessSiteId();
					loginId = BOSecurityUtil.getLoginId();
				}
				
				//log.info("★ ★★getAccessSiteId :: "+siteId);
				//log.info("★ ★★getAccessSiteId :: "+BOSecurityUtil.getCurrentUserDetail().getPoAccessSiteId());
				
				//0순위 : [1] 등록시 // 2개 등록 페치 
				//        -> UPDATE SYS_MENU SET MENU_URL = REPLACE(MENU_URL,'//','/') WHERE MENU_URL LIKE '%//%'
				
				//1순위 : 메뉴 권한 체크
				hasAccessAuth = BOSecurityUtil.hasAccessUrl(requestUri);
				log.debug("★ ★★1순위 :: "+(hasAccessAuth?"OK":"X"));
				boolean firstStepSuccess = hasAccessAuth;
				//2순위 : 정규 표현식 체크 (권한 없는 요청에 대해서 * 문자가 삽입되어 있는 db menu url 조사)
				if(!hasAccessAuth){
					hasAccessAuth = BOSecurityUtil.hasAccessDynamicUrl(requestUri);
				}
				log.debug("★ ★★2순위 :: "+(hasAccessAuth?"OK":"X"));
				if(firstStepSuccess == false && hasAccessAuth == true) {	//페이지 full url 접근권한 패스 안됨 && 페이지 url like 조건(/sc/**) 패스
					if(BOSecurityUtil.hasAuthCheckUrl(requestUri)) {
						//현재 url 이 like 조건(/sc/**)만 패스하고, full url 일치 조건 메뉴인 경우 접근을 막는다.
						hasAccessAuth = false;
					}
				}
				log.debug("★ ★★3순위 :: "+(hasAccessAuth?"OK":"X"));
				
				//접속 권한 체크 이력 저장
				/* 2016-11-08 김정석 매니저 요청으로 주석 처리 
				   #22763 SR 번호 입니다				
				String grp = BODateUtil.getCurrentDateTimeString();
				uriInfo.setGrp(grp);
				uriInfo.setSiteId((siteId==null?"X":siteId));
				uriInfo.setUrl(ContextService.getCurrentRequest().getRequestURL().toString());
				uriInfo.setUri(requestUri);
				uriInfo.setAccessMenuYn((hasAccessAuth?"Y":"N"));		
				uriInfo.setIp(clientIp);
				uriInfo.setLoginId(loginId);
				uriAuthService.insertUriAuth(sqlSession1, uriInfo);
				*/
				passYn = (hasAccessAuth?true:false);
				if(!passYn){
					log.info("");
					log.info("=================================================");
					log.info("=================================================");
					log.info("=================================================");
					log.info("★ ★★ 접근 권한 체크 시작 ★★★ :: 0) 아래 로그는 권한이 없는 메뉴에 접근 시 에만 로깅 됩니다. -> 정상 운영 시 에는 안나와야 합니다.");
					log.info("★ ★★ 접근 권한 체크 시작 ★★★ :: 1-1) 공통권한 -> 1 depth 공통권한 의 공통권한등록 메뉴의 url 에 등록(주의 : PO/BO 별도)");
					log.info("★ ★★ 접근 권한 체크 시작 ★★★ :: 1-2) 각 화면별 권한 -> 해당 메뉴 의 url 에 등록");
					log.info("★ ★★ 접근 권한 체크 시작 ★★★ :: 2) 메뉴 등록 후 권한 assign 바랍니다.");
					log.info("=================================================");				
					log.info("{"+request.getProtocol()+"} / { "+reqUri+" } => 권한 미등록  => access denied [X]");
					log.info("메뉴 권한 등록 후 해당 권한그룹에 추가 바랍니다.]");
					
					redirectStrategy.sendRedirect(request, response, "/accessDenied?k=NOT_ASSIGN_ROLE");
					log.info("=================================================");
					log.info("★ ★★ 접근 권한 체크 종료 ★★★ :: 메뉴 체크 방식 : 로그인 성공시 권한 메뉴 메모리 적채 됨.");
					log.info("★ ★★ 접근 권한 체크 종료 ★★★ :: logout 후 브라우져 닫고 재로그인 후에 확인 바랍니다. (간혹 즉시 반영 확인 안됩니다.)");
					log.info("★ ★★ 접근 권한 체크 종료 ★★★ :: TEMP_MENU_AUTH 테이블 모니터링 바랍니다.");
					log.info("★ ★★ 접근 권한 체크 종료 ★★★ :: 주의 : 모든 처리 url 도 메뉴등록 및 권한 assign 되어 있어야 처리 가능합니다.");
					log.info("★ ★★ 접근 권한 체크 종료 ★★★ :: 주의 : 기타 사항 peter 문의 바랍니다.");
					log.info("=================================================");
					log.info("=================================================");
					log.info("=================================================");
					log.info("");
				}
			}catch(Exception e){
				redirectStrategy.sendRedirect(request, response, "/accessDenied?k="+requestUri);
				passYn = false;
			}
		}
		
		return passYn;		
    }
	
	private boolean isNotLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		boolean isNotLoginFlag = (session.getAttribute("SPRING_SECURITY_CONTEXT") == null?true:false);
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> isNotLoginFlag :: "+isNotLoginFlag);
		return session.getAttribute("SPRING_SECURITY_CONTEXT") == null;
	}
}
