
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysExchgRt;

/**
 * The Class SysExchgRtRepository.
 */
@Repository
public class SysExchgRtRepository extends AbstractRepository {
	

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
	 * 시스템 환율 상세 조회.
	 *
	 * @param sysExchgRt the SysExchgRt
	 * @return the SysExchgRt
	 * @throws SQLException the SQL exception
	 */
	public SysExchgRt selectSysExchgRt(SysExchgRt sysExchgRt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysExchgRt", sysExchgRt);
	}
	
	/**
	 * 시스템 환율 등록.
	 *
	 * @param sysExchgRt the SysExchgRt
	 * @throws SQLException the SQL exception
	 */
	public void insertSysExchgRt(SysExchgRt sysExchgRt) {
		getSession1().insert("com.plgrim.ncp.base.insertSysExchgRt", sysExchgRt);
	}
	
	/**
	 * 시스템 환율 수정.
	 *
	 * @param sysExchgRt the SysExchgRt
	 * @throws SQLException the SQL exception
	 */
	public int updateSysExchgRt(SysExchgRt sysExchgRt) {
		return getSession1().update("com.plgrim.ncp.base.updateSysExchgRt", sysExchgRt);
	}
	
	/**
	 * 시스템 환율 삭제.
	 *
	 * @param sysExchgRt the SysExchgRt
	 * @return the SysExchgRt
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysExchgRt(SysExchgRt sysExchgRt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysExchgRt", sysExchgRt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

