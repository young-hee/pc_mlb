
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysGrpCdCdCnnc;

/**
 * The Class SysGrpCdCdCnncRepository.
 */
@Repository
public class SysGrpCdCdCnncRepository extends AbstractRepository {
	

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
	 * 시스템 그룹 코드 코드 연결 상세 조회.
	 *
	 * @param sysGrpCdCdCnnc the SysGrpCdCdCnnc
	 * @return the SysGrpCdCdCnnc
	 * @throws SQLException the SQL exception
	 */
	public SysGrpCdCdCnnc selectSysGrpCdCdCnnc(SysGrpCdCdCnnc sysGrpCdCdCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysGrpCdCdCnnc", sysGrpCdCdCnnc);
	}
	
	/**
	 * 시스템 그룹 코드 코드 연결 등록.
	 *
	 * @param sysGrpCdCdCnnc the SysGrpCdCdCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertSysGrpCdCdCnnc(SysGrpCdCdCnnc sysGrpCdCdCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertSysGrpCdCdCnnc", sysGrpCdCdCnnc);
	}
	
	/**
	 * 시스템 그룹 코드 코드 연결 수정.
	 *
	 * @param sysGrpCdCdCnnc the SysGrpCdCdCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateSysGrpCdCdCnnc(SysGrpCdCdCnnc sysGrpCdCdCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateSysGrpCdCdCnnc", sysGrpCdCdCnnc);
	}
	
	/**
	 * 시스템 그룹 코드 코드 연결 삭제.
	 *
	 * @param sysGrpCdCdCnnc the SysGrpCdCdCnnc
	 * @return the SysGrpCdCdCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysGrpCdCdCnnc(SysGrpCdCdCnnc sysGrpCdCdCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysGrpCdCdCnnc", sysGrpCdCdCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

