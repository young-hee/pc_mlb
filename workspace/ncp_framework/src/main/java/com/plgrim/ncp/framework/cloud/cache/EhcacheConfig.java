package com.plgrim.ncp.framework.cloud.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.support.MBeanServerFactoryBean;

import com.plgrim.ncp.framework.config.PropertyCondition;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.management.ManagementService;

/**
 * spring cache implementation으로 ehcache 사용 (WAS의 in-memory cache)<br> 
 * default로는 개발자의 local envrionment에서만 사용한다.
 * 
 * @author charles <charles@plgrim.com>
 *
 */
@PropertyCondition(value="ncp_base.cache.external.enable", expectCondition=false)
@Configuration
@EnableCaching
@Slf4j
public class EhcacheConfig {
	@Value("${ncp_base.cloud.ehcache.config.location}")
	private String configLocation;
	
	@Bean
	public CacheManager cacheManager(EhCacheManagerFactoryBean ehCacheFactory) {
		log.info("create EhCacheManager");
		
		return new EhCacheCacheManager(ehCacheFactory.getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource(configLocation));
		cmfb.setShared(true);
		return cmfb;
	}
	
	@Bean
	public MBeanExporter exporter() {
		MBeanExporter exporter = new MBeanExporter();
		exporter.setAutodetect(true);
		return exporter;
	}
	
	@Bean
	public ManagementService managementService(EhCacheCacheManager cacheManager) {
		MBeanServerFactoryBean factory = new MBeanServerFactoryBean();
		factory.setLocateExistingServerIfPossible(true);
		factory.afterPropertiesSet();
		
		ManagementService service = new ManagementService(cacheManager.getCacheManager(), factory.getObject(), true, true, true, true);
		service.init();
		
		log.info("create Ehcache MBean ManagementService");
		
		return service;
	}
}
