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
public class ItmQtyResult {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/** 공통필드 */
	private String itmNo;
	private String shopId; /*재고위치*/
	private int invQty; /*퀵배송 가용재고(재고위치재고-판매예정재고-안전재고)*/
	
}
