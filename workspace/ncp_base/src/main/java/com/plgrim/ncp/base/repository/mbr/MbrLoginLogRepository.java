
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
package com.plgrim.ncp.base.repository.mbr;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrLoginLog;

/**
 * The Class MbrLoginLogRepository.
 */
@Repository
public class MbrLoginLogRepository extends AbstractRepository {
	

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
	 * 회원 로그인 로그 상세 조회.
	 *
	 * @param mbrLoginLog the MbrLoginLog
	 * @return the MbrLoginLog
	 * @throws SQLException the SQL exception
	 */
	public MbrLoginLog selectMbrLoginLog(MbrLoginLog mbrLoginLog) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrLoginLog", mbrLoginLog);
	}
	
	/**
	 * 회원 로그인 로그 등록.
	 *
	 * @param mbrLoginLog the MbrLoginLog
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrLoginLog(MbrLoginLog mbrLoginLog) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrLoginLog", mbrLoginLog);
	}
	
	/**
	 * 회원 로그인 로그 수정.
	 *
	 * @param mbrLoginLog the MbrLoginLog
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrLoginLog(MbrLoginLog mbrLoginLog) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrLoginLog", mbrLoginLog);
	}
	
	/**
	 * 회원 로그인 로그 삭제.
	 *
	 * @param mbrLoginLog the MbrLoginLog
	 * @return the MbrLoginLog
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrLoginLog(MbrLoginLog mbrLoginLog) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrLoginLog", mbrLoginLog);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

