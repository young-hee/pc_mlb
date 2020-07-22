package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtAplGod;

/**
 * 적용상품 Bo DTO
 * @author js
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EventApplyGoodBoDTO extends AbstractDTO {

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
     * 적용상품
     */
    private EvtAplGod evtAplGod;
    /**
     * 적용상품 확장
     */
    private EvtAplGodExtend evtAplGodEx;

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
