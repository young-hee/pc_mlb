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
 * beyondj2ee			2015.02.09     
 */
package com.plgrim.ncp.base.abstracts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;
import java.io.Serializable;
/**
 * 최상위 추상 entity 클래스.
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper=false)
public abstract class AbstractEntity implements Serializable, Cloneable{
	// ~ Instance fields. ~~~~~~~~~~~~~~
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2933453273621043676L;

	/** 페이지 시작 row 번호. */
	int beginIndex = 0;
	
	/** 페이지 마지막 row 번호. */
	int endIndex = 0;
	
	/* 몰 아이디 */
	String mallId;
	
	/* 언어코드 */
	String lang;
	
	/* 접속 채널 */
	String channel;
	
	/* 접속 디바이스 */
	String device;
	
	/* 마스킹 여부 */
	String maskingYn;
	
	/* 메뉴 일련번호 */
	Long accessMenuSn;
	
	/* 로그인 ID */
	String loginId;
	
	String userTrackingId;
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	// ~ Implementation Method. ~~~~~~~~
	// ~ Public Methods. ~~~~~~~~~~~~~~~

	/**
	 * convert jsonString to entity class using jackson json parser
	 *
	 * @param jsonString source string of json format
	 * @param type type for serialized object
	 * @return deserized instnace with type
	 */
	public static <T> T fromJSON(String jsonString, Class<T> type)
			throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(jsonString, type);
	}

	/**
	 * convert JSON format string using jackson json
	 *
	 * @return return JSON formatted string
	 */
	public String toJSON() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		String jsonInString;
		try {
			jsonInString = objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			log.warn("error occured during parse to json:" + e.getMessage(), e);
			return "{" + ToStringBuilder.reflectionToString(this) + "}";
		}

		return jsonInString;
	}

	// ~ Private Methods. ~~~~~~~~~~~~~~
	// ~ Getter and Setter. ~~~~~~~~~~~~
}
