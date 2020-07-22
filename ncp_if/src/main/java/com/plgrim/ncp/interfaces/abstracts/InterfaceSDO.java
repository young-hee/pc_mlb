package com.plgrim.ncp.interfaces.abstracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class InterfaceSDO extends InterfaceBaseSDO{

    private static final long serialVersionUID = 1L;

    /**
     * 결과 정보
     */
    @JsonProperty("RESPONSE_DATA")
    private String responseData;
    
    @JsonSetter("RESPONSE_DATA")
    public void setResponseData(JsonNode data)
    {
        this.responseData = data.toString();
    }
}
