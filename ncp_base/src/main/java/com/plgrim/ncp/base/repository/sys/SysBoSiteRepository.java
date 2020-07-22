
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysBoSite;

/**
 * The Class SysBoSiteRepository.
 */
@Repository
public class SysBoSiteRepository extends AbstractRepository {
	

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
	 * 시스템 BO 사이트 상세 조회.
	 *
	 * @param sysBoSite the SysBoSite
	 * @return the SysBoSite
	 * @throws SQLException the SQL exception
	 */
	public SysBoSite selectSysBoSite(SysBoSite sysBoSite) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysBoSite", sysBoSite);
	}
	
	/**
	 * 시스템 BO 사이트 등록.
	 *
	 * @param sysBoSite the SysBoSite
	 * @throws SQLException the SQL exception
	 */
	public void insertSysBoSite(SysBoSite sysBoSite) {
		getSession1().insert("com.plgrim.ncp.base.insertSysBoSite", sysBoSite);
	}
	
	/**
	 * 시스템 BO 사이트 수정.
	 *
	 * @param sysBoSite the SysBoSite
	 * @throws SQLException the SQL exception
	 */
	public int updateSysBoSite(SysBoSite sysBoSite) {
		return getSession1().update("com.plgrim.ncp.base.updateSysBoSite", sysBoSite);
	}
	
	/**
	 * 시스템 BO 사이트 삭제.
	 *
	 * @param sysBoSite the SysBoSite
	 * @return the SysBoSite
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysBoSite(SysBoSite sysBoSite) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysBoSite", sysBoSite);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

