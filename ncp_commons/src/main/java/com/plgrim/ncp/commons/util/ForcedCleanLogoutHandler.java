package com.plgrim.ncp.commons.util;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class ForcedCleanLogoutHandler extends SimpleUrlLogoutSuccessHandler {
	String logoutSuccessUrl;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		log.info("Logout ");

		Cookie cookie;
		String contextPath = request.getContextPath();

		cookie = new Cookie("MBRNO", null);
		cookie.setMaxAge(0);
		cookie.setPath(contextPath.length() > 0 ? contextPath : "/");
		response.addCookie(cookie);
		
		cookie = new Cookie("e_mbr", null);
		cookie.setMaxAge(0);
		cookie.setPath(contextPath.length() > 0 ? contextPath : "/");
		response.addCookie(cookie);
		
		cookie = new Cookie("LOGIN_FLAG", null);
		cookie.setMaxAge(0);
		cookie.setPath(contextPath.length() > 0 ? contextPath : "/");
		response.addCookie(cookie);

		request.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");
		Enumeration<String> names = request.getSession().getAttributeNames();

		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			request.getSession().removeAttribute(name);
		}

		request.getSession().invalidate();

		/* 일반 로그아웃인 경우 메인페이지 리턴 */
		setDefaultTargetUrl(logoutSuccessUrl);

		// below does the 'standard' spring logout handling
		super.onLogoutSuccess(request, response, authentication);
	}

}
