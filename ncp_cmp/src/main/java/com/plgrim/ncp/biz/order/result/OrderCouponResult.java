package com.plgrim.ncp.biz.order.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.god.God;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderCouponResult extends AbstractResult {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 947073787812682291L;

	private List<GoodsCouponResult> goodsCouponResultList;

	private BskGod bskGod;

	private God god;

}
