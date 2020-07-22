
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysProcdrExecutHist;

/**
 * The Class SysProcdrExecutHistRepository.
 */
@Repository
public class SysProcdrExecutHistRepository extends AbstractRepository {
	

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
	 * 시스템 프로시저 실행 이력 상세 조회.
	 *
	 * @param sysProcdrExecutHist the SysProcdrExecutHist
	 * @return the SysProcdrExecutHist
	 * @throws SQLException the SQL exception
	 */
	public SysProcdrExecutHist selectSysProcdrExecutHist(SysProcdrExecutHist sysProcdrExecutHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysProcdrExecutHist", sysProcdrExecutHist);
	}
	
	/**
	 * 시스템 프로시저 실행 이력 등록.
	 *
	 * @param sysProcdrExecutHist the SysProcdrExecutHist
	 * @throws SQLException the SQL exception
	 */
	public void insertSysProcdrExecutHist(SysProcdrExecutHist sysProcdrExecutHist) {
		getSession1().insert("com.plgrim.ncp.base.insertSysProcdrExecutHist", sysProcdrExecutHist);
	}
	
	/**
	 * 시스템 프로시저 실행 이력 수정.
	 *
	 * @param sysProcdrExecutHist the SysProcdrExecutHist
	 * @throws SQLException the SQL exception
	 */
	public int updateSysProcdrExecutHist(SysProcdrExecutHist sysProcdrExecutHist) {
		return getSession1().update("com.plgrim.ncp.base.updateSysProcdrExecutHist", sysProcdrExecutHist);
	}
	
	/**
	 * 시스템 프로시저 실행 이력 삭제.
	 *
	 * @param sysProcdrExecutHist the SysProcdrExecutHist
	 * @return the SysProcdrExecutHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysProcdrExecutHist(SysProcdrExecutHist sysProcdrExecutHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysProcdrExecutHist", sysProcdrExecutHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

