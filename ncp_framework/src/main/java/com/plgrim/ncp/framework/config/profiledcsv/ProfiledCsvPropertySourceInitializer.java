package com.plgrim.ncp.framework.config.profiledcsv;

import com.plgrim.ncp.framework.systems.ApplicationType;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;

@Slf4j
public class ProfiledCsvPropertySourceInitializer
		implements ApplicationContextInitializer<ConfigurableApplicationContext> {
	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		ConfigurableEnvironment env = applicationContext.getEnvironment();
		// TODO 발견되지 않은 설정은 잠재적 오류인데... 기존 설정이 그렇게 구성되어있음.. 추후 설정을 전체 검토하여
		// resolve 되지 않는 설정이 없게 해야함
		env.setIgnoreUnresolvableNestedPlaceholders(true);

		// 먼저 추가된 source가 우선 순위가 낮다. 따라서 ncp_base의 값들은 system의 값이 없을때 읽혀진다
		registerProfiledCsvPropertySource(env, "ncp_base");
		registerProfiledCsvPropertySource(env, applicationId(env));
		registerLocalPropertySource(env);
	}

	private void registerLocalPropertySource(ConfigurableEnvironment env) {
		try {
			Properties props = new Properties();
			props.load(new ClassPathResource("/META-INF/config/local_overwrite.properties").getInputStream());
			PropertiesPropertySource source =new PropertiesPropertySource("local_overwrite", props);
			env.getPropertySources().addFirst(source);
		} catch (IOException e) {
			log.warn("Can not register local overwrite properties source", e);
		}		
	}

	private String applicationId(ConfigurableEnvironment env) {
		ApplicationType app = ApplicationType.decideApplicationType(env);
		switch(app){
		case GF_FRONT_MOBILE:
			return  ApplicationType.NCP_FRONT_MOBILE.getSystemId();
		case GF_FRONT_PC:
			return  ApplicationType.NCP_FRONT_PC.getSystemId();
		default:
			return app.getSystemId();
		}
		
	}

	private void registerProfiledCsvPropertySource(ConfigurableEnvironment env, String systemId) {
		if (ApplicationType.UNKNOWN.getSystemId().equals(systemId)){
			return;
		}
		ProfiledCsvPropertySource propertySource = new ProfiledCsvPropertySource(systemId);
		
		// 현재 사이트 구성상 
		propertySource.activate(env.getActiveProfiles());
		env.getPropertySources().addFirst(propertySource);

	}
 
}
