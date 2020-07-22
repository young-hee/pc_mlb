
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;

/**
 * The Class SysStplatRepository.
 */
@Repository
public class SysStplatRepository extends AbstractRepository {
	

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
	 * 시스템 약관 상세 조회.
	 *
	 * @param sysStplat the SysStplat
	 * @return the SysStplat
	 * @throws SQLException the SQL exception
	 */
	public SysStplat selectSysStplat(SysStplat sysStplat) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysStplat", sysStplat);
	}
	
	/**
	 * 시스템 약관 등록.
	 *
	 * @param sysStplat the SysStplat
	 * @throws SQLException the SQL exception
	 */
	public void insertSysStplat(SysStplat sysStplat) {
		getSession1().insert("com.plgrim.ncp.base.insertSysStplat", sysStplat);
	}
	
	/**
	 * 시스템 약관 수정.
	 *
	 * @param sysStplat the SysStplat
	 * @throws SQLException the SQL exception
	 */
	public int updateSysStplat(SysStplat sysStplat) {
		return getSession1().update("com.plgrim.ncp.base.updateSysStplat", sysStplat);
	}
	
	/**
	 * 시스템 약관 삭제.
	 *
	 * @param sysStplat the SysStplat
	 * @return the SysStplat
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysStplat(SysStplat sysStplat) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysStplat", sysStplat);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

