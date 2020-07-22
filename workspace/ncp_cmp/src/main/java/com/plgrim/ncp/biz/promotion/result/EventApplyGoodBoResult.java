package com.plgrim.ncp.biz.promotion.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.biz.promotion.data.EvtAplGodExtend;

/**
 * 이벤트 적용상품 Bo Result
 * @author js
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EventApplyGoodBoResult extends AbstractResult {
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
     * 이벤트 번호
     */
    private String evtNo;

    /**
     * 적용 순번
     */
    private String aplTurn;

    /**
     * 적용상품 선택코드
     */
    private String aplGodSectCd;

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
