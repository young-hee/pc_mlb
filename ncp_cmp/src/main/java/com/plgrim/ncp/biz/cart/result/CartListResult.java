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

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodExtend;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvCstPlc;

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
public class CartListResult extends AbstractResult{
	
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5400998630902897613L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    
    /** 페이지 요청 오브젝트. */
    @JsonIgnore
	Pageable pageable = new PageRequest(0, 30);
	
	
	/** The cart list. */
	List<List<CartResult>> cartList;
	
	/** The bundle prm cnd. */
	List<CartPrmResult> bundlePrmCnd;
	
	/** The bsk info. */
	List<BskGodExtend> bskInfo;
	
	/** The com dmstc dlv cst plc. */
	List<ComDmstcDlvCstPlc> comDmstcDlvCstPlc;
	
	/** The dlv sect cd. */
	private String dlvSectCd;
	
	/**주문 사은품 대상*/
	List<CartGiftResult> ordGiftList;
	
	/** The gnrl cart cnt. */
	private int gnrlCartCnt;
	
	/** The pkup cart cnt. */
	private int pkupCartCnt;
	
	/** The over sea cart cnt. */
	private int overSeaCartCnt;
	
	/** 예약상품 장바구니담기 가능 : 2016.04.06 by cannon */
	private int resvCartCnt;
	
	/** 퀵배송 장바구니 카운트 */
	private int quickCartCnt;

	private String mbrTpCd;


	/** ncp 3차 배송정책 관련 추가. */
	//해외국내배송 정책 리스트
	@JsonIgnore
	List<ComDmstcDlvCstPlc> ovseaComDmstcDlvCstPlc;
	//해외배송 정책 리스트
	@JsonIgnore
	List<ComOvseaDlvCstPlc> comOvseaDlvCstPlc;
	
	// 배송비즉시할인쿠폰 : by cannon (2016.06.07)
	List<CartGodPrmResult> dlvFeeImdtlCpnResultList;
	
	List<String> bskPrmGodNoList;

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
