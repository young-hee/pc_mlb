
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;

/**
 * The Class SysBrndRepository.
 */
@Repository
public class SysBrndRepository extends AbstractRepository {
	

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
	 * 시스템 브랜드 상세 조회.
	 *
	 * @param sysBrnd the SysBrnd
	 * @return the SysBrnd
	 * @throws SQLException the SQL exception
	 */
	public SysBrnd selectSysBrnd(SysBrnd sysBrnd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysBrnd", sysBrnd);
	}
	
	/**
	 * 시스템 브랜드 등록.
	 *
	 * @param sysBrnd the SysBrnd
	 * @throws SQLException the SQL exception
	 */
	public void insertSysBrnd(SysBrnd sysBrnd) {
		getSession1().insert("com.plgrim.ncp.base.insertSysBrnd", sysBrnd);
	}
	
	/**
	 * 시스템 브랜드 수정.
	 *
	 * @param sysBrnd the SysBrnd
	 * @throws SQLException the SQL exception
	 */
	public int updateSysBrnd(SysBrnd sysBrnd) {
		return getSession1().update("com.plgrim.ncp.base.updateSysBrnd", sysBrnd);
	}
	
	/**
	 * 시스템 브랜드 삭제.
	 *
	 * @param sysBrnd the SysBrnd
	 * @return the SysBrnd
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysBrnd(SysBrnd sysBrnd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysBrnd", sysBrnd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

