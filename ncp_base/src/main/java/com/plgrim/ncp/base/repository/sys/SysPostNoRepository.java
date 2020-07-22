
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysPostNo;

/**
 * The Class SysPostNoRepository.
 */
@Repository
public class SysPostNoRepository extends AbstractRepository {
	

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
	 * 시스템 우편번호 상세 조회.
	 *
	 * @param sysPostNo the SysPostNo
	 * @return the SysPostNo
	 * @throws SQLException the SQL exception
	 */
	public SysPostNo selectSysPostNo(SysPostNo sysPostNo) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysPostNo", sysPostNo);
	}
	
	/**
	 * 시스템 우편번호 등록.
	 *
	 * @param sysPostNo the SysPostNo
	 * @throws SQLException the SQL exception
	 */
	public void insertSysPostNo(SysPostNo sysPostNo) {
		getSession1().insert("com.plgrim.ncp.base.insertSysPostNo", sysPostNo);
	}
	
	/**
	 * 시스템 우편번호 수정.
	 *
	 * @param sysPostNo the SysPostNo
	 * @throws SQLException the SQL exception
	 */
	public int updateSysPostNo(SysPostNo sysPostNo) {
		return getSession1().update("com.plgrim.ncp.base.updateSysPostNo", sysPostNo);
	}
	
	/**
	 * 시스템 우편번호 삭제.
	 *
	 * @param sysPostNo the SysPostNo
	 * @return the SysPostNo
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysPostNo(SysPostNo sysPostNo) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysPostNo", sysPostNo);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

