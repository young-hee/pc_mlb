/* Copyright (c) 2015 plgrim, Inc.
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
 * jackie(jackie)			20150529                      
 */
package com.plgrim.ncp.commons.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysErpSaleShop;

/**
 * The Class SysErpSaleShopRepository.
 */
@Repository
public class SysErpSaleRepository extends AbstractRepository {
	

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
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

	/**
	 * 시스템 ERP 판매 매장 조회.
	 *
	 * @param sysErpSaleShop the SysErpSaleShop
	 * @return the SysErpSaleShop
	 * @throws SQLException the SQL exception
	 */
	public List<SysErpSaleShop> selectSysErpSaleShopList(SysErpSaleShop sysErpSaleShop) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.sysshop.selectSysErpSaleShopList", sysErpSaleShop);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}
