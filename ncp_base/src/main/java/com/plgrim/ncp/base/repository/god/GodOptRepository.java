
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
import com.plgrim.ncp.base.entities.datasource1.god.GodOpt;

/**
 * The Class GodOptRepository.
 */
@Repository
public class GodOptRepository extends AbstractRepository {
	

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
	 * 상품 옵션 상세 조회.
	 *
	 * @param godOpt the GodOpt
	 * @return the GodOpt
	 * @throws SQLException the SQL exception
	 */
	public GodOpt selectGodOpt(GodOpt godOpt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodOpt", godOpt);
	}
	
	/**
	 * 상품 옵션 등록.
	 *
	 * @param godOpt the GodOpt
	 * @throws SQLException the SQL exception
	 */
	public void insertGodOpt(GodOpt godOpt) {
		getSession1().insert("com.plgrim.ncp.base.insertGodOpt", godOpt);
	}
	
	/**
	 * 상품 옵션 수정.
	 *
	 * @param godOpt the GodOpt
	 * @throws SQLException the SQL exception
	 */
	public int updateGodOpt(GodOpt godOpt) {
		return getSession1().update("com.plgrim.ncp.base.updateGodOpt", godOpt);
	}
	
	/**
	 * 상품 옵션 삭제.
	 *
	 * @param godOpt the GodOpt
	 * @return the GodOpt
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodOpt(GodOpt godOpt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodOpt", godOpt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

