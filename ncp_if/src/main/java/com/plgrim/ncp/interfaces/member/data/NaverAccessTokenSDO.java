package com.plgrim.ncp.interfaces.member.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)
public class NaverAccessTokenSDO extends AbstractSDO {

    private static final long serialVersionUID = 1L;

    /**
     * Caller Class Name
     */
    private String callerId;
    
    /**
     * 네이버 로그인 Access Token
     */
    private String userAccessToken;
}