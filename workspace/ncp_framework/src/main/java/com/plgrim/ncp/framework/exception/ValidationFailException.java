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
package com.plgrim.ncp.framework.exception;

/**
 * 현재 버전에서 지원하지 않는 기능에 대한 명시적인 exception.
 */
public class ValidationFailException extends NCPException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8348148747792988914L;

	public ValidationFailException(String key,String[] params) {
		init(key,params);
	}
	
}
