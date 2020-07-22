package com.plgrim.ncp.interfaces.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * Remote 통신을 위한 Header 세팅에 이용하는 class 이다.
 *
 * @author John_Kim
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("AdapterHeader")
public class AdapterHeader {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    private String requestId;            // request id.
    private String mallId;                // mall id.
    private String locale;                // locale.
    private String channel;                // channel. 웹/모바일 등 구분.
    private String userId;                // 보안관련 : user id.
    private String password;            // 보안관련 : password.

    private String langCd;
    private String dvcCd;

    /**
     * Read Timeout
     */
    private int readTimeout = -1;

    /**
     * Connection Timout
     */
    private int connectionTimeout = -1;
    
    private String accessKey;

    /*
     * ---------------------------------------------------------------------
     * Constructors.
     * ---------------------------------------------------------------------
     */

    /*
     * ---------------------------------------------------------------------
     * public & interface method.
     * ---------------------------------------------------------------------
     */

    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}
