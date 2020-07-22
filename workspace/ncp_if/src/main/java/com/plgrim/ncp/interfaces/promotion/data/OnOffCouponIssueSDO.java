package com.plgrim.ncp.interfaces.promotion.data;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plgrim.ncp.interfaces.abstracts.InterfaceBaseSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)
public class OnOffCouponIssueSDO extends InterfaceBaseSDO  implements Serializable{

	private static final long serialVersionUID = -12734430429908993L;

	/*******************************************************************************
     * Request parameter
     *******************************************************************************/
	
	/**
     * 기준일자
     */
    @JsonProperty("STD_DT")
    private String stdDt;
    
    /**
     * 브랜드코드
     */
    @JsonProperty("BRAND")
    private String brand;
    
    /**
     * ERP 회원 번호
     */
    @JsonProperty("CID")
    private String cid;
    
    /**
     * 발행코드
     */
    @JsonProperty("ISSUENO")
    private String issueno;
    
    /**
     * 요청구분코드
     */
    @JsonProperty("REQUEST_TYPE_CD")
    private String requestTypeCd;
    
    /*******************************************************************************
     * Response parameter
     *******************************************************************************/
    
    /**
     * 응답 데이터
     *  - 발행쿠폰 리스트
     */
    @JsonProperty("RESPONSE_DATA")
    private List<OnOffCouponIssueResultDataSDO> responseData;
}
