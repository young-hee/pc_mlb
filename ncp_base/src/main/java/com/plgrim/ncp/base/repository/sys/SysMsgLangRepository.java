
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysMsgLang;

/**
 * The Class SysMsgLangRepository.
 */
@Repository
public class SysMsgLangRepository extends AbstractRepository {
	

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
	 * 시스템 메시지 언어 상세 조회.
	 *
	 * @param sysMsgLang the SysMsgLang
	 * @return the SysMsgLang
	 * @throws SQLException the SQL exception
	 */
	public SysMsgLang selectSysMsgLang(SysMsgLang sysMsgLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysMsgLang", sysMsgLang);
	}
	
	/**
	 * 시스템 메시지 언어 등록.
	 *
	 * @param sysMsgLang the SysMsgLang
	 * @throws SQLException the SQL exception
	 */
	public void insertSysMsgLang(SysMsgLang sysMsgLang) {
		getSession1().insert("com.plgrim.ncp.base.insertSysMsgLang", sysMsgLang);
	}
	
	/**
	 * 시스템 메시지 언어 수정.
	 *
	 * @param sysMsgLang the SysMsgLang
	 * @throws SQLException the SQL exception
	 */
	public int updateSysMsgLang(SysMsgLang sysMsgLang) {
		return getSession1().update("com.plgrim.ncp.base.updateSysMsgLang", sysMsgLang);
	}
	
	/**
	 * 시스템 메시지 언어 삭제.
	 *
	 * @param sysMsgLang the SysMsgLang
	 * @return the SysMsgLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysMsgLang(SysMsgLang sysMsgLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysMsgLang", sysMsgLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

