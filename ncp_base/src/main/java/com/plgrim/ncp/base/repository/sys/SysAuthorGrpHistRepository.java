
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAuthorGrpHist;

/**
 * The Class SysAuthorGrpHistRepository.
 */
@Repository
public class SysAuthorGrpHistRepository extends AbstractRepository {
	

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
	 * 시스템 권한 그룹 이력 상세 조회.
	 *
	 * @param sysAuthorGrpHist the SysAuthorGrpHist
	 * @return the SysAuthorGrpHist
	 * @throws SQLException the SQL exception
	 */
	public SysAuthorGrpHist selectSysAuthorGrpHist(SysAuthorGrpHist sysAuthorGrpHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysAuthorGrpHist", sysAuthorGrpHist);
	}
	
	/**
	 * 시스템 권한 그룹 이력 등록.
	 *
	 * @param sysAuthorGrpHist the SysAuthorGrpHist
	 * @throws SQLException the SQL exception
	 */
	public void insertSysAuthorGrpHist(SysAuthorGrpHist sysAuthorGrpHist) {
		getSession1().insert("com.plgrim.ncp.base.insertSysAuthorGrpHist", sysAuthorGrpHist);
	}
	
	/**
	 * 시스템 권한 그룹 이력 수정.
	 *
	 * @param sysAuthorGrpHist the SysAuthorGrpHist
	 * @throws SQLException the SQL exception
	 */
	public int updateSysAuthorGrpHist(SysAuthorGrpHist sysAuthorGrpHist) {
		return getSession1().update("com.plgrim.ncp.base.updateSysAuthorGrpHist", sysAuthorGrpHist);
	}
	
	/**
	 * 시스템 권한 그룹 이력 삭제.
	 *
	 * @param sysAuthorGrpHist the SysAuthorGrpHist
	 * @return the SysAuthorGrpHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysAuthorGrpHist(SysAuthorGrpHist sysAuthorGrpHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysAuthorGrpHist", sysAuthorGrpHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

