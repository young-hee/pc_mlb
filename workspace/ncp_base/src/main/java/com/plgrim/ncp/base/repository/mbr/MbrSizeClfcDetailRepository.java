
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSizeClfcDetail;

/**
 * The Class MbrSizeClfcDetailRepository.
 */
@Repository
public class MbrSizeClfcDetailRepository extends AbstractRepository {
	

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
	 * 회원 사이즈 분류 상세 상세 조회.
	 *
	 * @param mbrSizeClfcDetail the MbrSizeClfcDetail
	 * @return the MbrSizeClfcDetail
	 * @throws SQLException the SQL exception
	 */
	public MbrSizeClfcDetail selectMbrSizeClfcDetail(MbrSizeClfcDetail mbrSizeClfcDetail) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrSizeClfcDetail", mbrSizeClfcDetail);
	}
	
	/**
	 * 회원 사이즈 분류 상세 등록.
	 *
	 * @param mbrSizeClfcDetail the MbrSizeClfcDetail
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrSizeClfcDetail(MbrSizeClfcDetail mbrSizeClfcDetail) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrSizeClfcDetail", mbrSizeClfcDetail);
	}
	
	/**
	 * 회원 사이즈 분류 상세 수정.
	 *
	 * @param mbrSizeClfcDetail the MbrSizeClfcDetail
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrSizeClfcDetail(MbrSizeClfcDetail mbrSizeClfcDetail) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrSizeClfcDetail", mbrSizeClfcDetail);
	}
	
	/**
	 * 회원 사이즈 분류 상세 삭제.
	 *
	 * @param mbrSizeClfcDetail the MbrSizeClfcDetail
	 * @return the MbrSizeClfcDetail
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrSizeClfcDetail(MbrSizeClfcDetail mbrSizeClfcDetail) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrSizeClfcDetail", mbrSizeClfcDetail);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

