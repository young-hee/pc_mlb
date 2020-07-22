package com.plgrim.ncp.framework.systems;

import com.plgrim.ncp.framework.utils.CookieUtil;
import com.plgrim.ncp.framework.utils.RemoteAddrUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Getter
@Setter
@NoArgsConstructor
public class RequestInfo {
	final static String[] APPS = { "Discovery-Expedition"};
	public static final String USER_SESSION_ID_COOKIE_KEY = "sid";
	public static final String LOCALE_COOKIE_NAME = "__NCP_LOCALE__";

	HttpServletRequest request;
	HttpServletResponse response;
	SystemContext systemContext;
	UserContextInjector userContextInjector;

	String remoteAddress;
	String requestURI;
	String queryString;
	String referer;
	String requestId;
	String threadId;
	String userAgent;
	String clientApp;
	String clientAppVersion;
	String memerNo;
	String memberTypeCode;
	String userSessionId;
	private Language language;

	public RequestInfo(HttpServletRequest request, HttpServletResponse response, SystemContext systemContext,
			UserContextInjector userContextInjector) {
		super();
		this.request = request;
		this.response = response;
		this.systemContext = systemContext;
		this.userContextInjector = userContextInjector;
		decide();
	}

	public void decide() {
		decideRemoteAddress();
		decideUrl();
		decideReferer();
		decideRequestId();
		decideThreadId();
		decideMemberNo();
		decideMemberType();
		decideUserSessionId();

		decideLanguage();

		decideUserAgent();

		if (checkIsApp()) {
			decideAppInfo();
		}
	}

	void decideRequestId() {
		this.requestId = systemContext.nextRequestId();
	}

	void decideRemoteAddress() {
		this.remoteAddress = RemoteAddrUtil.getRemoteAddr(request);
	}

	void decideUrl() {
		this.requestURI = request.getRequestURI();
		this.queryString = request.getQueryString();
	}

	void decideReferer() {
		String referer = request.getHeader("Referer");
		if (StringUtils.isBlank(referer)) {
			return;
		}
		this.referer = referer;
	}

	void decideThreadId() {
		this.threadId = String.valueOf(Thread.currentThread().getId());
	}

	void decideMemberNo() {
		Object principal = getCurrentUserPricipal();
		if (principal == null) {
			return;
		}
		String mbrNo = userContextInjector.getCurrentUserId(request, principal);
		if (StringUtils.isNotEmpty(mbrNo)) {
			decideMemberNo(mbrNo);
		}
	}

	void decideMemberType() {
		Object principal = getCurrentUserPricipal();
		if (principal == null) {
			return;
		}
		String mbrTpCd = userContextInjector.getCurrentUserType(request, principal);
		if (StringUtils.isNotEmpty(mbrTpCd)) {
			decideMemberTypeCode(mbrTpCd);
		}
	}

	void decideUserSessionId() {
		String cookie = CookieUtil.getCookieValue(request, USER_SESSION_ID_COOKIE_KEY);
		if (StringUtils.isEmpty(cookie)) {
			cookie = generateUserSessionId(response);
		}
		injectUserSessionId(cookie);
	}

	void decideLanguage() {
		// setup default language
		this.language = Language.KOREAN;

		String locale = CookieUtil.getCookieValue(request, USER_SESSION_ID_COOKIE_KEY);
		if ("en".equals(locale)) {
			this.language = Language.ENGLISH;
		} else if ("en".equals(locale)) {
			this.language = Language.CHINESS;
		}
		systemContext.setCurrentRequestLanguage(language);

	}

	void decideUserAgent() {
		if (StringUtils.isNotEmpty(request.getHeader("User-Agent"))) {
			this.userAgent = request.getHeader("User-Agent");
		}
	}

	boolean checkIsApp() {
		return StringUtils.isNotEmpty(request.getHeader("User-Agent")) && request.getHeader("User-Agent").contains("-App");
	}

	void decideAppInfo() {
		String userAgent = request.getHeader("User-Agent");
		int appVersionIndex = userAgent.indexOf("Ver.");
		String version = userAgent.substring(appVersionIndex + 4, appVersionIndex + 7);

		for (String app : APPS) {
			if (userAgent.contains(app)) {
				this.clientApp = app;
				this.clientAppVersion = version;
				break;
			}
		}
	}

	Object getCurrentUserPricipal() {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return null;
		}
		SecurityContext sc = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		if (sc == null) {
			return null;
		}
		Authentication auth = sc.getAuthentication();
		if (auth == null || ((auth instanceof UsernamePasswordAuthenticationToken) == false
				&& (auth instanceof RememberMeAuthenticationToken) == false)) {
			return null;
		}
		Object principal = auth.getPrincipal();
		return principal;
	}

	String generateUserSessionId(HttpServletResponse response) {
		String uid = systemContext.nextUid();
		CookieUtil.addCookie(response, USER_SESSION_ID_COOKIE_KEY, uid, null, CookieUtil.MAXAGE_SESSION);
		return uid;
	}

	void decideMemberNo(String memberNo) {
		this.memerNo = memberNo;
	}

	void decideMemberTypeCode(String mbrTpCd) {
		this.memberTypeCode = mbrTpCd;
	}

	public void injectUserSessionId(String userSessionId) {
		this.userSessionId = userSessionId;
	}

}
