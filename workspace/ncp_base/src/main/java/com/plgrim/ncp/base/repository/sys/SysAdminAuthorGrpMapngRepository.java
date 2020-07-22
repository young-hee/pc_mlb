
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminAuthorGrpMapng;

/**
 * The Class SysAdminAuthorGrpMapngRepository.
 */
@Repository
public class SysAdminAuthorGrpMapngRepository extends AbstractRepository {
	

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
	 * 시스템 관리자 권한 그룹 매핑 상세 조회.
	 *
	 * @param sysAdminAuthorGrpMapng the SysAdminAuthorGrpMapng
	 * @return the SysAdminAuthorGrpMapng
	 * @throws SQLException the SQL exception
	 */
	public SysAdminAuthorGrpMapng selectSysAdminAuthorGrpMapng(SysAdminAuthorGrpMapng sysAdminAuthorGrpMapng) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysAdminAuthorGrpMapng", sysAdminAuthorGrpMapng);
	}
	
	/**
	 * 시스템 관리자 권한 그룹 매핑 등록.
	 *
	 * @param sysAdminAuthorGrpMapng the SysAdminAuthorGrpMapng
	 * @throws SQLException the SQL exception
	 */
	public void insertSysAdminAuthorGrpMapng(SysAdminAuthorGrpMapng sysAdminAuthorGrpMapng) {
		getSession1().insert("com.plgrim.ncp.base.insertSysAdminAuthorGrpMapng", sysAdminAuthorGrpMapng);
	}
	
	/**
	 * 시스템 관리자 권한 그룹 매핑 수정.
	 *
	 * @param sysAdminAuthorGrpMapng the SysAdminAuthorGrpMapng
	 * @throws SQLException the SQL exception
	 */
	public int updateSysAdminAuthorGrpMapng(SysAdminAuthorGrpMapng sysAdminAuthorGrpMapng) {
		return getSession1().update("com.plgrim.ncp.base.updateSysAdminAuthorGrpMapng", sysAdminAuthorGrpMapng);
	}
	
	/**
	 * 시스템 관리자 권한 그룹 매핑 삭제.
	 *
	 * @param sysAdminAuthorGrpMapng the SysAdminAuthorGrpMapng
	 * @return the SysAdminAuthorGrpMapng
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysAdminAuthorGrpMapng(SysAdminAuthorGrpMapng sysAdminAuthorGrpMapng) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysAdminAuthorGrpMapng", sysAdminAuthorGrpMapng);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

