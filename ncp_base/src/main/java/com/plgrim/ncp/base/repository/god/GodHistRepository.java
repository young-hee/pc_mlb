
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
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodHist;

/**
 * The Class GodHistRepository.
 */
@Repository
public class GodHistRepository extends AbstractRepository {
	

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
	 * 상품 이력 상세 조회.
	 *
	 * @param godHist the GodHist
	 * @return the GodHist
	 * @throws SQLException the SQL exception
	 */
	public GodHist selectGodHist(GodHist godHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodHist", godHist);
	}
	
	/**
	 * 상품 이력 등록.
	 *
	 * @param godHist the GodHist
	 * @throws SQLException the SQL exception
	 */
	public void insertGodHist(GodHist godHist) {
		getSession1().insert("com.plgrim.ncp.base.insertGodHist", godHist);
	}
	
	/**
	 * 상품 이력 수정.
	 *
	 * @param godHist the GodHist
	 * @throws SQLException the SQL exception
	 */
	public int updateGodHist(GodHist godHist) {
		return getSession1().update("com.plgrim.ncp.base.updateGodHist", godHist);
	}
	
	/**
	 * 상품 이력 삭제.
	 *
	 * @param godHist the GodHist
	 * @return the GodHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodHist(GodHist godHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodHist", godHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

