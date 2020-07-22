
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysGrpCd;

/**
 * The Class SysGrpCdRepository.
 */
@Repository
public class SysGrpCdRepository extends AbstractRepository {
	

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
	 * 시스템 그룹 코드 상세 조회.
	 *
	 * @param sysGrpCd the SysGrpCd
	 * @return the SysGrpCd
	 * @throws SQLException the SQL exception
	 */
	public SysGrpCd selectSysGrpCd(SysGrpCd sysGrpCd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysGrpCd", sysGrpCd);
	}
	
	/**
	 * 시스템 그룹 코드 등록.
	 *
	 * @param sysGrpCd the SysGrpCd
	 * @throws SQLException the SQL exception
	 */
	public void insertSysGrpCd(SysGrpCd sysGrpCd) {
		getSession1().insert("com.plgrim.ncp.base.insertSysGrpCd", sysGrpCd);
	}
	
	/**
	 * 시스템 그룹 코드 수정.
	 *
	 * @param sysGrpCd the SysGrpCd
	 * @throws SQLException the SQL exception
	 */
	public int updateSysGrpCd(SysGrpCd sysGrpCd) {
		return getSession1().update("com.plgrim.ncp.base.updateSysGrpCd", sysGrpCd);
	}
	
	/**
	 * 시스템 그룹 코드 삭제.
	 *
	 * @param sysGrpCd the SysGrpCd
	 * @return the SysGrpCd
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysGrpCd(SysGrpCd sysGrpCd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysGrpCd", sysGrpCd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

