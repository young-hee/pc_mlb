package com.plgrim.ncp.framework.exception;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.LocaleService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.json.JsendStatus;
import com.plgrim.ncp.framework.json.ValidationFailResponse;
import com.plgrim.ncp.framework.responsecode.FailedValidation;
import com.plgrim.ncp.framework.responsecode.ResponseCode;
import com.plgrim.ncp.framework.responsecode.ResponseException;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.plgrim.ncp.framework.systems.ApplicationType;
import com.plgrim.ncp.framework.systems.SystemContext;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Deprecated
 * 
 * 새로운 ExceptionHandler는 CommonExceptionHandlerConfig.java 임 
 * 
 * 추후 이 파일은 삭제 될 것임
 */
/**
 * ExceptionResolver를 커스터마이징. Exception이 발생을 하면 해당 Exception에 해당하는 메세지를 번들에서
 * 찾는다. 만약 없을 경우는 기본 exception 메세지를 출력한다. 그리고 만약 요청이 ajax일 경우는 json 형태로 메세지를 리턴
 * 한다. 기본 상태 코드는 500으로 하고 필요에 따라서 상태 코드를 부여 할수 있다.
 */
@Setter
@Slf4j
public class MVCMappingExceptionResolver extends AbstractHandlerExceptionResolver { 

	final static String POPUP_EXTENSION = "popup";

	/* 기본 에러 처리 뷰 이름 */
	final static String DEFAULT_VIEW_NAME = "errors/generalError";

	/* 기본 팝업 에러 처리 뷰 이름 */
	final static String DEFAULT_POPUP_VIEW_NAME = "errors/generalPopupError";

	/* 기본 에러 메세지 키 */
	final static String DEFAULT_MESSAGE_KEY = "DefaultException";

	/* views 밑에 부터 경로로 설정 한다. 단, .jsp는 생략 한다. 예) erros/generalError */
	String defualtView;

	/* views 밑에 부터 경로로 설정 한다. 단, .jsp는 생략 한다. 예) erros/generalError */
	String defaultPopupView;

	String goodsView;

	String orderView;

	@Autowired
	MessageSourceAccessor messageSourceAccessor;

	@Autowired
	SystemContext systemContext;

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);

		ModelAndView mav = createModelAndView(request, handler, ex);
		return mav;
	}

	private ModelAndView createModelAndView(HttpServletRequest request, Object handler, Exception ex) {
		HandlerMethod method = (HandlerMethod) handler;
		MethodParameter returnType = method.getReturnType();
		Type genParam = returnType.getGenericParameterType();
		String typeName = genParam.toString();

		if ("org.springframework.http.ResponseEntity<com.plgrim.ncp.framework.json.JSendResponse>".equals(typeName)) {
			return handlerJsendResponse(request, ex);
		} else {
			return handlerLegacy(request, ex);
		}
	}

	private ModelAndView handlerJsendResponse(HttpServletRequest request, Exception ex) {
		if (ex instanceof ResponseException) {
			return handleResponseExceptionToJsend(request, (ResponseException) ex);
		} else if (isValidationException(ex)) {
			return handleValidationFailExceptionToJsend(request, (ResponseException) ex);
		} else {
			return handleLegacyExceptionAsJSend(request, ex);
		}

	}

	private ModelAndView handleResponseExceptionToJsend(HttpServletRequest request, ResponseException ex) {
		ModelAndView mav = new ModelAndView();
		ResponseCode responseCode = ex.getResponseCode();
		if (responseCode == null) {
			responseCode = CommonResponseCode.SC_50000_시스템_오류가_발생하였습니다;
		}
		mav.addObject("status", JsendStatus.error);
		mav.addObject("message", responseCode.toRawMessage());
		mav.addObject("code", responseCode.getCode());
		// Exception이 발생해서 여기까지 왔다는 것은, controller에서 예외처리를 하지 않고 시스템에 맞겼다는 의미이다.
		// 이런 상황에서 반환할 data가 있을리가 없다.
		return mav;
	}

	private boolean isValidationException(Exception ex) {
		return ex instanceof BindException || ex instanceof MethodArgumentNotValidException || ex instanceof ConstraintViolationException;
	}

	private ModelAndView handleValidationFailExceptionToJsend(HttpServletRequest request, Exception ex) {
		List<FailedValidation> errors = null;
		if (ex instanceof BindException){
			errors =collectFieldErrors(((BindException) ex).getBindingResult().getFieldErrors());	
		}
		else if (ex instanceof MethodArgumentNotValidException){
			errors =collectFieldErrors(((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors());	
		}
		else if (ex instanceof ConstraintViolationException) {
			errors = new ArrayList<>();
			Set<ConstraintViolation<?>> violations = ((ConstraintViolationException)ex).getConstraintViolations();
			for (ConstraintViolation<?> constraintViolation : violations) {
				String errorType = constraintViolation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
				String field = constraintViolation.getPropertyPath().toString();
				errors.add(new FailedValidation(errorType, field));
			}
		}
		
		
		String renderViewName = decideViewName(request);

		ModelAndView mav = new ModelAndView(renderViewName);
		mav.addObject("status", JsendStatus.fail);
		mav.addObject("message", CommonResponseCode.SC_40000_요청한_입력값이_유효하지_않습니다.toRawMessage());
		mav.addObject("code", CommonResponseCode.SC_40000_요청한_입력값이_유효하지_않습니다.getCode());
		mav.addObject("data", ValidationFailResponse.consolidateViolations(errors));
		
		return mav;
	}
	private List<FailedValidation> collectFieldErrors(List<FieldError> errors) {
		List<FailedValidation> failedValidations = new ArrayList<>();
		if (CollectionUtils.isEmpty(errors)) {
			return failedValidations;
		}
		for (FieldError fieldError : errors) {
			String errorType = fieldError.getCode();
			String field = fieldError.getField();
			failedValidations.add(new FailedValidation(errorType, field));
		}
		return failedValidations;
	}

	private ModelAndView handleLegacyExceptionAsJSend(HttpServletRequest request, Exception ex) {
		String renderViewName = decideViewName(request);

		ModelAndView mav = new ModelAndView(renderViewName);
		mav.addObject("status", JsendStatus.error);
		mav.addObject("message", decideMessage(request, ex));
		mav.addObject("code", CommonResponseCode.SC_50000_시스템_오류가_발생하였습니다.getCode());
		return mav;
	}

	private ModelAndView handlerLegacy(HttpServletRequest request, Exception ex) {

		String renderViewName = decideViewName(request);

		ModelAndView mav = new ModelAndView(renderViewName);
		mav.addObject("message", decideMessage(request, ex));
		return mav;
	}

	private String decideMessage(HttpServletRequest request, Exception ex) {
		if (ex instanceof NCPException) {
			return deviceNcpExceptionMessage(request, ex);
		}
		String key = IOService.getExtension(ex.getClass().getName());
		return findMessage(request, key, null);

	}

	String deviceNcpExceptionMessage(HttpServletRequest request, Exception ex) {
		// NCPException으로 캐스팅 처리
		NCPException ncpException = (NCPException) ex;

		String directMessage = ncpException.getDirectMessage();
		// 직접 입력 메세지가 있을 경우 그대로 출력
		if (StringService.isNotEmpty(directMessage)) {
			return directMessage;
		}

		// 사용자 정의 메세지 키가 존재시 사용자 정의 키로 대체(validation)
		String key = StringService.isNotEmpty(ncpException.getCustomKey()) ? ncpException.getCustomKey()
				: IOService.getExtension(ex.getClass().getName());

		String[] params = ncpException.getParams();
		return findMessage(request, key, params);
	}

	/*
	 *
	 * Exception 메세지 우선 순위 (1) Exception (NCPException 상속받은)의 메세지가 있을 경우 그대로 출력
	 * (2) 없을 경우 exception.properties에서 Exception 키에 해당하는 메세지 와 파라미터를 출력
	 *
	 */
	private String findMessage(HttpServletRequest request, String key, String[] params) {
		try {
			return messageSourceAccessor.getMessage(key, params, LocaleService.getCurrentLocale(request));
		} catch (Exception ex) {
			// 오류처리 과정의 일부이기 때문에 오류를 또 발생시키지는 않는다
			try {
				ApplicationType appType = systemContext.getAppType();
				String phoneMessage = "CS CENTER : 080-820-8802";
				switch (appType) {
				case NCP_FRONT_PC:
					phoneMessage = messageSourceAccessor.getMessage("DefaultCsoPhoneMessage", LocaleService.getCurrentLocale(request));
					break;
				case NCP_FRONT_MOBILE:
					phoneMessage = messageSourceAccessor.getMessage("DefaultCsoPhoneMessage", LocaleService.getCurrentLocale(request));
					break;
				default:
					phoneMessage = "";
				}

				String[] failedParam = { phoneMessage };

				switch (appType) {
				case BACK_OFFICE:
					return messageSourceAccessor.getMessage("DefaultBOException", failedParam, LocaleService.getCurrentLocale(request));
				default:
					return messageSourceAccessor.getMessage(DEFAULT_MESSAGE_KEY, failedParam, LocaleService.getCurrentLocale(request));
				}
			} catch (Exception ext) {
				
				return messageSourceAccessor.getMessage(DEFAULT_MESSAGE_KEY, new String[] { "CS CENTER : 080-820-8802" },
						LocaleService.getCurrentLocale(request));
			}

		}

	}

	private String decideViewName(HttpServletRequest request) {
		String renderViewName = DEFAULT_VIEW_NAME;
		// try {
		// 기본 뷰를 설정 하지 않으면 DEFAULT_VIEW_NAME으로 설정 한다.
		if (StringService.isNotEmpty(defualtView)) {
			renderViewName = defualtView;
		}

		// 팝업일 경우
		if (IOService.getExtension(request.getRequestURI()).equals(POPUP_EXTENSION)) {
			renderViewName = defaultPopupView;

		} else if (request.getRequestURI().indexOf("public/goods") > 0) {// 상품
			renderViewName = goodsView;
		} else if (request.getRequestURI().indexOf("secured/order") > 0) {// 주문
			renderViewName = orderView;
		}
		return renderViewName;
	}

	private String parametersMessage(Map<String, String[]> parameters) {
		if (parameters == null) {
			return null;
		}
		Map<String, String> result = new HashMap<>();
		for (Entry<String, String[]> entry : parameters.entrySet()) {
			String key = entry.getKey();
			String[] values = entry.getValue();

			if (ArrayUtils.isEmpty(values)) {
				result.put(key, "");
			} else if (values.length == 1) {
				result.put(key, values[0]);
			} else {
				result.put(key, ArrayUtils.toString(entry.getValue()));
			}

		}
		return result.toString();
	}
}
