package com.plgrim.ncp.commons.features;

import org.togglz.core.Feature;
import org.togglz.core.repository.FeatureState;

/**
 * DB 에 대한 접근은 ncp_cmp 이하에서만 가능하기 때문에 의존성 역전을 이용하기 위한 Delegate 인터페이스를 정의한다.
 * 
 * @author narusas
 *
 */
public interface FeatureStateRepositoryDelegate {
	FeatureState getFeatureState(Feature feature);
}
