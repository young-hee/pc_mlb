package com.plgrim.ncp.interfaces.abstracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class InterfaceBaseSDO extends AbstractSDO {

    private static final long serialVersionUID = 1L;

    /**
     * Caller Class Name
     */
    private String callerId;

    /**
     * 결과코드
     */
    @JsonProperty("RESPONSE_CD")
    private String responseCd;
    /**
     * 결과 메시지
     */
    @JsonProperty("RESPONSE_MSG")
    private String responseMsg;

}
