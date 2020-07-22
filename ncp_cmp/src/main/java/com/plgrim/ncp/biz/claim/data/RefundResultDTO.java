package com.plgrim.ncp.biz.claim.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class RefundResultDTO extends AbstractDTO {

	/*
	 * 결제환율 통화금액 합계
	 */
	private String payExchgRtCrncyAmtSum; 
	
	/*
	 * 사이트 기준 금액 합계
	 */
	private String stdrCrncyUntPrcSum;
	
	
	private String dlvCstSumAmt;
	
	
	/**
	 * 상품 할인 금액	 
	 */
	private String godDcAmtSum;
	/**
	 * 임직원 할인 금액	 
	 */
	private String empDcAmtSum;
	/**
	 * 상품 쿠폰 할인 금액	 
	 */
	private String godCpnDcAmtSum;
	/**
	 * 장바구니 쿠폰 할인 금액	 
	 */
	private String bskCpnDcAmtSum;
	/**
	 * 통합 포인트 사용 금액	 
	 */
	private String unityPntUseAmtSum;
	/**
	 * 퍼플 코인 사용 금액
	 */
	private String webpntUseAmtSum;
	/**
	 * 이벤트 포인트 사용 금액
	 */
	private String evtPntUseAmtSum;
	/**
	 * 웹포인트 사용 금액
적립은 되고 있으나 사용 할 수 없는부분	 
	 */
	private String webpntUseAmt;
	/**
	 * 통합 포인트 적립 금액	 
	 */
	private String unityPntAccmlAmtSum;
	/**
	 * 이벤트 포인트 적립 금액
	 */
	private String evtPntAccmlAmtSum;
	/**
	 * 퍼플 코인 적립 금액
	 */
	private String webpntAccmlUntPrcSum;
	
	/**
	 * 환불 배송비
	 */
	private String refundDlvFee;
	
	/**
	 * 배송지 별 환불금액
	 */
	private List<RefundClmDlvAmtDTO> clmDlvAmtDTOList;	
	
	/**
	 * 이전 클레임으로 인한 환불금액정보
	 */
	private RefundResultDTO befordRefundDTOList;
	
	private int iSalePrcSumAmt;	// 판매가
	private int iBundleDcSumAmt;	// 묶음할인
	private int iCrsDcSumAmt;	// 교차할인
	private int iGodCpnDcSumAmt;	// 상품쿠폰
	private int iBskCpnDcSumAmt;	// 장바구니쿠폰
	
}
