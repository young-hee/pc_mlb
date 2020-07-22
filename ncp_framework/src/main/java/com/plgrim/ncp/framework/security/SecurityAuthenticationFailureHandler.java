package com.plgrim.ncp.framework.security;

import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SecurityJsonResult;
import com.plgrim.ncp.framework.enums.SecurityParam;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 인증이 실패 된 후 처리를 담당 한다.
 */
@Data
public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /* 로그인 실패시 이동하는 URL */
    String failURL;

    /* 로그인 실패시 이동하는 팝업 URL */
    String failPopupURL;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        // Request 객체의 Attribute에 사용자가 실패시 입력했던 로그인 ID와 비밀번호를 저장해두어 로그인 페이지에서 이를 접근하도록 한다.
        String userId = StringService.trimToEmpty(request.getParameter(SecurityParam.USER_ID_INPUT_NAME.toString()));
        String password = StringService.trimToEmpty(request.getParameter(SecurityParam.PASSWORD_INPUT_NAME.toString()));
        String loginRedirect = StringService.trimToEmpty(request.getParameter(SecurityParam.TARGET_PARAM_NAME.toString()));

        request.setAttribute(SecurityParam.USER_ID_INPUT_NAME.toString(), userId);
        request.setAttribute(SecurityParam.TARGET_PARAM_NAME.toString(), loginRedirect);


        String pageMode = request.getParameter(SecurityParam.LOGIN_POPUP_ENALBED.toString());
        String targetURL = failURL;

        //pageMode값 true일 경우
        if(StringService.isNotEmpty(pageMode) &&
                BooleanUtils.toBoolean(pageMode)) {
            targetURL = failPopupURL;
        }

        renderView(request, response, targetURL+ "?authenticationNok=1");

    }


    private void renderView (HttpServletRequest request, HttpServletResponse response, String targetURL) throws IOException, ServletException {

        boolean ajaxAvailable = BooleanUtils.toBoolean(request.getHeader(SecurityParam.HEADER_AJAX_LOGIN.toString()));

        //요청이 ajax 면
        if (ajaxAvailable) {

            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

            SecurityJsonResult resultData = new SecurityJsonResult();
            String jsonData = "";
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
            request.getRequestDispatcher(targetURL).forward(request, response);
        }
    }
}
