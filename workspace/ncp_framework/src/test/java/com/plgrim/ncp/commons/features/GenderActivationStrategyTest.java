package com.plgrim.ncp.commons.features;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.util.NamedFeature;

import com.plgrim.ncp.framework.systems.MockUserContextInjector;
import com.plgrim.ncp.framework.systems.UserContextInfo;
import com.plgrim.ncp.framework.systems.UserGender;

public class GenderActivationStrategyTest extends AbstractStrategyTest {
	private GenderActivationStrategy strategy;

	@Before
	public void setUp() {
		setupDecidedFeatures();
		setupUserInjector();

		strategy = new GenderActivationStrategy();
		feature = new NamedFeature("GENDER_MALE");
		state = new FeatureState(feature);
		state.setStrategyId("gender");
		state.setParameter("gender", "MALE");

		strategy.userInjector = injector;
		strategy.setDecidedFeatures(decidedFeatures);

	}

	void setupGender(UserGender gender) {
		UserContextInfo info = new UserContextInfo();
		info.setGender(gender);
		injector.setInfo(info);
	}

	@Test
	public void 비로그인_회원일경우_성별체크는_불가함() {
		assertFalse(strategy.isActive(state, null));
	}

	@Test
	public void 로그인회원이고_성별정보가_있으면_분석가능해야함() {
		setupGender(UserGender.MALE);
		assertTrue(strategy.isActive(state, null));
	}



}
