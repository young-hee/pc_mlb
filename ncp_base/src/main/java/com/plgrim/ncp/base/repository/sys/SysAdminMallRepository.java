
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminMall;

/**
 * The Class SysAdminMallRepository.
 */
@Repository
public class SysAdminMallRepository extends AbstractRepository {
	

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
	 * 시스템 관리자 몰 상세 조회.
	 *
	 * @param sysAdminMall the SysAdminMall
	 * @return the SysAdminMall
	 * @throws SQLException the SQL exception
	 */
	public SysAdminMall selectSysAdminMall(SysAdminMall sysAdminMall) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysAdminMall", sysAdminMall);
	}
	
	/**
	 * 시스템 관리자 몰 등록.
	 *
	 * @param sysAdminMall the SysAdminMall
	 * @throws SQLException the SQL exception
	 */
	public void insertSysAdminMall(SysAdminMall sysAdminMall) {
		getSession1().insert("com.plgrim.ncp.base.insertSysAdminMall", sysAdminMall);
	}
	
	/**
	 * 시스템 관리자 몰 수정.
	 *
	 * @param sysAdminMall the SysAdminMall
	 * @throws SQLException the SQL exception
	 */
	public int updateSysAdminMall(SysAdminMall sysAdminMall) {
		return getSession1().update("com.plgrim.ncp.base.updateSysAdminMall", sysAdminMall);
	}
	
	/**
	 * 시스템 관리자 몰 삭제.
	 *
	 * @param sysAdminMall the SysAdminMall
	 * @return the SysAdminMall
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysAdminMall(SysAdminMall sysAdminMall) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysAdminMall", sysAdminMall);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

