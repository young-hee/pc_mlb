package com.plgrim.ncp.commons.features;

import org.springframework.beans.factory.annotation.Autowired;
import org.togglz.core.activation.Parameter;
import org.togglz.core.activation.ParameterBuilder;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.user.FeatureUser;

import com.plgrim.ncp.framework.systems.SystemClock;
import com.plgrim.ncp.framework.systems.SystemClockImpl;
import com.plgrim.ncp.framework.systems.UserContextInfo;
import com.plgrim.ncp.framework.systems.UserContextInjector;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class AgeActivationStrategy implements ActivationStrategy {
	@Autowired
	UserContextInjector userInjector;

	SystemClock clock = new SystemClockImpl();

	@Override
	public String getId() {
		return "age";
	}

	@Override
	public String getName() {
		return "연령대";
	}

	@Override
	public boolean isActive(FeatureState featureState, FeatureUser user) {
		return new AgeCheck(featureState, userInjector, clock).check();
	}

	@Override
	public Parameter[] getParameters() {
		// @formatter:off
		return new Parameter[] {
				ParameterBuilder.create("from").matching("\\d+").label("나이대 시작 연령").description("나이대가 시작하는 연령"),
				ParameterBuilder.create("to").matching("\\d+").label("나이대 종료 연령").description("나이대가 종료하는 연령"),

		};
		// @formatter:on
	}

}

@Slf4j
class AgeCheck {

	private FeatureState featureState;
	private Integer birthYear;
	private int age;
	private SystemClock clock;
	private UserContextInjector userInjector;

	public AgeCheck(FeatureState featureState, UserContextInjector userInjector, SystemClock clock) {
		this.featureState = featureState;
		this.userInjector = userInjector;
		this.clock = clock;
	}

	public boolean check() {
		try {
			if (canReadCurrentUserInfo() == false) {
				return Boolean.FALSE;
			}

			calcAge();
			return canApplyAgeRange();
		} catch (Exception ex) {
			// Feature Toggle으로 인한 오류로 인해 프로그램 수행이 중단되어선 않됨
			log.warn("", ex);
			return false;
		}
	}

	private boolean canReadCurrentUserInfo() {
		UserContextInfo info = userInjector.getCurrentUser();
		if (info == null || info.getBirthYear() == null) {
			return false;
		}

		birthYear = info.getBirthYear();
		if (birthYear == null) {
			return false;
		}
		return true;
	}

	// TODO 한국 나이를 사용할지 법정 나이를 사용할지? 일단 법정 나이인 현재 연도 - 출생년도 를 사용함
	void calcAge() {
		age = clock.now().getYear() + 1900 - birthYear;
	}

	boolean canApplyAgeRange() {
		int from = Integer.parseInt(featureState.getParameter("from"));
		int to = Integer.parseInt(featureState.getParameter("to"));
		return from <= age && age <= to;
	}

}