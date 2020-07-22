
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
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGod;

/**
 * The Class LgsRtrvlDrctGodRepository.
 */
@Repository
public class LgsRtrvlDrctGodRepository extends AbstractRepository {
	

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
	 * 물류 회수지시 상품 상세 조회.
	 *
	 * @param lgsRtrvlDrctGod the LgsRtrvlDrctGod
	 * @return the LgsRtrvlDrctGod
	 * @throws SQLException the SQL exception
	 */
	public LgsRtrvlDrctGod selectLgsRtrvlDrctGod(LgsRtrvlDrctGod lgsRtrvlDrctGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectLgsRtrvlDrctGod", lgsRtrvlDrctGod);
	}
	
	/**
	 * 물류 회수지시 상품 등록.
	 *
	 * @param lgsRtrvlDrctGod the LgsRtrvlDrctGod
	 * @throws SQLException the SQL exception
	 */
	public void insertLgsRtrvlDrctGod(LgsRtrvlDrctGod lgsRtrvlDrctGod) {
		getSession1().insert("com.plgrim.ncp.base.insertLgsRtrvlDrctGod", lgsRtrvlDrctGod);
	}
	
	/**
	 * 물류 회수지시 상품 수정.
	 *
	 * @param lgsRtrvlDrctGod the LgsRtrvlDrctGod
	 * @throws SQLException the SQL exception
	 */
	public int updateLgsRtrvlDrctGod(LgsRtrvlDrctGod lgsRtrvlDrctGod) {
		return getSession1().update("com.plgrim.ncp.base.updateLgsRtrvlDrctGod", lgsRtrvlDrctGod);
	}
	
	/**
	 * 물류 회수지시 상품 삭제.
	 *
	 * @param lgsRtrvlDrctGod the LgsRtrvlDrctGod
	 * @return the LgsRtrvlDrctGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteLgsRtrvlDrctGod(LgsRtrvlDrctGod lgsRtrvlDrctGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteLgsRtrvlDrctGod", lgsRtrvlDrctGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

