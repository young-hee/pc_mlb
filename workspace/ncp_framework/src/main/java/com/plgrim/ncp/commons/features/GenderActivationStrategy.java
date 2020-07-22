package com.plgrim.ncp.commons.features;

import org.springframework.beans.factory.annotation.Autowired;
import org.togglz.core.activation.Parameter;
import org.togglz.core.activation.ParameterBuilder;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.user.FeatureUser;

import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.systems.UserContextInfo;
import com.plgrim.ncp.framework.systems.UserContextInjector;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class GenderActivationStrategy implements ActivationStrategy {

	final static String GENDER_KEY = "gender";

	@Autowired
	UserContextInjector userInjector;

	@Autowired
	DecidedFeatures decidedFeatures;

	@Override
	public String getId() {
		return GENDER_KEY;
	}

	@Override
	public String getName() {
		return "성별 구분";
	}

	@Override
	public boolean isActive(FeatureState featureState, FeatureUser user) {
		try {
			UserContextInfo info = userInjector.getCurrentUser();
			if (info == null || info.getGender() == null) {
				log.debug("No user info");
				return false;
			}
			log.debug("User info: {}", info);
			log.debug("Given gender: {}, user's gender: {}", featureState.getParameter(GENDER_KEY), info.getGender());
			return info.getGender().name().equals(featureState.getParameter(GENDER_KEY));
		} catch (Exception e) {
			log.warn("", e);
			return false;
		}
	}

	@Override
	public Parameter[] getParameters() {
		return new Parameter[] { ParameterBuilder.create("gender").label("성별").description("대상 성별") };
	}

}
