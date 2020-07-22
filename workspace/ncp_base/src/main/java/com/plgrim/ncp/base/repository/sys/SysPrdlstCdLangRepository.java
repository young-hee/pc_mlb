
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysPrdlstCdLang;

/**
 * The Class SysPrdlstCdLangRepository.
 */
@Repository
public class SysPrdlstCdLangRepository extends AbstractRepository {
	

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
	 * 시스템 품목 코드 언어 상세 조회.
	 *
	 * @param sysPrdlstCdLang the SysPrdlstCdLang
	 * @return the SysPrdlstCdLang
	 * @throws SQLException the SQL exception
	 */
	public SysPrdlstCdLang selectSysPrdlstCdLang(SysPrdlstCdLang sysPrdlstCdLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysPrdlstCdLang", sysPrdlstCdLang);
	}
	
	/**
	 * 시스템 품목 코드 언어 등록.
	 *
	 * @param sysPrdlstCdLang the SysPrdlstCdLang
	 * @throws SQLException the SQL exception
	 */
	public void insertSysPrdlstCdLang(SysPrdlstCdLang sysPrdlstCdLang) {
		getSession1().insert("com.plgrim.ncp.base.insertSysPrdlstCdLang", sysPrdlstCdLang);
	}
	
	/**
	 * 시스템 품목 코드 언어 수정.
	 *
	 * @param sysPrdlstCdLang the SysPrdlstCdLang
	 * @throws SQLException the SQL exception
	 */
	public int updateSysPrdlstCdLang(SysPrdlstCdLang sysPrdlstCdLang) {
		return getSession1().update("com.plgrim.ncp.base.updateSysPrdlstCdLang", sysPrdlstCdLang);
	}
	
	/**
	 * 시스템 품목 코드 언어 삭제.
	 *
	 * @param sysPrdlstCdLang the SysPrdlstCdLang
	 * @return the SysPrdlstCdLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysPrdlstCdLang(SysPrdlstCdLang sysPrdlstCdLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysPrdlstCdLang", sysPrdlstCdLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

