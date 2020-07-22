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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public abstract class NCPException extends RuntimeException { 

	static final long serialVersionUID = 4984157272671390553L;

	/* exception에 직접 출력할 메세지 */
	protected String directMessage;

	/* 메세지 파라미터 */
	protected String[] params;

	protected String customKey;

	public NCPException(String message) {
		super(message);
	}

	public NCPException(String[] messages) {
		super(messages != null && messages.length > 0 ? messages[0] : "Undescribed Excepton");
	}

	public NCPException(Throwable ex) {
		super(ex);
	}

	public NCPException(String message, Throwable ex) {
		super(message, ex);
	}

	/* Exception을 초기화 한다. */
	protected void init(String[] params) {
		this.params = params;
	}

	/* Exception을 초기화 한다. */
	protected void init(String key, String[] params) {
		this.customKey = key;
		this.params = params;
	}

}
