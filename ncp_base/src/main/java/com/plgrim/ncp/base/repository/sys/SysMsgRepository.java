
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysMsg;

/**
 * The Class SysMsgRepository.
 */
@Repository
public class SysMsgRepository extends AbstractRepository {
	

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
	 * 시스템 메시지 상세 조회.
	 *
	 * @param sysMsg the SysMsg
	 * @return the SysMsg
	 * @throws SQLException the SQL exception
	 */
	public SysMsg selectSysMsg(SysMsg sysMsg) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysMsg", sysMsg);
	}
	
	/**
	 * 시스템 메시지 등록.
	 *
	 * @param sysMsg the SysMsg
	 * @throws SQLException the SQL exception
	 */
	public void insertSysMsg(SysMsg sysMsg) {
		getSession1().insert("com.plgrim.ncp.base.insertSysMsg", sysMsg);
	}
	
	/**
	 * 시스템 메시지 수정.
	 *
	 * @param sysMsg the SysMsg
	 * @throws SQLException the SQL exception
	 */
	public int updateSysMsg(SysMsg sysMsg) {
		return getSession1().update("com.plgrim.ncp.base.updateSysMsg", sysMsg);
	}
	
	/**
	 * 시스템 메시지 삭제.
	 *
	 * @param sysMsg the SysMsg
	 * @return the SysMsg
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysMsg(SysMsg sysMsg) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysMsg", sysMsg);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

