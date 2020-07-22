
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
import com.plgrim.ncp.base.entities.datasource1.god.GodItmHist;

/**
 * The Class GodItmHistRepository.
 */
@Repository
public class GodItmHistRepository extends AbstractRepository {
	

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
	 * 상품 단품 이력 상세 조회.
	 *
	 * @param godItmHist the GodItmHist
	 * @return the GodItmHist
	 * @throws SQLException the SQL exception
	 */
	public GodItmHist selectGodItmHist(GodItmHist godItmHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodItmHist", godItmHist);
	}
	
	/**
	 * 상품 단품 이력 등록.
	 *
	 * @param godItmHist the GodItmHist
	 * @throws SQLException the SQL exception
	 */
	public void insertGodItmHist(GodItmHist godItmHist) {
		getSession1().insert("com.plgrim.ncp.base.insertGodItmHist", godItmHist);
	}
	
	/**
	 * 상품 단품 이력 수정.
	 *
	 * @param godItmHist the GodItmHist
	 * @throws SQLException the SQL exception
	 */
	public int updateGodItmHist(GodItmHist godItmHist) {
		return getSession1().update("com.plgrim.ncp.base.updateGodItmHist", godItmHist);
	}
	
	/**
	 * 상품 단품 이력 삭제.
	 *
	 * @param godItmHist the GodItmHist
	 * @return the GodItmHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodItmHist(GodItmHist godItmHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodItmHist", godItmHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

