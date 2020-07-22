package com.plgrim.ncp.commons.filters.apppush;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.commons.auth.SecurityUserDetail;
import com.plgrim.ncp.framework.systems.SystemClock;
import com.plgrim.ncp.framework.utils.Crypto;
import com.plgrim.ncp.framework.utils.JsonUtil;

@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class AppPushAuthenticationInterceptorTest {

	private AppPushAuthenticationInterceptor interceptor;
	private Crypto crypto;
	private String accessTokenJson;
	private String encryptedAccessToken;
	private String decryptedAccessToken;
	private MockHttpServletRequest req;
	private MockHttpServletResponse res;
	private SystemClock clock;
	private Authentication authentication;
	private String authTokenJson;
	private String encryptedAuthToken;
	private String decryptedAuthToken;
	private SecurityUserDetail userDetails;
	private Mbr mbr;

	@Before
	public void setUp() throws Exception {
		interceptor = new AppPushAuthenticationInterceptor();

		crypto = new Crypto("ncp_apppush");
		interceptor.setCrypto(crypto);

		clock = mock(SystemClock.class);
		interceptor.setClock(clock);

		req = new MockHttpServletRequest();
		res = new MockHttpServletResponse();

		Map<String, String> accessTokenMap = new HashMap() {
			{
				put(AppPushAuthenticationInterceptor.DEVICE_UUID, "A12345");
				put(AppPushAuthenticationInterceptor.TIMESTAMP, "2016-09-20T06:29:35");
			}
		};

		accessTokenJson = JsonUtil.marshallingJson(accessTokenMap);
		encryptedAccessToken = crypto.encrypt(accessTokenJson);
		decryptedAccessToken = crypto.decrypt(encryptedAccessToken);
		assertEquals(accessTokenJson, decryptedAccessToken);

		Map<String, String> authTokenMap = new HashMap() {
			{
				put(AppPushAuthenticationInterceptor.DEVICE_UUID, "A12345");
				put(AppPushAuthenticationInterceptor.TIMESTAMP, "2016-09-20T06:29:35");
				put(AppPushAuthenticationInterceptor.MBR_NO, "MB1234");
			}
		};

		authTokenJson = JsonUtil.marshallingJson(authTokenMap);
		encryptedAuthToken = crypto.encrypt(authTokenJson);
		decryptedAuthToken = crypto.decrypt(encryptedAuthToken);
		assertEquals(authTokenJson, decryptedAuthToken);

		authentication = mock(Authentication.class);
		userDetails = mock(SecurityUserDetail.class);
		mbr = new Mbr();
		when(userDetails.getMbr()).thenReturn(mbr);
		when(authentication.getPrincipal()).thenReturn(userDetails);

		SecurityContext sc = new SecurityContextImpl();
		sc.setAuthentication(authentication);
		SecurityContextHolder.setContext(sc);
	}

//	@Test
//	public void decrypt() throws Exception {
//		System.out.println(JsonUtil.unmarshallingJson(
//				crypto.decrypt(
//						"mf9E2NSlIqt93PudGgc6etEXdNikqyXMMvkCUqlt/JtJu/VyMDNXRueddcidYsG9ZRIDQqnrM7T7q6xwd/YG7EkYu5cl5N0nSFuaOnzgZwvmJUzmyQsm6O3MhN1pIx2b"),
//				Map.class));
//	}
//	
//	@Test
//	public void unmarshall() throws Exception {
//		 String src = "{\"timestamp\":\"2016-11-02T10:40:43\",\"uuid\":\"ffffffff-d6b3-905f-3326-ad630033c587\"}";
//		 System.out.println(JsonUtil.unmarshallingJson(src, Map.class));
//	}

	@SuppressWarnings("deprecation")
	@Test
	public void parseTimestamp() throws ParseException {
		Date timestamp = interceptor.parseTimestamp("2016-09-20T17:29:35");
		assertEquals(2016, timestamp.getYear() + 1900);
		assertEquals(9, timestamp.getMonth() + 1);
		assertEquals(20, timestamp.getDate());
		assertEquals(17, timestamp.getHours());
		assertEquals(29, timestamp.getMinutes());
		assertEquals(35, timestamp.getSeconds());
	}

	@Test
	public void createTimestamp() throws ParseException {
		assertEquals("2016-09-20T17:29:35", interceptor.createTimestamp(new Date(2016 - 1900, 9 - 1, 20, 17, 29, 35)));
	}

	@Test
	public void testHandleAccessToken() throws Exception {
		mbr.setMbrNo("MB1234");
		req.setCookies(new Cookie(AppPushAuthenticationInterceptor.ACCESS_TOKEN_NAME, encryptedAccessToken));
		assertEquals(1, req.getCookies().length);

		// access token 과 1초 차이 남
		when(clock.currentTimeMillis()).thenReturn(interceptor.parseTimestamp("2016-09-20T06:29:36").getTime());

		// auth token 은 39 초에 만들어짐
		when(clock.currentTimeMillis()).thenReturn(interceptor.parseTimestamp("2016-09-20T06:29:39").getTime());

		assertTrue(interceptor.preHandle(req, res, null));

		Cookie[] cookies = res.getCookies();
		assertEquals("Access Token  지워져야 하고,  Auth Token 이 전송되어야 하기 때문에 변경이 발생하는 쿠키는 2개", 2, cookies.length);

		Cookie deletedAccessTokenCookie = cookies[0];
		assertEquals(AppPushAuthenticationInterceptor.ACCESS_TOKEN_NAME, deletedAccessTokenCookie.getName());
		assertEquals("Access token 이 처리된 후에는 더이상 필요 없기 때문에 삭제 되어야 함", 0, deletedAccessTokenCookie.getMaxAge());

		Cookie addedAuthTokenCookie = cookies[1];
		assertEquals(AppPushAuthenticationInterceptor.AUTH_TOKEN_NAME, addedAuthTokenCookie.getName());
		assertEquals("m.plgrimshop 과    push.plgrimshop  간에 적용 되어야 하기 때문에 낮은 도메인을 가져야 함", "plgrimshop.com",
				addedAuthTokenCookie.getDomain());
		assertEquals("세션 만료 쿠키여야 함", -1, addedAuthTokenCookie.getMaxAge());

		Map<String, String> authMap = interceptor.parseToken(addedAuthTokenCookie.getValue());
		assertEquals("A12345", authMap.get(AppPushAuthenticationInterceptor.DEVICE_UUID));
		assertEquals(" auth token 은  39  초에 만들어짐 ", "2016-09-20T06:29:39",
				authMap.get(AppPushAuthenticationInterceptor.TIMESTAMP));
		assertEquals("MB1234", authMap.get(AppPushAuthenticationInterceptor.MBR_NO));
	}

	@Test
	public void accessTokenFromTMS() throws Exception {
		mbr.setMbrNo("MB1234");
		String src = "mf9E2NSlIqt93PudGgc6es8IS2VbnXn/77rErQbEQ0e4ZItD/Tu7kejsrlqaVf2p9IhcyEQbfU8T06wpUK48SBHOLdlg/ehr11jiRWgDBqWgoQ/WY5RQOpwEPY78YiAa";
		System.out.println(crypto.decrypt(src));
		req.setCookies(new Cookie(AppPushAuthenticationInterceptor.ACCESS_TOKEN_NAME, src));

		// access token 과 1초 차이 남
		when(clock.currentTimeMillis()).thenReturn(interceptor.parseTimestamp("2016-09-22T11:19:36").getTime());

		// auth token 은 39 초에 만들어짐
		when(clock.currentTimeMillis()).thenReturn(interceptor.parseTimestamp("2016-09-22T11:19:39").getTime());

		assertTrue(interceptor.preHandle(req, res, null));
		Cookie[] cookies = res.getCookies();
		assertEquals("Access Token  지워져야 하고,  Auth Token 이 전송되어야 하기 때문에 변경이 발생하는 쿠키는 2개", 2, cookies.length);

		Cookie addedAuthTokenCookie = cookies[1];
		assertEquals(AppPushAuthenticationInterceptor.AUTH_TOKEN_NAME, addedAuthTokenCookie.getName());
		assertEquals("m.plgrimshop 과    push.plgrimshop  간에 적용 되어야 하기 때문에 낮은 도메인을 가져야 함", "plgrimshop.com",
				addedAuthTokenCookie.getDomain());
		assertEquals("세션 만료 쿠키여야 함", -1, addedAuthTokenCookie.getMaxAge());

		Map<String, String> authMap = interceptor.parseToken(addedAuthTokenCookie.getValue());
		assertEquals("ffffffff-ffc5-1a96-f069-09890033c587", authMap.get(AppPushAuthenticationInterceptor.DEVICE_UUID));
		assertEquals("auth token 은  39  초에 만들어짐 ", "2016-09-22T11:19:39",
				authMap.get(AppPushAuthenticationInterceptor.TIMESTAMP));
		assertEquals("MB1234", authMap.get(AppPushAuthenticationInterceptor.MBR_NO));
	}

	@Test
	public void testWhenMbrNoIsNull() throws Exception {
		mbr.setMbrNo(null);
		req.setCookies(new Cookie(AppPushAuthenticationInterceptor.ACCESS_TOKEN_NAME, encryptedAccessToken));
		// access token 과 1초 차이 남
		when(clock.currentTimeMillis()).thenReturn(interceptor.parseTimestamp("2016-09-20T06:29:36").getTime());
		assertTrue(interceptor.preHandle(req, res, null));

		Cookie[] cookies = res.getCookies();
		assertEquals("현재 로그인 하지 않은 사용자 이기 때문에 아무런 처리를 하지 않는다.", 0, cookies.length);
	}

	@Test
	public void testHandleAuthToken() throws Exception {
		mbr.setMbrNo("MB1234");
		when(clock.currentTimeMillis()).thenReturn(interceptor.parseTimestamp("2016-09-20T06:29:40").getTime());

		req.setCookies(new Cookie(AppPushAuthenticationInterceptor.AUTH_TOKEN_NAME, encryptedAuthToken));

		assertTrue(interceptor.preHandle(req, res, null));
		assertEquals("Auth Token 의 정보가 문제 없으므로 변화도 없음", 0, res.getCookies().length);
	}

	@Test
	public void testDifferentMbrNo() throws Exception {
		mbr.setMbrNo("MB1234_DIFF");
		when(clock.currentTimeMillis()).thenReturn(interceptor.parseTimestamp("2016-09-20T06:29:40").getTime());

		req.setCookies(new Cookie(AppPushAuthenticationInterceptor.AUTH_TOKEN_NAME, encryptedAuthToken));

		assertTrue(interceptor.preHandle(req, res, null));
		assertEquals("Auth Token 안에 포함된  MBR NO 와 현재 접속한 사용자의  MBR_BO 가 서로 다를 경우  Auth Token 을 삭제한다 ", 1,
				res.getCookies().length);

		Cookie[] cookies = res.getCookies();
		Cookie deletedAuthTokenCookie = cookies[0];
		assertEquals(AppPushAuthenticationInterceptor.AUTH_TOKEN_NAME, deletedAuthTokenCookie.getName());
		assertEquals("Auth token 이  Invalid 하기 때문에 삭제 되어야 함", 0, deletedAuthTokenCookie.getMaxAge());
	}

	@Test
	public void testAccessAndAuthSameTime() throws Exception {
		mbr.setMbrNo("MB1234");

		// 3 시간 경과
		when(clock.currentTimeMillis()).thenReturn(interceptor.parseTimestamp("2016-09-20T09:29:40").getTime());
		when(clock.currentTimeMillis()).thenReturn(interceptor.parseTimestamp("2016-09-20T09:29:40").getTime());

		req.setCookies(new Cookie(AppPushAuthenticationInterceptor.AUTH_TOKEN_NAME, encryptedAuthToken));

		assertTrue(interceptor.preHandle(req, res, null));

		assertEquals("Auth Token 안에 포함된  Timestamp 가  expire 되었기 때문에 새로운  Auth Token 이 발행된다. ", 1,
				res.getCookies().length);

		Cookie[] cookies = res.getCookies();
		Cookie changedAuthTokenCookie = cookies[0];
		assertEquals(AppPushAuthenticationInterceptor.AUTH_TOKEN_NAME, changedAuthTokenCookie.getName());
		assertEquals("m.plgrimshop 과    push.plgrimshop  간에 적용 되어야 하기 때문에 낮은 도메인을 가져야 함", "plgrimshop.com",
				changedAuthTokenCookie.getDomain());
		assertEquals("세션 만료 쿠키여야 함", -1, changedAuthTokenCookie.getMaxAge());

		Map<String, String> map = interceptor.parseToken(changedAuthTokenCookie.getValue());
		assertEquals("MB1234", map.get(AppPushAuthenticationInterceptor.MBR_NO));
		assertEquals("A12345", map.get(AppPushAuthenticationInterceptor.DEVICE_UUID));
		assertEquals("2016-09-20T09:29:40", map.get(AppPushAuthenticationInterceptor.TIMESTAMP));

	}

}
