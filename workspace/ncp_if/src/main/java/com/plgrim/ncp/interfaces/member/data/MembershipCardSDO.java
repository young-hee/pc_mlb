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
public class MembershipCardSDO extends InterfaceSDO {

    private static final long serialVersionUID = 1L;

    /**
     * 고객 일련번호
     */
    @JsonProperty("CID")
    private String cid;
    /**
     * 멤버십 카드 번호
     */
    @JsonProperty("MEMBERSHIP_CARD_NO")
    private String membershipCardNo;
    /**
     * 인증번호
     */
    @JsonProperty("CERTIFY_NO")
    private String certifyNo;
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