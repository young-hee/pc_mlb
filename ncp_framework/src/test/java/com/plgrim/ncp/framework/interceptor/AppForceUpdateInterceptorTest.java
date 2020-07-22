package com.plgrim.ncp.framework.interceptor;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.plgrim.ncp.framework.systems.Stage;
import com.plgrim.ncp.framework.systems.SystemContext;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;

/**
 * Created by narusas on 2016. 11. 30..
 */
public class AppForceUpdateInterceptorTest {
	private AppForceUpdateInterceptor interceptor;
	private MockHttpServletRequest req;
	private MockHttpServletResponse res;
	private Object handler;

	@Before
	public void tearDown() {
		interceptor = new AppForceUpdateInterceptor();
		interceptor.systemContext = new SystemContext();
		req = new MockHttpServletRequest();
		res = new MockHttpServletResponse();
		handler = new Object();
		req.setRequestURI("/main");
	}

	@Test
	public void 로컬은_언제나_통과() throws Exception {
		interceptor.systemContext.setStage(Stage.LOCAL);
		assertTrue("로컬은 언제나 통과", interceptor.preHandle(req, res, handler));
	}

	@Test
	public void 앱_스토어_이동_uri일때_통과() throws Exception {
		interceptor.systemContext.setStage(Stage.PRODUCTION);
		req.setRequestURI("/appForceUpdate");
		assertTrue("appForceUpdate일때는 통과", interceptor.preHandle(req, res, handler));
	}

	@Test
	public void userAgent가_null일때_통과() throws Exception {
		interceptor.systemContext.setStage(Stage.PRODUCTION);
		assertTrue("user agent 가 null일때 통과", interceptor.preHandle(req, res, handler));
	}

	@Test
	public void 앱이_아닐때_통과() throws Exception {
		interceptor.systemContext.setStage(Stage.PRODUCTION);
		req.addHeader("user-agent",
				"Mozilla/5.0 (Linux; <Android Version>; <Build Tag etc.>) AppleWebKit/<WebKit Rev> (KHTML, like Gecko) Chrome/<Chrome Rev> Mobile Safari/<WebKit Rev>");
		assertTrue("user agent 에 app_version이 없으면 앱이 아님", interceptor.preHandle(req, res, handler));
	}

	@Test
	public void PLGRIMSHOP_APP_ANDROID_인데_업그레이드_대상이_아닐때() throws Exception {
		interceptor.systemContext.setStage(Stage.PRODUCTION);
		req.addHeader("user-agent",
				"Mozilla/5.0 (Linux; Android 6.0.1; LG-F700L Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/54.0.2840.85 Mobile Safari/537.36/plgrimshop_app//app_version_1.20/");

		assertTrue("버전이 충족되면 다음으로 진행해야 함", interceptor.preHandle(req, res, handler));
	}

	@Test
	public void PLGRIMSHOP_APP_ANDROID_인데_업그레이드_대상일때() throws Exception {
		interceptor.systemContext.setStage(Stage.PRODUCTION);
		req.addHeader("user-agent",
				"Mozilla/5.0 (Linux; Android 6.0.1; LG-F700L Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/54.0.2840.85 Mobile Safari/537.36/plgrimshop_app//app_version_1.10/");
		assertLowerVersion();
	}
	
	@Test
	public void PLGRIMSHOP_APP_IOS_인데_업그레이드_대상이_아닐때() throws Exception {
		interceptor.systemContext.setStage(Stage.PRODUCTION);
		req.addHeader("user-agent",
				"Mozilla/5.0 (iPhone; CPU iPhone OS 10_0_1 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) Mobile/14A403  /plgrimshop_app//app_version_1.10/");

		assertTrue("버전이 충족되면 다음으로 진행해야 함", interceptor.preHandle(req, res, handler));
	}

	@Test
	public void PLGRIMSHOP_APP_IOS_인데_업그레이드_대상일때() throws Exception {
		interceptor.systemContext.setStage(Stage.PRODUCTION);
		req.addHeader("user-agent",
				"Mozilla/5.0 (iPhone; CPU iPhone OS 10_0_1 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) Mobile/14A403  /plgrimshop_app//app_version_1.00/");
		assertLowerVersion();
	}
	
	@Test
	public void _8SECONDS_APP인데_업그레이드_대상이_아닐때() throws Exception {
		interceptor.systemContext.setStage(Stage.PRODUCTION);
		req.addHeader("user-agent",
				"Mozilla/5.0 (iPhone; CPU iPhone OS 10_1_1 like Mac OS X) AppleWebKit/602.2.14 (KHTML, like Gecko) Mobile/14B100  /8seconds_app//app_version_4.10//app_2015/");
		assertTrue("버전이 충족되면 다음으로 진행해야 함", interceptor.preHandle(req, res, handler));
	}
	
	@Test
	public void _8SECONDS_APP인데_업그레이드_대상일때() throws Exception {
		interceptor.systemContext.setStage(Stage.PRODUCTION);
		req.addHeader("user-agent",
				"Mozilla/5.0 (iPhone; CPU iPhone OS 10_1_1 like Mac OS X) AppleWebKit/602.2.14 (KHTML, like Gecko) Mobile/14B100  /8seconds_app//app_version_4.00//app_2015/");
		assertLowerVersion();
	}
	
	@Test
	public void BEANPOLE_APP인데_업그레이드_대상이_아닐때() throws Exception {
		interceptor.systemContext.setStage(Stage.PRODUCTION);
		req.addHeader("user-agent",
				"Mozilla/5.0 (Linux; Android 6.0.1; SM-N915S Build/MMB29K; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/55.0.2883.91 Mobile Safari/537.36/beanpole_app//app_version_2.10/");
		assertTrue("버전이 충족되면 다음으로 진행해야 함", interceptor.preHandle(req, res, handler));
	}
	
	@Test
	public void BEANPOLE_APP인데_업그레이드_대상일때() throws Exception {
		interceptor.systemContext.setStage(Stage.PRODUCTION);
		req.addHeader("user-agent",
				"Mozilla/5.0 (Linux; Android 6.0.1; SM-N915S Build/MMB29K; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/55.0.2883.91 Mobile Safari/537.36/beanpole_app//app_version_2.00/");
		assertLowerVersion();
	}
	
	

	private void assertLowerVersion() throws Exception {
		assertFalse("버전이 충족되지 못하면 진행되지 말아야 함", interceptor.preHandle(req, res, handler));
		assertEquals("/appForceUpdate로 리다이렉트 되어야 함", "/appForceUpdate", res.getHeader("Location"));
		assertEquals("/appForceUpdate로 리다이렉트 되어야 함", HttpServletResponse.SC_MOVED_TEMPORARILY, res.getStatus());
	}
}
