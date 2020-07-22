
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminCrtfc;

/**
 * The Class SysAdminCrtfcRepository.
 */
@Repository
public class SysAdminCrtfcRepository extends AbstractRepository {
	

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
	 * 시스템 관리자 인증 상세 조회.
	 *
	 * @param sysAdminCrtfc the SysAdminCrtfc
	 * @return the SysAdminCrtfc
	 * @throws SQLException the SQL exception
	 */
	public SysAdminCrtfc selectSysAdminCrtfc(SysAdminCrtfc sysAdminCrtfc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysAdminCrtfc", sysAdminCrtfc);
	}
	
	/**
	 * 시스템 관리자 인증 등록.
	 *
	 * @param sysAdminCrtfc the SysAdminCrtfc
	 * @throws SQLException the SQL exception
	 */
	public void insertSysAdminCrtfc(SysAdminCrtfc sysAdminCrtfc) {
		getSession1().insert("com.plgrim.ncp.base.insertSysAdminCrtfc", sysAdminCrtfc);
	}
	
	/**
	 * 시스템 관리자 인증 수정.
	 *
	 * @param sysAdminCrtfc the SysAdminCrtfc
	 * @throws SQLException the SQL exception
	 */
	public int updateSysAdminCrtfc(SysAdminCrtfc sysAdminCrtfc) {
		return getSession1().update("com.plgrim.ncp.base.updateSysAdminCrtfc", sysAdminCrtfc);
	}
	
	/**
	 * 시스템 관리자 인증 삭제.
	 *
	 * @param sysAdminCrtfc the SysAdminCrtfc
	 * @return the SysAdminCrtfc
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysAdminCrtfc(SysAdminCrtfc sysAdminCrtfc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysAdminCrtfc", sysAdminCrtfc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

