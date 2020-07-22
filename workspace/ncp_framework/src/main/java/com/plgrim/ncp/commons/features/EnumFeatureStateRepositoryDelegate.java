package com.plgrim.ncp.commons.features;

import org.togglz.core.Feature;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.metadata.FeatureMetaData;
import org.togglz.core.repository.FeatureState;

public class EnumFeatureStateRepositoryDelegate implements FeatureStateRepositoryDelegate {

	private EnumBasedFeatureProvider provider;

	public EnumFeatureStateRepositoryDelegate(EnumBasedFeatureProvider provider) {
		this.provider = provider;
	}

	@Override
	public FeatureState getFeatureState(Feature feature) {
		FeatureMetaData metaData = provider.getMetaData(feature);
		// 2017.12.26 
		// 추천등 Legacy Enum은 EnableByDefault가 되어 있지 않기 때문에 아무런 Attribute를 가지지않고, 활성화 여부는 LabService.getFeatureState()를 통해 
		// 확인하게 되어 있으므로 실제적으로는 Enum에 의해 관리되는 Feature가 아니라고 볼수 있다. 
		// 따라서 아무런 attributes를 갖지 않는 feature는 여기에서 대상이 아니다
		// 하지만 현재 추천은 100% 레코픽만 사용하는 상황에서 매번 isActive가 호출되어 ANL 테이블을 조회하게 하는것은 불필요함. 
		// 일단 delegate의  코드를 수정하지만 추후 추천 서비스를 추가하게 되면 추천에 대해 isActive를 호출하는 부분에 대해 전반적으로 확인해봐야 할 필요가 있다. 
		if (metaData == null || metaData.getAttributes() == null || metaData.getAttributes().size() == 0)  {
			return null;
		}
		
		return metaData.getDefaultFeatureState();
	}

}
