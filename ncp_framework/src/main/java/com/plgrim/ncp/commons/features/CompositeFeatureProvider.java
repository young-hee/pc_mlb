package com.plgrim.ncp.commons.features;

import java.util.HashSet;
import java.util.Set;

import org.togglz.core.Feature;
import org.togglz.core.metadata.FeatureMetaData;
import org.togglz.core.spi.FeatureProvider;

public class CompositeFeatureProvider implements FeatureProvider {

	Set<FeatureProvider> providers = new HashSet<>();

	@Override
	public Set<Feature> getFeatures() {
		Set<Feature> features = new HashSet<>();
		for (FeatureProvider provider : providers) {
			features.addAll(provider.getFeatures());
		}

		return features;
	}

	@Override
	public FeatureMetaData getMetaData(Feature feature) {
		for (FeatureProvider provider : providers) {
			FeatureMetaData metaData = provider.getMetaData(feature);
			if (metaData != null) {
				return metaData;
			}
		}
		return null;
	}

	public CompositeFeatureProvider provider(FeatureProvider provider) {
		providers.add(provider);
		return this;
	}

}
