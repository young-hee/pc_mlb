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
 * @since       2015. 3. 26       
 */
package com.plgrim.ncp.framework.data;

import java.util.Map;

import lombok.Data;

import org.springframework.data.domain.Page;

/**
 * GridView에 전송할 파라미터 오브젝트
 * 
 * <p>.
 *
 * @author tktaeki.kim
 * @since 2015. 3. 26
 */

/**
 * Instantiates a new grid param.
 */
@Data
public class GridParam {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/** Grid 헤더 정보. ','로 구분 */
	String headers;
	
	/** db에 조회한 result 데이터. */
	Page<Map<String, Object>> results;
	
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

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
