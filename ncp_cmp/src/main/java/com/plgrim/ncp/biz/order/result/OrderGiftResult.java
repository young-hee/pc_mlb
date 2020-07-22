package com.plgrim.ncp.biz.order.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderGiftResult extends AbstractResult {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = -7503621574082587038L;

	private Prm prm = null;

	private String cartGodNo = null;

	private List<God> giftList = null;

}
