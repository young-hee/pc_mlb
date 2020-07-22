package com.plgrim.ncp.framework.responsecode;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

/**
 * 예외가 발생한 {@link HandlerMethod} 메소드를 가져 오기 위해 {@link ExceptionHandlerExceptionResolver}를 커스터마이징 클래스
 * 
 *@See ExceptionTargetHandlerMethodArgumentResolver
 */
@Primary
@Order(1)
public class HandlerMethodHoldingExceptionResolver extends ExceptionHandlerExceptionResolver { 

	public static final String ORIGINAL_OCCURRED_EXCEPTION_HANDLER_METHOD_ATTR = "__ORIGINAL_OCCURRED_EXCEPTION_HANDLER_METHOD";

	@Override
	protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response,
			HandlerMethod handlerMethod, Exception exception) {
		// 혹시 오류가 발생했을때 ExceptionHandler의 인자에 HandlerMethod가 있을때 전달하기위해 HandlerMethod를 저장해둔다. 
		// 실제로 Handler를 읽어 가는 곳은 ExceptionTragertHandlerMethodArgumentResolver이다 
		
		request.setAttribute(ORIGINAL_OCCURRED_EXCEPTION_HANDLER_METHOD_ATTR, handlerMethod);
		return super.doResolveHandlerMethodException(request, response, handlerMethod, exception);
	}

	@Override
	protected List<HandlerMethodArgumentResolver> getDefaultArgumentResolvers() {
		List<HandlerMethodArgumentResolver> defaultArgumentResolvers = super.getDefaultArgumentResolvers();
		defaultArgumentResolvers.add(0, new ExceptionTargetHandlerMethodArgumentResolver());
		return defaultArgumentResolvers;
	}
	
	@Override
	public List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> converters = super.getMessageConverters();
		converters.add(0, new MappingJackson2HttpMessageConverter());
		return converters;
	}
	
}