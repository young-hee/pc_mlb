
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAuthorGrp;

/**
 * The Class SysAuthorGrpRepository.
 */
@Repository
public class SysAuthorGrpRepository extends AbstractRepository {
	

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
	 * 시스템 권한 그룹 상세 조회.
	 *
	 * @param sysAuthorGrp the SysAuthorGrp
	 * @return the SysAuthorGrp
	 * @throws SQLException the SQL exception
	 */
	public SysAuthorGrp selectSysAuthorGrp(SysAuthorGrp sysAuthorGrp) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysAuthorGrp", sysAuthorGrp);
	}
	
	/**
	 * 시스템 권한 그룹 등록.
	 *
	 * @param sysAuthorGrp the SysAuthorGrp
	 * @throws SQLException the SQL exception
	 */
	public void insertSysAuthorGrp(SysAuthorGrp sysAuthorGrp) {
		getSession1().insert("com.plgrim.ncp.base.insertSysAuthorGrp", sysAuthorGrp);
	}
	
	/**
	 * 시스템 권한 그룹 수정.
	 *
	 * @param sysAuthorGrp the SysAuthorGrp
	 * @throws SQLException the SQL exception
	 */
	public int updateSysAuthorGrp(SysAuthorGrp sysAuthorGrp) {
		return getSession1().update("com.plgrim.ncp.base.updateSysAuthorGrp", sysAuthorGrp);
	}
	
	/**
	 * 시스템 권한 그룹 삭제.
	 *
	 * @param sysAuthorGrp the SysAuthorGrp
	 * @return the SysAuthorGrp
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysAuthorGrp(SysAuthorGrp sysAuthorGrp) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysAuthorGrp", sysAuthorGrp);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

