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
public class OrderUseTempMileageListSDO extends AbstractSDO {
	
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
     * 브랜드코드 (X : 디스커버리, M:MLB, I:MLB Kids)
     */
    @JsonProperty("BRAND")
    private String brand;
    
    /**
     * 회원일련번호
     */
    @JsonProperty("CID")
    private String cid;
    
    /**
     * 임시처리 마일리지금액
     */
    @JsonProperty("TEMP_MILE_AMT")
    private String tempMileAmt;
    
    /**
     * 임시처리 구분코드 (00:마일리지차감, 01:마일리지증가)
     */
    @JsonProperty("TEMP_SECT_CD")
    private String tempSectCd;
    
    /**
     * 임시처리 유형 (00:사용마일리지, 01:적립마일리지)
     */
    @JsonProperty("TEMP_TYPE")
    private String tempType;
    
}
