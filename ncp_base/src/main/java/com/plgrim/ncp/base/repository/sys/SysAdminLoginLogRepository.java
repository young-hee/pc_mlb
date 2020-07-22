
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminLoginLog;

/**
 * The Class SysAdminLoginLogRepository.
 */
@Repository
public class SysAdminLoginLogRepository extends AbstractRepository {
	

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
	 * 시스템 관리자 로그인 로그 상세 조회.
	 *
	 * @param sysAdminLoginLog the SysAdminLoginLog
	 * @return the SysAdminLoginLog
	 * @throws SQLException the SQL exception
	 */
	public SysAdminLoginLog selectSysAdminLoginLog(SysAdminLoginLog sysAdminLoginLog) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysAdminLoginLog", sysAdminLoginLog);
	}
	
	/**
	 * 시스템 관리자 로그인 로그 등록.
	 *
	 * @param sysAdminLoginLog the SysAdminLoginLog
	 * @throws SQLException the SQL exception
	 */
	public void insertSysAdminLoginLog(SysAdminLoginLog sysAdminLoginLog) {
		getSession1().insert("com.plgrim.ncp.base.insertSysAdminLoginLog", sysAdminLoginLog);
	}
	
	/**
	 * 시스템 관리자 로그인 로그 수정.
	 *
	 * @param sysAdminLoginLog the SysAdminLoginLog
	 * @throws SQLException the SQL exception
	 */
	public int updateSysAdminLoginLog(SysAdminLoginLog sysAdminLoginLog) {
		return getSession1().update("com.plgrim.ncp.base.updateSysAdminLoginLog", sysAdminLoginLog);
	}
	
	/**
	 * 시스템 관리자 로그인 로그 삭제.
	 *
	 * @param sysAdminLoginLog the SysAdminLoginLog
	 * @return the SysAdminLoginLog
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysAdminLoginLog(SysAdminLoginLog sysAdminLoginLog) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysAdminLoginLog", sysAdminLoginLog);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

