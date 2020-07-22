
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysDvc;

/**
 * The Class SysDvcRepository.
 */
@Repository
public class SysDvcRepository extends AbstractRepository {
	

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
	 * 시스템 디바이스 상세 조회.
	 *
	 * @param sysDvc the SysDvc
	 * @return the SysDvc
	 * @throws SQLException the SQL exception
	 */
	public SysDvc selectSysDvc(SysDvc sysDvc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysDvc", sysDvc);
	}
	
	/**
	 * 시스템 디바이스 등록.
	 *
	 * @param sysDvc the SysDvc
	 * @throws SQLException the SQL exception
	 */
	public void insertSysDvc(SysDvc sysDvc) {
		getSession1().insert("com.plgrim.ncp.base.insertSysDvc", sysDvc);
	}
	
	/**
	 * 시스템 디바이스 수정.
	 *
	 * @param sysDvc the SysDvc
	 * @throws SQLException the SQL exception
	 */
	public int updateSysDvc(SysDvc sysDvc) {
		return getSession1().update("com.plgrim.ncp.base.updateSysDvc", sysDvc);
	}
	
	/**
	 * 시스템 디바이스 삭제.
	 *
	 * @param sysDvc the SysDvc
	 * @return the SysDvc
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysDvc(SysDvc sysDvc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysDvc", sysDvc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

