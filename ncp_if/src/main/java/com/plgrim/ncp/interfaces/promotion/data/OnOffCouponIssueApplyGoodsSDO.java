package com.plgrim.ncp.interfaces.promotion.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)
public class OnOffCouponIssueApplyGoodsSDO {

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
     * ERP 상품코드
     */
    @JsonProperty("PARTCODE")
    private String partcode;
}
