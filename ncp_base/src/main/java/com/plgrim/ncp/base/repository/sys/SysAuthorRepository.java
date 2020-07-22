
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAuthor;

/**
 * The Class SysAuthorRepository.
 */
@Repository
public class SysAuthorRepository extends AbstractRepository {
	

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
	 * 시스템 권한 상세 조회.
	 *
	 * @param sysAuthor the SysAuthor
	 * @return the SysAuthor
	 * @throws SQLException the SQL exception
	 */
	public SysAuthor selectSysAuthor(SysAuthor sysAuthor) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysAuthor", sysAuthor);
	}
	
	/**
	 * 시스템 권한 등록.
	 *
	 * @param sysAuthor the SysAuthor
	 * @throws SQLException the SQL exception
	 */
	public void insertSysAuthor(SysAuthor sysAuthor) {
		getSession1().insert("com.plgrim.ncp.base.insertSysAuthor", sysAuthor);
	}
	
	/**
	 * 시스템 권한 수정.
	 *
	 * @param sysAuthor the SysAuthor
	 * @throws SQLException the SQL exception
	 */
	public int updateSysAuthor(SysAuthor sysAuthor) {
		return getSession1().update("com.plgrim.ncp.base.updateSysAuthor", sysAuthor);
	}
	
	/**
	 * 시스템 권한 삭제.
	 *
	 * @param sysAuthor the SysAuthor
	 * @return the SysAuthor
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysAuthor(SysAuthor sysAuthor) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysAuthor", sysAuthor);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

