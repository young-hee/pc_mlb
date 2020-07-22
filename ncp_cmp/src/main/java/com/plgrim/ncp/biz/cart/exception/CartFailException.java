package com.plgrim.ncp.biz.cart.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class CartFailException extends NCPException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8085084887384485922L;

	public CartFailException(String param) {
		directMessage = param;
	}

	public CartFailException(String[] params) {
		init(params);
	}

	public CartFailException(String key, String[] params) {
		init(key, params);
	}
}
