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

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * 최상위 추상 Result 클래스.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public abstract class AbstractResult implements Serializable {

	// ~ Instance fields. ~~~~~~~~~~~~~~
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -883497970625399057L;
	
	/** 페이지 요청 오브젝트. */
	Pageable pageable = new PageRequest(0, 30);
	
	/** row 순번. */
	Long rowNumber;
	
	/* 몰 아이디 */
	String mallId;
	
	/* 언어코드 */
	String lang;
	
	/* 접속 채널 */
	String channel;
	
	/* 접속 디바이스 */
	String device;
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	// ~ Implementation Method. ~~~~~~~~
	// ~ Public Methods. ~~~~~~~~~~~~~~~
	// ~ Private Methods. ~~~~~~~~~~~~~~
	// ~ Getter and Setter. ~~~~~~~~~~~~
}
