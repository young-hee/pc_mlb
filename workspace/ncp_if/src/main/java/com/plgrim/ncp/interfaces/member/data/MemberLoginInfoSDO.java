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
public class MemberLoginInfoSDO extends InterfaceSDO {

    private static final long serialVersionUID = 1L;

    /**
     * 고객 일련번호
     */
    @JsonProperty("CID")
    private String cid;
    /**
     * 웹로그인용 아이디
     */
    @JsonProperty("ID")
    private String id;
    /**
     * 로그인 IP
     */
    @JsonProperty("IP")
    private String ip;
    /**
     * 로그인 일시
     */
    @JsonProperty("LOG_DATE")
    private String logDate;
    /**
     * 휴면 해제 여부
     */
    @JsonProperty("SLEEP_RELEASE_YN")
    private String sleepReleaseYn;
    /**
     * 로그인 사이트
     */
    @JsonProperty("SITE")
    private String site;
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