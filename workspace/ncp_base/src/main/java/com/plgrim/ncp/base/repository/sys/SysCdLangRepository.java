
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysCdLang;

/**
 * The Class SysCdLangRepository.
 */
@Repository
public class SysCdLangRepository extends AbstractRepository {
	

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
	 * 시스템 코드 언어 상세 조회.
	 *
	 * @param sysCdLang the SysCdLang
	 * @return the SysCdLang
	 * @throws SQLException the SQL exception
	 */
	public SysCdLang selectSysCdLang(SysCdLang sysCdLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysCdLang", sysCdLang);
	}
	
	/**
	 * 시스템 코드 언어 등록.
	 *
	 * @param sysCdLang the SysCdLang
	 * @throws SQLException the SQL exception
	 */
	public void insertSysCdLang(SysCdLang sysCdLang) {
		getSession1().insert("com.plgrim.ncp.base.insertSysCdLang", sysCdLang);
	}
	
	/**
	 * 시스템 코드 언어 수정.
	 *
	 * @param sysCdLang the SysCdLang
	 * @throws SQLException the SQL exception
	 */
	public int updateSysCdLang(SysCdLang sysCdLang) {
		return getSession1().update("com.plgrim.ncp.base.updateSysCdLang", sysCdLang);
	}
	
	/**
	 * 시스템 코드 언어 삭제.
	 *
	 * @param sysCdLang the SysCdLang
	 * @return the SysCdLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysCdLang(SysCdLang sysCdLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysCdLang", sysCdLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

