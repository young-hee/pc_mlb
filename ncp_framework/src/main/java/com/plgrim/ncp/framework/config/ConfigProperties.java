package com.plgrim.ncp.framework.config;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.plgrim.ncp.framework.utils.JsonUtil;

/**
 * 어플리케이션에서 사용할 config 정보가 실제로 저장될 클래스.
 */
public class ConfigProperties {

	// ~ Instance fields. ~~~~~~~~~~~~~~

	/** The Constant logger. */
	private static final Logger LOG = LoggerFactory
			.getLogger(ConfigProperties.class);

	/** 실제 config 정보가 저정될 properties 객체. */
	private Properties properties;

	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	/**
	 * 생성자 함수.
	 */
	public ConfigProperties() {
		LOG.info("ConfigProperties");
	}

	// ~ Implementation Method. ~~~~~~~~
	// ~ Self Methods. ~~~~~~~~~~~~~~~~~

	/**
	 * 초기화 한다.
	 *
	 * @throws Exception the exception
	 */
	public final void init() throws Exception {
		// 환경 변수 설정 log 출력
		if (this.properties != null) {
			LOG.info("Initialize Config Variables");
			LOG.info(JsonUtil.marshallingJsonWithPretty(this.properties));
		}
	}

	// ~ Getter and Setter
	// ==============================================================================================

	/**
	 * config 정보가 저장된 Properties를 변경 한다 .
	 * 
	 * @param properties
	 *            the new properties
	 */
	public final  void setProperties(final Properties properties) {
		this.properties = properties;
	}

	/**
	 * key를 기준으로 맵핑된 값을 스트링 형태로 리턴 한다.
	 * 
	 * @param key
	 *            the key
	 * @return the string
	 */
	public final String getString(final String key) {

		return (String) this.properties.getProperty(key);
	}
	
	/**
	 * key에 해당하는 값을 Integer 형태로 리턴 한다.
	 * 
	 * @param key
	 *            the key
	 * @return the integer
	 */
	public final Integer getInteger(final String key) {
		String value = (String) this.properties.getProperty(key);
		return new Integer(value);
	}

}
