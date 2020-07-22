package com.plgrim.ncp.commons.features;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.togglz.core.Feature;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.repository.StateRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import scala.actors.threadpool.Arrays;

/**
 * Togglz 의 StateRpository 구현체.
 * 
 * 복수의 StateRepository를 지원하기 위한 Composite 역활을 수행한다
 * 
 * @author narusas
 *
 */
@Data
@Slf4j
public class CompositeFeatureStateRepository implements StateRepository {
	List<FeatureStateRepositoryDelegate> delegates;

	public CompositeFeatureStateRepository(List<FeatureStateRepositoryDelegate> delegates) {
		this.delegates = delegates;
	}

	@Override
	public FeatureState getFeatureState(Feature feature) {
		try {
			for (FeatureStateRepositoryDelegate delegate : delegates) {
				FeatureState state = delegate.getFeatureState(feature);
				log.debug("Check state deleate:{} state:{}", delegate, state);
				if (state != null) {
					return state;
				}
			}
			return null;
		} catch (Exception ex) {
			log.warn("Can nots find fetature state of: " + feature, ex);
			// null 을 반환하면 FeatureManager 에서 EmptyState 를 사용 하게 된다.
			return null;
		}
	}

	@Override
	public void setFeatureState(FeatureState featureState) {
		// DB 에 저장하지는 않는다.
	}

}
