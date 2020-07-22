package com.plgrim.ncp.interfaces.promotion.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 온오프라인 쿠폰 SDO
 * - 검증(IF-PRM-03), 사용(IF-PRM-04), 복원(IF-PRM-05) 시 사용한다.
 * 
 * @author Seed
 *
 */
@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)
public class OnOffCouponUseSDO extends InterfaceSDO {

	private static final long serialVersionUID = 2505273808449494473L;

	/*******************************************************************************
     * Request parameter
     *******************************************************************************/
    
    /**
     * 고객 일련번호
     */
    @JsonProperty("CID")
    private String cid;
    
    /**
     * 쿠폰발행코드
     */
    @JsonProperty("ISSUENO")
    private String issueNo;
    
    /**
     * 브랜드코드
     */
    @JsonProperty("BRAND")
    private String brand;
    
    /**
     * 매장코드
     */
    @JsonProperty("SPD_SHOPCODE")
    private String shopcode;
    
    /**
     * 주문번호
     */
    @JsonProperty("ORD_NO")
    private String ordNo;
    
    /**
     * 클레임번호
     */
    @JsonProperty("CLM_NO")
    private String clmNo;
    
    /**
     * 할인금액
     */
    @JsonProperty("SPD_AMOUNT")
    private String spdAmount;
    
    /**
     * 사용여부
     */
    @JsonProperty("USE_YN")
    private String useYn;
    
    /**
     * 처리결과코드
     */
    @JsonProperty("RESULT_CD")
    private String resultCd;
    /**
     * 처리결과메시지
     */
    @JsonProperty("RESULT_MSG")
    private String resultMsg;
}