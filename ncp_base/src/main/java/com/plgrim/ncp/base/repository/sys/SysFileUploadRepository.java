
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysFileUpload;

/**
 * The Class SysFileUploadRepository.
 */
@Repository
public class SysFileUploadRepository extends AbstractRepository {
	

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
	 * 시스템 파일 업로드 상세 조회.
	 *
	 * @param sysFileUpload the SysFileUpload
	 * @return the SysFileUpload
	 * @throws SQLException the SQL exception
	 */
	public SysFileUpload selectSysFileUpload(SysFileUpload sysFileUpload) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysFileUpload", sysFileUpload);
	}
	
	/**
	 * 시스템 파일 업로드 등록.
	 *
	 * @param sysFileUpload the SysFileUpload
	 * @throws SQLException the SQL exception
	 */
	public void insertSysFileUpload(SysFileUpload sysFileUpload) {
		getSession1().insert("com.plgrim.ncp.base.insertSysFileUpload", sysFileUpload);
	}
	
	/**
	 * 시스템 파일 업로드 수정.
	 *
	 * @param sysFileUpload the SysFileUpload
	 * @throws SQLException the SQL exception
	 */
	public int updateSysFileUpload(SysFileUpload sysFileUpload) {
		return getSession1().update("com.plgrim.ncp.base.updateSysFileUpload", sysFileUpload);
	}
	
	/**
	 * 시스템 파일 업로드 삭제.
	 *
	 * @param sysFileUpload the SysFileUpload
	 * @return the SysFileUpload
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysFileUpload(SysFileUpload sysFileUpload) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysFileUpload", sysFileUpload);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

