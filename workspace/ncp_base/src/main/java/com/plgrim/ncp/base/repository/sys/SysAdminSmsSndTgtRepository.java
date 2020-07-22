
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminSmsSndTgt;

/**
 * The Class SysAdminSmsSndTgtRepository.
 */
@Repository
public class SysAdminSmsSndTgtRepository extends AbstractRepository {
	

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
	 * 시스템 관리자 SMS 발송 대상 상세 조회.
	 *
	 * @param sysAdminSmsSndTgt the SysAdminSmsSndTgt
	 * @return the SysAdminSmsSndTgt
	 * @throws SQLException the SQL exception
	 */
	public SysAdminSmsSndTgt selectSysAdminSmsSndTgt(SysAdminSmsSndTgt sysAdminSmsSndTgt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysAdminSmsSndTgt", sysAdminSmsSndTgt);
	}
	
	/**
	 * 시스템 관리자 SMS 발송 대상 등록.
	 *
	 * @param sysAdminSmsSndTgt the SysAdminSmsSndTgt
	 * @throws SQLException the SQL exception
	 */
	public void insertSysAdminSmsSndTgt(SysAdminSmsSndTgt sysAdminSmsSndTgt) {
		getSession1().insert("com.plgrim.ncp.base.insertSysAdminSmsSndTgt", sysAdminSmsSndTgt);
	}
	
	/**
	 * 시스템 관리자 SMS 발송 대상 수정.
	 *
	 * @param sysAdminSmsSndTgt the SysAdminSmsSndTgt
	 * @throws SQLException the SQL exception
	 */
	public int updateSysAdminSmsSndTgt(SysAdminSmsSndTgt sysAdminSmsSndTgt) {
		return getSession1().update("com.plgrim.ncp.base.updateSysAdminSmsSndTgt", sysAdminSmsSndTgt);
	}
	
	/**
	 * 시스템 관리자 SMS 발송 대상 삭제.
	 *
	 * @param sysAdminSmsSndTgt the SysAdminSmsSndTgt
	 * @return the SysAdminSmsSndTgt
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysAdminSmsSndTgt(SysAdminSmsSndTgt sysAdminSmsSndTgt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysAdminSmsSndTgt", sysAdminSmsSndTgt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

