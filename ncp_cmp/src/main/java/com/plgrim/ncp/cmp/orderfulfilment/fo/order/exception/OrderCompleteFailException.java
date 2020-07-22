package com.plgrim.ncp.cmp.orderfulfilment.fo.order.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class OrderCompleteFailException extends NCPException {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = -4317595988596183065L;

	public OrderCompleteFailException(String param) {
		directMessage = param;
	}

	public OrderCompleteFailException(String[] params) {
		init(params);
	}

	public OrderCompleteFailException(String key, String[] params) {
		init(key, params);
	}

}
