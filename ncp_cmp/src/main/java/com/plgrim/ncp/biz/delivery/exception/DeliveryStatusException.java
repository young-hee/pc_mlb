package com.plgrim.ncp.biz.delivery.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class DeliveryStatusException extends NCPException {
    private static final long serialVersionUID = 5129757627899141001L;

	public DeliveryStatusException(String[] params) {
        init(params);
    }

}
