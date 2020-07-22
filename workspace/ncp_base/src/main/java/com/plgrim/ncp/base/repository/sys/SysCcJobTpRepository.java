
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysCcJobTp;

/**
 * The Class SysCcJobTpRepository.
 */
@Repository
public class SysCcJobTpRepository extends AbstractRepository {
	

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
	 * 시스템 고객센터 업무 유형 상세 조회.
	 *
	 * @param sysCcJobTp the SysCcJobTp
	 * @return the SysCcJobTp
	 * @throws SQLException the SQL exception
	 */
	public SysCcJobTp selectSysCcJobTp(SysCcJobTp sysCcJobTp) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysCcJobTp", sysCcJobTp);
	}
	
	/**
	 * 시스템 고객센터 업무 유형 등록.
	 *
	 * @param sysCcJobTp the SysCcJobTp
	 * @throws SQLException the SQL exception
	 */
	public void insertSysCcJobTp(SysCcJobTp sysCcJobTp) {
		getSession1().insert("com.plgrim.ncp.base.insertSysCcJobTp", sysCcJobTp);
	}
	
	/**
	 * 시스템 고객센터 업무 유형 수정.
	 *
	 * @param sysCcJobTp the SysCcJobTp
	 * @throws SQLException the SQL exception
	 */
	public int updateSysCcJobTp(SysCcJobTp sysCcJobTp) {
		return getSession1().update("com.plgrim.ncp.base.updateSysCcJobTp", sysCcJobTp);
	}
	
	/**
	 * 시스템 고객센터 업무 유형 삭제.
	 *
	 * @param sysCcJobTp the SysCcJobTp
	 * @return the SysCcJobTp
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysCcJobTp(SysCcJobTp sysCcJobTp) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysCcJobTp", sysCcJobTp);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

