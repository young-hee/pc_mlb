
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysExcpCd;

/**
 * The Class SysExcpCdRepository.
 */
@Repository
public class SysExcpCdRepository extends AbstractRepository {
	

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
	 * 시스템 예외 코드 상세 조회.
	 *
	 * @param sysExcpCd the SysExcpCd
	 * @return the SysExcpCd
	 * @throws SQLException the SQL exception
	 */
	public SysExcpCd selectSysExcpCd(SysExcpCd sysExcpCd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysExcpCd", sysExcpCd);
	}
	
	/**
	 * 시스템 예외 코드 등록.
	 *
	 * @param sysExcpCd the SysExcpCd
	 * @throws SQLException the SQL exception
	 */
	public void insertSysExcpCd(SysExcpCd sysExcpCd) {
		getSession1().insert("com.plgrim.ncp.base.insertSysExcpCd", sysExcpCd);
	}
	
	/**
	 * 시스템 예외 코드 수정.
	 *
	 * @param sysExcpCd the SysExcpCd
	 * @throws SQLException the SQL exception
	 */
	public int updateSysExcpCd(SysExcpCd sysExcpCd) {
		return getSession1().update("com.plgrim.ncp.base.updateSysExcpCd", sysExcpCd);
	}
	
	/**
	 * 시스템 예외 코드 삭제.
	 *
	 * @param sysExcpCd the SysExcpCd
	 * @return the SysExcpCd
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysExcpCd(SysExcpCd sysExcpCd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysExcpCd", sysExcpCd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

