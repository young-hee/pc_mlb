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
public class MembershipSDO extends InterfaceSDO {

    private static final long serialVersionUID = 1L;

    /**
     * Caller Class Name
     */
    private String callerId;
    
    /**
     * 본인인증 CI
     */
    @JsonProperty("CI")
    private String ci;
    /**
     * 본인인증 DI
     */
    @JsonProperty("DI")
    private String di;
    /**
     * 고객 일련번호
     */
    @JsonProperty("CID")
    private String cid;
    /**
     * 가입불가시 코드
     * T:탈퇴 30일이내, D:직권탈퇴
     */
    @JsonProperty("POSSIBILITY")
    private String possibility;
    /**
     * 처리 결과
     * Y:가입가능, N:가입불가
     */
    @JsonProperty("RESULT")
    private String result;
}