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
 *                       
 */
package com.plgrim.ncp.framework.utils;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.plgrim.ncp.framework.json.NullSerializer;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * jSON 데이터를 마샬링/언마샬링 하는 유틸리티 클래스.
 */
@Slf4j
public final class JsonUtil {

	/**
	 * The Constant LOG.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

	private JsonUtil() {
		LOG.info(JsonUtil.class.getName());
	}

	/**
	 * <p>
	 * 오브젝트를 JSON 형태로 마샬링 한다.
	 * </p>
	 * Marshalling json.
	 * 
	 * @param object
	 *            the object
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public static String marshallingJson(final Object object) {
		return marshallingJson(object, Include.ALWAYS);
	}

	public static String marshallingJson(final Object object, Include... includeOptions) {
		return marshalToJson(false, object, includeOptions);
	}

	/**
	 * 오브젝트를 JSON 데이터로 사람이 보기 편한 형태로 마샬링 한다.
	 * 
	 * @param object
	 *            the object
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public static String marshallingJsonWithPretty(final Object object) {
		return marshallingJsonWithPretty(object, Include.ALWAYS);
	}

	public static String marshallingJsonWithPretty(final Object object, Include... includeOptions) {
		return marshalToJson(true, object, includeOptions);
	}

	/**
	 * 실제로 오브젝트를 Json 텍스트로 마샬링 한다.
	 *
	 * @param pretty
	 *            the pretty
	 * @param object
	 *            the object
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	private static String marshalToJson(boolean pretty, Object object, Include... includeOptions) {

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			/* 20160422 null by abel */
			if (ArrayUtils.isNotEmpty(includeOptions)) {
				for (Include includeOption : includeOptions) {
					objectMapper.setSerializationInclusion(includeOption);
				}
			}

			DefaultSerializerProvider provider = new DefaultSerializerProvider.Impl();
			provider.setNullValueSerializer(new NullSerializer());
			objectMapper.setSerializerProvider(provider);

			// 보기 편하게 출력
			if (pretty) {
				objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			}

			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <p>
	 * JSON 형태의 스트링을 특정 오브젝트로 언마샬링 한다.
	 * </p>
	 * Unmarshalling json.
	 * 
	 * @param jsonText
	 *            the json text
	 * @param valueType
	 *            the value type
	 * @return the t
	 * @throws Exception
	 *             the exception
	 */
	public static <T> T unmarshallingJson(final String jsonText, final Class<T> valueType, DeserializationFeature... features) {

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			if (ArrayUtils.isNotEmpty(features)) {
				for (DeserializationFeature deserializationFeature : features) {
					objectMapper.enable(deserializationFeature);
				}
			}
			return (T) objectMapper.readValue(jsonText, valueType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T unmarshallingJson(final String jsonText, final Class<T> valueType) {
		return unmarshallingJson(jsonText, valueType, DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
				DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
	}

}
