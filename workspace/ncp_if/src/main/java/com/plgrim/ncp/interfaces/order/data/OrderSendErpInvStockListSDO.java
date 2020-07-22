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
public class OrderSendErpInvStockListSDO extends AbstractSDO {
	
	private static final long serialVersionUID = 1L;

    
    /**
     * 매출일자 (YYYY-MM-DD HH24:MI:SS)
     */
    @JsonProperty("SALEDATE")
    private String saleDate;
    
	/**
     * 브랜드코드 (X : 디스커버리, M:MLB, I:MLB Kids)
     */
    @JsonProperty("BRAND")
    private String brand;
    
    /**
     * 매장코드 ( MLB : 510 , MLB KIDS : 50002, DX : 30004 (VIP의 경우 RT처리))
     */
    @JsonProperty("SHOPCODE")
    private String shopCode;
    
    /**
     * 시즌코드
     */
    @JsonProperty("SEASON")
    private String season;
    
    /**
     * ERP 상품코드
     */
    @JsonProperty("PARTCODE")
    private String partCode;
    
    /**
     * 컬러코드
     */
    @JsonProperty("COLOR")
    private String color;
    
    /**
     * 사이즈
     */
    @JsonProperty("SIZ")
    private String siz;
    
    /**
     * 수량
     */
    @JsonProperty("QTY")
    private String qty;
    
    /**
     * 등록일시 (YYYY-MM-DD HH24:MI:SS)
     */
    @JsonProperty("INPUTDATE")
    private String inputDate;
    
    /**
     * 등록자ID
     */
    @JsonProperty("INPUT_USERID")
    private String inputUserId;
    
}
