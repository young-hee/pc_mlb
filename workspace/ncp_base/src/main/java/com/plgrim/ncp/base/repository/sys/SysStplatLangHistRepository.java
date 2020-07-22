
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplatLangHist;

/**
 * The Class SysStplatLangHistRepository.
 */
@Repository
public class SysStplatLangHistRepository extends AbstractRepository {
	

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
	 * 시스템 약관 언어 이력 상세 조회.
	 *
	 * @param sysStplatLangHist the SysStplatLangHist
	 * @return the SysStplatLangHist
	 * @throws SQLException the SQL exception
	 */
	public SysStplatLangHist selectSysStplatLangHist(SysStplatLangHist sysStplatLangHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysStplatLangHist", sysStplatLangHist);
	}
	
	/**
	 * 시스템 약관 언어 이력 등록.
	 *
	 * @param sysStplatLangHist the SysStplatLangHist
	 * @throws SQLException the SQL exception
	 */
	public void insertSysStplatLangHist(SysStplatLangHist sysStplatLangHist) {
		getSession1().insert("com.plgrim.ncp.base.insertSysStplatLangHist", sysStplatLangHist);
	}
	
	/**
	 * 시스템 약관 언어 이력 수정.
	 *
	 * @param sysStplatLangHist the SysStplatLangHist
	 * @throws SQLException the SQL exception
	 */
	public int updateSysStplatLangHist(SysStplatLangHist sysStplatLangHist) {
		return getSession1().update("com.plgrim.ncp.base.updateSysStplatLangHist", sysStplatLangHist);
	}
	
	/**
	 * 시스템 약관 언어 이력 삭제.
	 *
	 * @param sysStplatLangHist the SysStplatLangHist
	 * @return the SysStplatLangHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysStplatLangHist(SysStplatLangHist sysStplatLangHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysStplatLangHist", sysStplatLangHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

