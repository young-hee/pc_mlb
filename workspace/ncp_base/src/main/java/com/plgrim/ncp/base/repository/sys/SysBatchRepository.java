
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysBatch;

/**
 * The Class SysBatchRepository.
 */
@Repository
public class SysBatchRepository extends AbstractRepository {
	

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
	 * 시스템 배치 상세 조회.
	 *
	 * @param sysBatch the SysBatch
	 * @return the SysBatch
	 * @throws SQLException the SQL exception
	 */
	public SysBatch selectSysBatch(SysBatch sysBatch) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysBatch", sysBatch);
	}
	
	/**
	 * 시스템 배치 등록.
	 *
	 * @param sysBatch the SysBatch
	 * @throws SQLException the SQL exception
	 */
	public void insertSysBatch(SysBatch sysBatch) {
		getSession1().insert("com.plgrim.ncp.base.insertSysBatch", sysBatch);
	}
	
	/**
	 * 시스템 배치 수정.
	 *
	 * @param sysBatch the SysBatch
	 * @throws SQLException the SQL exception
	 */
	public int updateSysBatch(SysBatch sysBatch) {
		return getSession1().update("com.plgrim.ncp.base.updateSysBatch", sysBatch);
	}
	
	/**
	 * 시스템 배치 삭제.
	 *
	 * @param sysBatch the SysBatch
	 * @return the SysBatch
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysBatch(SysBatch sysBatch) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysBatch", sysBatch);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

