package com.plgrim.ncp.biz.delivery.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class DeliveryUnavailableStatusException extends NCPException {

	private static final long serialVersionUID = -5242516115587730918L;

	public DeliveryUnavailableStatusException(String[] params) {
        init(params);
    }

}
