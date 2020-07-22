package com.plgrim.ncp.cmp.orderfulfilment.fo.order.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class OrderValidFailException extends NCPException {

	private static final long serialVersionUID = -7293211356906160107L;

	public OrderValidFailException(String param) {
		directMessage = param;
	}

	public OrderValidFailException(String[] params) {
		init(params);
	}

	public OrderValidFailException(String key, String[] params) {
		init(key, params);
	}

}
