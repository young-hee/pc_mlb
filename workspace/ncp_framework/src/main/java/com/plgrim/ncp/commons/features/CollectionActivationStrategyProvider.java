package com.plgrim.ncp.commons.features;

import java.util.Collections;
import java.util.List;

import org.togglz.core.activation.ActivationStrategyProvider;
import org.togglz.core.spi.ActivationStrategy;

/**
 * ServiceLoader를 사용하는 DefaultActivationStrategyProvider는 Spring Bean을 활용하기가 어렵기
 * 때문에 생성에 관해서는 외부에 위임하고 단순히 목록만 반환하는 Provider를 제공한다
 * 
 * @author narusas
 *
 */
public class CollectionActivationStrategyProvider implements ActivationStrategyProvider {

	List<ActivationStrategy> strategyList;

	public CollectionActivationStrategyProvider(List<ActivationStrategy> list) {
		strategyList = Collections.synchronizedList(list);
	}

	@Override
	public List<ActivationStrategy> getActivationStrategies() {
		return strategyList;
	}

}
