
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminBrnd;

/**
 * The Class SysAdminBrndRepository.
 */
@Repository
public class SysAdminBrndRepository extends AbstractRepository {
	

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
	 * 시스템 관리자 브랜드 상세 조회.
	 *
	 * @param sysAdminBrnd the SysAdminBrnd
	 * @return the SysAdminBrnd
	 * @throws SQLException the SQL exception
	 */
	public SysAdminBrnd selectSysAdminBrnd(SysAdminBrnd sysAdminBrnd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysAdminBrnd", sysAdminBrnd);
	}
	
	/**
	 * 시스템 관리자 브랜드 등록.
	 *
	 * @param sysAdminBrnd the SysAdminBrnd
	 * @throws SQLException the SQL exception
	 */
	public void insertSysAdminBrnd(SysAdminBrnd sysAdminBrnd) {
		getSession1().insert("com.plgrim.ncp.base.insertSysAdminBrnd", sysAdminBrnd);
	}
	
	/**
	 * 시스템 관리자 브랜드 수정.
	 *
	 * @param sysAdminBrnd the SysAdminBrnd
	 * @throws SQLException the SQL exception
	 */
	public int updateSysAdminBrnd(SysAdminBrnd sysAdminBrnd) {
		return getSession1().update("com.plgrim.ncp.base.updateSysAdminBrnd", sysAdminBrnd);
	}
	
	/**
	 * 시스템 관리자 브랜드 삭제.
	 *
	 * @param sysAdminBrnd the SysAdminBrnd
	 * @return the SysAdminBrnd
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysAdminBrnd(SysAdminBrnd sysAdminBrnd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysAdminBrnd", sysAdminBrnd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

