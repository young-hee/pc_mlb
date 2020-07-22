package com.plgrim.ncp.commons.filters.apppush;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.plgrim.ncp.framework.systems.SystemClock;
import com.plgrim.ncp.framework.systems.UserContextInjector;
import com.plgrim.ncp.framework.utils.CookieUtil;
import com.plgrim.ncp.framework.utils.Crypto;
import com.plgrim.ncp.framework.utils.JsonUtil;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 휴머스온의 tms 앱 푸시 솔루션에 현재 접속자에 대한 회원 구분키를 전달하기 위한 인터셉터
 * 
 * tms apppush 서버에서 전달한 Access 토큰을 해석, 검증 성공시 현재 로그인한 사용자의 정보를 추가하여 Auth Token 을
 * 발행한다
 * 
 * 가로채는 기능이라 Servlet Filter 와 Spring Interceptor 두개의 선택지가 있는데, 로그인 사용자에 대한 정보를
 * SecurityContextHolder 에서 얻을수 있기 때문에 Interceptor 를 선택하였음.
 * 
 * @author narusas
 *
 */
@Data
@Slf4j
@SuppressWarnings("unchecked")
public class AppPushAuthenticationInterceptor implements HandlerInterceptor {
	public static final String ACCESS_TOKEN_NAME = "apppushaccess";
	public static final String AUTH_TOKEN_NAME = "apppushauth";
	public static final String DEVICE_UUID = "uuid";
	public static final String TIMESTAMP = "timestamp";
	public static final String MBR_NO = "mbrNo";

	long accessTokenExpireTime = 1000 * 60 * 30;
	long authTokenExpireTime = 1000 * 60 * 5;

	String cookieDomain;

	@Autowired(required = false)
	UserContextInjector userContextInjector;

	@Autowired
	Crypto crypto;

	@Autowired
	SystemClock clock;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			String accessToken = CookieUtil.getCookieValue(request, ACCESS_TOKEN_NAME);
			String authToken = CookieUtil.getCookieValue(request, AUTH_TOKEN_NAME);
			if (StringUtils.isEmpty(accessToken) && StringUtils.isEmpty(authToken)) {
				return true;
			}
			if (StringUtils.isNotEmpty(accessToken)) {
				handleAccessToken(request, response, accessToken);
			} else if (StringUtils.isNotEmpty(authToken)) {
				handleAuthToken(request, response, authToken);
			}
			return true;
		} catch (Exception e) {
			log.warn("Fail to handle apppush token", e);
			// app push access token 처리가 컨트롤러 진입을 막아서는 안됨
			return true;
		}
	}

	void handleAccessToken(HttpServletRequest request, HttpServletResponse response, String accessToken)
			throws Exception, ParseException {
		log.info("Handle Access Token: {}", accessToken);

		Map<String, String> map = parseToken(accessToken);
		log.debug("Parsed map: {}", map);
		Date timestamp = parseTimestamp(map.get(TIMESTAMP));
		log.debug("Parsed timestamp: {}", timestamp);
		if (isExpiredTimestamp(timestamp)) {
			log.warn("Expired apppush access token timestamp" + timestamp);
			return;
		}
		String mbrNo = getMemberIdentifier(request);
		log.debug("Matched mbrNo: {}", mbrNo);
		if (mbrNo == null || "anonymousUser".equals(mbrNo)) {
			log.debug("Not yet login. nothing to do");
			return;
		}

		log.info("Success to handle access token. matchd mbrNo: '{}'", mbrNo );

		CookieUtil.deleteCookie(response, ACCESS_TOKEN_NAME);
		map.put(MBR_NO, mbrNo);
		map.put(TIMESTAMP, createTimestamp(new Date(clock.currentTimeMillis())));
		log.debug("Created map: {}", map);
		CookieUtil.addCookie(response, AUTH_TOKEN_NAME, createToken(map), cookieDomain, -1); // session
	}

	String getMemberIdentifier(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return null;
		}
		SecurityContext sc = (SecurityContext) session
				.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		if (sc == null) {
			return null;
		}
		Authentication auth = sc.getAuthentication();
		if (auth == null || ((auth instanceof UsernamePasswordAuthenticationToken) == false
				&& (auth instanceof RememberMeAuthenticationToken) == false)) {
			return null;
		}
		Object principal = auth.getPrincipal();
		String mbrNo = userContextInjector.getCurrentUserId(request, principal);
		return mbrNo;
	}

	void handleAuthToken(HttpServletRequest request, HttpServletResponse response, String authToken) throws Exception {
		log.info("Handle auth token: {}", authToken);
		Map<String, String> map = parseToken(authToken);
		String mbrNo = getMemberIdentifier(request);
		if (mbrNo == null || map.get(MBR_NO) == null) {
			log.debug("No member info available. Invalidate auth token");
			CookieUtil.deleteCookie(response, AUTH_TOKEN_NAME);
			return;
		}

		if (mbrNo.equals(map.get(MBR_NO)) == false) {
			log.debug("Given auth token may be stolen. Invalidate auth token");
			CookieUtil.deleteCookie(response, AUTH_TOKEN_NAME);
			return;
		}

		Date oldTimestamp = parseTimestamp(map.get(TIMESTAMP));
		if (isAuthTokenExpired(oldTimestamp)) {
			log.debug("Everything is ok.");
			return;
		}

		map.put(TIMESTAMP, createTimestamp(new Date(clock.currentTimeMillis())));
		CookieUtil.addCookie(response, AUTH_TOKEN_NAME, createToken(map), cookieDomain, -1); // session

	}

	private boolean isAuthTokenExpired(Date oldTimestamp) {
		return clock.currentTimeMillis() - oldTimestamp.getTime() > authTokenExpireTime;
	}

	boolean isExpiredTimestamp(Date timestamp) {
		return clock.currentTimeMillis() - timestamp.getTime() > accessTokenExpireTime;
	}

	Date parseTimestamp(String timestampStr) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(timestampStr);
	}

	String createTimestamp(Date date) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date);
	}

	Map<String, String> parseToken(String accessToken) throws Exception {
		try {
			log.debug("Given Token: {}", accessToken);
			String decryptedToken = crypto.decrypt(accessToken);
			log.debug("Descrypted Token: {}",decryptedToken );
			return JsonUtil.unmarshallingJson(decryptedToken, Map.class);
		} catch (Exception ex) {
			log.error("Fail to parse token " + accessToken, ex);
			throw ex;
		}
	}

	@SuppressWarnings("rawtypes")
	String createToken(Map map) throws Exception {
		return crypto.encrypt(JsonUtil.marshallingJson(map));
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}
