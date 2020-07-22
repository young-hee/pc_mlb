package com.plgrim.ncp.biz.delivery.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryInvoiceResult extends AbstractResult {

	/**
	 * 
	 */
    private static final long serialVersionUID = -7582629838121191678L;
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** 주문번호. */
	private String  ordNo;
	
	/** 클레임번호. */
	private String  clmNo;

	/** 상품코드. */
	private String  godNo;
	
	/** ERP품번. */
	private String  erpGodNo;
	
	/** 상품명. */
	private String godNm;
	
	/** 수량. */
	private String ordQty;
	
	/** 회수수량. */
	private String rdQty;

	/** 판매가. */
	private String csmrPrc;
	
	/** 택배사. */
	private String dlvComCd;
	
	/** 택배사명. */
	private String dlvComNm;
	
	/** 신규 택배사. */
	private String newDlvComCd;
	
	/** 운송장번호. */
	private String dmstcWaybilNo;
	
	/** 신규운송장번호. */
	private String newDmstcWaybilNo;
	
	/** 최종수정일시. */
	private String udtDt;
	
	/** 배송 순번. */
	private String dlvTurn;
	
	/** 배송수거지 순번. */
	private String dlvPcupspTurn;
	
	/** SKU 번호. */
	private String skuNo;
	
	/** 옵션. */
	private String options;
	
	/** 출고지시번호 */
	private String dlivyDrctGodNo;

	/** 회수지시번호 */
	private String rtrvlDrctGodNo;

	/** 판매가. */
	private String saleAmt;
	
	/** 브랜드ID. */
	private String brndId;
	
	/** 브랜드명. */
	private String brndNm;

	/** 클레임 상품 카운트. */
	private int clmWrhsGodCnt;

	/** 교환 출고지시 상품 번호. */
	private String exchgDlivyDrctGodNo;

	/** 교환 출고지시 상품 수량. */
	private String exchgDlivyDrctQty;

	/** 택배사 링크. */
	private String dlvComLink;
	
	/** 배송상태코드 */
	private String dlvStatCd;


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
