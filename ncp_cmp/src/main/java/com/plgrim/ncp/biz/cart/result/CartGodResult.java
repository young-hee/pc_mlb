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

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;

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
public class CartGodResult extends AbstractResult{
    
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4625044104179721644L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/** The bsk god. */
	BskGod bskGod;
	
	/** The grp seq. */
	private int grpSeq;
	
	/** The sub grp seq. */
	private int subGrpSeq;
	
	private int dlvGrpRnum;
	private int dlvGrpCnt;
	
	/** The sub grp cnt. */
	private int subGrpCnt;
	
	/** The parent god turn. */
	private int parentGodTurn;
	
	/** The pckage god tp cd. */
	private String pckageGodTpCd;
	
	/** The sort seq. */
	private String sortSeq;
	
	/** The god itm. */
	GodItm godItm;
	
	/** The god. */
	God god;
	
	/** The brnd nm. */
	private String brndNm;
	
	/** The com dmstc dlv cst plc. */
	ComDmstcDlvCstPlc comDmstcDlvCstPlc;
	
	/** The real inv qty. */
	private int realInvQty;
	
	/** The shop inv qty. */
	private int shopInvQty;
	
	/** The cdc inv qty. */
	private int cdcInvQty;
	
	/** The shop. */
	private SysShopBrnd shop;
	
	/** The shop nm. */
	private String shopNm;
	private String shopId;
	private String shopTelAreaNo;
	private String shopTelTlofNo;
	private String shopTelTlofWthnNo;
	
 

	/** The prm no. */
	private String prmNo;
	
	/** The prm aply prc. */
	private BigDecimal prmAplyPrc;
	
	/** The img url. */
	private String imgUrl;
	
	/** 판매 여부 */
	private String sellYn;
	
	/** 재고 보유 여부 */
	private String invYn;
	
	/** 상품 가격 유형 */
	private String prcType ="GNRL";
	
	/** 상품가격 */
	private BigDecimal price;
	
	/** The pkup day. */
	private String pkupDay;
	
	/** The inv type. */
	private String invType;
	
	/** The crnt day. */
	private String crntDay;
	
	/** The sido cd. */
	private String sidoCd;
	
	private int sumItmQty;
	private int sumEtcItmQty;
	
	private String wishYn = "N";

	private OrdGodExtend ordGodExtend;




	ComDmstcDlvCstPlc ovseaComDmstcDlvCstPlc;//해외배송 국내배송비 정책

	ComOvseaDlvCstPlc comOvseaDlvCstPlc;//해외배송정책

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
	 * #34425 로 추가 201-01-13
	 */
	private String ovseaDspStatCd;//해외전시상태코드 god_langby_god_nm table 
	private String trsltStatCd;//번역상태코드 god_langby_god_nm table
	private String ovseaDlvPsbYn;//해외배송가능여부 god table
	
	
	private String pkupShopYn;
	
	
	//#55988 로 추가
	private String holdyYn;
	private String shopEndHhmm;
	private String shopAddr;
	private String shopBegHhmm;
	
	
	private int hoffInvQty;
	private int etcInvQty;

	private int rowSpanCount;
}
