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

import com.plgrim.ncp.base.abstracts.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 장바구니 데이터 적재 DTO
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author jcannon.lee
 * @since 2016.06.08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CartGodHistDTO extends AbstractEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	// bsk_god_hist 필드
	private Long bskGodHistSn;
	private String bskNo;
	private String mbrNo;
	private String godNo;
	private String itmNo;
	private java.util.Date bskRegDt;
	private String pckageGodYn;
	private String pckageGodTpCd;
	private Long upperBskGodHistSn;
	private String dlvSectCd;
	private String pkupShopSn;
	private String pkupShopVisitDate;
	private String trnscTpCd;
	private String regtrId;
	private java.util.Date regDt;
	private String udterId;
	private java.util.Date udtDt;
	
	// 추가필드
	private Long godTurn;

}
