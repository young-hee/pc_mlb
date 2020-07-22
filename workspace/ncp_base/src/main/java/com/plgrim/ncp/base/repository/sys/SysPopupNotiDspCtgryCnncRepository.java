
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysPopupNotiDspCtgryCnnc;

/**
 * The Class SysPopupNotiDspCtgryCnncRepository.
 */
@Repository
public class SysPopupNotiDspCtgryCnncRepository extends AbstractRepository {
	

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
	 * 시스템 팝업 공지 전시 카테고리 연결 상세 조회.
	 *
	 * @param sysPopupNotiDspCtgryCnnc the SysPopupNotiDspCtgryCnnc
	 * @return the SysPopupNotiDspCtgryCnnc
	 * @throws SQLException the SQL exception
	 */
	public SysPopupNotiDspCtgryCnnc selectSysPopupNotiDspCtgryCnnc(SysPopupNotiDspCtgryCnnc sysPopupNotiDspCtgryCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysPopupNotiDspCtgryCnnc", sysPopupNotiDspCtgryCnnc);
	}
	
	/**
	 * 시스템 팝업 공지 전시 카테고리 연결 등록.
	 *
	 * @param sysPopupNotiDspCtgryCnnc the SysPopupNotiDspCtgryCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertSysPopupNotiDspCtgryCnnc(SysPopupNotiDspCtgryCnnc sysPopupNotiDspCtgryCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertSysPopupNotiDspCtgryCnnc", sysPopupNotiDspCtgryCnnc);
	}
	
	/**
	 * 시스템 팝업 공지 전시 카테고리 연결 수정.
	 *
	 * @param sysPopupNotiDspCtgryCnnc the SysPopupNotiDspCtgryCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateSysPopupNotiDspCtgryCnnc(SysPopupNotiDspCtgryCnnc sysPopupNotiDspCtgryCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateSysPopupNotiDspCtgryCnnc", sysPopupNotiDspCtgryCnnc);
	}
	
	/**
	 * 시스템 팝업 공지 전시 카테고리 연결 삭제.
	 *
	 * @param sysPopupNotiDspCtgryCnnc the SysPopupNotiDspCtgryCnnc
	 * @return the SysPopupNotiDspCtgryCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysPopupNotiDspCtgryCnnc(SysPopupNotiDspCtgryCnnc sysPopupNotiDspCtgryCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysPopupNotiDspCtgryCnnc", sysPopupNotiDspCtgryCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

