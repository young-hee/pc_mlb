
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysStdOptCnnc;

/**
 * The Class SysStdOptCnncRepository.
 */
@Repository
public class SysStdOptCnncRepository extends AbstractRepository {
	

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
	 * 시스템 표준 옵션 연결 상세 조회.
	 *
	 * @param sysStdOptCnnc the SysStdOptCnnc
	 * @return the SysStdOptCnnc
	 * @throws SQLException the SQL exception
	 */
	public SysStdOptCnnc selectSysStdOptCnnc(SysStdOptCnnc sysStdOptCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysStdOptCnnc", sysStdOptCnnc);
	}
	
	/**
	 * 시스템 표준 옵션 연결 등록.
	 *
	 * @param sysStdOptCnnc the SysStdOptCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertSysStdOptCnnc(SysStdOptCnnc sysStdOptCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertSysStdOptCnnc", sysStdOptCnnc);
	}
	
	/**
	 * 시스템 표준 옵션 연결 수정.
	 *
	 * @param sysStdOptCnnc the SysStdOptCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateSysStdOptCnnc(SysStdOptCnnc sysStdOptCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateSysStdOptCnnc", sysStdOptCnnc);
	}
	
	/**
	 * 시스템 표준 옵션 연결 삭제.
	 *
	 * @param sysStdOptCnnc the SysStdOptCnnc
	 * @return the SysStdOptCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysStdOptCnnc(SysStdOptCnnc sysStdOptCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysStdOptCnnc", sysStdOptCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

