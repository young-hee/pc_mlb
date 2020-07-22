package com.plgrim.ncp.framework.dynamicconfig;

import javax.sql.DataSource;

import org.apache.commons.configuration.SystemConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.config.ConcurrentCompositeConfiguration;
import com.netflix.config.ConcurrentMapConfiguration;
import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicConfiguration;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.sources.JDBCConfigurationSource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DynamicConfigConfig {

	public final static int DEFAULT_POLLING_DELAY_TIMEMILLIS = 1000 * 15;

	/**
	 * DataSource 의존성이 'dataSource1' 이 아니고 'realDataSource'인 이유는, 15초에 한번씩 많게는
	 * 천개 가량의 Row을 select 하여 콘솔에 출력하는것이 개발자에게 큰 불편함이 되기 때문임.
	 * 
	 */
	@Bean
	public Object archauisConfig(@Qualifier("realDataSource") DataSource datasource1) {
		log.info("Archaius dynamic config installation");
		ConcurrentCompositeConfiguration finalConfig = new ConcurrentCompositeConfiguration();
		// 먼저 기술된것이 우선권을 가짐. 따라서 Dynamic config가 system config보다 우선됨
		finalConfig.addConfiguration(dynamicConfig(datasource1), "dynamicConfig");
		finalConfig.addConfiguration(createSystemConfig(), "systemConfig");
		ConfigurationManager.install(finalConfig);
		return finalConfig;
	}

	private ConcurrentMapConfiguration createSystemConfig() {
		ConcurrentMapConfiguration systemConfig = new ConcurrentMapConfiguration(new SystemConfiguration());
		return systemConfig;
	}

	@Bean 
	public DynamicConfiguration dynamicConfig(@Qualifier("realDataSource") DataSource datasource1) {
		FixedDelayPollingScheduler scheduler = new FixedDelayPollingScheduler(10, DEFAULT_POLLING_DELAY_TIMEMILLIS, true);
		String query = "";
		query += " SELECT B.CONFIG_KEY ";
		query += "      , B.CONFIG_VAL ";
		query += " FROM SYS_DYNMC_CONFIG A ";
		query += "     INNER JOIN SYS_DYNMC_CONFIG_DETAIL B ON (A.MALL_ID = B.MALL_ID AND A.CONFIG_NM = B.CONFIG_NM AND A.CONFIG_BEG_DT = B.CONFIG_BEG_DT) ";
		query += " WHERE A.USE_YN = 'Y' ";
		query += "     AND B.USE_YN = 'Y' ";
		query += "     AND SYSDATE >= A.CONFIG_BEG_DT ";
		query += "     AND SYSDATE <= A.CONFIG_END_DT ";
		query += "     AND A.MALL_ID = 'DXM' ";
		DynamicConfiguration dynamicConfig = new DynamicConfiguration(
				new JDBCConfigurationSource(datasource1, query, "CONFIG_KEY", "CONFIG_VAL"), scheduler);
		
		return dynamicConfig;
	}

}
