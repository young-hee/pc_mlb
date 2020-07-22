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
public class OrderOnlineShopRtListSDO extends AbstractSDO {
	
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
     * 수령매장코드
     */
    @JsonProperty("DELI_SHOPCODE")
    private String shopCode;
    
    /**
     * 브랜드코드
     */
    @JsonProperty("BRAND")
    private String brand;
    
    /**
     * 시즌코드
     */
    @JsonProperty("SEASON")
    private String season;
    
    /**
     * 상품코드
     */
    @JsonProperty("PARTCODE")
    private String partCode;
    
    /**
     * 컬러코드
     */
    @JsonProperty("COLOR")
    private String color;
    
    /**
     * 수량
     */
    @JsonProperty("QTY")
    private String qty;
    
    /**
     * 사이즈
     */
    @JsonProperty("SIZ")
    private String siz;
}
