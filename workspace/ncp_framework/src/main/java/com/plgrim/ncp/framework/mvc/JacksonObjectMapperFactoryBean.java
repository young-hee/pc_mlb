/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 3. 25       
 */
package com.plgrim.ncp.framework.mvc;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.plgrim.ncp.framework.json.NullSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.text.SimpleDateFormat;

/**
 * MappingJackson2JsonView 생성 시 ObjectMapper를 injection 한다.
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author tktaeki.kim
 * @since 2015. 3. 25
 */
public class JacksonObjectMapperFactoryBean {
	public static final Logger logger = LoggerFactory.getLogger(JacksonObjectMapperFactoryBean.class);

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */
	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * MappingJackson2JsonView에서 사용할 ObjectMapper를 생성 한다 .
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @return Object mapper [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 25
	 */
	public static ObjectMapper createObjectMapper() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.ALWAYS);

		DefaultSerializerProvider provider = new DefaultSerializerProvider.Impl();
		provider.setNullValueSerializer(new NullSerializer());
		objectMapper.setSerializerProvider(provider);

		String systemId = "";
		try {
			Context defCtx = (Context) new InitialContext().lookup("java:comp/env");
			systemId = (String) defCtx.lookup("system.id");

			if (!"ncp_web_bo".equals(systemId)) {
				objectMapper.setSerializationInclusion(Include.NON_EMPTY);
			}
		} catch (NamingException e) {
            logger.error("{} NamingException {}", JacksonObjectMapperFactoryBean.class.getSimpleName(), e.getStackTrace());
		}

		/** Bug fix customize (대소문자 변환) */
		objectMapper.configure(MapperFeature.USE_STD_BEAN_NAMING, true);
		objectMapper.setPropertyNamingStrategy(CustomizePropertyNamingStrategy.CASE_TO_CAMEL_CASE);
		/************************************/
		
//		objectMapper.setDateFormat(new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'ZZZ (z)"));
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		return objectMapper;
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
