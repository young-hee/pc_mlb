
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysChckHist;

/**
 * The Class SysChckHistRepository.
 */
@Repository
public class SysChckHistRepository extends AbstractRepository {
	

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
	 * 시스템 점검 이력 상세 조회.
	 *
	 * @param sysChckHist the SysChckHist
	 * @return the SysChckHist
	 * @throws SQLException the SQL exception
	 */
	public SysChckHist selectSysChckHist(SysChckHist sysChckHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysChckHist", sysChckHist);
	}
	
	/**
	 * 시스템 점검 이력 등록.
	 *
	 * @param sysChckHist the SysChckHist
	 * @throws SQLException the SQL exception
	 */
	public void insertSysChckHist(SysChckHist sysChckHist) {
		getSession1().insert("com.plgrim.ncp.base.insertSysChckHist", sysChckHist);
	}
	
	/**
	 * 시스템 점검 이력 수정.
	 *
	 * @param sysChckHist the SysChckHist
	 * @throws SQLException the SQL exception
	 */
	public int updateSysChckHist(SysChckHist sysChckHist) {
		return getSession1().update("com.plgrim.ncp.base.updateSysChckHist", sysChckHist);
	}
	
	/**
	 * 시스템 점검 이력 삭제.
	 *
	 * @param sysChckHist the SysChckHist
	 * @return the SysChckHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysChckHist(SysChckHist sysChckHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysChckHist", sysChckHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

