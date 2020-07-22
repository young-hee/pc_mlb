
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysStdOpt;

/**
 * The Class SysStdOptRepository.
 */
@Repository
public class SysStdOptRepository extends AbstractRepository {
	

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
	 * 시스템 표준 옵션 상세 조회.
	 *
	 * @param sysStdOpt the SysStdOpt
	 * @return the SysStdOpt
	 * @throws SQLException the SQL exception
	 */
	public SysStdOpt selectSysStdOpt(SysStdOpt sysStdOpt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysStdOpt", sysStdOpt);
	}
	
	/**
	 * 시스템 표준 옵션 등록.
	 *
	 * @param sysStdOpt the SysStdOpt
	 * @throws SQLException the SQL exception
	 */
	public void insertSysStdOpt(SysStdOpt sysStdOpt) {
		getSession1().insert("com.plgrim.ncp.base.insertSysStdOpt", sysStdOpt);
	}
	
	/**
	 * 시스템 표준 옵션 수정.
	 *
	 * @param sysStdOpt the SysStdOpt
	 * @throws SQLException the SQL exception
	 */
	public int updateSysStdOpt(SysStdOpt sysStdOpt) {
		return getSession1().update("com.plgrim.ncp.base.updateSysStdOpt", sysStdOpt);
	}
	
	/**
	 * 시스템 표준 옵션 삭제.
	 *
	 * @param sysStdOpt the SysStdOpt
	 * @return the SysStdOpt
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysStdOpt(SysStdOpt sysStdOpt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysStdOpt", sysStdOpt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

