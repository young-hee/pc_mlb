package com.plgrim.ncp.framework.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

/**
 * Properties 파일 정보를 얻는다.
 * <p/>
 * <p/>
 *
 * @author tktaeki.kim
 * @since 2015. 3. 4
 */
@Slf4j
public class ConfigService {

	@Autowired
	private Environment env;

	/* key값에 해당하는 properties 값을 리턴 한다. */
	public String getProperty(String key) {

		if (env == null) {
			log.warn("Environment object is null");
			return null;
		}
		return env.getProperty(key);
	}
}
