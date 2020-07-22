
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
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGodHist;

/**
 * The Class LgsRtrvlDrctGodHistRepository.
 */
@Repository
public class LgsRtrvlDrctGodHistRepository extends AbstractRepository {
	

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
	 * 물류 회수지시 상품 이력 상세 조회.
	 *
	 * @param lgsRtrvlDrctGodHist the LgsRtrvlDrctGodHist
	 * @return the LgsRtrvlDrctGodHist
	 * @throws SQLException the SQL exception
	 */
	public LgsRtrvlDrctGodHist selectLgsRtrvlDrctGodHist(LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectLgsRtrvlDrctGodHist", lgsRtrvlDrctGodHist);
	}
	
	/**
	 * 물류 회수지시 상품 이력 등록.
	 *
	 * @param lgsRtrvlDrctGodHist the LgsRtrvlDrctGodHist
	 * @throws SQLException the SQL exception
	 */
	public void insertLgsRtrvlDrctGodHist(LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist) {
		getSession1().insert("com.plgrim.ncp.base.insertLgsRtrvlDrctGodHist", lgsRtrvlDrctGodHist);
	}
	
	/**
	 * 물류 회수지시 상품 이력 수정.
	 *
	 * @param lgsRtrvlDrctGodHist the LgsRtrvlDrctGodHist
	 * @throws SQLException the SQL exception
	 */
	public int updateLgsRtrvlDrctGodHist(LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist) {
		return getSession1().update("com.plgrim.ncp.base.updateLgsRtrvlDrctGodHist", lgsRtrvlDrctGodHist);
	}
	
	/**
	 * 물류 회수지시 상품 이력 삭제.
	 *
	 * @param lgsRtrvlDrctGodHist the LgsRtrvlDrctGodHist
	 * @return the LgsRtrvlDrctGodHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteLgsRtrvlDrctGodHist(LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteLgsRtrvlDrctGodHist", lgsRtrvlDrctGodHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

