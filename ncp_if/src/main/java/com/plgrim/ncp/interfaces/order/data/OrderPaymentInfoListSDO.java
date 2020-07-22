package com.plgrim.ncp.interfaces.order.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.ALWAYS)
public class OrderPaymentInfoListSDO extends AbstractSDO {
	
	private static final long serialVersionUID = 1L;

	/**
     * 주문번호 
     */
    @JsonProperty("ORD_NO")
    private String ordNo;
    
    /**
     * 주문상품번호 (INF_ORD_GOD_ERP_DSTB 테이블 QTY_TURN 컬럼 값)
     */
    @JsonProperty("ORD_DTL_NO")
    private String ordDtlNo;
    
    /**
     * 판매일자 (YYYY-MM-DD) 
     */
    @JsonProperty("SALEDATE")
    private String saleDate;
    
    /**
     * 판매구분 (1 : 판매, 2 : 반품) 
     */
    @JsonProperty("SALETYPE")
    private String saleType;
    
	/**
     * 브랜드코드 (X : 디스커버리, M:MLB, I:MLB Kids)
     */
    @JsonProperty("BRAND")
    private String brand;
	
    /**
     * 매장코드 (MLB : 510 , MLB KIDS : 50002, DX : 30004 (VIP의 경우 RT처리) )
     */
    @JsonProperty("SHOPCODE")
    private String shopCode;
	
	/**
     * 시즌 
     */
    @JsonProperty("SEASON")
    private String season;
    
    /**
     * 품번 
     */
    @JsonProperty("PARTCODE")
    private String partCode;
    
    /**
     * 칼라 
     */
    @JsonProperty("COLOR")
    private String color;
    
    /**
     * 사이즈 
     */
    @JsonProperty("SIZ")
    private String siz;
    
    /**
     * 판매가유형 (ERP 판매가유형)
     */
    @JsonProperty("SALETRTYPE")
    private String saleTrType;
    
    /**
     * 택가정상가 
     */
    @JsonProperty("TAGPRICE")
    private String tagPrice;
    
    /**
     * 판매단가 (판매가(ERP 할인가) )
     */
    @JsonProperty("SELLPRICE")
    private String sellPrice;
    
    /**
     * 판매수량
     */
    @JsonProperty("QTY")
    private String qty;
    
    /**
     * 실판매가 (쿠폰, 마일리지 사용금액까지 제외한 해당 상품에 실 결제금액)
     */
    @JsonProperty("ACTUALAMT")
    private String actualAmt;
    
    /**
     * 결제유형카드금액
     */
    @JsonProperty("CARD_AMT")
    private String cardAmt;
    
    /**
     * 결제유형현금금액 
     */
    @JsonProperty("CASH_AMT")
    private String cashAmt;
    
    /**
     * 세일할인 (택가 - 판매가)
     */
    @JsonProperty("SALEDIS_AMT")
    private String saleDisAmt;
    
    /**
     * 마일리지사용액
     */
    @JsonProperty("USE_POINT")
    private String usePoint;
    
    /**
     * 쿠폰번호
     */
    @JsonProperty("ISSUENO")
    private String issueNo;
    
    /**
     * 쿠폰사용결제액
     */
    @JsonProperty("ISSUENO_AMT")
    private String issueNoAmt;
    
    /**
     * 기타할인 (쿠폰할인 + 이벤트할인 + 포인트할인)
     */
    @JsonProperty("ETCDIS_AMT")
    private String etcDisAmt;
    
    /**
     * 회원일련번호
     */
    @JsonProperty("CID")
    private String cid;
    
    /**
     * 적립마일리지
     */
    @JsonProperty("GET_POINT")
    private String getPoint;
    
    /**
     * 등록일 (YYYY-MM-DD HH24:MI:SS)
     */
    @JsonProperty("REGDATE")
    private String regDate;

    /**
     * 마일리지즉시적립여부
     */
    @JsonProperty("REAL_MILE_YN")
    private String realMileYn;
    
    /**
     * 직원번호 (임직원번호)
     */
    @JsonProperty("EMP_NO")
    private String empNo;
    
    /**
     * 구매량 타입 (B: VIP 대량구매, S:일반구매)
     */
    @JsonProperty("QUANTITY_TYPE")
    private String quantityType;
    
    /**
     * 수령매장코드
     */
    @JsonProperty("DELI_SHOPCODE")
    private String deliShopcode;
    
    /**
     * 클레임유형 (취소 : CANCEL ,반품 : RETURN)
     */
    @JsonProperty("CLM_TYPE")
    private String clmType;
    
    /**
     * 결제수단 (CREDT_CARD_PAY : 신용카드결제 , RLTM_BNK_ACCT_PAY : 실시간계좌결제
     *           , VIRTL_BNK_ACCT_PAY : 무통장입금(가상계좌) , MOBIL_PON_PAY : 휴대폰결제 , NAVER_PAY : 네이버 페이)
     */
    @JsonProperty("PAY_TYPE")
    private String payType;
    
    /**
     * 가상계좌 결제주문 또는 매장배송 건만 포함한 주문의 경우 Y
     */
    @JsonProperty("TMPR_MILE_DDCT_YN")
    private String tmprMileDdctYn;
    
}
