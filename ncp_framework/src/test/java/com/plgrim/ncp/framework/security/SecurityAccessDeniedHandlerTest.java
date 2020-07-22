package com.plgrim.ncp.framework.security;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

import com.plgrim.ncp.framework.enums.SecurityParam;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SecurityAccessDeniedHandlerTest {
	private SecurityAccessDeniedHandler handler;
	private MockHttpServletRequest req;
	private MockHttpServletResponse res;
	private Authentication authentication;
	private SecurityContext securityContext;
	private Collection authorities;
	private GrantedAuthority authority;

	final static String EXPIRE_URL = "/errors/expiredSession";
	final static AuthorizationServiceException AUTH_EXCEPTION = new AuthorizationServiceException("msg");

	@Before
	public void setUP() {
		handler = new SecurityAccessDeniedHandler();
		handler.setExpiredURL(EXPIRE_URL);
//		
//		handler.setMobileDevice("Android");
//		handler.setLoginMbUrl("/public/member/login");
//		handler.setLoginUrl("/public/member/login");
		
		req = new MockHttpServletRequest();
		req.setRequestURI("/secured/mypage/order");
		

		res = new MockHttpServletResponse();

		authentication = Mockito.mock(Authentication.class);
		securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);

		authorities = new ArrayList<GrantedAuthority>();
		authority = Mockito.mock(GrantedAuthority.class);

		Mockito.when(authentication.getAuthorities()).thenReturn(authorities);
	}

	@Test
	public void 리다이렉트_조건이_아닐때() throws IOException, ServletException {
		handler.handle(req, res, AUTH_EXCEPTION);
		assertEquals(HttpServletResponse.SC_FORBIDDEN, res.getStatus());
		assertEquals("msg", res.getErrorMessage());
		assertNull("리다이렉트 되면 않됨", res.getHeader("Location"));
	}

	@Test
	public void CSRF_Expire오류일때() throws IOException, ServletException {
		handler.handle(req, res, new MissingCsrfTokenException("token value"));
		assertEquals("Expire 페이지로 리다이렉트",HttpServletResponse.SC_MOVED_TEMPORARILY, res.getStatus());
		assertEquals("Expire 페이지로 리다이렉트", EXPIRE_URL, res.getHeader("Location"));
	}

	@Test
	public void 로그인한_사용자일때() throws IOException, ServletException {
		Mockito.when(authority.getAuthority()).thenReturn(SecurityParam.DEFAULT_ROLE.toString());
		authorities.add(authority);
		handler.handle(req, res, AUTH_EXCEPTION);
		assertEquals("로그인 한 사용자에게서 발생한 AccessDeny 임", HttpServletResponse.SC_FORBIDDEN, res.getStatus());
		assertEquals("msg", res.getErrorMessage());
		assertNull("리다이렉트 되면 않됨", res.getHeader("Location"));
	}

	@Test
	public void 비회원인증한_사용자일때_모바일() throws IOException, ServletException {
		Mockito.when(authority.getAuthority()).thenReturn(SecurityParam.GUEST_ROLE.toString());
		authorities.add(authority);
		req.addHeader("user-agent", "Mozilla/5.0 (Linux; <Android Version>; <Build Tag etc.>) AppleWebKit/<WebKit Rev> (KHTML, like Gecko) Chrome/<Chrome Rev> Mobile Safari/<WebKit Rev>");
		handler.handle(req, res, AUTH_EXCEPTION);
		assertEquals("비회원이므로 로그인 페이지로 리다이렉트",HttpServletResponse.SC_MOVED_TEMPORARILY, res.getStatus());
		assertEquals("로그인 페이지로 리다이렉트", "/public/member/login?loginTarget=%2Fsecured%2Fmypage%2Forder", res.getHeader("Location"));
	}
	
	@Test
	public void 비회원인증한_사용자일때_PC() throws IOException, ServletException {
		Mockito.when(authority.getAuthority()).thenReturn(SecurityParam.GUEST_ROLE.toString());
		authorities.add(authority);
		req.addHeader("user-agent", "Mozilla/5.0 (Windows NT 6.3; Trident/7.0; .NET4.0E; .NET4.0C; rv:11.0) like Gecko");
		handler.handle(req, res, AUTH_EXCEPTION);
		assertEquals("비회원이므로 로그인 페이지로 리다이렉트",HttpServletResponse.SC_MOVED_TEMPORARILY, res.getStatus());
		assertEquals("로그인 페이지로 리다이렉트", "/public/member/login?loginTarget=%2Fsecured%2Fmypage%2Forder", res.getHeader("Location"));
	}
	
	@Test
	public void 권한없는_사용자일때() throws IOException, ServletException {
		authorities.clear();
		handler.handle(req, res, AUTH_EXCEPTION);
		assertEquals("권한없는 사용자에게서 발생한 AccessDeny 임", HttpServletResponse.SC_FORBIDDEN, res.getStatus());
		assertEquals("msg", res.getErrorMessage());
		assertNull("리다이렉트 되면 않됨", res.getHeader("Location"));
	}

}
