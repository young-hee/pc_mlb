
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdmin;

/**
 * The Class SysAdminRepository.
 */
@Repository
public class SysAdminRepository extends AbstractRepository {
	

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
	 * 시스템 관리자 상세 조회.
	 *
	 * @param sysAdmin the SysAdmin
	 * @return the SysAdmin
	 * @throws SQLException the SQL exception
	 */
	public SysAdmin selectSysAdmin(SysAdmin sysAdmin) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysAdmin", sysAdmin);
	}
	
	/**
	 * 시스템 관리자 등록.
	 *
	 * @param sysAdmin the SysAdmin
	 * @throws SQLException the SQL exception
	 */
	public void insertSysAdmin(SysAdmin sysAdmin) {
		getSession1().insert("com.plgrim.ncp.base.insertSysAdmin", sysAdmin);
	}
	
	/**
	 * 시스템 관리자 수정.
	 *
	 * @param sysAdmin the SysAdmin
	 * @throws SQLException the SQL exception
	 */
	public int updateSysAdmin(SysAdmin sysAdmin) {
		return getSession1().update("com.plgrim.ncp.base.updateSysAdmin", sysAdmin);
	}
	
	/**
	 * 시스템 관리자 삭제.
	 *
	 * @param sysAdmin the SysAdmin
	 * @return the SysAdmin
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysAdmin(SysAdmin sysAdmin) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysAdmin", sysAdmin);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

