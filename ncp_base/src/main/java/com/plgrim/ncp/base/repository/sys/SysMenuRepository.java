
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysMenu;

/**
 * The Class SysMenuRepository.
 */
@Repository
public class SysMenuRepository extends AbstractRepository {
	

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
	 * 시스템 메뉴 상세 조회.
	 *
	 * @param sysMenu the SysMenu
	 * @return the SysMenu
	 * @throws SQLException the SQL exception
	 */
	public SysMenu selectSysMenu(SysMenu sysMenu) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysMenu", sysMenu);
	}
	
	/**
	 * 시스템 메뉴 등록.
	 *
	 * @param sysMenu the SysMenu
	 * @throws SQLException the SQL exception
	 */
	public void insertSysMenu(SysMenu sysMenu) {
		getSession1().insert("com.plgrim.ncp.base.insertSysMenu", sysMenu);
	}
	
	/**
	 * 시스템 메뉴 수정.
	 *
	 * @param sysMenu the SysMenu
	 * @throws SQLException the SQL exception
	 */
	public int updateSysMenu(SysMenu sysMenu) {
		return getSession1().update("com.plgrim.ncp.base.updateSysMenu", sysMenu);
	}
	
	/**
	 * 시스템 메뉴 삭제.
	 *
	 * @param sysMenu the SysMenu
	 * @return the SysMenu
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysMenu(SysMenu sysMenu) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysMenu", sysMenu);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

