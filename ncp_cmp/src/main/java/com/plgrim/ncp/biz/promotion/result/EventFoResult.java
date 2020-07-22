package com.plgrim.ncp.biz.promotion.result;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftInfo;

/**
 * 이벤트 Result
 * @author js
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EventFoResult extends AbstractResult {
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
    String evtNo;

    /**
     * 등록자ID
     */
    String regtrId;

    /**
     * 등록자명
     */
    String regtrNm;

    /**
     * 등록일시
     */
    Date regDt;

    /**
     * 수정자ID
     */
    String udterId;

    /**
     * 수정자명
     */
    String udterNm;

    /**
     * 수정자일시
     */
    Date udtDt;

    /**
     * 이벤트
     */
    Evt evt;

    /**
     * 이벤트경품 정보
     */
    EvtFreeGiftInfo evtFreeGiftInfo;
    
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
