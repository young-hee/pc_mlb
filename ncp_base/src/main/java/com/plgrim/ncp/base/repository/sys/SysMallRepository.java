
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysMall;

/**
 * The Class SysMallRepository.
 */
@Repository
public class SysMallRepository extends AbstractRepository {
	

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
	 * 시스템 몰 상세 조회.
	 *
	 * @param sysMall the SysMall
	 * @return the SysMall
	 * @throws SQLException the SQL exception
	 */
	public SysMall selectSysMall(SysMall sysMall) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysMall", sysMall);
	}
	
	/**
	 * 시스템 몰 등록.
	 *
	 * @param sysMall the SysMall
	 * @throws SQLException the SQL exception
	 */
	public void insertSysMall(SysMall sysMall) {
		getSession1().insert("com.plgrim.ncp.base.insertSysMall", sysMall);
	}
	
	/**
	 * 시스템 몰 수정.
	 *
	 * @param sysMall the SysMall
	 * @throws SQLException the SQL exception
	 */
	public int updateSysMall(SysMall sysMall) {
		return getSession1().update("com.plgrim.ncp.base.updateSysMall", sysMall);
	}
	
	/**
	 * 시스템 몰 삭제.
	 *
	 * @param sysMall the SysMall
	 * @return the SysMall
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysMall(SysMall sysMall) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysMall", sysMall);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

