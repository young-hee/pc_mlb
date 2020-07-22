
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysPopupNotiAplTgt;

/**
 * The Class SysPopupNotiAplTgtRepository.
 */
@Repository
public class SysPopupNotiAplTgtRepository extends AbstractRepository {
	

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
	 * 시스템 팝업 공지 적용 대상 상세 조회.
	 *
	 * @param sysPopupNotiAplTgt the SysPopupNotiAplTgt
	 * @return the SysPopupNotiAplTgt
	 * @throws SQLException the SQL exception
	 */
	public SysPopupNotiAplTgt selectSysPopupNotiAplTgt(SysPopupNotiAplTgt sysPopupNotiAplTgt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysPopupNotiAplTgt", sysPopupNotiAplTgt);
	}
	
	/**
	 * 시스템 팝업 공지 적용 대상 등록.
	 *
	 * @param sysPopupNotiAplTgt the SysPopupNotiAplTgt
	 * @throws SQLException the SQL exception
	 */
	public void insertSysPopupNotiAplTgt(SysPopupNotiAplTgt sysPopupNotiAplTgt) {
		getSession1().insert("com.plgrim.ncp.base.insertSysPopupNotiAplTgt", sysPopupNotiAplTgt);
	}
	
	/**
	 * 시스템 팝업 공지 적용 대상 수정.
	 *
	 * @param sysPopupNotiAplTgt the SysPopupNotiAplTgt
	 * @throws SQLException the SQL exception
	 */
	public int updateSysPopupNotiAplTgt(SysPopupNotiAplTgt sysPopupNotiAplTgt) {
		return getSession1().update("com.plgrim.ncp.base.updateSysPopupNotiAplTgt", sysPopupNotiAplTgt);
	}
	
	/**
	 * 시스템 팝업 공지 적용 대상 삭제.
	 *
	 * @param sysPopupNotiAplTgt the SysPopupNotiAplTgt
	 * @return the SysPopupNotiAplTgt
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysPopupNotiAplTgt(SysPopupNotiAplTgt sysPopupNotiAplTgt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysPopupNotiAplTgt", sysPopupNotiAplTgt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

