package com.plgrim.ncp.interfaces.order.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)
public class OrderFormerlyPurchaseCfmInfoResultSDO extends AbstractSDO {
	private static final long serialVersionUID = 1L;
	
	private String callerId;
	
	/**
     * ERP고객번호
     */
    @JsonProperty("CID")
    private String cid;
    
    /**
     * 브랜드코드
     */
    @JsonProperty("BRAND")
    private String brand;
    
    /**
     * 구매확정여부
     */
    @JsonProperty("PURCHASE_CONFIRM_FLAG")
    private String purchaseConfirmFlag;

}


