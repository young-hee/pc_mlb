package com.plgrim.ncp.framework.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AccessBlockingInterceptor extends HandlerInterceptorAdapter{
	
	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	public static final String COOKIE_NAME = "ALLOW_SITE_ACCESS";
	
	public static final String COOKIE_VALUE = "POIUYTREWQLKJHGFDSAZXCVBNM";
	
	@Override
    public boolean preHandle(HttpServletRequest request, 
            HttpServletResponse response, Object handler) throws Exception {
		
		boolean passYn = false;
		String reqUri = request.getRequestURI();
		
		//log.debug(request.getProtocol()+" "+reqUri);
		
		String device = getDevice(request);
		
		if (!"MOBILE_APP".equals(device)) {
			if(!reqUri.equals("/") && !reqUri.equals("/fakeIndex") && !reqUri.equals("/accessDenied.jsp")){
				Cookie[] cookies = request.getCookies();
				if(cookies != null){
					for (Cookie cookie : cookies) {
						if(COOKIE_NAME.equals(cookie.getName())){
							if(COOKIE_VALUE.equals(cookie.getValue())){
								passYn = true;
							}
						}
					}
				}   
			}else{
				passYn = true;
			}
		} else {
			passYn = true;
		}
		
		if(!passYn){
			redirectStrategy.sendRedirect(request, response, "/accessDenied.jsp");
		}
		
		return passYn;
		
    }

	private String getDevice(HttpServletRequest request) throws Exception {
		String deviceType = request.getHeader("user-agent").toLowerCase();
		String result = "PC";
		
		String[] deviceStr = StringUtils.split("iphone|ipod|blackberry|android|windows ce|lg|mot|samsung|sonyericsson","|");
		String[] appStr = StringUtils.split("beanpole_app|8seconds_app|plgrimshop_app","|");
		
		for (String mobile : deviceStr) {
			if (deviceType.contains(mobile)) {
				result = "MOBILE_WEB";
				break;
			}
		}
		for (String app : appStr) {
			if (deviceType.contains(app)) {
				result = "MOBILE_APP";
				break;
			}
		}
		return result;
	}
}
