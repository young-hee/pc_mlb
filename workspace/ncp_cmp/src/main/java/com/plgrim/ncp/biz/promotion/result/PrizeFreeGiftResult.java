package com.plgrim.ncp.biz.promotion.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftInfo;

/**
 * 당첨자 경품
 * @author js
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PrizeFreeGiftResult extends AbstractResult {
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
	 * 이벤트
	 */
	Evt evt;
	
	/**
	 * 경품
	 */
	EvtFreeGiftInfo freeGiftInfo;

    //당첨자 선정
    /**
     * 선정상태
     */
    String choseStats;
    
    /**
     * 선정인원
     */
    long choseQty;

    /**
     * 지급상태
     */
    String pymntStats;
    
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
