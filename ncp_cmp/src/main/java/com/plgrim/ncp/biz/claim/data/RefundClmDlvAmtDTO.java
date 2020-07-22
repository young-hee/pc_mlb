package com.plgrim.ncp.biz.claim.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class RefundClmDlvAmtDTO extends AbstractDTO {

	/**
	 * 배송지 순번
	 */
	private int dlvPcupspTurn;
	
	/**
	 * 배송지 별 환불금 결제 환율금액
	 */
	private int clmDlvAmt;

	/**
	 * 배송순번 : 2015-12-07 추가 [AshA]
	 */
	private java.lang.Integer dlvTurn;

}
