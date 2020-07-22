
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysBoNotiSite;

/**
 * The Class SysBoNotiSiteRepository.
 */
@Repository
public class SysBoNotiSiteRepository extends AbstractRepository {
	

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
	 * 시스템 BO 공지 사이트 상세 조회.
	 *
	 * @param sysBoNotiSite the SysBoNotiSite
	 * @return the SysBoNotiSite
	 * @throws SQLException the SQL exception
	 */
	public SysBoNotiSite selectSysBoNotiSite(SysBoNotiSite sysBoNotiSite) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysBoNotiSite", sysBoNotiSite);
	}
	
	/**
	 * 시스템 BO 공지 사이트 등록.
	 *
	 * @param sysBoNotiSite the SysBoNotiSite
	 * @throws SQLException the SQL exception
	 */
	public void insertSysBoNotiSite(SysBoNotiSite sysBoNotiSite) {
		getSession1().insert("com.plgrim.ncp.base.insertSysBoNotiSite", sysBoNotiSite);
	}
	
	/**
	 * 시스템 BO 공지 사이트 수정.
	 *
	 * @param sysBoNotiSite the SysBoNotiSite
	 * @throws SQLException the SQL exception
	 */
	public int updateSysBoNotiSite(SysBoNotiSite sysBoNotiSite) {
		return getSession1().update("com.plgrim.ncp.base.updateSysBoNotiSite", sysBoNotiSite);
	}
	
	/**
	 * 시스템 BO 공지 사이트 삭제.
	 *
	 * @param sysBoNotiSite the SysBoNotiSite
	 * @return the SysBoNotiSite
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysBoNotiSite(SysBoNotiSite sysBoNotiSite) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysBoNotiSite", sysBoNotiSite);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

