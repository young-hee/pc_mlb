package com.plgrim.ncp.commons.features;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.plgrim.ncp.framework.commons.ContextService;

/**
 * FeatureActivationSterategy에서 값을 저장하고 읽어오는 기능을 제공한다.
 * <p>
 * 현재 사용자에 대해 FeatureActivation을 결정하기전에 앞서 결정된 내용이 있는지 확인할수 있는 Cache 역활을 수행한다
 * <p>
 * 
 * 현재는 HttpSession을 이용하여 값을 저장하고 있다
 * 
 * @author narusas
 *
 */
@SuppressWarnings("rawtypes")
@Component
public class DecidedFeatures {
	static final String DECIDED_FEATURES = "decidedFeatures";

	/**
	 * 주어진 키에 해당하는 값이 이미 결정되어 저장되어 있는지 확인한다.
	 * 
	 * @param key
	 * @return
	 */
	public boolean hasDecided(String key) {
		Object value = loadFromSession().get(key);
		return value != null;
	}

	/**
	 * 주어진 키에 해당하는 이미 결정된 값을 반환한다.
	 * 
	 * @param key
	 * @return
	 */
	public Object decided(String key) {
		Map decidedFeaturesMap = loadFromSession();
		if (decidedFeaturesMap == null) {
			return null;
		}
		return decidedFeaturesMap.get(key);
	}

	/**
	 * 결정된 값을 저장한다
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public <T> T decide(String key, T value) {
		loadFromSession().put(key, value);
		return value;
	}

	@SuppressWarnings("unchecked")
	protected Map<String, Object> loadFromSession() {
		HttpServletRequest request = ContextService.getCurrentRequest();

		Map<String, Object> decidedFeaturesMap = (Map<String, Object>) request.getSession(false).getAttribute(DECIDED_FEATURES);
		if (decidedFeaturesMap == null) {
			decidedFeaturesMap = new HashMap<String, Object>();
			request.getSession(false).setAttribute(DECIDED_FEATURES, decidedFeaturesMap);
		}
		return decidedFeaturesMap;
	}

}
