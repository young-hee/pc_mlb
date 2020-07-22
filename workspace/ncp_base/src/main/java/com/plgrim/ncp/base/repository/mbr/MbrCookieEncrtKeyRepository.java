
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
 * Generator(Generator)		2018-04-13                      
 */
package com.plgrim.ncp.base.repository.mbr;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource3.mbr.MbrCookieEncrtKey;

/**
 * The Class MbrCookieEncrtKeyRepository.
 */
@Repository
public class MbrCookieEncrtKeyRepository extends AbstractRepository {
	

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
	 * 회원 쿠키 암호화 키 상세 조회.
	 *
	 * @param mbrCookieEncrtKey the MbrCookieEncrtKey
	 * @return the MbrCookieEncrtKey
	 * @throws SQLException the SQL exception
	 */
	public MbrCookieEncrtKey selectMbrCookieEncrtKey(MbrCookieEncrtKey mbrCookieEncrtKey) throws Exception {
		return getSession3().selectOne("com.plgrim.ncp.base.selectMbrCookieEncrtKey", mbrCookieEncrtKey);
	}
	
	/**
	 * 회원 쿠키 암호화 키 등록.
	 *
	 * @param mbrCookieEncrtKey the MbrCookieEncrtKey
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrCookieEncrtKey(MbrCookieEncrtKey mbrCookieEncrtKey) throws Exception {
		getSession3().insert("com.plgrim.ncp.base.insertMbrCookieEncrtKey", mbrCookieEncrtKey);
	}
	
	/**
	 * 회원 쿠키 암호화 키 수정.
	 *
	 * @param mbrCookieEncrtKey the MbrCookieEncrtKey
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrCookieEncrtKey(MbrCookieEncrtKey mbrCookieEncrtKey) throws Exception {
		return getSession3().update("com.plgrim.ncp.base.updateMbrCookieEncrtKey", mbrCookieEncrtKey);
	}
	
	/**
	 * 회원 쿠키 암호화 키 삭제.
	 *
	 * @param mbrCookieEncrtKey the MbrCookieEncrtKey
	 * @return the MbrCookieEncrtKey
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrCookieEncrtKey(MbrCookieEncrtKey mbrCookieEncrtKey) throws Exception {
		return getSession3().delete("com.plgrim.ncp.base.deleteMbrCookieEncrtKey", mbrCookieEncrtKey);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

