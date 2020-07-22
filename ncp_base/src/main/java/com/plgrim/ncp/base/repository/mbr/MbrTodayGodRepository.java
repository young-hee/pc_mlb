
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrTodayGod;

/**
 * The Class MbrTodayGodRepository.
 */
@Repository
public class MbrTodayGodRepository extends AbstractRepository {
	

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
	 * 회원 오늘본상품 상세 조회.
	 *
	 * @param mbrTodayGod the MbrTodayGod
	 * @return the MbrTodayGod
	 * @throws SQLException the SQL exception
	 */
	public MbrTodayGod selectMbrTodayGod(MbrTodayGod mbrTodayGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrTodayGod", mbrTodayGod);
	}
	
	/**
	 * 회원 오늘본상품 등록.
	 *
	 * @param mbrTodayGod the MbrTodayGod
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrTodayGod(MbrTodayGod mbrTodayGod) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrTodayGod", mbrTodayGod);
	}
	
	/**
	 * 회원 오늘본상품 수정.
	 *
	 * @param mbrTodayGod the MbrTodayGod
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrTodayGod(MbrTodayGod mbrTodayGod) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrTodayGod", mbrTodayGod);
	}
	
	/**
	 * 회원 오늘본상품 삭제.
	 *
	 * @param mbrTodayGod the MbrTodayGod
	 * @return the MbrTodayGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrTodayGod(MbrTodayGod mbrTodayGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrTodayGod", mbrTodayGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

