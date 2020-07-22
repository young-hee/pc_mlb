package com.plgrim.ncp.interfaces.member.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)
public class MemberEmpInformationSDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 고객 일련번호
     */
    @JsonProperty("CID")
    private String cid;
    /**
     * 임직원입사퇴사여부
     * Y:입사, N:퇴사
     */
    @JsonProperty("EMP_YN")
    private String empYn;
    /**
     * 임직원번호
     */
    @JsonProperty("EMP_NO")
    private String empNo;
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