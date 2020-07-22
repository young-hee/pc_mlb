package com.plgrim.ncp.commons.features;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.togglz.core.activation.Parameter;
import org.togglz.core.activation.ParameterBuilder;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.user.FeatureUser;

public class ProbabilityActivationStrategy implements ActivationStrategy {

	private static final String PROBABILITY_REAL_VALUE = "probability.realValue";

	@Autowired
	DecidedFeatures decidedFeatures;

	Random randomSeed = new Random(System.currentTimeMillis());

	@Override
	public String getId() {
		return "probability";
	}

	@Override
	public String getName() {
		return "확율";
	}

	@Override
	public boolean isActive(FeatureState featureState, FeatureUser user) {
		int realValue = realValue();
		int probabilityFrom = Integer.parseInt(featureState.getParameter("probabilityFrom"));
		int probabilityTo = Integer.parseInt(featureState.getParameter("probabilityTo"));
		return probabilityFrom <= realValue && realValue<= probabilityTo;
	}

	/**
	 * 한번 결정된 real value는 변경 되면 않되기 때문에 session에 저장
	 * 
	 * @return
	 */
	private int realValue() {
		if (decidedFeatures.hasDecided(PROBABILITY_REAL_VALUE)) {
			Integer realValue = (Integer) decidedFeatures.decided(PROBABILITY_REAL_VALUE);
			return realValue.intValue();
		}
		Integer realValue = Integer.valueOf(randomSeed.nextInt(100));
		decidedFeatures.decide(PROBABILITY_REAL_VALUE, realValue);
		return realValue.intValue();
	}

	@Override
	public Parameter[] getParameters() {
		return new Parameter[] { ParameterBuilder.create("probability").matching("\\d+").label("확율").description("지정된 확율을 넘기면 활성화됨")

		};
	}

}
