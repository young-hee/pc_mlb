/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      mc009.park
 * @since       2015. 6. 19       
 */
package com.plgrim.ncp.biz.cart.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;


/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 * <li>[기능1]
 * <li>[기능2]
 * </ul>.
 *
 * @author mc009.park
 * @since 2015. 6. 19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CartBskNoForUpdateResult extends AbstractResult {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8026119736820455342L;
	

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** The bsk cpst god cnnc. */
	//BskCpstGodCnnc bskCpstGodCnnc;

	/** The god no. */
	private String bskNo;

	/** The itm no. */
	private String mallId;

	private String bskTpCd;

	//private God god;

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
