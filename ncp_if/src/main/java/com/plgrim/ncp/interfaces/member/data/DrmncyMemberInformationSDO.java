package com.plgrim.ncp.interfaces.member.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)
public class DrmncyMemberInformationSDO extends InterfaceSDO {

    private static final long serialVersionUID = 1L;

    /**
     * 고객 일련번호
     */
    @JsonProperty("CID")
    private String cid;
    /**
     * 휴면일시
     */
    @JsonProperty("SLEEP_DTM")
    private String sleepDtm;
    /**
     * 휴면사유
     */
    @JsonProperty("SLEEP_REASON")
    private String sleepReason;
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
    /**
     * 휴면 해제 처리결과목록
     */
    @JsonProperty("RELEASE_DRMNCY_RESULT_LIST")
    private List<ReleaseDrmncyMemberResult> releaseDrmncyResultList;
}