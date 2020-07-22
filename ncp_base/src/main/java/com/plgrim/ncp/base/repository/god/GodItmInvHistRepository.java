
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
import com.plgrim.ncp.base.entities.datasource1.god.GodItmInvHist;

/**
 * The Class GodItmInvHistRepository.
 */
@Repository
public class GodItmInvHistRepository extends AbstractRepository {
	

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
	 * 상품 단품 재고 이력 상세 조회.
	 *
	 * @param godItmInvHist the GodItmInvHist
	 * @return the GodItmInvHist
	 * @throws SQLException the SQL exception
	 */
	public GodItmInvHist selectGodItmInvHist(GodItmInvHist godItmInvHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodItmInvHist", godItmInvHist);
	}
	
	/**
	 * 상품 단품 재고 이력 등록.
	 *
	 * @param godItmInvHist the GodItmInvHist
	 * @throws SQLException the SQL exception
	 */
	public void insertGodItmInvHist(GodItmInvHist godItmInvHist) {
		getSession1().insert("com.plgrim.ncp.base.insertGodItmInvHist", godItmInvHist);
	}
	
	/**
	 * 상품 단품 재고 이력 수정.
	 *
	 * @param godItmInvHist the GodItmInvHist
	 * @throws SQLException the SQL exception
	 */
	public int updateGodItmInvHist(GodItmInvHist godItmInvHist) {
		return getSession1().update("com.plgrim.ncp.base.updateGodItmInvHist", godItmInvHist);
	}
	
	/**
	 * 상품 단품 재고 이력 삭제.
	 *
	 * @param godItmInvHist the GodItmInvHist
	 * @return the GodItmInvHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodItmInvHist(GodItmInvHist godItmInvHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodItmInvHist", godItmInvHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

