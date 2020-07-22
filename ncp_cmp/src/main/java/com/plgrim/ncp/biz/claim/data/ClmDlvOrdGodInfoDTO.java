package com.plgrim.ncp.biz.claim.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClmDlvOrdGodInfoDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private String ordNo;			// 주문번호
	private String dlvPcupspTurn;	//배송지순번
	private String dlvTurn;				//배송순번(박스)
	private String dlivyDrctGodNo;				//출고지시 상품순번
	private String ordGodTurn;		//주문상품순번	
	private String qty;						//취소수량
	private String clmResnCd;		//클레임 사유 코드
	private String clmResnCont;		//클레임 사유 코드가 기타일 경우 직접 입력
	private String godNo;						//상품번호
	private String itmNo;						//단품번호

	private String godTpCd;			//주문상품유형
	//
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
