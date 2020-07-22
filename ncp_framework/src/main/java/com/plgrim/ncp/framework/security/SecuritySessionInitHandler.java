package com.plgrim.ncp.framework.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *SecurityAuthenticationSuccessHandler.onAuthenticationSuccess 메서드에서 인증 성공 후 동일 계정 세션 정보를 처리 하는 인터페이스.
 */
public interface SecuritySessionInitHandler {

    void initSecuritySession(HttpServletRequest request, HttpServletResponse response, Authentication authentication, SessionRegistry sessionRegistry) throws IOException, ServletException;

}
