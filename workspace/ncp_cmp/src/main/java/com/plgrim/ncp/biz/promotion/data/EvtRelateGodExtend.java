package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.entities.datasource1.evt.EvtRelateGod;

/**
 * 연관상품 확장
 * @author js
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EvtRelateGodExtend extends EvtRelateGod {

    /**
     * UID
     */
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */
    
    /**
     * 에러 메시지
     */
    private String errMsg;

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
