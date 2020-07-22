
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysDynmcConfig;

/**
 * The Class SysDynmcConfigRepository.
 */
@Repository
public class SysDynmcConfigRepository extends AbstractRepository {
	

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
	 * 시스템 유동 설정 상세 조회.
	 *
	 * @param sysDynmcConfig the SysDynmcConfig
	 * @return the SysDynmcConfig
	 * @throws SQLException the SQL exception
	 */
	public SysDynmcConfig selectSysDynmcConfig(SysDynmcConfig sysDynmcConfig) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysDynmcConfig", sysDynmcConfig);
	}
	
	/**
	 * 시스템 유동 설정 등록.
	 *
	 * @param sysDynmcConfig the SysDynmcConfig
	 * @throws SQLException the SQL exception
	 */
	public void insertSysDynmcConfig(SysDynmcConfig sysDynmcConfig) {
		getSession1().insert("com.plgrim.ncp.base.insertSysDynmcConfig", sysDynmcConfig);
	}
	
	/**
	 * 시스템 유동 설정 수정.
	 *
	 * @param sysDynmcConfig the SysDynmcConfig
	 * @throws SQLException the SQL exception
	 */
	public int updateSysDynmcConfig(SysDynmcConfig sysDynmcConfig) {
		return getSession1().update("com.plgrim.ncp.base.updateSysDynmcConfig", sysDynmcConfig);
	}
	
	/**
	 * 시스템 유동 설정 삭제.
	 *
	 * @param sysDynmcConfig the SysDynmcConfig
	 * @return the SysDynmcConfig
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysDynmcConfig(SysDynmcConfig sysDynmcConfig) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysDynmcConfig", sysDynmcConfig);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

