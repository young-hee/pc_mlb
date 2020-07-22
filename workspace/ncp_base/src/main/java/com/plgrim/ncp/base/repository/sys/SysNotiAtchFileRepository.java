
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysNotiAtchFile;

/**
 * The Class SysNotiAtchFileRepository.
 */
@Repository
public class SysNotiAtchFileRepository extends AbstractRepository {
	

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
	 * 시스템 공지 첨부 파일 상세 조회.
	 *
	 * @param sysNotiAtchFile the SysNotiAtchFile
	 * @return the SysNotiAtchFile
	 * @throws SQLException the SQL exception
	 */
	public SysNotiAtchFile selectSysNotiAtchFile(SysNotiAtchFile sysNotiAtchFile) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysNotiAtchFile", sysNotiAtchFile);
	}
	
	/**
	 * 시스템 공지 첨부 파일 등록.
	 *
	 * @param sysNotiAtchFile the SysNotiAtchFile
	 * @throws SQLException the SQL exception
	 */
	public void insertSysNotiAtchFile(SysNotiAtchFile sysNotiAtchFile) {
		getSession1().insert("com.plgrim.ncp.base.insertSysNotiAtchFile", sysNotiAtchFile);
	}
	
	/**
	 * 시스템 공지 첨부 파일 수정.
	 *
	 * @param sysNotiAtchFile the SysNotiAtchFile
	 * @throws SQLException the SQL exception
	 */
	public int updateSysNotiAtchFile(SysNotiAtchFile sysNotiAtchFile) {
		return getSession1().update("com.plgrim.ncp.base.updateSysNotiAtchFile", sysNotiAtchFile);
	}
	
	/**
	 * 시스템 공지 첨부 파일 삭제.
	 *
	 * @param sysNotiAtchFile the SysNotiAtchFile
	 * @return the SysNotiAtchFile
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysNotiAtchFile(SysNotiAtchFile sysNotiAtchFile) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysNotiAtchFile", sysNotiAtchFile);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

