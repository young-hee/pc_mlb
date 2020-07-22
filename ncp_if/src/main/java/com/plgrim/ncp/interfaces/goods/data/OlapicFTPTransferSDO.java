package com.plgrim.ncp.interfaces.goods.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)
public class OlapicFTPTransferSDO extends AbstractSDO {

    private static final long serialVersionUID = 1L;

    /**
     * Caller Class Name
     */
    private String callerId;
    
    private String responseCd;
}