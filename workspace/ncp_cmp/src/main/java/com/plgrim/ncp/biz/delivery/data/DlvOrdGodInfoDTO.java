package com.plgrim.ncp.biz.delivery.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class DlvOrdGodInfoDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private String ordNo;
	private String dlvPcupspTurn;	//배송지순번
	private String dlvTurn;				//배송순번(박스)
	private String ordGodTurn;		//주문상품순번	
	private String qty;						//취소수량
	private String dlivyDrctGodNo;				//출고지시 상품순번
	private String adminId;			
	private String clmNo;
	private String clmTpCd;	//교환 철회시
	private String clmStatCd;	//교환 철회시

	private java.lang.Integer clmWrhsGodTurn;	//교환 철회시
	//
	/*
	2015-11-19 : 여태성 - TMALL 요건으로 추가
	*/
//	private String clmWrhsGodTurn;	//클레임 입고상품 순번
	private String gftYn;	//사은품 여부

}
