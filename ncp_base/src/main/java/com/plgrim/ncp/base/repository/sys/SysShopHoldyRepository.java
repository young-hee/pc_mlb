
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopHoldy;

/**
 * The Class SysShopHoldyRepository.
 */
@Repository
public class SysShopHoldyRepository extends AbstractRepository {
	

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
	 * 시스템 매장 휴일 상세 조회.
	 *
	 * @param sysShopHoldy the SysShopHoldy
	 * @return the SysShopHoldy
	 * @throws SQLException the SQL exception
	 */
	public SysShopHoldy selectSysShopHoldy(SysShopHoldy sysShopHoldy) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysShopHoldy", sysShopHoldy);
	}
	
	/**
	 * 시스템 매장 휴일 등록.
	 *
	 * @param sysShopHoldy the SysShopHoldy
	 * @throws SQLException the SQL exception
	 */
	public void insertSysShopHoldy(SysShopHoldy sysShopHoldy) {
		getSession1().insert("com.plgrim.ncp.base.insertSysShopHoldy", sysShopHoldy);
	}
	
	/**
	 * 시스템 매장 휴일 수정.
	 *
	 * @param sysShopHoldy the SysShopHoldy
	 * @throws SQLException the SQL exception
	 */
	public int updateSysShopHoldy(SysShopHoldy sysShopHoldy) {
		return getSession1().update("com.plgrim.ncp.base.updateSysShopHoldy", sysShopHoldy);
	}
	
	/**
	 * 시스템 매장 휴일 삭제.
	 *
	 * @param sysShopHoldy the SysShopHoldy
	 * @return the SysShopHoldy
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysShopHoldy(SysShopHoldy sysShopHoldy) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysShopHoldy", sysShopHoldy);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

