package com.plgrim.ncp.commons.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class OrderPGFailException extends NCPException {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = -4317595988596183065L;

	public OrderPGFailException(String param) {
		directMessage = param;
	}

	public OrderPGFailException(String[] params) {
		init(params);
	}

	public OrderPGFailException(String key, String[] params) {
		init(key, params);
	}
}
