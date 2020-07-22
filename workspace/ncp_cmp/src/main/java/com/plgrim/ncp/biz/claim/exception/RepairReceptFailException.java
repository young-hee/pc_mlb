package com.plgrim.ncp.biz.claim.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class RepairReceptFailException extends NCPException {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = -4317595988596183065L;

	public RepairReceptFailException(String param) {
		directMessage = param;
	}

	public RepairReceptFailException(String[] params) {
		init(params);
	}

	public RepairReceptFailException(String key, String[] params) {
		init(key, params);
	}

}
