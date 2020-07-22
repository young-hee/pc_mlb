
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminAuthorGrpMapngHis;

/**
 * The Class SysAdminAuthorGrpMapngHisRepository.
 */
@Repository
public class SysAdminAuthorGrpMapngHisRepository extends AbstractRepository {
	

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
	 * 시스템 관리자 권한 그룹 매핑 이력 상세 조회.
	 *
	 * @param sysAdminAuthorGrpMapngHis the SysAdminAuthorGrpMapngHis
	 * @return the SysAdminAuthorGrpMapngHis
	 * @throws SQLException the SQL exception
	 */
	public SysAdminAuthorGrpMapngHis selectSysAdminAuthorGrpMapngHis(SysAdminAuthorGrpMapngHis sysAdminAuthorGrpMapngHis) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysAdminAuthorGrpMapngHis", sysAdminAuthorGrpMapngHis);
	}
	
	/**
	 * 시스템 관리자 권한 그룹 매핑 이력 등록.
	 *
	 * @param sysAdminAuthorGrpMapngHis the SysAdminAuthorGrpMapngHis
	 * @throws SQLException the SQL exception
	 */
	public void insertSysAdminAuthorGrpMapngHis(SysAdminAuthorGrpMapngHis sysAdminAuthorGrpMapngHis) {
		getSession1().insert("com.plgrim.ncp.base.insertSysAdminAuthorGrpMapngHis", sysAdminAuthorGrpMapngHis);
	}
	
	/**
	 * 시스템 관리자 권한 그룹 매핑 이력 수정.
	 *
	 * @param sysAdminAuthorGrpMapngHis the SysAdminAuthorGrpMapngHis
	 * @throws SQLException the SQL exception
	 */
	public int updateSysAdminAuthorGrpMapngHis(SysAdminAuthorGrpMapngHis sysAdminAuthorGrpMapngHis) {
		return getSession1().update("com.plgrim.ncp.base.updateSysAdminAuthorGrpMapngHis", sysAdminAuthorGrpMapngHis);
	}
	
	/**
	 * 시스템 관리자 권한 그룹 매핑 이력 삭제.
	 *
	 * @param sysAdminAuthorGrpMapngHis the SysAdminAuthorGrpMapngHis
	 * @return the SysAdminAuthorGrpMapngHis
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysAdminAuthorGrpMapngHis(SysAdminAuthorGrpMapngHis sysAdminAuthorGrpMapngHis) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysAdminAuthorGrpMapngHis", sysAdminAuthorGrpMapngHis);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

