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
public class OrderOfflinePurchaseResultSDO extends AbstractSDO {
	
	private static final long serialVersionUID = 1L;

    /**
     * 구매일 (YYYY-MM-DD HH24:MI:SS)
     */
    @JsonProperty("PURCHASE_DT")
    private String purchaseDt;
    
    /**
     * 매장명
     */
    @JsonProperty("SHOP_NM")
    private String shopNm;
    
    /**
     * 상품명
     */
    @JsonProperty("GOD_NM")
    private String godNm;
    
    /**
     * 구매금액
     */
    @JsonProperty("PURCHASE_AMT")
    private String purchaseAmt;
    
    /**
     * 구매수량
     */
    @JsonProperty("PURCHASE_QTY")
    private String purchaseQty;
}
