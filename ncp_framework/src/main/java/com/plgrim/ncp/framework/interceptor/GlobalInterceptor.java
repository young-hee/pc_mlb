package com.plgrim.ncp.framework.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class GlobalInterceptor extends LocaleChangeInterceptor {
	protected static final Log logger = LogFactory.getLog(GlobalInterceptor.class);

	@Value("${ncp.url.mb_DX.root}")
	private String redirectMBUrl;
	@Value("${ncp.url.pc_DX.root}")
	private String redirectPCUrl;

	@Value("${ncp_web_pc_dx.main.url}")
	private String mainPCUrl;

	@Value("${ncp_web_mb_dx.main.url}")
	private String mainMBUrl;

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {

        final String serverName = request.getServerName();

        Locale locale;

        if (serverName.contains("en.") || serverName.contains("en2.")) {
            locale = Locale.ENGLISH;
            this.setParamName("en");
        } else if (serverName.contains("cn.") || serverName.contains("cn2.")) {
            locale = Locale.CHINA;
            this.setParamName("zh");
        } else {
            locale = Locale.KOREAN;
            this.setParamName("ko");
        }

        RequestContextUtils.getLocaleResolver(request).setLocale(request, response, locale);

        return true;
	}
}
