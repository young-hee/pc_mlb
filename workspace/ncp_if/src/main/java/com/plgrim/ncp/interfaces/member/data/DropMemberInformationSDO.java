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
public class DropMemberInformationSDO extends InterfaceSDO {

    private static final long serialVersionUID = 1L;

    /**
     * 고객 일련번호
     */
    @JsonProperty("CID")
    private String cid;
    /**
     * 탈퇴사유
     */
    @JsonProperty("DROP_REASON")
    private String dropReason;
    /**
     * 탈퇴사유 세부내용
     */
    @JsonProperty("REMARK_IT")
    private String remarkIt;
    /**
     * 탈퇴일시
     */
    @JsonProperty("DROP_DATE")
    private String dropDate;
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