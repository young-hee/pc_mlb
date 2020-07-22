
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysPostNoTmpr;

/**
 * The Class SysPostNoTmprRepository.
 */
@Repository
public class SysPostNoTmprRepository extends AbstractRepository {
	

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
	 * 시스템 우편번호 임시 상세 조회.
	 *
	 * @param sysPostNoTmpr the SysPostNoTmpr
	 * @return the SysPostNoTmpr
	 * @throws SQLException the SQL exception
	 */
	public SysPostNoTmpr selectSysPostNoTmpr(SysPostNoTmpr sysPostNoTmpr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysPostNoTmpr", sysPostNoTmpr);
	}
	
	/**
	 * 시스템 우편번호 임시 등록.
	 *
	 * @param sysPostNoTmpr the SysPostNoTmpr
	 * @throws SQLException the SQL exception
	 */
	public void insertSysPostNoTmpr(SysPostNoTmpr sysPostNoTmpr) {
		getSession1().insert("com.plgrim.ncp.base.insertSysPostNoTmpr", sysPostNoTmpr);
	}
	
	/**
	 * 시스템 우편번호 임시 수정.
	 *
	 * @param sysPostNoTmpr the SysPostNoTmpr
	 * @throws SQLException the SQL exception
	 */
	public int updateSysPostNoTmpr(SysPostNoTmpr sysPostNoTmpr) {
		return getSession1().update("com.plgrim.ncp.base.updateSysPostNoTmpr", sysPostNoTmpr);
	}
	
	/**
	 * 시스템 우편번호 임시 삭제.
	 *
	 * @param sysPostNoTmpr the SysPostNoTmpr
	 * @return the SysPostNoTmpr
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysPostNoTmpr(SysPostNoTmpr sysPostNoTmpr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysPostNoTmpr", sysPostNoTmpr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

