
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysGrpco;

/**
 * The Class SysGrpcoRepository.
 */
@Repository
public class SysGrpcoRepository extends AbstractRepository {
	

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
	 * 시스템 그룹사 상세 조회.
	 *
	 * @param sysGrpco the SysGrpco
	 * @return the SysGrpco
	 * @throws SQLException the SQL exception
	 */
	public SysGrpco selectSysGrpco(SysGrpco sysGrpco) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysGrpco", sysGrpco);
	}
	
	/**
	 * 시스템 그룹사 등록.
	 *
	 * @param sysGrpco the SysGrpco
	 * @throws SQLException the SQL exception
	 */
	public void insertSysGrpco(SysGrpco sysGrpco) {
		getSession1().insert("com.plgrim.ncp.base.insertSysGrpco", sysGrpco);
	}
	
	/**
	 * 시스템 그룹사 수정.
	 *
	 * @param sysGrpco the SysGrpco
	 * @throws SQLException the SQL exception
	 */
	public int updateSysGrpco(SysGrpco sysGrpco) {
		return getSession1().update("com.plgrim.ncp.base.updateSysGrpco", sysGrpco);
	}
	
	/**
	 * 시스템 그룹사 삭제.
	 *
	 * @param sysGrpco the SysGrpco
	 * @return the SysGrpco
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysGrpco(SysGrpco sysGrpco) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysGrpco", sysGrpco);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

