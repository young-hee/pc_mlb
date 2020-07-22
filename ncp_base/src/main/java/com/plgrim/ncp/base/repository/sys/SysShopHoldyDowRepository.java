
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopHoldyDow;

/**
 * The Class SysShopHoldyDowRepository.
 */
@Repository
public class SysShopHoldyDowRepository extends AbstractRepository {
	

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
	 * 시스템 매장 휴일 요일 상세 조회.
	 *
	 * @param sysShopHoldyDow the SysShopHoldyDow
	 * @return the SysShopHoldyDow
	 * @throws SQLException the SQL exception
	 */
	public SysShopHoldyDow selectSysShopHoldyDow(SysShopHoldyDow sysShopHoldyDow) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysShopHoldyDow", sysShopHoldyDow);
	}
	
	/**
	 * 시스템 매장 휴일 요일 등록.
	 *
	 * @param sysShopHoldyDow the SysShopHoldyDow
	 * @throws SQLException the SQL exception
	 */
	public void insertSysShopHoldyDow(SysShopHoldyDow sysShopHoldyDow) {
		getSession1().insert("com.plgrim.ncp.base.insertSysShopHoldyDow", sysShopHoldyDow);
	}
	
	/**
	 * 시스템 매장 휴일 요일 수정.
	 *
	 * @param sysShopHoldyDow the SysShopHoldyDow
	 * @throws SQLException the SQL exception
	 */
	public int updateSysShopHoldyDow(SysShopHoldyDow sysShopHoldyDow) {
		return getSession1().update("com.plgrim.ncp.base.updateSysShopHoldyDow", sysShopHoldyDow);
	}
	
	/**
	 * 시스템 매장 휴일 요일 삭제.
	 *
	 * @param sysShopHoldyDow the SysShopHoldyDow
	 * @return the SysShopHoldyDow
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysShopHoldyDow(SysShopHoldyDow sysShopHoldyDow) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysShopHoldyDow", sysShopHoldyDow);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

