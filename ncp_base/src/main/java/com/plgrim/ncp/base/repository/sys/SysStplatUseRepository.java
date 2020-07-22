
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplatUse;

/**
 * The Class SysStplatUseRepository.
 */
@Repository
public class SysStplatUseRepository extends AbstractRepository {
	

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
	 * 시스템 약관 사용 상세 조회.
	 *
	 * @param sysStplatUse the SysStplatUse
	 * @return the SysStplatUse
	 * @throws SQLException the SQL exception
	 */
	public SysStplatUse selectSysStplatUse(SysStplatUse sysStplatUse) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysStplatUse", sysStplatUse);
	}
	
	/**
	 * 시스템 약관 사용 등록.
	 *
	 * @param sysStplatUse the SysStplatUse
	 * @throws SQLException the SQL exception
	 */
	public void insertSysStplatUse(SysStplatUse sysStplatUse) {
		getSession1().insert("com.plgrim.ncp.base.insertSysStplatUse", sysStplatUse);
	}
	
	/**
	 * 시스템 약관 사용 수정.
	 *
	 * @param sysStplatUse the SysStplatUse
	 * @throws SQLException the SQL exception
	 */
	public int updateSysStplatUse(SysStplatUse sysStplatUse) {
		return getSession1().update("com.plgrim.ncp.base.updateSysStplatUse", sysStplatUse);
	}
	
	/**
	 * 시스템 약관 사용 삭제.
	 *
	 * @param sysStplatUse the SysStplatUse
	 * @return the SysStplatUse
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysStplatUse(SysStplatUse sysStplatUse) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysStplatUse", sysStplatUse);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

