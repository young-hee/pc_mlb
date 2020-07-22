package com.plgrim.ncp.interfaces.goods.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class InfFacebookResponseSDO extends AbstractSDO {

    private static final long serialVersionUID = 1L;

    /**
     * Caller Class Name
     */
    private String callerId;

    /**
     * 결과코드
     */
    @JsonProperty("id")
    private String responseId;

}
