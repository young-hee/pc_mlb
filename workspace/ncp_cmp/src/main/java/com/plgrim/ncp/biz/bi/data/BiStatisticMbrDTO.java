package com.plgrim.ncp.biz.bi.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BiStatisticMbrDTO extends AbstractDTO{
	
	private static final long serialVersionUID = -5584308109859971218L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */	
	
	private String regTypeDt; 			// 1-최근 로그인(기본)/2-계정생성일
	private String regBegDt;  			// 조회시작일
	private String regEndDt;  			// 조회종료일	
	private String birthBegDt;  		// 생일시작일	
	private String birthEndDt;  		// 생일종료일
	private String sexSectCd;			// 성별	
	private String emailRecptnAgrYn;	// Mail 수신 동의
	private String smsRecptnAgrYn;		// SMS 수신동의
	private String ordMinQty;			// 주문상품 최소 수
	private String ordMaxQty;			// 주문상품 최고 수
	private String ordMinPrc;			// 주문상품 최소 금액
	private String ordMaxPrc;			// 주문상품 최고 금액
	private String bskMinQty;			// 장바구니 최소 수
	private String bskMaxQty;			// 장바구니 최고 수    
    private String godNo;				// 상품번호	
    private String erpGodNo;			// ERP상품번호
    private String ordGodCnt;			// 구매횟수
	
}
