package com.plgrim.ncp.commons.features;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;

import com.plgrim.ncp.framework.systems.ResidenceArea;
import com.plgrim.ncp.framework.systems.SystemClock;
import com.plgrim.ncp.framework.systems.UserContextInfo;
import com.plgrim.ncp.framework.systems.UserGender;

public class AgeActivationStrategyTest extends AbstractStrategyTest {
	private AgeActivationStrategy strategy;

	@Before
	public void setUp() {
		setupDecidedFeatures();
		setupUserInjector();
		setupFeature();
		strategy = new AgeActivationStrategy();
		strategy.userInjector = injector;
	}

	void userBirthYearIs(Integer birthyear) {
		injector.setInfo(new UserContextInfo(birthyear, UserGender.MALE,ResidenceArea.서울특별시));
	}

	private void setCurrentDate(int year, int month, int day) {
		SystemClock clock = mock(SystemClock.class);
		when(clock.now()).thenReturn(new Date(year - 1900, month - 1, day));
		strategy.setClock(clock);
	}

	private void expectedAgeRange(int from, int to) {
		state.setParameter("from", String.valueOf(from));
		state.setParameter("to", String.valueOf(to));
	}

	@After
	public void tearDown() {
		SecurityContextHolder.clearContext();
		RequestContextHolder.resetRequestAttributes();
	}

	@Test
	public void 비로그인_회원일경우_연령체크는_불가함() {
		assertFalse(strategy.isActive(state, null));
	}

	@Test
	public void 로그인_회원이고_생년월일정보가_있으면_분석가능해야함() {
		setCurrentDate(2017, 8, 19);
		expectedAgeRange(40, 49);
		userBirthYearIs(1977);
		assertTrue(strategy.isActive(state, null));
	}

	@Test
	public void 연령대가_다르면_실패() {
		setCurrentDate(2017, 8, 19);
		expectedAgeRange(20, 29);
		userBirthYearIs(1977);
		assertFalse(strategy.isActive(state, null));
	}

}
