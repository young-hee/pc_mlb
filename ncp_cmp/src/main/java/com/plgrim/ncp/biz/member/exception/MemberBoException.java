/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 6. 22       
 */
package com.plgrim.ncp.biz.member.exception;

import com.plgrim.ncp.base.abstracts.AbstractException;


/**
 * 회원 Exception
 * 
 * @author sy59.gim
 * @since 2015. 6. 22
 */
public class MemberBoException extends AbstractException {

	/** UID. */
	private static final long serialVersionUID = 2288482923252850584L;

	/**
	 * Instantiates a new member bo exception.
	 *
	 * @param message [설명]
	 */
	public MemberBoException(String message) {
		super(message);
	}
}
