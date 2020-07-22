
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;

/**
 * The Class SysInflowRepository.
 */
@Repository
public class SysInflowRepository extends AbstractRepository {
	

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
	 * 시스템 유입 상세 조회.
	 *
	 * @param sysInflow the SysInflow
	 * @return the SysInflow
	 * @throws SQLException the SQL exception
	 */
	public SysInflow selectSysInflow(SysInflow sysInflow) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysInflow", sysInflow);
	}
	
	/**
	 * 시스템 유입 등록.
	 *
	 * @param sysInflow the SysInflow
	 * @throws SQLException the SQL exception
	 */
	public void insertSysInflow(SysInflow sysInflow) {
		getSession1().insert("com.plgrim.ncp.base.insertSysInflow", sysInflow);
	}
	
	/**
	 * 시스템 유입 수정.
	 *
	 * @param sysInflow the SysInflow
	 * @throws SQLException the SQL exception
	 */
	public int updateSysInflow(SysInflow sysInflow) {
		return getSession1().update("com.plgrim.ncp.base.updateSysInflow", sysInflow);
	}
	
	/**
	 * 시스템 유입 삭제.
	 *
	 * @param sysInflow the SysInflow
	 * @return the SysInflow
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysInflow(SysInflow sysInflow) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysInflow", sysInflow);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

