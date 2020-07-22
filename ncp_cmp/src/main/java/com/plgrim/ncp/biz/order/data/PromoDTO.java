package com.plgrim.ncp.biz.order.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class PromoDTO extends AbstractDTO {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = -1445664540343887682L;

	private String bskNo = null;

	private String godTurn = null;

	private String godNo = null;

	private String goodsDcNo = null;

	private String orderDcNo = null;

	private String goodsGiftNo = null;

	private String orderGiftNo = null;

	private String goodsCouponNo = null;

	private String goodsCouponAmt = null;

	private String orderCouponNo = null;

	private String orderCouponAmt = null;

	private String orderCouponDupl = null;

}
