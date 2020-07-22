
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminChrgJob;

/**
 * The Class SysAdminChrgJobRepository.
 */
@Repository
public class SysAdminChrgJobRepository extends AbstractRepository {
	

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
	 * 시스템 관리자 담당 업무 상세 조회.
	 *
	 * @param sysAdminChrgJob the SysAdminChrgJob
	 * @return the SysAdminChrgJob
	 * @throws SQLException the SQL exception
	 */
	public SysAdminChrgJob selectSysAdminChrgJob(SysAdminChrgJob sysAdminChrgJob) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysAdminChrgJob", sysAdminChrgJob);
	}
	
	/**
	 * 시스템 관리자 담당 업무 등록.
	 *
	 * @param sysAdminChrgJob the SysAdminChrgJob
	 * @throws SQLException the SQL exception
	 */
	public void insertSysAdminChrgJob(SysAdminChrgJob sysAdminChrgJob) {
		getSession1().insert("com.plgrim.ncp.base.insertSysAdminChrgJob", sysAdminChrgJob);
	}
	
	/**
	 * 시스템 관리자 담당 업무 수정.
	 *
	 * @param sysAdminChrgJob the SysAdminChrgJob
	 * @throws SQLException the SQL exception
	 */
	public int updateSysAdminChrgJob(SysAdminChrgJob sysAdminChrgJob) {
		return getSession1().update("com.plgrim.ncp.base.updateSysAdminChrgJob", sysAdminChrgJob);
	}
	
	/**
	 * 시스템 관리자 담당 업무 삭제.
	 *
	 * @param sysAdminChrgJob the SysAdminChrgJob
	 * @return the SysAdminChrgJob
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysAdminChrgJob(SysAdminChrgJob sysAdminChrgJob) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysAdminChrgJob", sysAdminChrgJob);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

