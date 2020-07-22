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
@JsonInclude(value=Include.NON_EMPTY)
public class OrderFormerlyPurchaseResultSDO extends AbstractSDO {
	
	private static final long serialVersionUID = 1L;

	/**
     * 주문번호
     */
    @JsonProperty("ORD_NO")
    private String ordNo;
    
    /**
     * 주문일시
     */
    @JsonProperty("ORD_DT")
    private String ordDt;
    
    /**
     * 상품명
     */
    @JsonProperty("GOD_NM")
    private String godNm;
    
    /**
     * 상품이미지URL
     */
    @JsonProperty("GOD_IMG_URL")
    private String godImgUrl;
    
    /**
     * 단품명
     */
    @JsonProperty("OPT_NM")
    private String optNm;
    
    /**
     * 상품단가
     */
    @JsonProperty("GOD_PRC")
    private String godPrc;
    
    /**
     * 주문수량
     */
    @JsonProperty("ORD_QTY")
    private String ordQty;
    
    /**
     * 주문금액
     */
    @JsonProperty("ORD_AMT")
    private String ordAmt;
    
    /**
     * 주문상태명
     */
    @JsonProperty("ORD_STAT_NM")
    private String ordStatCd;   
    
    /**
     * 주문브랜드 사이트
     */
    @JsonProperty("ORD_SITE")
    private String ordSite;   
}
