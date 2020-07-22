
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysBoNotiTgt;

/**
 * The Class SysBoNotiTgtRepository.
 */
@Repository
public class SysBoNotiTgtRepository extends AbstractRepository {
	

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
	 * 시스템 BO 공지 대상 상세 조회.
	 *
	 * @param sysBoNotiTgt the SysBoNotiTgt
	 * @return the SysBoNotiTgt
	 * @throws SQLException the SQL exception
	 */
	public SysBoNotiTgt selectSysBoNotiTgt(SysBoNotiTgt sysBoNotiTgt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysBoNotiTgt", sysBoNotiTgt);
	}
	
	/**
	 * 시스템 BO 공지 대상 등록.
	 *
	 * @param sysBoNotiTgt the SysBoNotiTgt
	 * @throws SQLException the SQL exception
	 */
	public void insertSysBoNotiTgt(SysBoNotiTgt sysBoNotiTgt) {
		getSession1().insert("com.plgrim.ncp.base.insertSysBoNotiTgt", sysBoNotiTgt);
	}
	
	/**
	 * 시스템 BO 공지 대상 수정.
	 *
	 * @param sysBoNotiTgt the SysBoNotiTgt
	 * @throws SQLException the SQL exception
	 */
	public int updateSysBoNotiTgt(SysBoNotiTgt sysBoNotiTgt) {
		return getSession1().update("com.plgrim.ncp.base.updateSysBoNotiTgt", sysBoNotiTgt);
	}
	
	/**
	 * 시스템 BO 공지 대상 삭제.
	 *
	 * @param sysBoNotiTgt the SysBoNotiTgt
	 * @return the SysBoNotiTgt
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysBoNotiTgt(SysBoNotiTgt sysBoNotiTgt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysBoNotiTgt", sysBoNotiTgt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

