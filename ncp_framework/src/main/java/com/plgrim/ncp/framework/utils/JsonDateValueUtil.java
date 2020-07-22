package com.plgrim.ncp.framework.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueUtil implements JsonValueProcessor {

    /* 기본 날짜 포맷 */
    private final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final DateFormat dateFormat;
    
    public JsonDateValueUtil() {
        dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    }

    @Override
    public Object processArrayValue(Object param, JsonConfig config) {
        if (param != null) {
            return dateFormat.format(param);
        }
        return null;
    }

    @Override
    public Object processObjectValue(String pString, Object param, JsonConfig config) {
        return processArrayValue(param, config);
    }

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

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
