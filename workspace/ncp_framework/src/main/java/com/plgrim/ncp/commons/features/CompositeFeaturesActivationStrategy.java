package com.plgrim.ncp.commons.features;

import org.apache.commons.lang.StringUtils;
import org.togglz.core.activation.Parameter;
import org.togglz.core.activation.ParameterBuilder;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.user.FeatureUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompositeFeaturesActivationStrategy implements ActivationStrategy {

	@Override
	public String getId() {
		return "composite";
	}

	@Override
	public String getName() {
		return "Composite Feature";
	}

	@Override
	public boolean isActive(FeatureState featureState, FeatureUser user) {
		try {
			
			String[] features = featureState.getParameter("features").split(",");
			log.debug("Features: {}", features);
			String condition = featureState.getParameter("condition");
			if (StringUtils.isEmpty(condition)) {
				condition = "and";
			}
			switch (condition) {
			case "or":
				return or(features);
			case "not or":
				return notOr(features);
			case "not and":
				return notAnd(features);
			default: // and
				return and(features);
			}
		} catch (Exception e) {
			log.warn("", e);
			return false;
		}

	}

	boolean notOr(String[] features) {
		return !or(features);
	}

	boolean notAnd(String[] features) {
		return !and(features);
	}

	boolean or(String[] features) {
		for (String featureName : features) {
			if (FeatureUtils.isActive(featureName.trim())) {
				log.debug("Condition 'or' failed : {}", featureName);
				return true;
			}
		}
		return false;
	}

	boolean and(String[] features) {
		for (String featureName : features) {
			if (FeatureUtils.isNotActive(featureName.trim())) {
				log.debug("Condition 'and' failed : {}", featureName);
				return false;
			}
		}

		return true;
	}


	@Override
	public Parameter[] getParameters() {
		return new Parameter[] { ParameterBuilder.create("features").label("비교할 Feature 목록").description("컴마로 구분된 Feature 목록"),
				ParameterBuilder.create("condition").label("비교 방식").description("기본은 and. 옵션으로 or 을 줄수 있음").optional(),

		};
	}

}
