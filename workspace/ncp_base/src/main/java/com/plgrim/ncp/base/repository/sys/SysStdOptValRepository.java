
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysStdOptVal;

/**
 * The Class SysStdOptValRepository.
 */
@Repository
public class SysStdOptValRepository extends AbstractRepository {
	

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
	 * 시스템 표준 옵션 값 상세 조회.
	 *
	 * @param sysStdOptVal the SysStdOptVal
	 * @return the SysStdOptVal
	 * @throws SQLException the SQL exception
	 */
	public SysStdOptVal selectSysStdOptVal(SysStdOptVal sysStdOptVal) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysStdOptVal", sysStdOptVal);
	}
	
	/**
	 * 시스템 표준 옵션 값 등록.
	 *
	 * @param sysStdOptVal the SysStdOptVal
	 * @throws SQLException the SQL exception
	 */
	public void insertSysStdOptVal(SysStdOptVal sysStdOptVal) {
		getSession1().insert("com.plgrim.ncp.base.insertSysStdOptVal", sysStdOptVal);
	}
	
	/**
	 * 시스템 표준 옵션 값 수정.
	 *
	 * @param sysStdOptVal the SysStdOptVal
	 * @throws SQLException the SQL exception
	 */
	public int updateSysStdOptVal(SysStdOptVal sysStdOptVal) {
		return getSession1().update("com.plgrim.ncp.base.updateSysStdOptVal", sysStdOptVal);
	}
	
	/**
	 * 시스템 표준 옵션 값 삭제.
	 *
	 * @param sysStdOptVal the SysStdOptVal
	 * @return the SysStdOptVal
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysStdOptVal(SysStdOptVal sysStdOptVal) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysStdOptVal", sysStdOptVal);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

