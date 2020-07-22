package com.plgrim.ncp.interfaces.goods.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)
public class InfFacebookRequestSDO extends InterfaceSDO {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String method;
    
    /**
     * 
     */
    private String retailer_id;

    /**
     * Caller Class Name
     */
    private String callerId;
    
    /**
     * 
     */
    @JsonProperty("data")
    private InfFacebookTargetSDO data;

}
