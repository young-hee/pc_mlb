
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplatHist;

/**
 * The Class SysStplatHistRepository.
 */
@Repository
public class SysStplatHistRepository extends AbstractRepository {
	

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
	 * 시스템 약관 이력 상세 조회.
	 *
	 * @param sysStplatHist the SysStplatHist
	 * @return the SysStplatHist
	 * @throws SQLException the SQL exception
	 */
	public SysStplatHist selectSysStplatHist(SysStplatHist sysStplatHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysStplatHist", sysStplatHist);
	}
	
	/**
	 * 시스템 약관 이력 등록.
	 *
	 * @param sysStplatHist the SysStplatHist
	 * @throws SQLException the SQL exception
	 */
	public void insertSysStplatHist(SysStplatHist sysStplatHist) {
		getSession1().insert("com.plgrim.ncp.base.insertSysStplatHist", sysStplatHist);
	}
	
	/**
	 * 시스템 약관 이력 수정.
	 *
	 * @param sysStplatHist the SysStplatHist
	 * @throws SQLException the SQL exception
	 */
	public int updateSysStplatHist(SysStplatHist sysStplatHist) {
		return getSession1().update("com.plgrim.ncp.base.updateSysStplatHist", sysStplatHist);
	}
	
	/**
	 * 시스템 약관 이력 삭제.
	 *
	 * @param sysStplatHist the SysStplatHist
	 * @return the SysStplatHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysStplatHist(SysStplatHist sysStplatHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysStplatHist", sysStplatHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

