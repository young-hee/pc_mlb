package com.plgrim.ncp.commons.features;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.togglz.core.Feature;
import org.togglz.core.metadata.EmptyFeatureMetaData;
import org.togglz.core.metadata.FeatureMetaData;
import org.togglz.core.spi.FeatureProvider;
import org.togglz.core.util.NamedFeature;

import com.netflix.config.DynamicConfiguration;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class NcpFeatureProvider implements FeatureProvider {

	@Autowired
	DynamicConfiguration dynamicConfig;

	@Override
	@Cacheable(value="NcpFeatureProvider.getFeatures")
	public Set<Feature> getFeatures() {
		Set<String> names = uniqNames();
		log.debug("Feature Names: {}", names);

		Set<Feature> features  = new HashSet<>();
		for (String name : names) {
			features.add(new NamedFeature(name));
		}
		return features;
	}

	private Set<String> uniqNames() {
		Set<String> names = new HashSet<>();
		Iterator<String> keys = dynamicConfig.getKeys("features");
		while (keys.hasNext()) {
			String propertyKey = ((String) keys.next()).trim();
			String featureName = propertyKey.split("\\.")[1];
			names.add(featureName);
		}
		return names;
	}

	@Override
	public FeatureMetaData getMetaData(Feature feature) {
		return new EmptyFeatureMetaData(feature);
	}

}
