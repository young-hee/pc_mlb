package com.plgrim.ncp.commons.features;

import java.util.HashMap;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.util.NamedFeature;

import com.plgrim.ncp.framework.systems.MockUserContextInjector;
import com.plgrim.ncp.framework.systems.UserContextInfo;

public abstract class AbstractStrategyTest {
	protected FeatureState state;
	protected NamedFeature feature;
	protected HashMap<String, Object> decidedFeatureMap;
	protected MockUserContextInjector injector;
	protected DecidedFeatures decidedFeatures;
	protected MockHttpServletRequest request;
	protected MockHttpSession session;
	protected ServletRequestAttributes attr;
	protected UserContextInfo userInfo;

	protected void setupFeature() {
		feature = new NamedFeature("sample");
		state = new FeatureState(feature);
	}

	protected void setupUserInjector() {
		injector = new MockUserContextInjector();
		userInfo = new UserContextInfo();
		injector.setInfo(userInfo);
	}

	protected void setupDecidedFeatures() {
		decidedFeatures = new DecidedFeatures();
		request = new MockHttpServletRequest();
		session = new MockHttpSession();
		decidedFeatureMap = new HashMap<>();
		session.setAttribute("decidedFeatures", decidedFeatureMap);
		request.setSession(session);
		attr = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(attr);
	}

}
