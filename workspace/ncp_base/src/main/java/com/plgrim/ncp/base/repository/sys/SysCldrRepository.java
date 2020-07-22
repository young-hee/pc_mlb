
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysCldr;

/**
 * The Class SysCldrRepository.
 */
@Repository
public class SysCldrRepository extends AbstractRepository {
	

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
	 * 시스템 달력 상세 조회.
	 *
	 * @param sysCldr the SysCldr
	 * @return the SysCldr
	 * @throws SQLException the SQL exception
	 */
	public SysCldr selectSysCldr(SysCldr sysCldr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysCldr", sysCldr);
	}
	
	/**
	 * 시스템 달력 등록.
	 *
	 * @param sysCldr the SysCldr
	 * @throws SQLException the SQL exception
	 */
	public void insertSysCldr(SysCldr sysCldr) {
		getSession1().insert("com.plgrim.ncp.base.insertSysCldr", sysCldr);
	}
	
	/**
	 * 시스템 달력 수정.
	 *
	 * @param sysCldr the SysCldr
	 * @throws SQLException the SQL exception
	 */
	public int updateSysCldr(SysCldr sysCldr) {
		return getSession1().update("com.plgrim.ncp.base.updateSysCldr", sysCldr);
	}
	
	/**
	 * 시스템 달력 삭제.
	 *
	 * @param sysCldr the SysCldr
	 * @return the SysCldr
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysCldr(SysCldr sysCldr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysCldr", sysCldr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

