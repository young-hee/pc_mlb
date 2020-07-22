
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysCd;

/**
 * The Class SysCdRepository.
 */
@Repository
public class SysCdRepository extends AbstractRepository {
	

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
	 * 시스템 코드 상세 조회.
	 *
	 * @param sysCd the SysCd
	 * @return the SysCd
	 * @throws SQLException the SQL exception
	 */
	public SysCd selectSysCd(SysCd sysCd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysCd", sysCd);
	}
	
	/**
	 * 시스템 코드 등록.
	 *
	 * @param sysCd the SysCd
	 * @throws SQLException the SQL exception
	 */
	public void insertSysCd(SysCd sysCd) {
		getSession1().insert("com.plgrim.ncp.base.insertSysCd", sysCd);
	}
	
	/**
	 * 시스템 코드 수정.
	 *
	 * @param sysCd the SysCd
	 * @throws SQLException the SQL exception
	 */
	public int updateSysCd(SysCd sysCd) {
		return getSession1().update("com.plgrim.ncp.base.updateSysCd", sysCd);
	}
	
	/**
	 * 시스템 코드 삭제.
	 *
	 * @param sysCd the SysCd
	 * @return the SysCd
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysCd(SysCd sysCd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysCd", sysCd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

