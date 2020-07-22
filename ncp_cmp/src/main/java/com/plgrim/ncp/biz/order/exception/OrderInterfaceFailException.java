package com.plgrim.ncp.biz.order.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class OrderInterfaceFailException extends NCPException {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = -4317595988596183065L;

	public OrderInterfaceFailException(String param) {
		directMessage = param;
	}

	public OrderInterfaceFailException(String[] params) {
		init(params);
	}

	public OrderInterfaceFailException(String key, String[] params) {
		init(key, params);
	}
}
