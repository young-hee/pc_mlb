package com.plgrim.ncp.biz.order.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class CouponDTO extends AbstractDTO {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = -1445664540343887682L;

	private String bskNo = null;

	private Integer godTurn;

	private String godNo = null;

	private String goodsPrmNo = null;

	private String goodsCouponNo = null;

	private String goodsCouponAmt = null;
	private String goodsCouponAmtEx = null;	// 상품쿠폰 환율 금액 2016.02.27

	private String orderCouponNo = null;

	private String orderCouponAmt = null;
	private String orderCouponAmtEx = null; // 주문쿠폰 환율 금액 2016.02.27

	private String orderCouponDupl = null;

	private String goodsSectCd = null;

	private String goodsCouponDcRt = null;

	private String orderSectCd = null;

	private String orderCouponDcRt = null;

	private String maxDcPsbAmt = null;
	private String maxDcPsbAmtEx = null;

	private String orderStdAmt = null;
	private String orderStdAmtEx = null;
}
