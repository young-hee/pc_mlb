
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrLoginCookieLog;

/**
 * The Class MbrLoginCookieLogRepository.
 */
@Repository
public class MbrLoginCookieLogRepository extends AbstractRepository {
	

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
	 * 회원 로그인 쿠키 로그 상세 조회.
	 *
	 * @param mbrLoginCookieLog the MbrLoginCookieLog
	 * @return the MbrLoginCookieLog
	 * @throws SQLException the SQL exception
	 */
	public MbrLoginCookieLog selectMbrLoginCookieLog(MbrLoginCookieLog mbrLoginCookieLog) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrLoginCookieLog", mbrLoginCookieLog);
	}
	
	/**
	 * 회원 로그인 쿠키 로그 등록.
	 *
	 * @param mbrLoginCookieLog the MbrLoginCookieLog
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrLoginCookieLog(MbrLoginCookieLog mbrLoginCookieLog) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrLoginCookieLog", mbrLoginCookieLog);
	}
	
	/**
	 * 회원 로그인 쿠키 로그 수정.
	 *
	 * @param mbrLoginCookieLog the MbrLoginCookieLog
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrLoginCookieLog(MbrLoginCookieLog mbrLoginCookieLog) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrLoginCookieLog", mbrLoginCookieLog);
	}
	
	/**
	 * 회원 로그인 쿠키 로그 삭제.
	 *
	 * @param mbrLoginCookieLog the MbrLoginCookieLog
	 * @return the MbrLoginCookieLog
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrLoginCookieLog(MbrLoginCookieLog mbrLoginCookieLog) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrLoginCookieLog", mbrLoginCookieLog);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

