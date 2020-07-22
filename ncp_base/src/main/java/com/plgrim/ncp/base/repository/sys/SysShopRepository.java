
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysShop;

/**
 * The Class SysShopRepository.
 */
@Repository
public class SysShopRepository extends AbstractRepository {
	

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
	 * 시스템 매장 상세 조회.
	 *
	 * @param sysShop the SysShop
	 * @return the SysShop
	 * @throws SQLException the SQL exception
	 */
	public SysShop selectSysShop(SysShop sysShop) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysShop", sysShop);
	}
	
	/**
	 * 시스템 매장 등록.
	 *
	 * @param sysShop the SysShop
	 * @throws SQLException the SQL exception
	 */
	public void insertSysShop(SysShop sysShop) {
		getSession1().insert("com.plgrim.ncp.base.insertSysShop", sysShop);
	}
	
	/**
	 * 시스템 매장 수정.
	 *
	 * @param sysShop the SysShop
	 * @throws SQLException the SQL exception
	 */
	public int updateSysShop(SysShop sysShop) {
		return getSession1().update("com.plgrim.ncp.base.updateSysShop", sysShop);
	}
	
	/**
	 * 시스템 매장 삭제.
	 *
	 * @param sysShop the SysShop
	 * @return the SysShop
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysShop(SysShop sysShop) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysShop", sysShop);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

