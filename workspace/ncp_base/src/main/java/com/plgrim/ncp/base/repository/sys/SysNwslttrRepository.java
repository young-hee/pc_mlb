
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
 * Generator(Generator)		2018-11-08                      
 */
package com.plgrim.ncp.base.repository.sys;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNwslttr;

/**
 * The Class SysNwslttrRepository.
 */
@Repository
public class SysNwslttrRepository extends AbstractRepository {
	

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
	 * 시스템 뉴스레터 상세 조회.
	 *
	 * @param sysNwslttr the SysNwslttr
	 * @return the SysNwslttr
	 * @throws SQLException the SQL exception
	 */
	public SysNwslttr selectSysNwslttr(SysNwslttr sysNwslttr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysNwslttr", sysNwslttr);
	}
	
	/**
	 * 시스템 뉴스레터 등록.
	 *
	 * @param sysNwslttr the SysNwslttr
	 * @throws SQLException the SQL exception
	 */
	public void insertSysNwslttr(SysNwslttr sysNwslttr) {
		getSession1().insert("com.plgrim.ncp.base.insertSysNwslttr", sysNwslttr);
	}
	
	/**
	 * 시스템 뉴스레터 수정.
	 *
	 * @param sysNwslttr the SysNwslttr
	 * @throws SQLException the SQL exception
	 */
	public int updateSysNwslttr(SysNwslttr sysNwslttr) {
		return getSession1().update("com.plgrim.ncp.base.updateSysNwslttr", sysNwslttr);
	}
	
	/**
	 * 시스템 뉴스레터 삭제.
	 *
	 * @param sysNwslttr the SysNwslttr
	 * @return the SysNwslttr
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysNwslttr(SysNwslttr sysNwslttr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysNwslttr", sysNwslttr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

