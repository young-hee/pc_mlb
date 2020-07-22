package com.plgrim.ncp.framework.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie 에 대한 유틸리티
 * 
 * @author 안지성
 * @version 1.0
 * 
 */
public class CookieUtil {
	public static final int SECONDS_OF_DAY = 60 * 60 * 24;
	public static final int MAXAGE_SESSION = -1;
	public static final int MAXAGE_TERMINATION = 0;

	public static final String DEFAULT_PATH = "/";

	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					try {
						String value = cookie.getValue();
						return value;
					} catch (Exception e) {
						return "";
					}
				}
			}
		}
		return null;
	}

	/**
	 * 쿠키에 새로운 정보를 기록한다. (만료일 = Browser Session 주기)
	 * 
	 * @param HttpServletResponse
	 * @param String
	 *            name / Cookie name
	 * @param String
	 *            value / Cookie value
	 */
	public static void addCookie(HttpServletResponse response, String name, String value) {
		addCookie(response, name, value, MAXAGE_SESSION);
	}

	/**
	 * 쿠키 정보를 삭제한다.
	 * 
	 * @param HttpServletResponse
	 * @param String
	 *            name / Cookie name
	 * @param String
	 *            value / Cookie value
	 */
	public static void deleteCookie(HttpServletResponse response, String name) {
		addCookie(response, name, "", MAXAGE_TERMINATION);
	}

	/**
	 * 쿠키에 새로운 정보를 기록한다. (만료일 = 입력한 날짜)
	 * 
	 * @param HttpServletResponse
	 * @param String
	 *            name / Cookie name
	 * @param String
	 *            value / Cookie value
	 * @param int
	 *            expireDay / Cookie 만료일
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int expireSeconds) {
		addCookie(response, name, value, null, expireSeconds);
	}

	public static void addCookie(HttpServletResponse response, String name, String value, String domain,
			int expireSeconds) {
		addCookie(response, name, value, domain, DEFAULT_PATH, expireSeconds, false);
	}

	public static void addCookie(HttpServletResponse response, String name, String value, String domain, String path,
			int seconds, boolean isSecure) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(seconds < 0 ? MAXAGE_SESSION : seconds);

		if (domain != null) {
			cookie.setDomain(domain);
		}
		cookie.setPath(path);
		cookie.setSecure(isSecure);
		response.addCookie(cookie);
	}

}