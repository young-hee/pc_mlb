
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysLang;

/**
 * The Class SysLangRepository.
 */
@Repository
public class SysLangRepository extends AbstractRepository {
	

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
	 * 시스템 언어 상세 조회.
	 *
	 * @param sysLang the SysLang
	 * @return the SysLang
	 * @throws SQLException the SQL exception
	 */
	public SysLang selectSysLang(SysLang sysLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysLang", sysLang);
	}
	
	/**
	 * 시스템 언어 등록.
	 *
	 * @param sysLang the SysLang
	 * @throws SQLException the SQL exception
	 */
	public void insertSysLang(SysLang sysLang) {
		getSession1().insert("com.plgrim.ncp.base.insertSysLang", sysLang);
	}
	
	/**
	 * 시스템 언어 수정.
	 *
	 * @param sysLang the SysLang
	 * @throws SQLException the SQL exception
	 */
	public int updateSysLang(SysLang sysLang) {
		return getSession1().update("com.plgrim.ncp.base.updateSysLang", sysLang);
	}
	
	/**
	 * 시스템 언어 삭제.
	 *
	 * @param sysLang the SysLang
	 * @return the SysLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysLang(SysLang sysLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysLang", sysLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

