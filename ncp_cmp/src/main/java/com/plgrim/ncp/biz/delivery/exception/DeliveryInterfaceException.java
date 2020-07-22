package com.plgrim.ncp.biz.delivery.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class DeliveryInterfaceException extends NCPException {

    private static final long serialVersionUID = -3178072315784139588L;

	public DeliveryInterfaceException(String[] params) {
        init(params);
    }

}
