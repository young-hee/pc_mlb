/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      mc009.park
 * @since       2015. 6. 19       
 */
package com.plgrim.ncp.biz.cart.result;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author mc009.park
 * @since 2015. 6. 19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CartGodOptionResult extends AbstractResult{
	
    private static final long serialVersionUID = -8509277408441228174L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** The parent god turn. */
	private int parentGodTurn;
    
    /** The god nm. */
    private String godNm;

	/**
	 * 패키지명
	 */
	private String pckageGodNm;

	/**
	 * 품목코드명
	 */
	private String prdlstNm;

    /** The itm qty. */
    private Long itmQty;
    
    /** The bsk god. */
    BskGod bskGod;
    
    private int minOrdQty;
    
    private int maxOrdQty;
    
    /** The god list. */
    List<BskGodItmExtend> godList;
    
    /** The god itm list. */
    List<GodItm> godItmList;

    /**
     * ㅁ. 입점 구분 : PARTMAL_SECT
   	 * >. 자사 : MCOM
   	 * >. 입점 : PARTMAL
     */
    private String partmalSectCd;

    /**
     * 선택한 옵션1 코드
     */
    private String selectOptCd1;

    /**
     * 선택한 옵션1 값
     */
    private String selectOptVal1;

    /**
     * 선택한 옵션2 코드
     */
    private String selectOptCd2;

    /**
     * 선택한 옵션2 값
     */
    private String selectOptVal2;

    /**
     * 선택한 옵션3 코드
     */
    private String selectOptCd3;

    /**
     * 선택한 옵션3 값
     */
    private String selectOptVal3;
    
    /**
     * 옵션1타이틀
     */
    private String optNm1;
    /**
     * 옵션2타이틀
     */
    private String optNm2;
    /**
     * 옵션3타이틀
     */
    private String optNm3;

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

    /**
     * 퀵배송으로 추가 ROW_NUMBER 사용을 위한 변수(화면에선 쓰이지 않음)
     */
    private String rownm;

}
