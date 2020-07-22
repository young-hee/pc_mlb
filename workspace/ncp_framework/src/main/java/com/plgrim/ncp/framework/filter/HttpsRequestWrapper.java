package com.plgrim.ncp.framework.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
/**
 * Tomcat 에서는  세션쿠키를 생성할때 HTTPS 에서는  HTTPS 용으로만 만들어 낸다. 이에 따라  HTTPS 에서 로그인한후  HTTP 로 이동할 경우 세션이 끊기는 것처럼 보이는 현상이 발생한다. 
 * 
 * 이 문제를 해결하기 위해  HTTPS 에서  HTTP 용 쿠키를 굽게 하는 기능을 여기에서 구현한다. 
 * 
 * (정리하자면  Tomcat  에서만 필요한 기능이다. 웹로직에서는 동작하면 않됨 ) 
 * 
 * 참조: https://community.oracle.com/thread/1396970
 * 
 * @author narusas
 *
 */
@Slf4j
public class HttpsRequestWrapper extends HttpServletRequestWrapper {

	private HttpServletResponse response = null;

	public HttpsRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		HttpSession session = super.getSession();
		processSessionCookie(session);
		return session;
	}

	public HttpSession getSession(boolean create) {
		HttpSession session = super.getSession(create);
		processSessionCookie(session);
		return session;
	}

	private void processSessionCookie(HttpSession session) {
		if (session == null || response == null) {
			return;
		}

		Object cookieOverWritten = getAttribute("COOKIE_OVERWRITTEN_FLAG");

		if (cookieOverWritten == null && isSecure() && isRequestedSessionIdFromCookie() && session.isNew()) {

			log.info("SessionCookieWrapping is requested.  sessionId: '{}' isSecure: {} isFromCookie: {} isNew: {}",
					session.getId(), isSecure(), isRequestedSessionIdFromCookie(), session.isNew());
			Cookie cookie;

			String serverPort = String.valueOf(getRequest().getServerPort());

			cookie = new Cookie("JSESSIONID", session.getId());
			String contextPath = getContextPath();
			cookie.setMaxAge(-1);

			if (contextPath != null && contextPath.length() > 0) {
				cookie.setPath(contextPath);
			} else {
				cookie.setPath("/");
			}
			log.info("Rewrited Cookie value: {} domain: {} path: {} maxAge:{} isSecure: {}", cookie.getName(),
					cookie.getDomain(), cookie.getPath(), cookie.getMaxAge(), cookie.getSecure());
			response.addCookie(cookie);
			setAttribute("COOKIE_OVERWRITTEN_FLAG", "true");
		}

	}

}
