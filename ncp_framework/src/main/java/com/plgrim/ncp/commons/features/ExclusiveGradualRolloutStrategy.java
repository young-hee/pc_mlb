package com.plgrim.ncp.commons.features;

import com.plgrim.ncp.framework.commons.ContextService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.togglz.core.Feature;
import org.togglz.core.activation.Parameter;
import org.togglz.core.activation.ParameterBuilder;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.user.FeatureUser;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 원래 GradualActivationStrategy 은 사용자중 지정된 percentage 에게 기능을 활성화 하는 기능을 제공한다.
 * 그런데 GradualActivationStrategy의 단점은 각 Feature 별로 확률을 계산 하기 때문에 확률에 따라 여러
 * Feature 가 활성화 될수 있다. 각 개별 Feature 가 독립적으로 확률을 계산하여 활성화가 결정된다.
 * 
 * ExclusiveGradualRolloutStrategy 는 하나의 그룹을 형성하는 Feature 간에서는 단 하나만 활성화 하는 기능을
 * 제공한다. 서로 다른 그룹이라면 동시에 활성화 될수 있다.
 * 
 * @author naru
 *
 */
@Slf4j
@Data
public class ExclusiveGradualRolloutStrategy implements ActivationStrategy {
	public static final String ID = "exclusivegradual";

	public static final String PARAM_PERCENTAGE = "percentage";

	public static final String EXCLUSIVE_KEY = "exclusive";
	//

	Random randomSeed = new Random(System.currentTimeMillis());

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getName() {
		return "배타적 점진적 출시 전략";
	}

	public boolean activate(FeatureState featureState) {
		return isActive(featureState, null);
	}

	@Override
	public boolean isActive(FeatureState featureState, FeatureUser user) {
		ExclusiveState 독점상태 = 세션에_저장된_독점상태(featureState);
		synchronized (독점상태) {
			if (독점상태.is독점되었는가(featureState.getParameter(ExclusiveGradualRolloutStrategy.EXCLUSIVE_KEY))) {
				return 독점상태.다음_기능이_독점했는가(featureState.getFeature());
			}

			if (독점상태.is처리됨(featureState.getFeature())) {
				return false;
			}

			if (출시_대상인가(featureState) == false) {
				독점하지않는다(featureState);
				return false;
			}
			독점한다(featureState);
			return true;
		}
	}

	String sessionKey(String exclusiveGroupKey) {
		return EXCLUSIVE_KEY + "_" + exclusiveGroupKey;
	}

	boolean 출시_대상인가(FeatureState featureState) {
		int percentage = Integer.parseInt(featureState.getParameter(PARAM_PERCENTAGE));
		int random = randomSeed.nextInt(100);
		return random <= percentage;
	}

	void 독점하지않는다(FeatureState featureState) {
		log.info("독점 기능 비활성화:{}", featureState.getFeature().name());
		세션에_저장된_독점상태(featureState).독점하지않기_기록(featureState.getFeature());
	}

	void 독점한다(FeatureState featureState) {
		log.info("독점 기능 활성화:{}", featureState.getFeature().name());
		Feature feature = featureState.getFeature();
		String featureGroup = featureState.getParameter(ExclusiveGradualRolloutStrategy.EXCLUSIVE_KEY);
		세션에_저장된_독점상태(featureState).독점하기_기록(feature, featureGroup);
	}

	synchronized ExclusiveState 세션에_저장된_독점상태(FeatureState featureState) {
		ExclusiveState exclusiveState = (ExclusiveState) getSession().getAttribute(sessionKey(featureState));
		if (exclusiveState == null) {
			exclusiveState = new ExclusiveState();
			exclusiveState.setNewOne(true);
			getSession().setAttribute(sessionKey(featureState), exclusiveState);
		} else {
			exclusiveState.setNewOne(false);
		}
		return exclusiveState;
	}

	String sessionKey(FeatureState featureState) {
		return sessionKey(featureState.getParameter(EXCLUSIVE_KEY));
	}

	HttpSession getSession() {
		return ContextService.getCurrentRequest().getSession();
	}

	@Override
	public Parameter[] getParameters() {
		return new Parameter[] {
				ParameterBuilder.create(PARAM_PERCENTAGE).label("Percentage").matching("\\d{1,3}")
						.description("기능이 활성화 될 사용자의 퍼센테이지(예: 4명중 1명에게 노출하고 싶다면, '25')"),
				ParameterBuilder.create(EXCLUSIVE_KEY).label("배타적 기능 그룹 구분자").description("같은 그룹 구분자를 가지는 기능은 사용자 1명에게 단 하나만 활성화 된다.") };
	}

	public static void setupState(FeatureState state, String 배타적선택그룹_구분자, int percentage) {
		state.setStrategyId(ExclusiveGradualRolloutStrategy.ID);
		state.setParameter(ExclusiveGradualRolloutStrategy.PARAM_PERCENTAGE, String.valueOf(percentage));
		state.setParameter(ExclusiveGradualRolloutStrategy.EXCLUSIVE_KEY, 배타적선택그룹_구분자);
	}
}

@Data
class ExclusiveState {
	Set<Feature> 처리된_features = new HashSet<>();
	Set<Feature> 독점처리된_Feature = new HashSet<>();
	Set<String> 독점처리된_Groups = new HashSet<>();
	boolean isNewOne;

	public void 독점하기_기록(Feature feature, String featureGroup) {

		독점처리된_Feature.add(feature);
		독점처리된_Groups.add(featureGroup);
		처리됨(feature);
	}

	public boolean 다음_기능이_독점했는가(Feature feature) {
		return 독점처리된_Feature.contains(feature);
	}

	public void 독점하지않기_기록(Feature feature) {
		처리됨(feature);
	}

	private void 처리됨(Feature feature) {
		처리된_features.add(feature);
	}

	public boolean is독점되었는가(String featureGroup) {
		return 독점처리된_Groups.contains(featureGroup);
	}

	public boolean is처리됨(Feature feature) {
		return 처리된_features.contains(feature);
	}

}
