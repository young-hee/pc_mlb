package com.plgrim.ncp.biz.promotion.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPrize;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPrizeFreeGift;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtSnsReply;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;

/**
 * 이벤트 응모자 Bo Result
 * @author js
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EventApplcnBoResult extends AbstractResult {
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
	 * 응모자
	 */
	private EvtApplcn applcn;
	
	/**
	 * 당첨자
	 */
	private EvtPrize prize;
	
	/**
	 * 당첨자 경품
	 */
	private EvtPrizeFreeGift prizeFreeGift;
	
	/**
	 * 회원
	 */
	private Mbr mbr;
	
	/**
	 * SNS 댓글
	 */
	private EvtSnsReply snsReply;
	
	/**
	 * 주문건수
	 */
	private String ordAmt;
	
	/**
	 * sns 구분명
	 */
	private String snsSectNm;
	
    /**
     * 휴대폰 번호
     */
    private String mobilNo;                           
    
    /**
     * 전화번호
     */
    private String telNo;                             
    
    /**
     * 회원 상태 코드명
     */
    private String mbrStatNm;                         
    
    /**
     * 회원 유형 코드명
     */
    private String mbrTpNm;                           
    
    /**
     * 회원 속성 코드명
     */
    private String mbrAtrbNm;                         

	
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
