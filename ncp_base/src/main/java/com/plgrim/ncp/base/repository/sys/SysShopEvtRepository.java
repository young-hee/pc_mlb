
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopEvt;

/**
 * The Class SysShopEvtRepository.
 */
@Repository
public class SysShopEvtRepository extends AbstractRepository {
	

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
	 * 시스템 매장 이벤트 상세 조회.
	 *
	 * @param sysShopEvt the SysShopEvt
	 * @return the SysShopEvt
	 * @throws SQLException the SQL exception
	 */
	public SysShopEvt selectSysShopEvt(SysShopEvt sysShopEvt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysShopEvt", sysShopEvt);
	}
	
	/**
	 * 시스템 매장 이벤트 등록.
	 *
	 * @param sysShopEvt the SysShopEvt
	 * @throws SQLException the SQL exception
	 */
	public void insertSysShopEvt(SysShopEvt sysShopEvt) {
		getSession1().insert("com.plgrim.ncp.base.insertSysShopEvt", sysShopEvt);
	}
	
	/**
	 * 시스템 매장 이벤트 수정.
	 *
	 * @param sysShopEvt the SysShopEvt
	 * @throws SQLException the SQL exception
	 */
	public int updateSysShopEvt(SysShopEvt sysShopEvt) {
		return getSession1().update("com.plgrim.ncp.base.updateSysShopEvt", sysShopEvt);
	}
	
	/**
	 * 시스템 매장 이벤트 삭제.
	 *
	 * @param sysShopEvt the SysShopEvt
	 * @return the SysShopEvt
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysShopEvt(SysShopEvt sysShopEvt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysShopEvt", sysShopEvt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

