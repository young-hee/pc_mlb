
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminStplatAgr;

/**
 * The Class SysAdminStplatAgrRepository.
 */
@Repository
public class SysAdminStplatAgrRepository extends AbstractRepository {
	

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
	 * 시스템 관리자 약관 동의 상세 조회.
	 *
	 * @param sysAdminStplatAgr the SysAdminStplatAgr
	 * @return the SysAdminStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public SysAdminStplatAgr selectSysAdminStplatAgr(SysAdminStplatAgr sysAdminStplatAgr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysAdminStplatAgr", sysAdminStplatAgr);
	}
	
	/**
	 * 시스템 관리자 약관 동의 등록.
	 *
	 * @param sysAdminStplatAgr the SysAdminStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public void insertSysAdminStplatAgr(SysAdminStplatAgr sysAdminStplatAgr) {
		getSession1().insert("com.plgrim.ncp.base.insertSysAdminStplatAgr", sysAdminStplatAgr);
	}
	
	/**
	 * 시스템 관리자 약관 동의 수정.
	 *
	 * @param sysAdminStplatAgr the SysAdminStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public int updateSysAdminStplatAgr(SysAdminStplatAgr sysAdminStplatAgr) {
		return getSession1().update("com.plgrim.ncp.base.updateSysAdminStplatAgr", sysAdminStplatAgr);
	}
	
	/**
	 * 시스템 관리자 약관 동의 삭제.
	 *
	 * @param sysAdminStplatAgr the SysAdminStplatAgr
	 * @return the SysAdminStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysAdminStplatAgr(SysAdminStplatAgr sysAdminStplatAgr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysAdminStplatAgr", sysAdminStplatAgr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

