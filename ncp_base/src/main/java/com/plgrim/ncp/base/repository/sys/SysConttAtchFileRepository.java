
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysConttAtchFile;

/**
 * The Class SysConttAtchFileRepository.
 */
@Repository
public class SysConttAtchFileRepository extends AbstractRepository {
	

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
	 * 시스템 컨텐츠 첨부 파일 상세 조회.
	 *
	 * @param sysConttAtchFile the SysConttAtchFile
	 * @return the SysConttAtchFile
	 * @throws SQLException the SQL exception
	 */
	public SysConttAtchFile selectSysConttAtchFile(SysConttAtchFile sysConttAtchFile) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysConttAtchFile", sysConttAtchFile);
	}
	
	/**
	 * 시스템 컨텐츠 첨부 파일 등록.
	 *
	 * @param sysConttAtchFile the SysConttAtchFile
	 * @throws SQLException the SQL exception
	 */
	public void insertSysConttAtchFile(SysConttAtchFile sysConttAtchFile) {
		getSession1().insert("com.plgrim.ncp.base.insertSysConttAtchFile", sysConttAtchFile);
	}
	
	/**
	 * 시스템 컨텐츠 첨부 파일 수정.
	 *
	 * @param sysConttAtchFile the SysConttAtchFile
	 * @throws SQLException the SQL exception
	 */
	public int updateSysConttAtchFile(SysConttAtchFile sysConttAtchFile) {
		return getSession1().update("com.plgrim.ncp.base.updateSysConttAtchFile", sysConttAtchFile);
	}
	
	/**
	 * 시스템 컨텐츠 첨부 파일 삭제.
	 *
	 * @param sysConttAtchFile the SysConttAtchFile
	 * @return the SysConttAtchFile
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysConttAtchFile(SysConttAtchFile sysConttAtchFile) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysConttAtchFile", sysConttAtchFile);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

