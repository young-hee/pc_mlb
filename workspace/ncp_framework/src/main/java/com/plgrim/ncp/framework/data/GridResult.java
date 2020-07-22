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
package com.plgrim.ncp.framework.data;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * Grid 전용 Result.
 * @param <T>
 */
@Data
public class GridResult {

	/* 페이지 정보 */
	Map<String, Object> page;
	
	/* 각 row data */
	Collection rows;
}
