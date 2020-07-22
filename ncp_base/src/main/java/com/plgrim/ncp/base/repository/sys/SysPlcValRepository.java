
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysPlcVal;

/**
 * The Class SysPlcValRepository.
 */
@Repository
public class SysPlcValRepository extends AbstractRepository {
	

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
	 * 시스템 정책 값 상세 조회.
	 *
	 * @param sysPlcVal the SysPlcVal
	 * @return the SysPlcVal
	 * @throws SQLException the SQL exception
	 */
	public SysPlcVal selectSysPlcVal(SysPlcVal sysPlcVal) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysPlcVal", sysPlcVal);
	}
	
	/**
	 * 시스템 정책 값 등록.
	 *
	 * @param sysPlcVal the SysPlcVal
	 * @throws SQLException the SQL exception
	 */
	public void insertSysPlcVal(SysPlcVal sysPlcVal) {
		getSession1().insert("com.plgrim.ncp.base.insertSysPlcVal", sysPlcVal);
	}
	
	/**
	 * 시스템 정책 값 수정.
	 *
	 * @param sysPlcVal the SysPlcVal
	 * @throws SQLException the SQL exception
	 */
	public int updateSysPlcVal(SysPlcVal sysPlcVal) {
		return getSession1().update("com.plgrim.ncp.base.updateSysPlcVal", sysPlcVal);
	}
	
	/**
	 * 시스템 정책 값 삭제.
	 *
	 * @param sysPlcVal the SysPlcVal
	 * @return the SysPlcVal
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysPlcVal(SysPlcVal sysPlcVal) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysPlcVal", sysPlcVal);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

