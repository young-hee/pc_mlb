package com.plgrim.ncp.framework.systems;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 요청 실행에 대한 문맥 정보를 SLF4j의 MDC에 추가한다.
 * 
 * @author narusas
 *
 */
@Component
@Slf4j
public class ContextInfoFilter implements Filter {
	@Autowired
	ExecutionContext executionContext;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		inject(request, response);
		try {
			if (log.isTraceEnabled()) {
				log.trace("__ENTRY_EXECUTION__");
			}

			chain.doFilter(request, response);
		} finally {
			if (log.isTraceEnabled()) {
				log.trace("__EXIT_EXECUTION__");
			}
			cleanup();
		}
	}

	private void inject(ServletRequest request, ServletResponse response) {
		try {
			executionContext.inject((HttpServletRequest) request, (HttpServletResponse) response);
		} catch (Exception e) {
			// Context Info 를 획득하는 과정이 어플리케이션의 실행을 막아서는 않됨
			log.error("Context Info Error", e);
		}
	}

	private void cleanup() {
		executionContext.cleanCurrentThread();
	}

	@Override
	public void destroy() {
	}
}
