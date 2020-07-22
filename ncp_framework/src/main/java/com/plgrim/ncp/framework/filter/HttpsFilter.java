package com.plgrim.ncp.framework.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.framework.systems.Stage;
import com.plgrim.ncp.framework.systems.SystemContext;
import com.plgrim.ncp.framework.utils.CookieUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HttpsFilter implements Filter {
	@Autowired
	SystemContext systemContext;

	public HttpsFilter() {
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpsRequestWrapper httpsRequest = new HttpsRequestWrapper((HttpServletRequest) request);

		httpsRequest.setResponse((HttpServletResponse) response);

		chain.doFilter(httpsRequest, response);

	}

	public void destroy() {
	}
}
