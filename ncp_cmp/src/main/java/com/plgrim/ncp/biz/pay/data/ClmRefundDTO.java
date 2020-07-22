package com.plgrim.ncp.biz.pay.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.biz.claim.data.ClmDlvOrdGodInfoDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClmRefundDTO extends AbstractDTO {

	
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 2912156589541527539L;
    
    
    /**
     * 환불 배송비
     */
    private String refundDlvFee;
    
    /**
     * 환불 배송비 외화
     */
    private String refundDlvFeeEx;
    
    
    /**
     * 상품환불비
     */
    private String refundPrdFee;
    
    /**
     * 상품환불비 외화
     */
    private String refundPrdFeeEx;    
    
    /**
     * 외화 환불비 합계금액
     */
    private String refundSumFeeEx;
    
    
    /**
     * 결제 수단별 환불정보 리스트
     */
	private List<ClmRefundResultDTO> refundResultDTO;
	
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
	 * 배송비 팝업 추가
	 */
    /**
     * 환불 배송비
     */
    private String dmstDlvFee;
    
    /**
     * 환불 배송비 외화
     */
    private String dmstDlvFeeEx;
    
    /**
     * 초도배송비 - 환불금 계산용
     */
    private String orderDlvAmt;
    
    /**
     * 반품수거비 - 환불금 계산용
     */
    private String pickupDlvAmt;
    
    /**
     * 환불 배송비
     */
    private String ovseaDlvFee;
    
    /**
     * 환불 배송비 외화
     */
    private String ovseaDlvFeeEx;

    private String paycrncypayamtTotal;
    private String paycrncypayamtTotalEx;
    private String remainpayamtTotal;
    private String remainpayamtTotalEx;
    private String beforePayamtTotal;
    private String beforePayamtTotalEx;
    private String prcTotal;
    private String prcTotalEx;

    private Ord ord;

    /**
	 * 멤버십포인트 환급
	 */
    private String unityPntUseAmtSum;
    
    /**
     * 취소대상 주문사은품 리스트
     */
    private List<ClmDlvOrdGodInfoDTO> ordGftList;
    
    private int stdrCrncySumAmt; // 주결제환불가
    private int salePrcSumAmt;	// 판매가
	private int bundleDcSumAmt;	// 묶음할인
	private int crsDcSumAmt;	// 교차할인
	private int godCpnDcSumAmt;	// 상품쿠폰
	private int bskCpnDcSumAmt;	// 장바구니쿠폰
}
