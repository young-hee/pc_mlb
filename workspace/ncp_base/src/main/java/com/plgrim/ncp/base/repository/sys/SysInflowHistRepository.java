
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflowHist;

/**
 * The Class SysInflowHistRepository.
 */
@Repository
public class SysInflowHistRepository extends AbstractRepository {
	

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
	 * 시스템 유입 이력 상세 조회.
	 *
	 * @param sysInflowHist the SysInflowHist
	 * @return the SysInflowHist
	 * @throws SQLException the SQL exception
	 */
	public SysInflowHist selectSysInflowHist(SysInflowHist sysInflowHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysInflowHist", sysInflowHist);
	}
	
	/**
	 * 시스템 유입 이력 등록.
	 *
	 * @param sysInflowHist the SysInflowHist
	 * @throws SQLException the SQL exception
	 */
	public void insertSysInflowHist(SysInflowHist sysInflowHist) {
		getSession1().insert("com.plgrim.ncp.base.insertSysInflowHist", sysInflowHist);
	}
	
	/**
	 * 시스템 유입 이력 수정.
	 *
	 * @param sysInflowHist the SysInflowHist
	 * @throws SQLException the SQL exception
	 */
	public int updateSysInflowHist(SysInflowHist sysInflowHist) {
		return getSession1().update("com.plgrim.ncp.base.updateSysInflowHist", sysInflowHist);
	}
	
	/**
	 * 시스템 유입 이력 삭제.
	 *
	 * @param sysInflowHist the SysInflowHist
	 * @return the SysInflowHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysInflowHist(SysInflowHist sysInflowHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysInflowHist", sysInflowHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

