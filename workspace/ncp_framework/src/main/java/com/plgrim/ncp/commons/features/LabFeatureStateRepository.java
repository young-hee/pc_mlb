package com.plgrim.ncp.commons.features;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.togglz.core.Feature;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.repository.StateRepository;

import com.plgrim.ncp.framework.systems.SystemClock;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Togglz 의 StateRpository 구현체. eclipse 프로젝트의 위치상 DB 에 대한 접근은 ncp_framework 에서는
 * 해서는 않되기 때문에 메커니즘은 여기에서 제공하되 실제 구현은 ncp_cmp 상에 위치하게 한다.
 * 
 * @author narusas
 *
 */
@Component
@Data
@Slf4j
public class LabFeatureStateRepository implements StateRepository {
	@Autowired(required = false)
	LabFeatureStateRepositoryDelegate delegate;

	@Autowired
	SystemClock clock;

	@Override
	public FeatureState getFeatureState(Feature feature) {
		try {
			LabFeatureState state = delegate.getFeatureState(feature);
			if (state == null) {
				return null;
			}
			return state.toTogglzState(clock);
		} catch (Exception ex) {
			log.error("Can not fetch fetature state of: " + feature, ex);
			// null 을 반환하면 FeatureManager 에서 EmptyState 를 사용 하게 된다.
			return null;
		}
	}

	@Override
	public void setFeatureState(FeatureState featureState) {
		// DB 에 저장하지는 않는다.
	}

}
