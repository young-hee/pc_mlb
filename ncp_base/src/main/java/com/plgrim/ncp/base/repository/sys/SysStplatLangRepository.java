
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplatLang;

/**
 * The Class SysStplatLangRepository.
 */
@Repository
public class SysStplatLangRepository extends AbstractRepository {
	

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
	 * 시스템 약관 언어 상세 조회.
	 *
	 * @param sysStplatLang the SysStplatLang
	 * @return the SysStplatLang
	 * @throws SQLException the SQL exception
	 */
	public SysStplatLang selectSysStplatLang(SysStplatLang sysStplatLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysStplatLang", sysStplatLang);
	}
	
	/**
	 * 시스템 약관 언어 등록.
	 *
	 * @param sysStplatLang the SysStplatLang
	 * @throws SQLException the SQL exception
	 */
	public void insertSysStplatLang(SysStplatLang sysStplatLang) {
		getSession1().insert("com.plgrim.ncp.base.insertSysStplatLang", sysStplatLang);
	}
	
	/**
	 * 시스템 약관 언어 수정.
	 *
	 * @param sysStplatLang the SysStplatLang
	 * @throws SQLException the SQL exception
	 */
	public int updateSysStplatLang(SysStplatLang sysStplatLang) {
		return getSession1().update("com.plgrim.ncp.base.updateSysStplatLang", sysStplatLang);
	}
	
	/**
	 * 시스템 약관 언어 삭제.
	 *
	 * @param sysStplatLang the SysStplatLang
	 * @return the SysStplatLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysStplatLang(SysStplatLang sysStplatLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysStplatLang", sysStplatLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

