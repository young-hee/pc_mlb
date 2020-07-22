
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBukmkBrnd;

/**
 * The Class MbrBukmkBrndRepository.
 */
@Repository
public class MbrBukmkBrndRepository extends AbstractRepository {
	

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
	 * 회원 즐겨찾기 브랜드 상세 조회.
	 *
	 * @param mbrBukmkBrnd the MbrBukmkBrnd
	 * @return the MbrBukmkBrnd
	 * @throws SQLException the SQL exception
	 */
	public MbrBukmkBrnd selectMbrBukmkBrnd(MbrBukmkBrnd mbrBukmkBrnd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrBukmkBrnd", mbrBukmkBrnd);
	}
	
	/**
	 * 회원 즐겨찾기 브랜드 등록.
	 *
	 * @param mbrBukmkBrnd the MbrBukmkBrnd
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrBukmkBrnd(MbrBukmkBrnd mbrBukmkBrnd) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrBukmkBrnd", mbrBukmkBrnd);
	}
	
	/**
	 * 회원 즐겨찾기 브랜드 수정.
	 *
	 * @param mbrBukmkBrnd the MbrBukmkBrnd
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrBukmkBrnd(MbrBukmkBrnd mbrBukmkBrnd) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrBukmkBrnd", mbrBukmkBrnd);
	}
	
	/**
	 * 회원 즐겨찾기 브랜드 삭제.
	 *
	 * @param mbrBukmkBrnd the MbrBukmkBrnd
	 * @return the MbrBukmkBrnd
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrBukmkBrnd(MbrBukmkBrnd mbrBukmkBrnd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrBukmkBrnd", mbrBukmkBrnd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

