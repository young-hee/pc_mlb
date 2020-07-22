package com.plgrim.ncp.framework.systems;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plgrim.ncp.framework.utils.CookieUtil;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import lombok.Getter;

@Component
public class ExecutionContext {
	@Autowired
	@Getter
	SystemContext systemContext;

	@Autowired(required = false)
	UserContextInjector userContextInjector;

	public static final String URL = "requestUri";
	public static final String QUERY_STRING = "requestQuery";
	public static final String REFERER = "referer";
	public static final String REMOTE_ADDR = "remoteAddr";
	public static final String REQUEST_ID = "requestId";
	public static final String PROGRAM_ID = "programId";
	public static final String THREAD_ID = "threadId";
	public static final String MEMBER_NO = "mbrNo";
	public static final String LANGUAGE = "lang";
	public static final String MEMBER_TYPE_CODE = "mbrTpCd";
	public static final String USER_SESSION_ID = "sid";
	public static final String USER_AGENT = "userAgent";

	public static final String CLIENT_APP = "clientApp";
	public static final String CLIENT_APP_VERSION = "clientAppVersion";

	static ThreadLocal<RequestInfo> requestInfo = new ThreadLocal<>();

	public static RequestInfo currentRequestInfo() {
		return requestInfo.get();
	}

	public static void setCurrentRequestInfo(RequestInfo info) {
		ExecutionContext.requestInfo.set(info);
	}

	public void inject(HttpServletRequest request, HttpServletResponse response) {

		RequestInfo info = new RequestInfo(request, response, systemContext, userContextInjector);
		requestInfo.set(info);

		request.setAttribute("REQUEST_INFO", info);

		inject(info);

	}

	public static void inject(RequestInfo info) {
		MDC.put(USER_AGENT, info.getUserAgent());
		MDC.put(REMOTE_ADDR, info.getRemoteAddress());

		MDC.put(URL, info.getRequestURI());
		MDC.put(QUERY_STRING, info.getQueryString());
		MDC.put(REFERER, info.getReferer());
		MDC.put(REQUEST_ID, info.getRequestId());
		MDC.put(THREAD_ID, info.getThreadId());

		MDC.put(MEMBER_NO, info.getMemerNo());
		MDC.put(MEMBER_TYPE_CODE, info.getMemberTypeCode());

		MDC.put(USER_SESSION_ID, info.getUserSessionId());
		MDC.put(LANGUAGE, (info.getLanguage() == null ? Language.KOREAN.name() : info.getLanguage().name()));
//		systemContext.setCurrentRequestLanguage(info.getLanguage());

		if (info.checkIsApp()) {
			MDC.put(CLIENT_APP, info.getClientApp());
			MDC.put(CLIENT_APP_VERSION, info.getClientAppVersion());
		}
	}

	public void injectProgramId(HandlerMethod hm) {
		MDC.put(PROGRAM_ID, ProgramId.from(hm));
	}

	@SuppressWarnings("rawtypes")
	public void injectProgramId(Class clazz) {
		MDC.put(PROGRAM_ID, ProgramId.from(clazz));
	}

	public void injectRequestId() {
		MDC.put(REQUEST_ID, systemContext.nextRequestId());
	}

	public void cleanCurrentThread() {
		requestInfo.remove();
		MDC.remove(URL);
		MDC.remove(QUERY_STRING);
		MDC.remove(REFERER);
		MDC.remove(REQUEST_ID);
		MDC.remove(THREAD_ID);
		MDC.remove(REMOTE_ADDR);

		// com.plgrim.ncp.framework.systems.ContextInfoInterceptor 에서 넣은 내용을
		// cleanup한다.
		MDC.remove(PROGRAM_ID);
		MDC.remove(MEMBER_NO);
		MDC.remove(MEMBER_TYPE_CODE);
		MDC.remove(USER_AGENT);
		MDC.remove(LANGUAGE);
		MDC.remove(CLIENT_APP);
		MDC.remove(CLIENT_APP_VERSION);

		// reset to default language
		systemContext.setCurrentRequestLanguage(Language.KOREAN);
	}
}
