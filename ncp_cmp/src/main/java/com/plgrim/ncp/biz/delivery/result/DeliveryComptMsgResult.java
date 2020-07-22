package com.plgrim.ncp.biz.delivery.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DeliveryComptMsgResult extends AbstractResult {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private String ordNo;	        // 주문번호
	private String godNm;			// 상품명
	private String url;				// 택배사 url
	private String mallId;	        // mallID
	private String brndNm;			// 브랜드명
	private String mobile;			// 고객 모바일 전화번호
	private String smsRecptnSectCd;	// UXUI개선: SMS수신구분
	private String ordmanMobileNo;	// UXUI개선: 주문자전화번호
	private String addrseMobileNo;	// UXUI개선: 수령자전화번호
	private String godSumry;	    // summary
	private String mbrNo;	        // 고객번호
//	private String ordTpCd;	        // 주문 유형
//	private String ordGodTurn;	    // 주문 순번
//	private String ordCnt;	        // 주문상품수량
//	private String totalDlivyQty;   // 전체출고수량
//	private String dlvComptQty;	    // 배송완료수량
//	private String waybilQty;	    // 운송장개수

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
