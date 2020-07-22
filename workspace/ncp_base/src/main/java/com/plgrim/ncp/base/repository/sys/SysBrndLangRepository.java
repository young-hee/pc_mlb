
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndLang;

/**
 * The Class SysBrndLangRepository.
 */
@Repository
public class SysBrndLangRepository extends AbstractRepository {
	

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
	 * 시스템 브랜드 언어 상세 조회.
	 *
	 * @param sysBrndLang the SysBrndLang
	 * @return the SysBrndLang
	 * @throws SQLException the SQL exception
	 */
	public SysBrndLang selectSysBrndLang(SysBrndLang sysBrndLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysBrndLang", sysBrndLang);
	}
	
	/**
	 * 시스템 브랜드 언어 등록.
	 *
	 * @param sysBrndLang the SysBrndLang
	 * @throws SQLException the SQL exception
	 */
	public void insertSysBrndLang(SysBrndLang sysBrndLang) {
		getSession1().insert("com.plgrim.ncp.base.insertSysBrndLang", sysBrndLang);
	}
	
	/**
	 * 시스템 브랜드 언어 수정.
	 *
	 * @param sysBrndLang the SysBrndLang
	 * @throws SQLException the SQL exception
	 */
	public int updateSysBrndLang(SysBrndLang sysBrndLang) {
		return getSession1().update("com.plgrim.ncp.base.updateSysBrndLang", sysBrndLang);
	}
	
	/**
	 * 시스템 브랜드 언어 삭제.
	 *
	 * @param sysBrndLang the SysBrndLang
	 * @return the SysBrndLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysBrndLang(SysBrndLang sysBrndLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysBrndLang", sysBrndLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

