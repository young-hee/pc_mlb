package com.plgrim.ncp.interfaces.promotion.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)
public class OnOffCouponIssueResultDataSDO {

    /**
     * 사용가능 시작일시
     */
    @JsonProperty("USE_START_DT")
    private String useStartDt;
    
    /**
     * 사용가능 종료일시
     */
    @JsonProperty("USE_END_DT")
    private String useEndDt;
    
    /**
     * ERP 회원 번호
     */
    @JsonProperty("CID")
    private String cid;
    
    /**
     * 브랜드코드
     */
    @JsonProperty("BRAND")
    private String brand;
    
    /**
     * 발행 쿠폰 유형
     */
    @JsonProperty("ISSUED_COUPON_TYPE")
    private String issuedCouponType;
    
    /**
     * 발행코드
     */
    @JsonProperty("ISSUENO")
    private String issueno;
    
    /**
     * 사용여부
     */
    @JsonProperty("USE_YN")
    private String useYn;
    
    /**
     * 사용일자
     * YYYY-MM-DD
     */
    @JsonProperty("USE_DT")
    private String useDt;
    
    /**
     * 쿠폰명
     */
    @JsonProperty("CPN_NM")
    private String cpnNm;
    
    /**
     * 생성일
     */
    @JsonProperty("CREATE_DT")
    private String createDt;
    
    /**
     * 할인유형코드
     */
    @JsonProperty("DC_TP_CD")
    private String dcTpCd;
    
    /**
     * 할인금액
     */
    @JsonProperty("DC_AMT")
    private String dcAmt;
    
    /**
     * 할인율
     */
    @JsonProperty("DC_RT")
    private String dcRt;
    
    /**
     * 쿠폰적용최소주문금액
     */
    @JsonProperty("CPN_USE_MIN_PCH_AMT")
    private String cpnUseMinPchAmt;
    
    /**
     * 최대할인금액
     */
    @JsonProperty("CPN_MAX_DC_PSB_AMT")
    private String cpnMaxDcPsbAmt;
    
    /**
     * 적용대상조건여부
     */
    @JsonProperty("APL_GOD_YN")
    private String aplGodYn;
    
    /**
     * 적용대상조건여부
     */
    @JsonProperty("APL_GOD_LIST")
    private List<OnOffCouponIssueApplyGoodsSDO> aplGodList;

    /**
     * 쿠폰 그룹 번호
     * - ERP에서 사용하는 쿠폰의 묶음 번호
     */
    @JsonProperty("COUPON_NO")
    private String couponNo;
    
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
