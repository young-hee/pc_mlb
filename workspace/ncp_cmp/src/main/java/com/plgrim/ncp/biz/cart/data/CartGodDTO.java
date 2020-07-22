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
public class CartGodDTO extends AbstractDTO {

	
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1084962440434433228L;
	
	/** 상품 순번 */
	private int godTurn;
	
	/** 상품번호 */
	private String godNo;
	
	/** 단품 번호 */
	private String itmNo;
	
	/** 패키지 상품 유형 코드 */
	private String pckageGodTpCd;
	
	/** 추가 구성 상품 수량 */
	private Long itmQty;
		
	/** 구성 상품 수량 */
	private Long cpstGodQty;
	 
	/** 정렬 순서 */
	private int sortSeq;
	
	/** 구성 상품 수량 */
	private int cpstGodTurn;
	
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
