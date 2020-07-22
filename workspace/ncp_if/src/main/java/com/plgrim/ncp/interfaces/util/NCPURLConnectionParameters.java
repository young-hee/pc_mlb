package com.plgrim.ncp.interfaces.util;


import lombok.Data;

import java.util.Map;

@Data
public class NCPURLConnectionParameters {

    /**
     * Connector Request Method
     * - GET
     * - POST
     * - PUT
     */
    private String requestMethod;

    /**
     * Request Property Map
     */
    private Map<String, String> requestPropertyMap;

    /**
     * input json encoding charset
     */
    private String charsetName;
}
