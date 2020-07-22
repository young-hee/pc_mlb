
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysExcpCdGrp;

/**
 * The Class SysExcpCdGrpRepository.
 */
@Repository
public class SysExcpCdGrpRepository extends AbstractRepository {
	

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
	 * 시스템 예외 코드 그룹 상세 조회.
	 *
	 * @param sysExcpCdGrp the SysExcpCdGrp
	 * @return the SysExcpCdGrp
	 * @throws SQLException the SQL exception
	 */
	public SysExcpCdGrp selectSysExcpCdGrp(SysExcpCdGrp sysExcpCdGrp) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysExcpCdGrp", sysExcpCdGrp);
	}
	
	/**
	 * 시스템 예외 코드 그룹 등록.
	 *
	 * @param sysExcpCdGrp the SysExcpCdGrp
	 * @throws SQLException the SQL exception
	 */
	public void insertSysExcpCdGrp(SysExcpCdGrp sysExcpCdGrp) {
		getSession1().insert("com.plgrim.ncp.base.insertSysExcpCdGrp", sysExcpCdGrp);
	}
	
	/**
	 * 시스템 예외 코드 그룹 수정.
	 *
	 * @param sysExcpCdGrp the SysExcpCdGrp
	 * @throws SQLException the SQL exception
	 */
	public int updateSysExcpCdGrp(SysExcpCdGrp sysExcpCdGrp) {
		return getSession1().update("com.plgrim.ncp.base.updateSysExcpCdGrp", sysExcpCdGrp);
	}
	
	/**
	 * 시스템 예외 코드 그룹 삭제.
	 *
	 * @param sysExcpCdGrp the SysExcpCdGrp
	 * @return the SysExcpCdGrp
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysExcpCdGrp(SysExcpCdGrp sysExcpCdGrp) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysExcpCdGrp", sysExcpCdGrp);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

