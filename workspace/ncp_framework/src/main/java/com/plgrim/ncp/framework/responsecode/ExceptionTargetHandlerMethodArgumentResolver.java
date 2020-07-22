package com.plgrim.ncp.framework.responsecode;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link HandlerMethod} 를 파라메터로 가져 오기 위한 ArgumentResolver
 * 
 * ExceptionHandler의 인자중 HandlerMethod 는 기본 지원되지 않는다. 이를 위해
 * HandlerMethodHoldingExceptionResolver 가 ServletRequest 에 저장해 놓은 HandlerMethod
 * 를 가져다 인자로 제공한다.
 */
@Slf4j
public class ExceptionTargetHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver { 
	
	public ExceptionTargetHandlerMethodArgumentResolver() {
		log.info("Inited");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (HandlerMethod.class.equals(parameter.getParameterType())) {
			return true;
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return (HandlerMethod) webRequest.getAttribute(
				HandlerMethodHoldingExceptionResolver.ORIGINAL_OCCURRED_EXCEPTION_HANDLER_METHOD_ATTR,
				RequestAttributes.SCOPE_REQUEST);
	}
}