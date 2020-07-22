
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysPopupNoti;

/**
 * The Class SysPopupNotiRepository.
 */
@Repository
public class SysPopupNotiRepository extends AbstractRepository {
	

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
	 * 시스템 팝업 공지 상세 조회.
	 *
	 * @param sysPopupNoti the SysPopupNoti
	 * @return the SysPopupNoti
	 * @throws SQLException the SQL exception
	 */
	public SysPopupNoti selectSysPopupNoti(SysPopupNoti sysPopupNoti) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysPopupNoti", sysPopupNoti);
	}
	
	/**
	 * 시스템 팝업 공지 등록.
	 *
	 * @param sysPopupNoti the SysPopupNoti
	 * @throws SQLException the SQL exception
	 */
	public void insertSysPopupNoti(SysPopupNoti sysPopupNoti) {
		getSession1().insert("com.plgrim.ncp.base.insertSysPopupNoti", sysPopupNoti);
	}
	
	/**
	 * 시스템 팝업 공지 수정.
	 *
	 * @param sysPopupNoti the SysPopupNoti
	 * @throws SQLException the SQL exception
	 */
	public int updateSysPopupNoti(SysPopupNoti sysPopupNoti) {
		return getSession1().update("com.plgrim.ncp.base.updateSysPopupNoti", sysPopupNoti);
	}
	
	/**
	 * 시스템 팝업 공지 삭제.
	 *
	 * @param sysPopupNoti the SysPopupNoti
	 * @return the SysPopupNoti
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysPopupNoti(SysPopupNoti sysPopupNoti) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysPopupNoti", sysPopupNoti);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

