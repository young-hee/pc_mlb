package com.plgrim.ncp.biz.delivery.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class DeliverySelectException extends NCPException {

    private static final long serialVersionUID = -6886453993134737785L;

	public DeliverySelectException(String[] params) {
        init(params);
    }

}
