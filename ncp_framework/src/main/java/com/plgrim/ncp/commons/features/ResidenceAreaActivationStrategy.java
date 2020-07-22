package com.plgrim.ncp.commons.features;

import org.springframework.beans.factory.annotation.Autowired;
import org.togglz.core.activation.Parameter;
import org.togglz.core.activation.ParameterBuilder;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.user.FeatureUser;

import com.plgrim.ncp.framework.systems.UserContextInfo;
import com.plgrim.ncp.framework.systems.UserContextInjector;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResidenceAreaActivationStrategy implements ActivationStrategy {

	final static String RESIDENCE_KEY = "residence";

	@Autowired
	UserContextInjector userInjector;

	@Autowired
	DecidedFeatures decidedFeatures;

	@Override
	public String getId() {
		return "residence";
	}

	@Override
	public String getName() {
		return "거주지";
	}

	@Override
	public boolean isActive(FeatureState featureState, FeatureUser user) {
		try {
			UserContextInfo info = userInjector.getCurrentUser();
			if (info == null || info.getArea() == null) {
				return false;
			}
			return info.getArea().getCode().equals(featureState.getParameter(RESIDENCE_KEY));
		} catch (Exception e) {
			log.warn("", e);
			return false;
		}
	}

	@Override
	public Parameter[] getParameters() {
		return new Parameter[] { ParameterBuilder.create(RESIDENCE_KEY).label("주거지").description("주거지 코드") };
	}

}
