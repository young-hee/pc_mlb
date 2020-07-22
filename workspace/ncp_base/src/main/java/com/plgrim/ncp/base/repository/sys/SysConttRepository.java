
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysContt;

/**
 * The Class SysConttRepository.
 */
@Repository
public class SysConttRepository extends AbstractRepository {
	

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
	 * 시스템 컨텐츠 상세 조회.
	 *
	 * @param sysContt the SysContt
	 * @return the SysContt
	 * @throws SQLException the SQL exception
	 */
	public SysContt selectSysContt(SysContt sysContt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysContt", sysContt);
	}
	
	/**
	 * 시스템 컨텐츠 등록.
	 *
	 * @param sysContt the SysContt
	 * @throws SQLException the SQL exception
	 */
	public void insertSysContt(SysContt sysContt) {
		getSession1().insert("com.plgrim.ncp.base.insertSysContt", sysContt);
	}
	
	/**
	 * 시스템 컨텐츠 수정.
	 *
	 * @param sysContt the SysContt
	 * @throws SQLException the SQL exception
	 */
	public int updateSysContt(SysContt sysContt) {
		return getSession1().update("com.plgrim.ncp.base.updateSysContt", sysContt);
	}
	
	/**
	 * 시스템 컨텐츠 삭제.
	 *
	 * @param sysContt the SysContt
	 * @return the SysContt
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysContt(SysContt sysContt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysContt", sysContt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

