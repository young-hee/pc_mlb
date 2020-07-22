/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 5. 11       
 */
package com.plgrim.ncp.biz.vendor.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShop;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrndFeeHist;

/**
 * [매장]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author sy59.gim
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SysShopResult extends AbstractResult {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5580016099692967180L;
	
	/**
	 * 시스템 매장
	 */
	private SysShop sysShop;			
	
	/**
	 * 시스템 매장 브랜드
	 */
	private SysShopBrnd sysShopBrnd;
	
	/* 시스템 매장 브랜드 이력*/
	private SysShopBrndFeeHist sysShopBrndFeeHist;
	
	/**
	 * 시스템 브랜드
	 */
	private SysBrnd sysBrnd;			
	
	/**
	 * 브랜드 경로
	 */
	private String brndPath;			 
	
	/**
	 * 등록자명
	 */
	private String regterNm;
	
	/**
	 * 수정자명
	 */
	private String udterNm;
	
	/**
	 * 등록일(YYYY-MM-DD HH24:MI)
	 */
	private String viewRegDt;
	
	/**
	 * 수정일(YYYY-MM-DD HH24:MI)
	 */
	private String viewUdtDt;
	
	/**
	 * 배송 매장 수수료 사용 금액 또는 율
	 */
	private java.math.BigDecimal dlvShopFeeUse;
	
	/**
	 * 픽업 매장 수수료 사용 금액 또는 율
	 */
	private java.math.BigDecimal pkupShopFeeUse;
	
	/**
	 * 픽업 매장 물류 수수료 사용 금액 또는 율
	 */
	private java.math.BigDecimal pkupShopLgsFeeUse;
	
	/**
	 * 변경 이전 매장 수수료 구분 코드
	 */
	private String modBfShopFeeSectCd;
	
	/**
	 * 변경 이후 매장 수수료 금액
	 */
	private java.math.BigDecimal modAfShopFeeAmt;
	
	/**
	 * 변경 이후 매장 수수료 율
	 */
	private java.math.BigDecimal modAfShopFeeRt;
	
	/**
	 * 변경 이전 매장 수수료 금액
	 */
	private java.math.BigDecimal modBfShopFeeAmt;
	
	/**
	 * 변경 이전 매장 수수료 율
	 */
	private java.math.BigDecimal modBfShopFeeRt;
	 
	
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
