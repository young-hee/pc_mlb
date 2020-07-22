package com.plgrim.ncp.commons.features;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.activation.ActivationStrategyProvider;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.spi.FeatureProvider;
import org.togglz.core.user.UserProvider;

import com.google.common.collect.ImmutableList;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class TogglzConfiguration {
	@Bean
	@Autowired
	public FeatureManager featureManager(CompositeFeatureStateRepository stateRepository) {
		log.info("Create FeatureManager");
		//@formatter:off
		return FeatureManagerBuilder
			.begin()
				.name("NCP Feature Manager")
				.activationStrategyProvider(activationStrategyProvider())
				.featureProvider(compositeFeatureProvider())
				.userProvider(userProvider())
				.stateRepository(stateRepository)
			.build();
		//@formatter:on
	}

	@SuppressWarnings("unchecked")
	@Bean
	public FeatureProvider compositeFeatureProvider() {
		//@formatter:off
		return new CompositeFeatureProvider()
				.provider(enumBasedFeatureProvider())
				.provider(ncpFeatureProvider())
				;
		//@formatter:on
	}
	
	@Bean
	public EnumBasedFeatureProvider enumBasedFeatureProvider(){
		EnumBasedFeatureProvider provider = new EnumBasedFeatureProvider(NCPFeatures.class);
		return provider;
	}
	
	@Bean
	public EnumFeatureStateRepositoryDelegate enumFeatureStateRepositoryDelegate(){
		return new EnumFeatureStateRepositoryDelegate(enumBasedFeatureProvider());
	}
	
	
	
	@Bean
	@Autowired
	public CompositeFeatureStateRepository compositeFeatureStateRepository(List<FeatureStateRepositoryDelegate> delegates){
		delegates.add(0, enumFeatureStateRepositoryDelegate());
		CompositeFeatureStateRepository repo = new CompositeFeatureStateRepository(delegates);
		return repo;
	}

	@Bean
	public FeatureProvider ncpFeatureProvider() {
		return new NcpFeatureProvider();
	}

	@Bean
	public UserProvider userProvider() {
		return new SimpleUserProvider();
	}

	@Bean
	public ActivationStrategyProvider activationStrategyProvider() {
		//@formatter:off
		return new CollectionActivationStrategyProvider(ImmutableList.<ActivationStrategy>of(
				// togglz-core
				new org.togglz.core.activation.UsernameActivationStrategy(),
				new org.togglz.core.activation.GradualActivationStrategy(),
				new org.togglz.core.activation.ScriptEngineActivationStrategy(),
				new org.togglz.core.activation.ReleaseDateActivationStrategy(),
				new org.togglz.core.activation.ServerIpActivationStrategy(),
				new org.togglz.core.activation.UserRoleActivationStrategy(),
				new org.togglz.core.activation.SystemPropertyActivationStrategy(),

				
				
				// NCP - old : 예전에 만들어져서  ServiceLoader에 의해 Loading 되었기에 Bean 처리 되지 못하는 Strategy
				new com.plgrim.ncp.commons.features.ExclusiveGradualRolloutStrategy(),
//				new com.plgrim.ncp.framework.circuitbreaker.CircuitBreakerFeatureActivationStrategy(),


				// NCP - new: Bean 처리 추가됨
				genderActivationStrategy(),
				ageActivationStrategy(),
				probabilityActivationStrategy(),
				residenceAreaActivationStrategy(),
				purchaseTotalAmountActivationStrategy(),
				purchaseTotalCountActivationStrategy(),
				compositeActivationStrategy()
		));
		//@formatter:on
	}

	@Bean
	public  PurchaseTotalAmountActivationStrategy purchaseTotalAmountActivationStrategy() {
		return new PurchaseTotalAmountActivationStrategy();
	}

	@Bean
	public  PurchaseTotalCountActivationStrategy purchaseTotalCountActivationStrategy() {
		return new PurchaseTotalCountActivationStrategy();
	}

	@Bean
	public  ResidenceAreaActivationStrategy residenceAreaActivationStrategy() {
		return new ResidenceAreaActivationStrategy();
	}

	@Bean
	public CompositeFeaturesActivationStrategy compositeActivationStrategy() {
		return new com.plgrim.ncp.commons.features.CompositeFeaturesActivationStrategy();
	}

	@Bean
	public ProbabilityActivationStrategy probabilityActivationStrategy() {
		return new com.plgrim.ncp.commons.features.ProbabilityActivationStrategy();
	}

	@Bean
	public AgeActivationStrategy ageActivationStrategy() {
		return new com.plgrim.ncp.commons.features.AgeActivationStrategy();
	}

	@Bean
	public GenderActivationStrategy genderActivationStrategy() {
		return new com.plgrim.ncp.commons.features.GenderActivationStrategy();
	}
}
