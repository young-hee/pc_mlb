package com.plgrim.ncp.interfaces.order.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.interfaces.abstracts.InterfaceBaseSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)

public class OrderPaymentInfoSDO extends InterfaceBaseSDO{
	private static final long serialVersionUID = 1L;
	
	private String callerId;
	
    /**
     * 주문상품 리스트
     */
    @JsonProperty("ORD_LIST")
    private List<OrderPaymentInfoListSDO> ordList;
    
    /**
     * 마일리지 내역 리스트
     */
    @JsonProperty("RESPONSE_DATA")
	private List<OrderPaymentInfoResultSDO> responseData;

}


