package com.plgrim.ncp.cmp.member.fo.auth;


import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrLoginLog;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.MemberEnum.MemberAtrbCd;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.data.SingleDTO;
import com.plgrim.ncp.biz.member.service.*;
import com.plgrim.ncp.framework.commons.*;
import com.plgrim.ncp.framework.data.SecurityJsonResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.RedirectOrder;
import com.plgrim.ncp.framework.enums.SecurityParam;
import com.plgrim.ncp.framework.security.SecuritySessionInitHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** 
 * 인증이 성공이 된 후 처리를 담당 한다.
 */
@Data
@Slf4j
public class SimpleSecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    RequestCache requestCache = new HttpSessionRequestCache();

    RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /* referer 헤더 사용 유무  */
    boolean referer = false;

    /* 기본 성공 url*/
    private String defaultUrl;

    private String popupSuccessURL;

    @Autowired(required = false)
    private SecuritySessionInitHandler securitySessionInitHandler;

    @Autowired(required = false)
    private SessionRegistry sessionRegistry;
    
    @Autowired
    private IdGenService idGenService;
    
    @Autowired
	MemberPersonalInfoSelectService memberPersonalInfoSelectService;
	
	@Autowired
	MemberPersonalInfoCommandService memberPersonalInfoCommandService;
	
	@Autowired
	MemberActivitySelectService memberActivitySelectService;
	
	@Autowired
	MemberActivityCommandService memberActivityCommandService;
	
	@Autowired
	MemberBaseSelectService memberBaseSelectService;
	
	@Autowired
	MemberBaseCommandService memberBaseCommandService;
	
	@Autowired
	MemberBenefitSelectService memberBenefitSelectService;
	
	@Autowired
	MemberBenefitCommandService memberBenefitCommandService;
	
	@Autowired
	MemberOrderSelectService memberOrderSelectService;
	
	@Autowired
	MemberOrderCommandService memberOrderCommandService;
	
	@Autowired
	MemberCertifyCommandService memberCertifyCommandService;
    
    /**
     * 회원들에게 부여되는 권한.
     */
    final static String ROLE_USER_AUTHORIZE = "ROLE_USER";
    

    /** {@inheritDoc} */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        /***인증 성공후 business logic**/
        String password = StringService.trimToEmpty(request.getParameter(SecurityParam.PASSWORD_INPUT_NAME.toString()));
        
        try {

            HttpSession session = request.getSession();
            SystemPK pk = idGenService.getAutoGeneratorSystemPK(request);
//            session.setAttribute(SessionEnum.SESSION_ID.toString(), session.getId());
            
            SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

            String loginId = userDetail.getMbr().getMbrId();
            String mbrNo = userDetail.getMbr().getMbrNo();
            //인증이 실패 시키는 방법은 null 값을 리턴 하면 됨.



                //로그인 실패횟수 0
                userDetail.getMbr().setMbrLoginLastFailrCount(0);
    			userDetail.getMbr().setDrmcResnCont("");
    			userDetail.getMbr().setDrmcDt(null);
				memberBaseCommandService.updateLoginFailCount(userDetail.getMbr());

                //md5 이면 SHA 256으로 업데이트
                if (IdGenService.generateMD5(password).toLowerCase().equals(userDetail.getMbr().getMbrPw().toLowerCase())
                		|| IdGenService.generateSHA256(IdGenService.generateMD5(password)).toLowerCase().equals(userDetail.getMbr().getMbrPw().toLowerCase()) ) {

                    userDetail.getMbr().setMbrPw(IdGenService.generateSHA256(password));
                    memberBaseCommandService.updatePassword(userDetail.getMbr());

                }
                
              //ip
//        		InetAddress address = InetAddress.getLocalHost();
//        		String ip = address.getHostAddress();
        		String  ip = request.getRemoteAddr();
        		Locale locale = LocaleService.getCurrentLocale();
        		MbrLoginLog mbrLoginLog = new MbrLoginLog();
        		mbrLoginLog.setMbrNo(userDetail.getMbr().getMbrNo());
        		mbrLoginLog.setMbrLoginCd("LOGIN");
        		mbrLoginLog.setLoginIp(ip);
        		
        		String loginNationCd = locale.getCountry();
        		if (StringService.isEmpty(loginNationCd)) {
                    loginNationCd = Locale.KOREA.getCountry();
                }
        		
        		mbrLoginLog.setLoginNationCd(loginNationCd.toLowerCase());
        		mbrLoginLog.setLangCd(pk.getLang());
        		mbrLoginLog.setDvcCd(pk.getDevice());
        		mbrLoginLog.setMallId(pk.getMall());
        		if(session.getAttribute(SessionEnum.INFLOW_SN.toString()) != null){
        			long inflowSn = Long.parseLong((String)session.getAttribute(SessionEnum.INFLOW_SN.toString()));
        			mbrLoginLog.setInflowSn(inflowSn);
        			
        		}
        		log.info("locale {} ",locale);

                //로그인 히스토리 추가
        		log.info("mbrLoginLog {}",mbrLoginLog);
        		memberBaseCommandService.addMbrLoginLog(pk, mbrLoginLog);

                //비밀번호는 세션에 담을수 없으므로
                userDetail.getMbr().setMbrPw("");

                List<GrantedAuthority> grantedAuths = new ArrayList<>();
                grantedAuths.add(new SimpleGrantedAuthority(ROLE_USER_AUTHORIZE));
                authentication = new UsernamePasswordAuthenticationToken(userDetail, null, grantedAuths);
                
                //세션 정보를 체크하여 중복로그인을 제거한다.
                //마지막 세션을 threadlocal에 저장
                if (securitySessionInitHandler != null && sessionRegistry != null) {
                    securitySessionInitHandler.initSecuritySession(request, response, authentication, sessionRegistry);
                }
                
                 

        } catch (Exception ex) {
            throw new AuthenticationServiceException("Credentials could not be verified", ex);
        }
        
        
        
        
        String ajax = request.getHeader("ajax-login");
        RedirectOrder redirectOrder = decideRedirectStrategy(request, response);

        switch (redirectOrder) {
            case TARGET_PARAM:
                useTargetUrl(request, response);
                break;
            case SESSION_URL:
                useSessionUrl(request, response);
                break;
            case SESSION_REFERER_URL:
            	useSessionRefererUrl(request, response);
            	break;
            case HEADER_REFERER:
                useRefererUrl(request, response);
                break;
            default:
                useDefaultUrl(request, response);
        }
    }

    /**
     * 인증 성공후 어떤 URL로 redirect 할지를 결정한다
     * <p/>
     * 순서되로 체크를 하며 먼저 해당이 되는 것을 처리 하도록 한다.
     * <p/>
     * 1순위 헤더 값 ajax-login이 true일 경우;
     * 2순위 loginTarget 파라미터 값을 읽은 URL이 존재할 경우
     * 3순위 Spring Security가 세션에 저장한 URL이 존재할 경우
     * 4순위 Request의 REFERER의 URL이 존재 할 경우
     * 5순위 모두 해당 사항이 없으면 Default URL를 사용 한다.
     *
     * @param request
     * @param response
     * @return RedirectOrder
     */
    private RedirectOrder decideRedirectStrategy(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        RedirectOrder redirectOrder = RedirectOrder.DEFAULT;
        HttpSession session = request.getSession();
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        //request 부터 호출한 URL 정보가 있는지 파악을 한다.
        String targetURL = request.getParameter(SecurityParam.TARGET_PARAM_NAME.toString());
        //targetURL이 존재 하면
        if (!StringUtils.isEmpty(targetURL)) {
            return RedirectOrder.TARGET_PARAM;
        } else {

            //세션에 URL 정보가 있을 경우
            if (savedRequest != null) {
                return RedirectOrder.SESSION_URL;
            } else {
            	if(session != null && session.getAttribute("REFERER_URL") != null){
            		return RedirectOrder.SESSION_REFERER_URL;
            		
            	}else{
            		//referer 헤더를 사용할 경우
            		if (referer) {
            			String refererUrl = request.getHeader("REFERER");
            			if (StringUtils.isEmpty(refererUrl)) {
            				return RedirectOrder.HEADER_REFERER;
            			}
            		}
            		
            	}
            }
        }
        return redirectOrder;

    }

    /* 실패시 임시로 생성한 세션 객체를 삭제 한다. */
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    private void useTargetUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest != null){
            requestCache.removeRequest(request, response);
        }
        String targetUrl = request.getParameter(SecurityParam.TARGET_PARAM_NAME.toString());

        //로그인 모드가 팝업일 경우 팝업 로그인 성공 페이지로 이동 한다.
        String pageMode = request.getParameter(SecurityParam.LOGIN_POPUP_ENALBED.toString());

        //pageMode값 true일 경우
        if(StringService.isNotEmpty(pageMode) &&
                BooleanUtils.toBoolean(pageMode)) {
            targetUrl = popupSuccessURL + "?"+ SecurityParam.TARGET_PARAM_NAME.toString() + "=" + targetUrl;
        }

        renderView(request, response, targetUrl);

    }

    private void useSessionUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String targetUrl = savedRequest.getRedirectUrl();
        renderView(request, response, targetUrl);
    }

    private void useRefererUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String targetUrl = request.getHeader("REFERER");
        renderView(request, response, targetUrl);
    }
    private void useSessionRefererUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	HttpSession session = request.getSession();
    	String targetUrl = (String)session.getAttribute("REFERER_URL");
    	renderView(request, response, targetUrl);
    }

    private void useDefaultUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
        renderView(request, response, defaultUrl);
    }


    private void renderView (HttpServletRequest request, HttpServletResponse response, String targetURL) throws IOException {

        boolean ajaxAvailable = BooleanUtils.toBoolean(request.getHeader(SecurityParam.HEADER_AJAX_LOGIN.toString()));

        //요청이 ajax 면
        if (ajaxAvailable) {

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

            SecurityJsonResult resultData = new SecurityJsonResult();
            resultData.setLoginTarget(targetURL);
            String jsonData = defaultUrl;
            try {
                jsonData = JsonService.marshalling(resultData);
            }catch (Exception e) {
                throw new IOException(e);
            }
            InputStream is =
                    new ByteArrayInputStream(jsonData.getBytes());
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();

        } else {

            redirectStrategy.sendRedirect(request, response, targetURL);

        }
    }
}

