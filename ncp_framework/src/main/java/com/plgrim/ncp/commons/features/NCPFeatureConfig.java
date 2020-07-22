package com.plgrim.ncp.commons.features;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.user.FeatureUser;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;

import lombok.Data;

/**
 * Feature Manager를 직접 생성하고 변경하면서 
 * @author narusas
 *
 */
//@Component
@Data
public class NCPFeatureConfig {
//	implements TogglzConfig {
//}
//
//	@Autowired
//	CompositeFeatureStateRepository repository;
//
//	@Override
//	public Class<? extends Feature> getFeatureClass() {
//		return NCPFeatures.class;
//	}
//
//	@Override
//	public StateRepository getStateRepository() {
//		return repository;
//	}
//
//	@Override
//	public UserProvider getUserProvider() {
//		return new UserProvider() {
//			@Override
//			public FeatureUser getCurrentUser() {
//				return new SimpleFeatureUser("admin", true);
//			}
//		};
//	}

}
