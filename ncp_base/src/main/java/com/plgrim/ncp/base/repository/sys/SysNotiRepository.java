
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysNoti;

/**
 * The Class SysNotiRepository.
 */
@Repository
public class SysNotiRepository extends AbstractRepository {
	

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
	 * 시스템 공지 상세 조회.
	 *
	 * @param sysNoti the SysNoti
	 * @return the SysNoti
	 * @throws SQLException the SQL exception
	 */
	public SysNoti selectSysNoti(SysNoti sysNoti) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysNoti", sysNoti);
	}
	
	/**
	 * 시스템 공지 등록.
	 *
	 * @param sysNoti the SysNoti
	 * @throws SQLException the SQL exception
	 */
	public void insertSysNoti(SysNoti sysNoti) {
		getSession1().insert("com.plgrim.ncp.base.insertSysNoti", sysNoti);
	}
	
	/**
	 * 시스템 공지 수정.
	 *
	 * @param sysNoti the SysNoti
	 * @throws SQLException the SQL exception
	 */
	public int updateSysNoti(SysNoti sysNoti) {
		return getSession1().update("com.plgrim.ncp.base.updateSysNoti", sysNoti);
	}
	
	/**
	 * 시스템 공지 삭제.
	 *
	 * @param sysNoti the SysNoti
	 * @return the SysNoti
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysNoti(SysNoti sysNoti) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysNoti", sysNoti);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

