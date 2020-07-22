
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistDetail;

/**
 * The Class MbrWebpntHistDetailRepository.
 */
@Repository
public class MbrWebpntHistDetailRepository extends AbstractRepository {
	

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
	 * 회원 웹포인트 이력 상세 상세 조회.
	 *
	 * @param mbrWebpntHistDetail the MbrWebpntHistDetail
	 * @return the MbrWebpntHistDetail
	 * @throws SQLException the SQL exception
	 */
	public MbrWebpntHistDetail selectMbrWebpntHistDetail(MbrWebpntHistDetail mbrWebpntHistDetail) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrWebpntHistDetail", mbrWebpntHistDetail);
	}
	
	/**
	 * 회원 웹포인트 이력 상세 등록.
	 *
	 * @param mbrWebpntHistDetail the MbrWebpntHistDetail
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrWebpntHistDetail(MbrWebpntHistDetail mbrWebpntHistDetail) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrWebpntHistDetail", mbrWebpntHistDetail);
	}
	
	/**
	 * 회원 웹포인트 이력 상세 수정.
	 *
	 * @param mbrWebpntHistDetail the MbrWebpntHistDetail
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrWebpntHistDetail(MbrWebpntHistDetail mbrWebpntHistDetail) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrWebpntHistDetail", mbrWebpntHistDetail);
	}
	
	/**
	 * 회원 웹포인트 이력 상세 삭제.
	 *
	 * @param mbrWebpntHistDetail the MbrWebpntHistDetail
	 * @return the MbrWebpntHistDetail
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrWebpntHistDetail(MbrWebpntHistDetail mbrWebpntHistDetail) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrWebpntHistDetail", mbrWebpntHistDetail);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

