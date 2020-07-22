package com.plgrim.ncp.biz.order.result;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderLinkPriceResult extends AbstractResult{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6475062511242491314L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	private String lpInfo;
    private String mbrNo;                           	//회원 번호
    private String cstmrNm;
    private String ordNo;                           	//주문 번호
    private String godNo;                           	//상품 번호
    private Long  ordQty;
	private BigDecimal stdrCrncyAmt;
	private String categoryCd;
	private String godNm;
    
    /*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
