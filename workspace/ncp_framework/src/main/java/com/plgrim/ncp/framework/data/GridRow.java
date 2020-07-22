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

import java.util.List;

import lombok.Data;

/**
 * [GridResult의 각 row에 해당하는 오브젝트]
 * 
 *
 * @author tktaeki.kim
 * @since 2015. 3. 25
 */
@Data
public class GridRow {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/**/
	/** grid ID. */
	String id;
	
	/* 각 row 별  데이터 (쿼리와 동일한 순번으로 저장 된다.)*/
	List<String> data;
	
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
