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
package com.plgrim.ncp.biz.vendor.data;

import java.util.ArrayList;
import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopPhoto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * VendorBrndRegDTO
 * @author lss
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class VendorBrndRegDTO extends AbstractDTO {

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
	 * 시스템 매장 브랜드 이미지
	 */
	private List<SysShopPhoto> sysShopPhotoList = new ArrayList<SysShopPhoto>();
	
	/**
	 * 시스템 매장 브랜드 디스플레이 이미지 
	 */
	private List<SysShopPhoto> sysShopDsplPhotoList = new ArrayList<SysShopPhoto>();
	
	/**
	 * 시스템 매장 브랜드 삭제 이미지
	 */
	private List<SysShopPhoto> sysShopPhotoDeleteList = new ArrayList<SysShopPhoto>();;
	
	
	
	

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
