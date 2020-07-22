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

import java.util.List;

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
 * @author jewellig.lee
 * @since 2017. 7. 26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GodItmQtyResult {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5732549913298457149L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/** 공통필드 */
	private String itmNo;
	private int minOrdQty; /*최소 주문 수량 */
	private int maxOrdQty; /*최대 주문 수량 */

	/** 픽업매장 */
	private long shopInvQty; /*지정매장재고*/

	/** 일반배송 */
	private long realInvQty; /*일반배송재고*/
	private long etcInvQty; /*일반배송매장재고*/
	private long hoffInvQty; /*본사재고*/






	/** 옵션변경으로 추가 */
	private String godNo; /*상품번호*/
	private String itmNm; /*옵션명*/


	/** 퀵배송으로 추가 */
	private String chkShopId; /*체크용 재고위치(X30004 이 FC08,FC01 나눠져있음 퀵배송매장 포함)*/
	private String shopId; /*재고위치*/
	private int invQty; /*퀵배송 가용재고(재고위치재고-판매예정재고-안전재고)*/
	
	/** 퀵배송 옵션변경으로 추가 */
	List<ItmQtyResult> invQtyList;


}
