package com.plgrim.ncp.commons.features;

import org.togglz.core.user.FeatureUser;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;


public class SimpleUserProvider implements UserProvider {

	@Override
	public FeatureUser getCurrentUser() {
		return new SimpleFeatureUser("admin", true);
	}

}
