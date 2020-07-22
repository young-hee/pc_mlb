package config.base;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.plgrim.ncp.framework.mybatis.RefreshableSqlSessionFactoryBean;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;

@Configuration
@Profile("integrationTest")
public class BaseIntergrationTestConfig {
	@Bean
	public DataSource dataSource1() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:11522:ORASTG");
		ds.setUsername("plgrim");
		ds.setPassword("plgrim2015!");

		Log4jdbcProxyDataSource wrapper = new Log4jdbcProxyDataSource(ds);
		return wrapper;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		txManager.setDataSource(dataSource1());
		return txManager;
	}

	@Bean
	public JdbcTemplate jdbcTemplate1() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource1());
		return jdbcTemplate;
	}

	@Value("${sql1.resource}")
	String sql1Resource;

	@Bean(name = "sessionFactory1")
	public SqlSessionFactory sessionFactory1() throws Exception {
		RefreshableSqlSessionFactoryBean factory = new RefreshableSqlSessionFactoryBean();
		factory.setDataSource(dataSource1());
		factory.setInterval(5000);
		factory.setTypeAliasesPackage("com.plgrim.ncp.base.entities.datasource1");
		factory.setConfigLocation(new ClassPathResource("META-INF/mybatis/datasource1.mybatis.config.xml"));

		ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
		if (sql1Resource == null || sql1Resource.startsWith("META-INF") == false) {
			sql1Resource = "META-INF/mybatis/datasource1/**/*.xml";
		}

		Resource[] subs = patternResolver.getResources("classpath*:" + sql1Resource);

		Resource[] mappingLocations = new Resource[subs.length + 1];

		mappingLocations[0] = new ClassPathResource("META-INF/mybatis/base.common.xml");

		System.arraycopy(subs, 0, mappingLocations, 1, subs.length);

		factory.setMapperLocations(mappingLocations);

		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Bean(name = "sessionTemplate1")
	public SqlSessionTemplate sessionTemplate1() throws Exception {
		return new SqlSessionTemplate(sessionFactory1());
	}

	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		final PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		return ppc;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
