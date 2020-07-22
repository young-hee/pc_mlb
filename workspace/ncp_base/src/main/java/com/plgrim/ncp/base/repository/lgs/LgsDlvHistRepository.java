
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
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvHist;

/**
 * The Class LgsDlvHistRepository.
 */
@Repository
public class LgsDlvHistRepository extends AbstractRepository {
	

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
	 * 물류 배송 이력 상세 조회.
	 *
	 * @param lgsDlvHist the LgsDlvHist
	 * @return the LgsDlvHist
	 * @throws SQLException the SQL exception
	 */
	public LgsDlvHist selectLgsDlvHist(LgsDlvHist lgsDlvHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectLgsDlvHist", lgsDlvHist);
	}
	
	/**
	 * 물류 배송 이력 등록.
	 *
	 * @param lgsDlvHist the LgsDlvHist
	 * @throws SQLException the SQL exception
	 */
	public void insertLgsDlvHist(LgsDlvHist lgsDlvHist) {
		getSession1().insert("com.plgrim.ncp.base.insertLgsDlvHist", lgsDlvHist);
	}
	
	/**
	 * 물류 배송 이력 수정.
	 *
	 * @param lgsDlvHist the LgsDlvHist
	 * @throws SQLException the SQL exception
	 */
	public int updateLgsDlvHist(LgsDlvHist lgsDlvHist) {
		return getSession1().update("com.plgrim.ncp.base.updateLgsDlvHist", lgsDlvHist);
	}
	
	/**
	 * 물류 배송 이력 삭제.
	 *
	 * @param lgsDlvHist the LgsDlvHist
	 * @return the LgsDlvHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteLgsDlvHist(LgsDlvHist lgsDlvHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteLgsDlvHist", lgsDlvHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

