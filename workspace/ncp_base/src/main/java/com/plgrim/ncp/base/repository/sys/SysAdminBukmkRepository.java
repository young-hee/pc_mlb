
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminBukmk;

/**
 * The Class SysAdminBukmkRepository.
 */
@Repository
public class SysAdminBukmkRepository extends AbstractRepository {
	

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
	 * 시스템 관리자 즐겨찾기 상세 조회.
	 *
	 * @param sysAdminBukmk the SysAdminBukmk
	 * @return the SysAdminBukmk
	 * @throws SQLException the SQL exception
	 */
	public SysAdminBukmk selectSysAdminBukmk(SysAdminBukmk sysAdminBukmk) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysAdminBukmk", sysAdminBukmk);
	}
	
	/**
	 * 시스템 관리자 즐겨찾기 등록.
	 *
	 * @param sysAdminBukmk the SysAdminBukmk
	 * @throws SQLException the SQL exception
	 */
	public void insertSysAdminBukmk(SysAdminBukmk sysAdminBukmk) {
		getSession1().insert("com.plgrim.ncp.base.insertSysAdminBukmk", sysAdminBukmk);
	}
	
	/**
	 * 시스템 관리자 즐겨찾기 수정.
	 *
	 * @param sysAdminBukmk the SysAdminBukmk
	 * @throws SQLException the SQL exception
	 */
	public int updateSysAdminBukmk(SysAdminBukmk sysAdminBukmk) {
		return getSession1().update("com.plgrim.ncp.base.updateSysAdminBukmk", sysAdminBukmk);
	}
	
	/**
	 * 시스템 관리자 즐겨찾기 삭제.
	 *
	 * @param sysAdminBukmk the SysAdminBukmk
	 * @return the SysAdminBukmk
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysAdminBukmk(SysAdminBukmk sysAdminBukmk) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysAdminBukmk", sysAdminBukmk);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

