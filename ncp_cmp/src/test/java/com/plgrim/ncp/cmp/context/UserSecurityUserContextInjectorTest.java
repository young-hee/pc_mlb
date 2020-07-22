package com.plgrim.ncp.cmp.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.framework.systems.UserContextInfo;
import com.plgrim.ncp.framework.systems.UserGender;

public class UserSecurityUserContextInjectorTest {
	private UserSecurityUserContextInjector injector;
	private Mbr mbr;
	private SecurityUserDetail principal;
	private MockHttpServletRequest request;

	@Before
	public void setUp() {
		injector = new UserSecurityUserContextInjector();
		mbr = new Mbr();
		principal = new SecurityUserDetail();
		principal.setMbr(mbr);
		request = new MockHttpServletRequest();

		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		SecurityContext context = new SecurityContextImpl();
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, null);
		context.setAuthentication(auth);

		SecurityContextHolder.setContext(context);
	}

	@After
	public void tearDown() {
		SecurityContextHolder.clearContext();
		RequestContextHolder.resetRequestAttributes();
	}

	@Test
	public void empty() {
		SecurityContextHolder.clearContext();
		UserContextInfo info = injector.getCurrentUser();
		assertTrue(info.isEmpty());
	}

	@Test
	public void parseBirthYear() {
		mbr.setMbrBrthdy("19770601");
		UserContextInfo info = injector.getCurrentUser();
		assertEquals(Integer.valueOf(1977), info.getBirthYear());
	}

	@Test
	public void noBirthday() {
		mbr.setMbrBrthdy(null);
		UserContextInfo info = injector.getCurrentUser();
		assertNull(info.getBirthYear());
	}

	@Test
	public void parseGender() {
		mbr.setMbrSexSectCd("MALE");
		UserContextInfo info = injector.getCurrentUser();
		assertEquals(UserGender.MALE, info.getGender());
	}

	@Test
	public void parseGenderFemale() {
		mbr.setMbrSexSectCd("FEMALE");
		UserContextInfo info = injector.getCurrentUser();
		assertEquals(UserGender.FEMALE, info.getGender());
	}

	@Test
	public void noGender() {
		mbr.setMbrSexSectCd(null);
		UserContextInfo info = injector.getCurrentUser();
		assertNull(info.getGender());
	}

}
