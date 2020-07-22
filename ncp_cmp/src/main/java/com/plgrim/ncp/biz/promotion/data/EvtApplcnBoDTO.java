package com.plgrim.ncp.biz.promotion.data;

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.evt.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;

/**
 * 이벤트 응모 Bo DTO
 * @author js
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EvtApplcnBoDTO extends AbstractDTO  {

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
	private Evt evt = new Evt();

	/**
	 * 회원
	 */
	private Mbr mbr = new Mbr();
	
	/**
	 * 이벤트 응모
	 */
	private EvtApplcn applcn = new EvtApplcn();

	/**
	 * 이벤트 당첨
	 */
	private EvtPrize prize;
	
	/**
	 * 이벤트 당첨 경품
	 */
	private EvtPrizeFreeGift prizeFreeGift;

	/**
	 * 회원 타입 코드 배열
	 */
	private String[] mbrTpCd;
	
	/**
	 * 당첨선정(당첨이력)
	 */
	private String history;
	
	/**
	 * 당첨선정(구매조건)
	 */
	private String prtCnd;
	
	/**
	 * 당첨선정(임직원제외여부)
	 */
	private String managerYn;

	/**
	 * 검색 조건 구분
	 */
	private String apGbn;

	/**
	 * 검색 조건 - 시작일
	 */
	private String begDtStr;

	/**
	 * 검색 조건 - 마감일
	 */
	private String endDtStr;
    
	/**
	 * 회원 검색 구분
	 */
	private String listMbrGbn;
	
    /**
     * 사용자 array
     */
    private List<String> listMbr;

    /**
     * 회원유형 array
     */
    private List<String> arrMbrTpCd;

    /**
     * 회원상태 array
     */
    private List<String> arrMbrStatCd;

    /**
     * 공유형태 array
     */
    private List<String> arrCnrsDiv;

    // #41419 이벤트 기획전 (SNS댓글) 기능 개선 170417
    private EvtSnsReply snsReply;
    
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
