
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysDynmcConfigDetail;

/**
 * The Class SysDynmcConfigDetailRepository.
 */
@Repository
public class SysDynmcConfigDetailRepository extends AbstractRepository {
	

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
	 * 시스템 유동 설정 상세 상세 조회.
	 *
	 * @param sysDynmcConfigDetail the SysDynmcConfigDetail
	 * @return the SysDynmcConfigDetail
	 * @throws SQLException the SQL exception
	 */
	public SysDynmcConfigDetail selectSysDynmcConfigDetail(SysDynmcConfigDetail sysDynmcConfigDetail) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysDynmcConfigDetail", sysDynmcConfigDetail);
	}
	
	/**
	 * 시스템 유동 설정 상세 등록.
	 *
	 * @param sysDynmcConfigDetail the SysDynmcConfigDetail
	 * @throws SQLException the SQL exception
	 */
	public void insertSysDynmcConfigDetail(SysDynmcConfigDetail sysDynmcConfigDetail) {
		getSession1().insert("com.plgrim.ncp.base.insertSysDynmcConfigDetail", sysDynmcConfigDetail);
	}
	
	/**
	 * 시스템 유동 설정 상세 수정.
	 *
	 * @param sysDynmcConfigDetail the SysDynmcConfigDetail
	 * @throws SQLException the SQL exception
	 */
	public int updateSysDynmcConfigDetail(SysDynmcConfigDetail sysDynmcConfigDetail) {
		return getSession1().update("com.plgrim.ncp.base.updateSysDynmcConfigDetail", sysDynmcConfigDetail);
	}
	
	/**
	 * 시스템 유동 설정 상세 삭제.
	 *
	 * @param sysDynmcConfigDetail the SysDynmcConfigDetail
	 * @return the SysDynmcConfigDetail
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysDynmcConfigDetail(SysDynmcConfigDetail sysDynmcConfigDetail) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysDynmcConfigDetail", sysDynmcConfigDetail);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

