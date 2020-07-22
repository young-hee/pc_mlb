
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;

/**
 * The Class SysShopBrndRepository.
 */
@Repository
public class SysShopBrndRepository extends AbstractRepository {
	

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
	 * 시스템 매장 브랜드 상세 조회.
	 *
	 * @param sysShopBrnd the SysShopBrnd
	 * @return the SysShopBrnd
	 * @throws SQLException the SQL exception
	 */
	public SysShopBrnd selectSysShopBrnd(SysShopBrnd sysShopBrnd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysShopBrnd", sysShopBrnd);
	}
	
	/**
	 * 시스템 매장 브랜드 등록.
	 *
	 * @param sysShopBrnd the SysShopBrnd
	 * @throws SQLException the SQL exception
	 */
	public void insertSysShopBrnd(SysShopBrnd sysShopBrnd) {
		getSession1().insert("com.plgrim.ncp.base.insertSysShopBrnd", sysShopBrnd);
	}
	
	/**
	 * 시스템 매장 브랜드 수정.
	 *
	 * @param sysShopBrnd the SysShopBrnd
	 * @throws SQLException the SQL exception
	 */
	public int updateSysShopBrnd(SysShopBrnd sysShopBrnd) {
		return getSession1().update("com.plgrim.ncp.base.updateSysShopBrnd", sysShopBrnd);
	}
	
	/**
	 * 시스템 매장 브랜드 삭제.
	 *
	 * @param sysShopBrnd the SysShopBrnd
	 * @return the SysShopBrnd
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysShopBrnd(SysShopBrnd sysShopBrnd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysShopBrnd", sysShopBrnd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

