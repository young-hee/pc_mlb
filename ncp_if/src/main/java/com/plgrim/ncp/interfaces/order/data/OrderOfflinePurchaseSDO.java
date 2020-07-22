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

public class OrderOfflinePurchaseSDO extends InterfaceBaseSDO{
	private static final long serialVersionUID = 1L;
	
	private String callerId;
	
	/**
     * ERP고객번호
     */
    @JsonProperty("CID")
    private String cid;
    
    /**
     * 시작일시 (YYYY-MM-DD)
     */
    @JsonProperty("START_DT")
    private String startDt;
    
    /**
     * 종료일시 (YYYY-MM-DD)
     */
    @JsonProperty("END_DT")
    private String endDt;
    
    /**
     * 브랜드코드
     */
    @JsonProperty("BRAND")
    private String brand;
    
    /**
     * 오프라인 구매내역 리스트
     */
    @JsonProperty("RESPONSE_DATA")
	private List<OrderOfflinePurchaseResultSDO> responseData;

}


