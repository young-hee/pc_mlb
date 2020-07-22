package com.plgrim.ncp.commons.features;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.togglz.core.Feature;
import org.togglz.core.repository.FeatureState;

import com.plgrim.ncp.framework.systems.SystemClock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DB 에 저장된 내용에 대한  DTO
 * @author narusas
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabFeatureState {
	Feature feature;
	String strategyId;
	Date startDate;
	Date endDate;
	Boolean isUse;
	Map<String, String> params = new HashMap<>();
	
	
	public FeatureState toTogglzState(SystemClock clock) {
		FeatureState togglzState = new FeatureState(feature, isEnabled(clock));
		togglzState.setStrategyId(strategyId);
		Iterator<Entry<String, String>> it = params.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			togglzState.setParameter(entry.getKey(), entry.getValue());
		}
		return togglzState;
	}
	

	boolean isEnabled(SystemClock clock) {
		Date now = clock.now();
		return isUse 
				&& startDate != null && (startDate.getTime() <= now.getTime()) 
				&& endDate != null && (endDate.getTime() >= now.getTime());
	}
}
