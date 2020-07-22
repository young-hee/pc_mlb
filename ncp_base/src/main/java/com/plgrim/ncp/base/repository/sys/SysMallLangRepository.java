
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysMallLang;

/**
 * The Class SysMallLangRepository.
 */
@Repository
public class SysMallLangRepository extends AbstractRepository {
	

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
	 * 시스템 몰 언어 상세 조회.
	 *
	 * @param sysMallLang the SysMallLang
	 * @return the SysMallLang
	 * @throws SQLException the SQL exception
	 */
	public SysMallLang selectSysMallLang(SysMallLang sysMallLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysMallLang", sysMallLang);
	}
	
	/**
	 * 시스템 몰 언어 등록.
	 *
	 * @param sysMallLang the SysMallLang
	 * @throws SQLException the SQL exception
	 */
	public void insertSysMallLang(SysMallLang sysMallLang) {
		getSession1().insert("com.plgrim.ncp.base.insertSysMallLang", sysMallLang);
	}
	
	/**
	 * 시스템 몰 언어 수정.
	 *
	 * @param sysMallLang the SysMallLang
	 * @throws SQLException the SQL exception
	 */
	public int updateSysMallLang(SysMallLang sysMallLang) {
		return getSession1().update("com.plgrim.ncp.base.updateSysMallLang", sysMallLang);
	}
	
	/**
	 * 시스템 몰 언어 삭제.
	 *
	 * @param sysMallLang the SysMallLang
	 * @return the SysMallLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysMallLang(SysMallLang sysMallLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysMallLang", sysMallLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

