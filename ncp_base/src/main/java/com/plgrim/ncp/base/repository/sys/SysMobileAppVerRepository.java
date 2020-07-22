
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysMobileAppVer;

/**
 * The Class SysMobileAppVerRepository.
 */
@Repository
public class SysMobileAppVerRepository extends AbstractRepository {
	

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
	 * 시스템 모바일 어플리케이션 버전 상세 조회.
	 *
	 * @param sysMobileAppVer the SysMobileAppVer
	 * @return the SysMobileAppVer
	 * @throws SQLException the SQL exception
	 */
	public SysMobileAppVer selectSysMobileAppVer(SysMobileAppVer sysMobileAppVer) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysMobileAppVer", sysMobileAppVer);
	}
	
	/**
	 * 시스템 모바일 어플리케이션 버전 등록.
	 *
	 * @param sysMobileAppVer the SysMobileAppVer
	 * @throws SQLException the SQL exception
	 */
	public void insertSysMobileAppVer(SysMobileAppVer sysMobileAppVer) {
		getSession1().insert("com.plgrim.ncp.base.insertSysMobileAppVer", sysMobileAppVer);
	}
	
	/**
	 * 시스템 모바일 어플리케이션 버전 수정.
	 *
	 * @param sysMobileAppVer the SysMobileAppVer
	 * @throws SQLException the SQL exception
	 */
	public int updateSysMobileAppVer(SysMobileAppVer sysMobileAppVer) {
		return getSession1().update("com.plgrim.ncp.base.updateSysMobileAppVer", sysMobileAppVer);
	}
	
	/**
	 * 시스템 모바일 어플리케이션 버전 삭제.
	 *
	 * @param sysMobileAppVer the SysMobileAppVer
	 * @return the SysMobileAppVer
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysMobileAppVer(SysMobileAppVer sysMobileAppVer) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysMobileAppVer", sysMobileAppVer);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

