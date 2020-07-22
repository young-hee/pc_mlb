
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysProcdr;

/**
 * The Class SysProcdrRepository.
 */
@Repository
public class SysProcdrRepository extends AbstractRepository {
	

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
	 * 시스템 프로시저 상세 조회.
	 *
	 * @param sysProcdr the SysProcdr
	 * @return the SysProcdr
	 * @throws SQLException the SQL exception
	 */
	public SysProcdr selectSysProcdr(SysProcdr sysProcdr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysProcdr", sysProcdr);
	}
	
	/**
	 * 시스템 프로시저 등록.
	 *
	 * @param sysProcdr the SysProcdr
	 * @throws SQLException the SQL exception
	 */
	public void insertSysProcdr(SysProcdr sysProcdr) {
		getSession1().insert("com.plgrim.ncp.base.insertSysProcdr", sysProcdr);
	}
	
	/**
	 * 시스템 프로시저 수정.
	 *
	 * @param sysProcdr the SysProcdr
	 * @throws SQLException the SQL exception
	 */
	public int updateSysProcdr(SysProcdr sysProcdr) {
		return getSession1().update("com.plgrim.ncp.base.updateSysProcdr", sysProcdr);
	}
	
	/**
	 * 시스템 프로시저 삭제.
	 *
	 * @param sysProcdr the SysProcdr
	 * @return the SysProcdr
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysProcdr(SysProcdr sysProcdr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysProcdr", sysProcdr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

