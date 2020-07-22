
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
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvRtrvlDrctHist;

/**
 * The Class LgsDlvRtrvlDrctHistRepository.
 */
@Repository
public class LgsDlvRtrvlDrctHistRepository extends AbstractRepository {
	

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
	 * 물류 배송 회수지시 이력 상세 조회.
	 *
	 * @param lgsDlvRtrvlDrctHist the LgsDlvRtrvlDrctHist
	 * @return the LgsDlvRtrvlDrctHist
	 * @throws SQLException the SQL exception
	 */
	public LgsDlvRtrvlDrctHist selectLgsDlvRtrvlDrctHist(LgsDlvRtrvlDrctHist lgsDlvRtrvlDrctHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectLgsDlvRtrvlDrctHist", lgsDlvRtrvlDrctHist);
	}
	
	/**
	 * 물류 배송 회수지시 이력 등록.
	 *
	 * @param lgsDlvRtrvlDrctHist the LgsDlvRtrvlDrctHist
	 * @throws SQLException the SQL exception
	 */
	public void insertLgsDlvRtrvlDrctHist(LgsDlvRtrvlDrctHist lgsDlvRtrvlDrctHist) {
		getSession1().insert("com.plgrim.ncp.base.insertLgsDlvRtrvlDrctHist", lgsDlvRtrvlDrctHist);
	}
	
	/**
	 * 물류 배송 회수지시 이력 수정.
	 *
	 * @param lgsDlvRtrvlDrctHist the LgsDlvRtrvlDrctHist
	 * @throws SQLException the SQL exception
	 */
	public int updateLgsDlvRtrvlDrctHist(LgsDlvRtrvlDrctHist lgsDlvRtrvlDrctHist) {
		return getSession1().update("com.plgrim.ncp.base.updateLgsDlvRtrvlDrctHist", lgsDlvRtrvlDrctHist);
	}
	
	/**
	 * 물류 배송 회수지시 이력 삭제.
	 *
	 * @param lgsDlvRtrvlDrctHist the LgsDlvRtrvlDrctHist
	 * @return the LgsDlvRtrvlDrctHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteLgsDlvRtrvlDrctHist(LgsDlvRtrvlDrctHist lgsDlvRtrvlDrctHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteLgsDlvRtrvlDrctHist", lgsDlvRtrvlDrctHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

