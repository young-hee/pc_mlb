
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
package com.plgrim.ncp.base.repository.lgs;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspHist;

/**
 * The Class LgsDlvspHistRepository.
 */
@Repository
public class LgsDlvspHistRepository extends AbstractRepository {
	

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
	 * 물류 배송지 이력 상세 조회.
	 *
	 * @param lgsDlvspHist the LgsDlvspHist
	 * @return the LgsDlvspHist
	 * @throws SQLException the SQL exception
	 */
	public LgsDlvspHist selectLgsDlvspHist(LgsDlvspHist lgsDlvspHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectLgsDlvspHist", lgsDlvspHist);
	}
	
	/**
	 * 물류 배송지 이력 등록.
	 *
	 * @param lgsDlvspHist the LgsDlvspHist
	 * @throws SQLException the SQL exception
	 */
	public void insertLgsDlvspHist(LgsDlvspHist lgsDlvspHist) {
		getSession1().insert("com.plgrim.ncp.base.insertLgsDlvspHist", lgsDlvspHist);
	}
	
	/**
	 * 물류 배송지 이력 수정.
	 *
	 * @param lgsDlvspHist the LgsDlvspHist
	 * @throws SQLException the SQL exception
	 */
	public int updateLgsDlvspHist(LgsDlvspHist lgsDlvspHist) {
		return getSession1().update("com.plgrim.ncp.base.updateLgsDlvspHist", lgsDlvspHist);
	}
	
	/**
	 * 물류 배송지 이력 삭제.
	 *
	 * @param lgsDlvspHist the LgsDlvspHist
	 * @return the LgsDlvspHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteLgsDlvspHist(LgsDlvspHist lgsDlvspHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteLgsDlvspHist", lgsDlvspHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

