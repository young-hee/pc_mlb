package com.plgrim.ncp.interfaces.goods.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)
public class OlapicFTPInfoSDO extends AbstractSDO {

    private static final long serialVersionUID = 1L;

    private String mall;
    
    private String lang;
    
    private String path;
    
    private String ftpPW;
    
    private int ftpPort;
    
    private String ftpID;
    
    private String ftpHost;
}