/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 * beyondj2ee 			2015.02.16                     
 */
package com.plgrim.ncp.framework.mvc;

import java.util.Locale;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.plgrim.ncp.framework.json.NullSerializer;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * Json 전용 뷰 리졸버.
 */
@Slf4j
public class JsonViewResolver implements ViewResolver, Ordered {

	
	private int order = Integer.MAX_VALUE; // default: same as non-Ordered

	public void setOrder(int order) {
		this.order = order;
	}

	public int getOrder() {
		return order;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.ViewResolver#resolveViewName(java.lang
	 * .String, java.util.Locale)
	 */
	@Override
	public View resolveViewName(String viewName, Locale locale)
	        throws Exception {

		log.debug("############" + viewName);
		MappingJackson2JsonView view = new MappingJackson2JsonView();

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.ALWAYS);

		DefaultSerializerProvider provider = new DefaultSerializerProvider.Impl();
		provider.setNullValueSerializer(new NullSerializer());
		objectMapper.setSerializerProvider(provider);

		view.setObjectMapper(objectMapper);

		return view;
	}

}
