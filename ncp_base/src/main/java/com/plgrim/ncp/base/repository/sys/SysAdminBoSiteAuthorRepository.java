
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminBoSiteAuthor;

/**
 * The Class SysAdminBoSiteAuthorRepository.
 */
@Repository
public class SysAdminBoSiteAuthorRepository extends AbstractRepository {
	

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
	 * 시스템 관리자 BO 사이트 권한 상세 조회.
	 *
	 * @param sysAdminBoSiteAuthor the SysAdminBoSiteAuthor
	 * @return the SysAdminBoSiteAuthor
	 * @throws SQLException the SQL exception
	 */
	public SysAdminBoSiteAuthor selectSysAdminBoSiteAuthor(SysAdminBoSiteAuthor sysAdminBoSiteAuthor) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysAdminBoSiteAuthor", sysAdminBoSiteAuthor);
	}
	
	/**
	 * 시스템 관리자 BO 사이트 권한 등록.
	 *
	 * @param sysAdminBoSiteAuthor the SysAdminBoSiteAuthor
	 * @throws SQLException the SQL exception
	 */
	public void insertSysAdminBoSiteAuthor(SysAdminBoSiteAuthor sysAdminBoSiteAuthor) {
		getSession1().insert("com.plgrim.ncp.base.insertSysAdminBoSiteAuthor", sysAdminBoSiteAuthor);
	}
	
	/**
	 * 시스템 관리자 BO 사이트 권한 수정.
	 *
	 * @param sysAdminBoSiteAuthor the SysAdminBoSiteAuthor
	 * @throws SQLException the SQL exception
	 */
	public int updateSysAdminBoSiteAuthor(SysAdminBoSiteAuthor sysAdminBoSiteAuthor) {
		return getSession1().update("com.plgrim.ncp.base.updateSysAdminBoSiteAuthor", sysAdminBoSiteAuthor);
	}
	
	/**
	 * 시스템 관리자 BO 사이트 권한 삭제.
	 *
	 * @param sysAdminBoSiteAuthor the SysAdminBoSiteAuthor
	 * @return the SysAdminBoSiteAuthor
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysAdminBoSiteAuthor(SysAdminBoSiteAuthor sysAdminBoSiteAuthor) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysAdminBoSiteAuthor", sysAdminBoSiteAuthor);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

