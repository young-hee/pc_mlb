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
 * @since       2015. 6. 23
 */
package com.plgrim.ncp.biz.cart.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;

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
 * @author jewellig.lee
 * @since 2017. 7. 26 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CartOptChgGodResult extends AbstractResult{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8361436640817678558L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	
	
	/** 옵션변경으로 추가 */
	private String godNo; /*상품번호*/
	
	private String godTurn; /*상품번호*/
	
	private String godNm; /*상품명*/
	
	private String selectedIItmNo; /*선택된 옵션번호*/
	
	private String prdlstNm; /*품목명*/
	
	private String ordQty; /*주문수량*/
	
	private String bskNo; /*장바구니번호*/
	
	private String parentGodTurn; /*패키지형 부모상품순번*/
	
	private String pickUpShopCd; /* 픽업매장코드 */
	
	List<GodItmQtyResult> godItmList;
	


}
