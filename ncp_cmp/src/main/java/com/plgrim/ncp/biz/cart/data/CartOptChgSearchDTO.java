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
 * @since       2015. 6. 20       
 */
package com.plgrim.ncp.biz.cart.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

/**
 * [장바구니 등록용 DTO]
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
public class CartOptChgSearchDTO extends AbstractDTO {

	
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 379716318207916252L;	

	
	
	/** 장바구니번호 */
	private String bskNo;
	
	/** 장바구니번호 */
	private String pckageGodYn;
	
	/** 옵션정보 */
	private String optInfo;
	
	/** 배송구분코드 */
	private String dlvSectCd;
	
	
	/** 상품 순번 */
	private int godTurn;
	
	/** 상품번호 */
	private String godNo;
	
	/** 단품번호 */
	private String itmNo;
	
	/** 주문수량 */
	private Long ordQty;
			 
	/** 정렬 순서 */
	private int sortSeq;
	
	/** 처음조회시 Y 첫 상품 정해지면 N */
	private String isFirst;
	
	/** 재고위치리스트 픽업매장코드,FC01,FC08*/
	List<String> invLocList;
	
	
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
