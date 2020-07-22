package com.plgrim.ncp.commons.features;

import org.springframework.beans.factory.annotation.Autowired;
import org.togglz.core.activation.Parameter;
import org.togglz.core.activation.ParameterBuilder;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.user.FeatureUser;

import com.plgrim.ncp.framework.systems.UserContextInfo;
import com.plgrim.ncp.framework.systems.UserContextInjector;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class PurchaseTotalAmountActivationStrategy implements ActivationStrategy {

	final static String KEY = "purchaseTotalAmount";

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
		return "구매 금액";
	}

	@Override
	public boolean isActive(FeatureState featureState, FeatureUser user) {
		try {
			UserContextInfo info = userInjector.getCurrentUser();
			if (info == null) {
				return false;
			}
			Integer decidedAmount = decidedAmount(featureState);
			if (decidedAmount == null) {
				return false;
			}
			return testAmount(featureState, decidedAmount);
		} catch (Exception e) {
			log.warn("", e);
			return false;
		}
	}

	private Integer decidedAmount(FeatureState featureState) {
		if (decidedFeatures.hasDecided(KEY)) {
			return (Integer) decidedFeatures.decided(KEY);
		} else {
			Integer amount = fetchAmount(featureState);
			return amount;
		}
	}

	private Boolean testAmount(FeatureState featureState, Integer amount) {
		int targetPurchaseAmountFrom = Integer.parseInt(featureState.getParameter(KEY + "From"));
		int targetPurchaseAmountTo = Integer.parseInt(featureState.getParameter(KEY + "To"));

		Boolean isActive = targetPurchaseAmountFrom <= amount && amount <= targetPurchaseAmountTo;
		return isActive;
	}

	private Integer fetchAmount(FeatureState featureState) {
		String queryFrom = featureState.getParameter(KEY + "QueryFrom");
		String queryTo = featureState.getParameter(KEY + "QueryTo");
		Integer amount = userInjector.getCurrentUserPurchaseAmountHistory(queryFrom, queryTo);
		return amount;
	}

	@Override
	public Parameter[] getParameters() {
		return new Parameter[] { ParameterBuilder.create(KEY).label("총 구매 금액").description("총 구매 금액") };
	}

}
