package com.plgrim.ncp.biz.promotion.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftInfo;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;

/**
 * 당첨 Bo Result
 * @author js
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EventPrizeBoResult extends AbstractResult {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	//private EvtPrize prize;
	
	/**
	 * 경품
	 */
	private EvtFreeGiftInfo freeGift;
	
	/**
	 * 회원
	 */
	private Mbr mbr;
	
    /**
     * 휴대폰 번호
     */
    private String mobilNo;                         
    
    /**
     * 전화 번호
     */
    private String telNo;                           
	
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
