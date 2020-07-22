package com.plgrim.ncp.biz.order.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class OrderFailException extends NCPException {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = -4317595988596183065L;

	public OrderFailException(String param) {
		directMessage = param;
	}

	public OrderFailException(String[] params) {
		init(params);
	}

	public OrderFailException(String key, String[] params) {
		init(key, params);
	}
}
