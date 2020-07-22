/* Copyright (c) 2013 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 *                       
 */
package com.plgrim.ncp.biz.vendor.result;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndPrdlst;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndPrdlstHist;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPrdlstCd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShop;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopPhoto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * VendorBrndListResult
 * @author lss
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class VendorBrndListResult extends AbstractResult {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */	
	/**
	 * 시스템 매장 브랜드 Entity
	 */
	private SysShopBrnd sysShopBrnd;
	
	/**
	 * 시스템 브랜드 Entity
	 */
	private SysBrnd sysBrnd;
	
	/**
	 * 시스템 매장 Entity
	 */
	private SysShop sysShop;
	
	/**
	 * 시스템 품목 Entity
	 */
	private SysPrdlstCd sysPrdlstCd;

	/**
	 * 시스템 브랜드 품목이력 Entity
	 */
	private SysBrndPrdlstHist sysBrndPrdlstHist;

	/**
	 * 시스템 브랜드 품목 Entity
	 */
	private SysBrndPrdlst sysBrndPrdlst;

	/**
	 * 시스템 브랜드&품목 수수료
	 */
	private java.math.BigDecimal shopRepairFee;

	/**
	 * 시스템 브랜드&품목 더미 컬럼
	 */
	private String sysBrndPrdDummy = "개정이력";

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
	 * 쿠폰명
	 */
	private String cpnPrmNm;
	
	/**
	 * 시스템 매장 브랜드 이미지 Entity
	 */
	private List<SysShopPhoto> sysShopPhotoList;
	
	/**
	 * 시스템 매장 브랜드 디스플레이 이미지 Entity
	 */
	private List<SysShopPhoto> sysShopDsplPhotoList;

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
