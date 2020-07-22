package com.plgrim.ncp.commons.features;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashSet;
import java.util.Set;

import org.togglz.core.Feature;
import org.togglz.core.context.FeatureContext;
import org.togglz.core.util.NamedFeature;

import lombok.extern.slf4j.Slf4j;

/**
 * 특정 Feature가 활성화 되어 있는가를 쉽게 테스트 하기 위한 유틸
 * 
 * @author narusas
 *
 */
@Slf4j
public class FeatureUtils {

	public static boolean isActive(String name) {
		checkNotNull(name);
		boolean active = FeatureContext.getFeatureManager().isActive(new NamedFeature(name)); 
		log.debug("Feature: {} Active: {}", name, active);
		return active;
	}

	public static boolean isNotActive(String name) {
		return !isActive(name);
	}

	public static Set<Feature> features() {
		return FeatureContext.getFeatureManager().getFeatures();
	}

	public static Set<Feature> featureWithPrefix(String prefix) {
		Set<Feature> res = new HashSet<>();

		for (Feature feature : features()) {
			if (feature.name().startsWith(prefix)) {
				res.add(feature);
			}
		}
		return res;
	}
	
	public static Set<Feature> featureWithABTest() {
		return featureWithPrefix("AB_");
	}

}
