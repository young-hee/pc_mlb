package com.plgrim.ncp.interfaces.member.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)
public class MemberGradeInformationSDO extends InterfaceSDO {

    private static final long serialVersionUID = 1L;

    /**
     * 고객 일련번호
     */
    @JsonProperty("CID")
    private String cid;
    /**
     * 회원등급변경일시
     */
    @JsonProperty("MEMBER_GRADE_CHG_DTM")
    private String memberGradeChgDtm;
    /**
     * 회원등급
     */
    @JsonProperty("MEMBER_GRADE")
    private String memberGrade;
    /**
     * 브랜드구분
     */
    @JsonProperty("BRAND_DIV")
    private String brandDiv;
    /**
     * 발급 쿠폰 유형
     */
    @JsonProperty("ISSUED_COUPON_TYPE")
    private String issuedCouponType;
    /**
     * 발급 쿠폰 발행번호
     */
    @JsonProperty("ISSUED_COUPON_NO")
    private String issuedCouponNo;
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