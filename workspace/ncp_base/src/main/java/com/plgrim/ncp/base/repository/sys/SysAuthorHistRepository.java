
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAuthorHist;

/**
 * The Class SysAuthorHistRepository.
 */
@Repository
public class SysAuthorHistRepository extends AbstractRepository {
	

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
	 * 시스템 권한 이력 상세 조회.
	 *
	 * @param sysAuthorHist the SysAuthorHist
	 * @return the SysAuthorHist
	 * @throws SQLException the SQL exception
	 */
	public SysAuthorHist selectSysAuthorHist(SysAuthorHist sysAuthorHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysAuthorHist", sysAuthorHist);
	}
	
	/**
	 * 시스템 권한 이력 등록.
	 *
	 * @param sysAuthorHist the SysAuthorHist
	 * @throws SQLException the SQL exception
	 */
	public void insertSysAuthorHist(SysAuthorHist sysAuthorHist) {
		getSession1().insert("com.plgrim.ncp.base.insertSysAuthorHist", sysAuthorHist);
	}
	
	/**
	 * 시스템 권한 이력 수정.
	 *
	 * @param sysAuthorHist the SysAuthorHist
	 * @throws SQLException the SQL exception
	 */
	public int updateSysAuthorHist(SysAuthorHist sysAuthorHist) {
		return getSession1().update("com.plgrim.ncp.base.updateSysAuthorHist", sysAuthorHist);
	}
	
	/**
	 * 시스템 권한 이력 삭제.
	 *
	 * @param sysAuthorHist the SysAuthorHist
	 * @return the SysAuthorHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysAuthorHist(SysAuthorHist sysAuthorHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysAuthorHist", sysAuthorHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

