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
 *  beyondj2ee			2015.02.09                     
 */
package com.plgrim.ncp.base.abstracts;

/**
 * Exception 추상 클래스.
 */
public abstract class AbstractException extends RuntimeException {
	// ~ Instance fields. ~~~~~~~~~~~~~~
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4984157272671390553L;
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	/**
	 * 생성자 함수.
	 *
	 * @param message the message
	 */
	protected AbstractException (String message) {
        super(message);
    }
	
}
