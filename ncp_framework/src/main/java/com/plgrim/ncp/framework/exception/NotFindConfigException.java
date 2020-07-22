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
 * 환경변수 (*.properties)에 값 설정이 존재하지 않을경우 발생.
 */
public class NotFindConfigException extends NCPException {


	/** The Constant serialVersionUID. */
	static final long serialVersionUID = 4984157272671390553L;

	public NotFindConfigException(String[] params) {
		init(params);
	}

}
