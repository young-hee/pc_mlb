
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysPlc;

/**
 * The Class SysPlcRepository.
 */
@Repository
public class SysPlcRepository extends AbstractRepository {
	

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
	 * 시스템 정책 상세 조회.
	 *
	 * @param sysPlc the SysPlc
	 * @return the SysPlc
	 * @throws SQLException the SQL exception
	 */
	public SysPlc selectSysPlc(SysPlc sysPlc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysPlc", sysPlc);
	}
	
	/**
	 * 시스템 정책 등록.
	 *
	 * @param sysPlc the SysPlc
	 * @throws SQLException the SQL exception
	 */
	public void insertSysPlc(SysPlc sysPlc) {
		getSession1().insert("com.plgrim.ncp.base.insertSysPlc", sysPlc);
	}
	
	/**
	 * 시스템 정책 수정.
	 *
	 * @param sysPlc the SysPlc
	 * @throws SQLException the SQL exception
	 */
	public int updateSysPlc(SysPlc sysPlc) {
		return getSession1().update("com.plgrim.ncp.base.updateSysPlc", sysPlc);
	}
	
	/**
	 * 시스템 정책 삭제.
	 *
	 * @param sysPlc the SysPlc
	 * @return the SysPlc
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysPlc(SysPlc sysPlc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysPlc", sysPlc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

