
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
 * Generator(Generator)		2018-06-05                      
 */
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodMovi;

/**
 * The Class GodMoviRepository.
 */
@Repository
public class GodMoviRepository extends AbstractRepository {
	

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
	 * 상품 동영상 상세 조회.
	 *
	 * @param godMovi the GodMovi
	 * @return the GodMovi
	 * @throws SQLException the SQL exception
	 */
	public GodMovi selectGodMovi(GodMovi godMovi) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodMovi", godMovi);
	}
	
	/**
	 * 상품 동영상 등록.
	 *
	 * @param godMovi the GodMovi
	 * @throws SQLException the SQL exception
	 */
	public void insertGodMovi(GodMovi godMovi) {
		getSession1().insert("com.plgrim.ncp.base.insertGodMovi", godMovi);
	}
	
	/**
	 * 상품 동영상 수정.
	 *
	 * @param godMovi the GodMovi
	 * @throws SQLException the SQL exception
	 */
	public int updateGodMovi(GodMovi godMovi) {
		return getSession1().update("com.plgrim.ncp.base.updateGodMovi", godMovi);
	}
	
	/**
	 * 상품 동영상 삭제.
	 *
	 * @param godMovi the GodMovi
	 * @return the GodMovi
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodMovi(GodMovi godMovi) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodMovi", godMovi);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

