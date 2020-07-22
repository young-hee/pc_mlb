
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;

/**
 * The Class MbrWebpntHistRepository.
 */
@Repository
public class MbrWebpntHistRepository extends AbstractRepository {
	

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
	 * 회원 웹포인트 이력 상세 조회.
	 *
	 * @param mbrWebpntHist the MbrWebpntHist
	 * @return the MbrWebpntHist
	 * @throws SQLException the SQL exception
	 */
	public MbrWebpntHist selectMbrWebpntHist(MbrWebpntHist mbrWebpntHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrWebpntHist", mbrWebpntHist);
	}
	
	/**
	 * 회원 웹포인트 이력 등록.
	 *
	 * @param mbrWebpntHist the MbrWebpntHist
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrWebpntHist(MbrWebpntHist mbrWebpntHist) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrWebpntHist", mbrWebpntHist);
	}
	
	/**
	 * 회원 웹포인트 이력 수정.
	 *
	 * @param mbrWebpntHist the MbrWebpntHist
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrWebpntHist(MbrWebpntHist mbrWebpntHist) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrWebpntHist", mbrWebpntHist);
	}
	
	/**
	 * 회원 웹포인트 이력 삭제.
	 *
	 * @param mbrWebpntHist the MbrWebpntHist
	 * @return the MbrWebpntHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrWebpntHist(MbrWebpntHist mbrWebpntHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrWebpntHist", mbrWebpntHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

