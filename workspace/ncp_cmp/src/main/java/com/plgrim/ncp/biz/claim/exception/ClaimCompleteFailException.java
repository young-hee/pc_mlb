package com.plgrim.ncp.biz.claim.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class ClaimCompleteFailException extends NCPException {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = -4317595988596183065L;

	public ClaimCompleteFailException(String param) {
		directMessage = param;
	}

	public ClaimCompleteFailException(String[] params) {
		init(params);
	}

	public ClaimCompleteFailException(String key, String[] params) {
		init(key, params);
	}
}
