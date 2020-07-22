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

public class OrderSendErpInvStockSDO extends InterfaceBaseSDO{
	private static final long serialVersionUID = 1L;
	
	private String callerId;
	
	/**
     * 본사재고 사용 리스트
     */
    @JsonProperty("INV_LIST")
    private List<OrderSendErpInvStockListSDO> invList;
    
    /**
     * RESPONSE_DATA
     */
    @JsonProperty("RESPONSE_DATA")
	private List<OrderSendErpInvStockResultSDO> responseData;
}


