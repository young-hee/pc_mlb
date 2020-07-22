package com.plgrim.ncp.framework.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class RemoteAddrUtil {
	static final String[] FORWARDED_HEADERS = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR" };

	public static String getRemoteAddr(HttpServletRequest request) {
		for (String header : FORWARDED_HEADERS) {
			if (StringUtils.isNotEmpty(request.getHeader(header))) {
				return request.getHeader(header);
			}
		}
		return request.getRemoteAddr();
	}
}
