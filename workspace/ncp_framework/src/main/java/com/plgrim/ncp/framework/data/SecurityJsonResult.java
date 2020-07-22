package com.plgrim.ncp.framework.data;

import lombok.Data;

/**
 * 스프링 시큐리티 성공 하면 받는 json 오브젝트.
 */
@Data
public class SecurityJsonResult {
    /* 리다이렉트 URL */
    String loginTarget;
    
    String token;
    
    String failMessage;

    String releaseDrmncy;
}

