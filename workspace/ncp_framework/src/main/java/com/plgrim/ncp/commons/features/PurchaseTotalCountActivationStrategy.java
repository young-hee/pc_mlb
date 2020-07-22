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
public class PurchaseTotalCountActivationStrategy implements ActivationStrategy {

	final static String KEY = "purchaseTotalCount";

	@Autowired
	UserContextInjector userInjector;

	@Autowired
	DecidedFeatures decidedFeatures;

	@Override
	public String getId() {
		return KEY;
	}

	@Override
	public String getName() {
		return "구매 횟수";
	}

	@Override
	public boolean isActive(FeatureState featureState, FeatureUser user) {
		try {
			UserContextInfo info = userInjector.getCurrentUser();
			if (info == null) {
				return false;
			}

			Integer count = decideCount(featureState);
			if (count == null) {
				return false;
			}
			return testCount(featureState, count);

		} catch (Exception e) {
			log.warn("", e);
			return false;
		}
	}

	private Integer decideCount(FeatureState featureState) {
		if (decidedFeatures.hasDecided(KEY)) {
			return (Integer) decidedFeatures.decided(KEY);
		} else {
			Integer count = fetchCount(featureState);
			decidedFeatures.decide(KEY, count);
			return count;
		}

	}

	private Integer fetchCount(FeatureState featureState) {
		String queryFrom = featureState.getParameter(KEY + "QueryFrom");
		String queryTo = featureState.getParameter(KEY + "QueryTo");
		Integer count = userInjector.getCurrentUserPurchaseCountHistory(queryFrom, queryTo);
		return count;
	}

	private Boolean testCount(FeatureState featureState, Integer count) {
		int targetPurchaseCountFrom = Integer.parseInt(featureState.getParameter(KEY + "From"));
		int targetPurchaseCountTo = Integer.parseInt(featureState.getParameter(KEY + "To"));

		Boolean isActive = targetPurchaseCountFrom <= count && count <= targetPurchaseCountTo;
		return isActive;
	}

	@Override
	public Parameter[] getParameters() {
		return new Parameter[] { ParameterBuilder.create(KEY).label("구매 횟수").description("구매 횟수") };
	}
}
