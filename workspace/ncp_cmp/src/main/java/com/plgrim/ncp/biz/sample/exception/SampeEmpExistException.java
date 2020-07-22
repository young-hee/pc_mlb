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
 * @since       2015. 2. 27       
 */
package com.plgrim.ncp.biz.sample.exception;

import com.plgrim.ncp.framework.exception.NCPException;

/**
 * 사원이 존재 할 경우 SampeEmpExistException을 발생
 * 
 * 
 * <p>
 * 
 *
 * @author tktaeki.kim
 * @since 2015. 2. 27
 */
public class SampeEmpExistException extends NCPException {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2288482923252850584L;

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * Instantiates a new sampe emp exist exception.
	 *
	 * @param message the message
	 */
	public SampeEmpExistException(String message) {
		super(message);
	}
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
