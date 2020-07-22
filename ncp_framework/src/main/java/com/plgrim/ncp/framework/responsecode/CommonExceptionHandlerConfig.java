package com.plgrim.ncp.framework.responsecode;

import com.google.common.collect.ImmutableSet;
import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.commons.LocaleService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.enums.SecurityParam;
import com.plgrim.ncp.framework.exception.NCPException;
import com.plgrim.ncp.framework.json.ErrorResponseWithArgs;
import com.plgrim.ncp.framework.json.JSendResponse;
import com.plgrim.ncp.framework.json.JsendStatus;
import com.plgrim.ncp.framework.json.ValidationFailResponse;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.plgrim.ncp.framework.systems.ApplicationType;
import com.plgrim.ncp.framework.systems.SystemContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;

/**
 * Spring MVC 컨트롤러를 실행하다 발생하는 오류에 대한 공통적인 처리.
 * 
 * note: 과거에 일반적으로 사용되었던 AbstractHandlerExceptionResolver를 상속하는 방식의
 * ExceptionHandler 처리는 내부적으로 ExceptionHandler 우선순위에 밀려서 BindException등
 * Validation 관련 오류를 처리할수 없기 때문에 ControllerAdvice 를 사용한다
 * 
 * 주의!! Spring MVC(을 포함한 하위 Layer) 에서 Check 할수 있는 오류만 처리된다. Servlet Filter(ex
 * Spring Security)나 BG Thread 등에서 발생하는 오류를 처리하지는 않는다.
 * 
 * 주의!! 공통(Framework, commons)에서 component 로 검색되면 web 관련 컴포넌트가 없는 상태에서 2중 으로
 * 등록되어서 오류가 발생하는 경우가 생김. 따라서 Web Project에서만 인식될수 있게 해야 함
 * 
 */
@Slf4j
@Data
@ControllerAdvice() 
public class CommonExceptionHandlerConfig {
	public final static String EXCEPTION_ATTR = "__exception__";
	public final static String ERROR_MESSAGE_ATTR = "__errorMessage__";
	public final static String FAILED_VALIDATIONS_ATTR = "__failedValidations__";

	public static final String OCCURRED_EXCEPTION_INFORMATION_ATTR = "__OCCURRED_EXCEPTION_INFORMATION";
	public static final String OCCURRED_HTTP_STATUS_CODE_ATTR = "__OCCURRED_HTTP_STATUS_CODE";
	public static final String OCCURRED_FAILED_VALIDATIONS_ATTR = "__OCCURRED_FAILED_VALIDATIONS";

	final static String POPUP_EXTENSION = "popup";

	/* 기본 에러 처리 뷰 이름 */
	final static String DEFAULT_VIEW_NAME = "tiles:errors/generalError";

	/* 기본 팝업 에러 처리 뷰 이름 */
	final static String DEFAULT_POPUP_VIEW_NAME = "tiles:errors/generalPopupError";

	/* 기본 에러 메세지 키 */
	final static String DEFAULT_MESSAGE_KEY = "DefaultException";

	private final String FRONT_LOGIN_URI = "/member/login/view";


	String defaultView;
	String defaultPopupView;
	String goodsView;
	String orderView;

	ResponseCode internalServerErrorCode = CommonResponseCode.SC_50000_시스템_오류가_발생하였습니다;
	ResponseCode validationFailCode = CommonResponseCode.SC_40000_요청한_입력값이_유효하지_않습니다;
	ResponseCode authenticationFailCode = CommonResponseCode.SC_40004_해당_정보에_대한_권한이_없습니다;

	@Autowired(required=false)
	MessageSourceAccessor messageSourceAccessor;

	@Autowired
	SystemContext systemContext;

	@Order(Integer.MAX_VALUE)
	@ExceptionHandler(Throwable.class)
	public Object throwables(Throwable ex,  HttpServletRequest request, HttpServletResponse response)
			throws Throwable {
		log.error("Exception", ex);

        /**
         * FRONT AccessDeniedException 에 대해서만 front login page 로 redirect
         * 이외는 확인 후 error page 이동
         */
        String systemId = systemContext.getAppType().getSystemId();
        if (ex instanceof AccessDeniedException && (systemId.contains("pc") || systemId.contains("mb"))) {
            /**
             * API 호출에 대한 AccessDenied 발생 시 에러코드 403 return
             */
            if(isAPISusffix(request)){
                return new ResponseEntity<HttpStatus>(HttpStatus.FORBIDDEN);
            }

            String requestUri = request.getRequestURI();
            String queryString = request.getQueryString();
            String responseUrl = FRONT_LOGIN_URI;

            if(StringService.isNotEmpty(queryString)) {
            	if(queryString.indexOf(SecurityParam.TARGET_PARAM_NAME.toString()) > -1) {
            		responseUrl += "?"+queryString;
            	}
            	else {
            		// queryString 이 이미 encode 되어 있다고 판단하여 그냥 붙임.
            		// 정상작동하지 않으면 확인 후 encodeURIComponent(queryString) 이렇게 처리 필요.
            		responseUrl += "?"+SecurityParam.TARGET_PARAM_NAME.toString()+"="+requestUri+"?"+queryString;
            	}
            }
            else {
            	responseUrl += "?"+SecurityParam.TARGET_PARAM_NAME.toString()+"="+requestUri;
            }

        	response.sendRedirect(responseUrl);
            return null;
        } else {
            return createModelAndView(request, response, null, ex);
        }
    }

	@ExceptionHandler(BindException.class)
	public Object binds(BindException ex, HandlerMethod handlerMethod, HttpServletRequest request, HttpServletResponse response)
			throws Throwable {
		checkIgnoreCommonExceptionHandler(ex, handlerMethod);

		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		List<FailedValidation> failedValidations = collectFieldErrors(errors);

		return handleValidationException(validationFailCode.toException(ex), handlerMethod, request, failedValidations);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Object methodArgumentNotValid(MethodArgumentNotValidException ex, HandlerMethod handlerMethod, HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		checkIgnoreCommonExceptionHandler(ex, handlerMethod);
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		List<FailedValidation> failedValidations = collectFieldErrors(errors);

		return handleValidationException(validationFailCode.toException(ex), handlerMethod, request, failedValidations);
	}

	/**
	 * Service/Dao/Repository 검증 오류
	 * 
	 * @throws Throwable
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public Object constraintViolation(ConstraintViolationException ex, HandlerMethod handlerMethod, HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		checkIgnoreCommonExceptionHandler(ex, handlerMethod);
		List<FailedValidation> failedValidations = new ArrayList<>();
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		for (ConstraintViolation<?> constraintViolation : violations) {
			String errorType = constraintViolation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
			String field = constraintViolation.getPropertyPath().toString();
			failedValidations.add(new FailedValidation(errorType, field));
		}

		return handleValidationException(validationFailCode.toException(), handlerMethod, request, failedValidations);
	}

	private ModelAndView createModelAndView(HttpServletRequest request, HttpServletResponse response, Object handler, Throwable ex) {
		response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
		if (isJSendResponse((HandlerMethod) handler)) {
			return handlerJsendResponse(request, ex);
		} else {
			return handlerLegacy(request, ex);
		}
	}

	private ModelAndView handlerJsendResponse(HttpServletRequest request, Throwable ex) {
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

	private boolean isValidationException(Throwable ex) {
		return ex instanceof BindException || ex instanceof MethodArgumentNotValidException || ex instanceof ConstraintViolationException;
	}

	private ModelAndView handleValidationFailExceptionToJsend(HttpServletRequest request, Exception ex) {
		List<FailedValidation> errors = null;
		if (ex instanceof BindException) {
			errors = collectFieldErrors(((BindException) ex).getBindingResult().getFieldErrors());
		} else if (ex instanceof MethodArgumentNotValidException) {
			errors = collectFieldErrors(((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors());
		} else if (ex instanceof ConstraintViolationException) {
			errors = new ArrayList<>();
			Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) ex).getConstraintViolations();
			for (ConstraintViolation<?> constraintViolation : violations) {
				String errorType = constraintViolation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
				String field = constraintViolation.getPropertyPath().toString();
				errors.add(new FailedValidation(errorType, field));
			}
		}

		String renderViewName = decideViewName(request, ex);

		ModelAndView mav = new ModelAndView(renderViewName);
		mav.addObject("status", JsendStatus.fail);
		mav.addObject("message", CommonResponseCode.SC_40000_요청한_입력값이_유효하지_않습니다.toRawMessage());
		mav.addObject("code", CommonResponseCode.SC_40000_요청한_입력값이_유효하지_않습니다.getCode());
		mav.addObject("data", ValidationFailResponse.consolidateViolations(errors));

		return mav;
	}

	private ModelAndView handleLegacyExceptionAsJSend(HttpServletRequest request, Throwable ex) {
		String renderViewName = decideViewName(request, ex);

		ModelAndView mav = new ModelAndView(renderViewName);
		mav.addObject("status", JsendStatus.error);
		mav.addObject("message", decideMessage(request, ex));
		mav.addObject("code", CommonResponseCode.SC_50000_시스템_오류가_발생하였습니다.getCode());

		return mav;
	}

	private ModelAndView handlerLegacy(HttpServletRequest request, Throwable ex) {
		String renderViewName = decideViewName(request, ex);

		ModelAndView mav = new ModelAndView(renderViewName);
		mav.addObject("message", decideMessage(request, ex));
		mav.addObject("stacktrace", ExceptionUtils.getStackTrace(ex));

		return mav;
	}

	private String decideMessage(HttpServletRequest request, Throwable ex) {
		if (ex instanceof NCPException) {
			return deviceNcpExceptionMessage(request, ex);
		}
		String key = IOService.getExtension(ex.getClass().getName());
		return findMessage(request, key, null);

	}

	String deviceNcpExceptionMessage(HttpServletRequest request, Throwable ex) {
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
				// case GF_FRONT_PC :
				// case GF_FRONT_MOBILE :
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

	private String decideViewName(HttpServletRequest request, Throwable ex) {
		String renderViewName = DEFAULT_VIEW_NAME;
		// try {
		// 기본 뷰를 설정 하지 않으면 DEFAULT_VIEW_NAME으로 설정 한다.
		if (StringService.isNotEmpty(defaultView)) {
			renderViewName = defaultView;
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

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * CommonExceptionAdvisor 의 로직을 태우지 않고 싶다면, Controller 메소드에
	 * IgnoreCommonExceptionHandler를 붙여서 무시하게 한다.
	 * 
	 * @param ex
	 * @param handlerMethod
	 * @throws Throwable
	 */
	private void checkIgnoreCommonExceptionHandler(Throwable ex, HandlerMethod handlerMethod) throws Throwable {
		if (handlerMethod == null) {
			return;
		}
		IgnoreCommonExceptionHandler ignore = handlerMethod.getMethodAnnotation(IgnoreCommonExceptionHandler.class);
		if (ignore == null) {
			log.debug("Ignore common exception handler");
			return;
		}
		throw ex;
	}

	private Object handleValidationException(Throwable ex, HandlerMethod handlerMethod, HttpServletRequest request,
			List<FailedValidation> failedValidations) {
		ResponseCode errorCode = validationFailCode;
		HttpStatus httpStatus = failStatus();
		return handleException(ex, handlerMethod, request, errorCode, httpStatus, failedValidations);
	}

	private Object handleException(Throwable throwable, HandlerMethod handlerMethod, HttpServletRequest request, ResponseCode errorCode,
			HttpStatus httpStatus, List<FailedValidation> failedValidations) {
		log.error("Exception Parameters when exception throw: {}", parametersMessage(request.getParameterMap()), throwable);

		if (wasViewResponse(handlerMethod, request)) {
			return responseView(throwable, request, httpStatus, failedValidations);
		} else {
			return responseJson(request, errorCode, httpStatus, failedValidations);
		}
	}

	private Object responseJson(HttpServletRequest request, ResponseCode errorCode, HttpStatus httpStatus, List<FailedValidation> failedValidations) {
		request.setAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE, ImmutableSet.of(MediaType.APPLICATION_JSON));
		if (CollectionUtils.isEmpty(failedValidations)) {
			return new ResponseEntity<JSendResponse>(new ErrorResponseWithArgs(errorCode),  httpStatus);
		} else {
			return new ResponseEntity<JSendResponse>(new ValidationFailResponse(errorCode, failedValidations), httpStatus);
		}
	}

	private Object responseView(Throwable throwable, HttpServletRequest request, HttpStatus httpStatus,
			List<FailedValidation> failedValidations) {
		// // 예외를 그대로 넘길 수도 있지만 여러가지 랩핑 시켜서 DTO를 생성하는 방법도 있음
		request.setAttribute(OCCURRED_EXCEPTION_INFORMATION_ATTR, throwable);

		// // 예외에 따라 status코드를 다이나믹하게 변경 필요
		request.setAttribute(OCCURRED_HTTP_STATUS_CODE_ATTR, httpStatus);

		if (CollectionUtils.isNotEmpty(failedValidations)) {
			request.setAttribute(OCCURRED_FAILED_VALIDATIONS_ATTR, failedValidations);
		}

		ModelAndView mav = new ModelAndView(defaultView);

		String renderViewName = defaultView;
		// 팝업일 경우
		if (IOService.getExtension(request.getRequestURI()).equals(POPUP_EXTENSION)) {
			renderViewName = defaultPopupView;

		} else if (request.getRequestURI().indexOf("public/goods") > 0) {// 상품
			renderViewName = goodsView;
		} else if (request.getRequestURI().indexOf("secured/order") > 0) {// 주문
			renderViewName = orderView;
		}

		mav.setViewName(renderViewName);
		String phoneMessage = "CS CENTER : 080-820-8802";

		ApplicationType appType = systemContext.getAppType();

		return mav;
	}

	private boolean wasViewResponse(HandlerMethod handlerMethod, HttpServletRequest request) {
		if (handlerMethod == null || isJSendResponse(handlerMethod) || isResponseBodyAnnotated(handlerMethod) || isAPISusffix(request)) {
			return false;
		}
		return true;
	}

	boolean isJSendResponse(HandlerMethod method) {
		if (method == null) {
			return false;
		}
		MethodParameter returnType = method.getReturnType();
		Type genParam = returnType.getGenericParameterType();
		String typeName = genParam.toString();
//		String typeName = genParam.getTypeName();

		return "org.springframework.http.ResponseEntity<com.plgrim.ncp.framework.json.JSendResponse>".equals(typeName);
		        
	}

	private boolean isAPISusffix(HttpServletRequest request) {
		return StringUtils.endsWithIgnoreCase(request.getRequestURI(), "ajax")
				|| StringUtils.endsWithIgnoreCase(request.getRequestURI(), "json");
	}

	boolean isResponseBodyAnnotated(HandlerMethod handlerMethod) {
		ResponseBody methodAnnotation = handlerMethod.getMethodAnnotation(ResponseBody.class);
		MethodParameter returnType = handlerMethod.getReturnType();
		log.debug("Return type: {}", returnType);
		log.debug("ReturnType type:{}  annotation:{} genericType:{}", returnType.getDeclaringClass(), returnType.getAnnotatedElement(),
				returnType.getGenericParameterType());

		return methodAnnotation != null;
	}

	protected HttpStatus errorStatus() {
		// 일반적으로는 INTERNAL_SERVER_ERROR를 반환하지만 NCP에서는 Service Unavailable을 사용함.
		// 이유는... 알수없음. 누가 결정한 것인지 알수 없음.
		return HttpStatus.SERVICE_UNAVAILABLE;
	}

	protected HttpStatus failStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	public static Exception getStoredExeption(WebRequest webRequest) {
		return (Exception) webRequest.getAttribute(OCCURRED_EXCEPTION_INFORMATION_ATTR, RequestAttributes.SCOPE_REQUEST);
	}

	public static HttpStatus getStoredHttpStatus(WebRequest webRequest) {
		return (HttpStatus) webRequest.getAttribute(OCCURRED_HTTP_STATUS_CODE_ATTR, RequestAttributes.SCOPE_REQUEST);
	}

	@SuppressWarnings("unchecked")
	public static List<FailedValidation> getStoredFailedValidation(WebRequest webRequest) {
		return (List<FailedValidation>) webRequest.getAttribute(OCCURRED_FAILED_VALIDATIONS_ATTR, RequestAttributes.SCOPE_REQUEST);
	}

	public static String encodeURIComponent(String s) {
		String encodeString = null;
		try {
			// 자바스크립
			encodeString = URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\%21", "!").replaceAll("\\%27", "'")
					.replaceAll("\\%28", "(").replaceAll("\\%29", ")").replaceAll("\\%7E", "~");

		} catch (UnsupportedEncodingException e) {
			throw new ResponseException(e);
		}

		return encodeString;
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
