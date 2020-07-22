
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysBatchHist;

/**
 * The Class SysBatchHistRepository.
 */
@Repository
public class SysBatchHistRepository extends AbstractRepository {
	

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
	 * 시스템 배치 이력 상세 조회.
	 *
	 * @param sysBatchHist the SysBatchHist
	 * @return the SysBatchHist
	 * @throws SQLException the SQL exception
	 */
	public SysBatchHist selectSysBatchHist(SysBatchHist sysBatchHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysBatchHist", sysBatchHist);
	}
	
	/**
	 * 시스템 배치 이력 등록.
	 *
	 * @param sysBatchHist the SysBatchHist
	 * @throws SQLException the SQL exception
	 */
	public void insertSysBatchHist(SysBatchHist sysBatchHist) {
		getSession1().insert("com.plgrim.ncp.base.insertSysBatchHist", sysBatchHist);
	}
	
	/**
	 * 시스템 배치 이력 수정.
	 *
	 * @param sysBatchHist the SysBatchHist
	 * @throws SQLException the SQL exception
	 */
	public int updateSysBatchHist(SysBatchHist sysBatchHist) {
		return getSession1().update("com.plgrim.ncp.base.updateSysBatchHist", sysBatchHist);
	}
	
	/**
	 * 시스템 배치 이력 삭제.
	 *
	 * @param sysBatchHist the SysBatchHist
	 * @return the SysBatchHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysBatchHist(SysBatchHist sysBatchHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysBatchHist", sysBatchHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

