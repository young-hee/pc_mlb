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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodExtend;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmBundleDcCnd;

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
public class CartGodPrmResult extends AbstractResult{
	
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	 /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7734728490954318468L;
	 
	 /** The prm. */
 	Prm prm;
	 
    Prm coin;

	 /** The god no list. */
 	List<String> godNoList = new ArrayList<String>();
	 
	 /** The god no. */
 	private String godNo;
	 
	 /** The grp seq. */
 	private int grpSeq;
	 
	 /** The bundle qty. */
 	private int bundleQty;
	 
	 /** The prm dc amt. */
 	private BigDecimal prmDcAmt;
	 
	 /** The prm aply amt. */
 	private BigDecimal prmAplyAmt;
	 
	 /** The prm bundle dc cnd list. */
 	List<PrmBundleDcCnd> prmBundleDcCndList;
	 
	 /** The gift list. */
 	List<BskGodExtend> giftList;
	 
	 /** 교차할인 그룹 코드 */
 	private String crsGodGrpCd;
	 
	 /** 교차할인 1그룹 상품 수 */
 	private int grp1Cnt;
	 
	 /** 교차할인 2그룹 상품 수 */
 	private int grp2Cnt;
	 
	 /** The god itm qty. */
 	private int godItmQty = 0;
	 
	 /** The ord amt. */
 	private BigDecimal ordAmt = BigDecimal.ZERO;
	 
	 /** The total dc amt. */
 	private BigDecimal totalDcAmt = BigDecimal.ZERO; 
	 
	 
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
