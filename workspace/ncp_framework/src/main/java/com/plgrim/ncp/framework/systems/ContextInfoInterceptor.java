package com.plgrim.ncp.framework.systems;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Mbr에 대한 접근이 필요하기 때문에 ncp_cmp에 있음
 * @author narusas
 *
 */
public class ContextInfoInterceptor implements HandlerInterceptor {
	@Autowired
	ExecutionContext executionContext;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		injectProgramId(handler);
		return true;
	}



	private void injectProgramId(Object handler) {
		if ((handler instanceof HandlerMethod) == false) {
			return;
		}
		executionContext.injectProgramId((HandlerMethod) handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
	        throws Exception {
		// 필터처리까지 로그가 남을수 있기 때문에 context 는 Filter에서 지운다.
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

}
