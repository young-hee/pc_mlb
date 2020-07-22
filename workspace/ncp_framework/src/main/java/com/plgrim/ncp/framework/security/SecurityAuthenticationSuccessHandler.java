package com.plgrim.ncp.framework.security;

import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SecurityJsonResult;
import com.plgrim.ncp.framework.enums.RedirectOrder;
import com.plgrim.ncp.framework.enums.SecurityParam;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

/**
 * 인증이 성공이 된 후 처리를 담당 한다.
 */
@Data
@Slf4j
public class SecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    RequestCache requestCache = new HttpSessionRequestCache();

    RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /* referer 헤더 사용 유무  */
    boolean referer = false;

    /* 기본 성공 url*/
    String defaultUrl;

    String popupSuccessURL;

    @Autowired(required = false)
    SecuritySessionInitHandler securitySessionInitHandler;

    @Autowired(required = false)
    SessionRegistry sessionRegistry;

    /** {@inheritDoc} */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //securitySessionInitHandler, sessionRegistry 오브젝트가 injection 될 경우만 실행 한다.
        if (securitySessionInitHandler != null && sessionRegistry != null) {
            securitySessionInitHandler.initSecuritySession(request, response, authentication, sessionRegistry);
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
                //referer 헤더를 사용할 경우
                if (referer) {
                    String refererUrl = request.getHeader("REFERER");
                    if (StringUtils.isEmpty(refererUrl)) {
                        return RedirectOrder.HEADER_REFERER;
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
