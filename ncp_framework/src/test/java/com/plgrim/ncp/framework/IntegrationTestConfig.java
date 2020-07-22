package com.plgrim.ncp.framework;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;

//@Configuration
public class IntegrationTestConfig {
	
//	@Bean
//	public DataSource dataSource() {
//		BasicDataSource ds = new BasicDataSource();
//		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//		ds.setUrl("jdbc:oracle:thin:@localhost:11522:ORASTG");
//		ds.setUsername("plgrim");
//		ds.setPassword("plgrim2015!");
//		
//		Log4jdbcProxyDataSource wrapper = new Log4jdbcProxyDataSource(ds);
//		return wrapper;
//	}
//	
////	<bean id="dataSource1" class="net.sf.log4jdbc.Log4jdbcProxyDataSource">
////	<constructor-arg ref="realDataSource" />
////	<property name="logFormatter">
////		<bean class="net.sf.log4jdbc.tools.Log4JdbcCustomFormatter">
////			<property name="loggingType" value="MULTI_LINE" />
////			<property name="sqlPrefix" value="SQL:::" />
////		</bean>
////	</property>
////</bean>
//
//
//	@Bean
//	public PlatformTransactionManager transactionManager() {
//		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
//		txManager.setDataSource(dataSource());
//		return txManager;
//	}
//
//	@Bean
//	public JdbcTemplate realJdbcTemplate1() {
//		JdbcTemplate jdbcTemplate = new JdbcTemplate();
//		jdbcTemplate.setDataSource(dataSource());
//		return jdbcTemplate;
//	}
}
