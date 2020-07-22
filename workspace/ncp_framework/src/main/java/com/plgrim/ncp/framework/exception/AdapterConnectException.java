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
 * @since       2015. 4. 16       
 */
package com.plgrim.ncp.framework.exception;


/**
 * adapter 에서 interface server 접속 오류에 대한 명시적인 exception.
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author jwcale.kim
 * @since 2015. 4. 16
 */
public class AdapterConnectException extends NCPException {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	static final long serialVersionUID = 4984157272671390553L;

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
	 * Instantiates a new adapter connect exception.
	 *
	 * @param params [설명]
	 */
	public AdapterConnectException(String[] params) {
		init(params);
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
