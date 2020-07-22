package com.plgrim.ncp.commons.features;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.togglz.core.Feature;
import org.togglz.core.repository.FeatureState;

import com.netflix.config.DynamicConfiguration;
import com.netflix.config.DynamicPropertyFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * NCP Feature 목록은 DynamicConfig에 의해 얻어온다
 * <p>
 * 다음은 config 목록이다
 * <ul>
 * <li>features.[FEAUTRE-ID].enabled
 * <li>features.[FEAUTRE-ID].strategyId
 * <li>features.[FEAUTRE-ID].parameters.[PARAM_KEY]
 * 
 * 
 * </ul>
 * 
 * @author narusas
 *
 */
@Component
@Slf4j
public class NcpFeatureStateRepositoryDelegate implements FeatureStateRepositoryDelegate {
	@Autowired
	DynamicConfiguration dynamicConfig;

	@Override
	public FeatureState getFeatureState(Feature feature) {
		String prefix = "features." + feature.name() ;
		log.debug("Feature config prefix: {}", prefix);
		Iterator<String> keys = dynamicConfig.getKeys(prefix);
		log.debug("Config Keys: {}", keys);
		FeatureState state = new FeatureState(feature);
		boolean finded = false;
		while (keys.hasNext()) {
			String key = (String) keys.next();
			if (StringUtils.isEmpty(key)) {
				break;
			}
			finded = true;

			if (expect(prefix, "enabled").equals(key)) {
				state.setEnabled(dynamicConfig.getBoolean(key));
			} else if (expect(prefix, "strategyId").equals(key)) {
				state.setStrategyId(dynamicConfig.getString(key).trim());
			}

			else if (key.startsWith(expect(prefix, "parameters."))) {
				String[] tokens = key.split("\\.");
				String paramName = tokens[tokens.length - 1];
				String[] value = dynamicConfig.getStringArray(key);
				state.setParameter(paramName, StringUtils.join(value,","));
			}

		}
		log.debug("Finded: {} State: {}", finded, state);
		return finded ? state : null;
	}

	private String expect(String prefix, String attr) {
		return prefix + "." + attr;
	}

}
