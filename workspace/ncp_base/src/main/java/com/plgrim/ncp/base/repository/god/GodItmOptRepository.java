
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
import com.plgrim.ncp.base.entities.datasource1.god.GodItmOpt;

/**
 * The Class GodItmOptRepository.
 */
@Repository
public class GodItmOptRepository extends AbstractRepository {
	

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
	 * 상품 단품 옵션 상세 조회.
	 *
	 * @param godItmOpt the GodItmOpt
	 * @return the GodItmOpt
	 * @throws SQLException the SQL exception
	 */
	public GodItmOpt selectGodItmOpt(GodItmOpt godItmOpt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodItmOpt", godItmOpt);
	}
	
	/**
	 * 상품 단품 옵션 등록.
	 *
	 * @param godItmOpt the GodItmOpt
	 * @throws SQLException the SQL exception
	 */
	public void insertGodItmOpt(GodItmOpt godItmOpt) {
		getSession1().insert("com.plgrim.ncp.base.insertGodItmOpt", godItmOpt);
	}
	
	/**
	 * 상품 단품 옵션 수정.
	 *
	 * @param godItmOpt the GodItmOpt
	 * @throws SQLException the SQL exception
	 */
	public int updateGodItmOpt(GodItmOpt godItmOpt) {
		return getSession1().update("com.plgrim.ncp.base.updateGodItmOpt", godItmOpt);
	}
	
	/**
	 * 상품 단품 옵션 삭제.
	 *
	 * @param godItmOpt the GodItmOpt
	 * @return the GodItmOpt
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodItmOpt(GodItmOpt godItmOpt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodItmOpt", godItmOpt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

