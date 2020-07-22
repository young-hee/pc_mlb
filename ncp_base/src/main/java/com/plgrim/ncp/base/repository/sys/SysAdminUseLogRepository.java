
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminUseLog;

/**
 * The Class SysAdminUseLogRepository.
 */
@Repository
public class SysAdminUseLogRepository extends AbstractRepository {
	

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
	 * 시스템 관리자 사용 로그 상세 조회.
	 *
	 * @param sysAdminUseLog the SysAdminUseLog
	 * @return the SysAdminUseLog
	 * @throws SQLException the SQL exception
	 */
	public SysAdminUseLog selectSysAdminUseLog(SysAdminUseLog sysAdminUseLog) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysAdminUseLog", sysAdminUseLog);
	}
	
	/**
	 * 시스템 관리자 사용 로그 등록.
	 *
	 * @param sysAdminUseLog the SysAdminUseLog
	 * @throws SQLException the SQL exception
	 */
	public void insertSysAdminUseLog(SysAdminUseLog sysAdminUseLog) {
		getSession1().insert("com.plgrim.ncp.base.insertSysAdminUseLog", sysAdminUseLog);
	}
	
	/**
	 * 시스템 관리자 사용 로그 수정.
	 *
	 * @param sysAdminUseLog the SysAdminUseLog
	 * @throws SQLException the SQL exception
	 */
	public int updateSysAdminUseLog(SysAdminUseLog sysAdminUseLog) {
		return getSession1().update("com.plgrim.ncp.base.updateSysAdminUseLog", sysAdminUseLog);
	}
	
	/**
	 * 시스템 관리자 사용 로그 삭제.
	 *
	 * @param sysAdminUseLog the SysAdminUseLog
	 * @return the SysAdminUseLog
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysAdminUseLog(SysAdminUseLog sysAdminUseLog) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysAdminUseLog", sysAdminUseLog);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

