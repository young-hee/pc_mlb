package com.plgrim.ncp.biz.order.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderStockDTO extends AbstractDTO {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 2667102634374828525L;

	String itmNo;

	String godNo;

	String onlineLimitYn;

	String reserveYn;

	String basicPackYn;

	String shopId;

	String pckageGodTpCd;

	long itmQty;


}
