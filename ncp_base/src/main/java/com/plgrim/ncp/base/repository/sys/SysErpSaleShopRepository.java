
/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://www.plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              			Date                         	Description
 * ------------------   		--------------                  ------------------
 * Generator(Generator)		2018-05-23                      
 */
package com.plgrim.ncp.base.repository.sys;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysErpSaleShop;

/**
 * The Class SysErpSaleShopRepository.
 */
@Repository
public class SysErpSaleShopRepository extends AbstractRepository {
	

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
	 * 시스템 ERP 판매 매장 상세 조회.
	 *
	 * @param sysErpSaleShop the SysErpSaleShop
	 * @return the SysErpSaleShop
	 * @throws SQLException the SQL exception
	 */
	public SysErpSaleShop selectSysErpSaleShop(SysErpSaleShop sysErpSaleShop) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysErpSaleShop", sysErpSaleShop);
	}
	
	/**
	 * 시스템 ERP 판매 매장 등록.
	 *
	 * @param sysErpSaleShop the SysErpSaleShop
	 * @throws SQLException the SQL exception
	 */
	public void insertSysErpSaleShop(SysErpSaleShop sysErpSaleShop) {
		getSession1().insert("com.plgrim.ncp.base.insertSysErpSaleShop", sysErpSaleShop);
	}
	
	/**
	 * 시스템 ERP 판매 매장 수정.
	 *
	 * @param sysErpSaleShop the SysErpSaleShop
	 * @throws SQLException the SQL exception
	 */
	public int updateSysErpSaleShop(SysErpSaleShop sysErpSaleShop) {
		return getSession1().update("com.plgrim.ncp.base.updateSysErpSaleShop", sysErpSaleShop);
	}
	
	/**
	 * 시스템 ERP 판매 매장 삭제.
	 *
	 * @param sysErpSaleShop the SysErpSaleShop
	 * @return the SysErpSaleShop
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysErpSaleShop(SysErpSaleShop sysErpSaleShop) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysErpSaleShop", sysErpSaleShop);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

