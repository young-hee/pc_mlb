
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
import com.plgrim.ncp.base.entities.datasource1.god.GodColor;

/**
 * The Class GodColorRepository.
 */
@Repository
public class GodColorRepository extends AbstractRepository {
	

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
	 * 상품 색상 상세 조회.
	 *
	 * @param godColor the GodColor
	 * @return the GodColor
	 * @throws SQLException the SQL exception
	 */
	public GodColor selectGodColor(GodColor godColor) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodColor", godColor);
	}
	
	/**
	 * 상품 색상 등록.
	 *
	 * @param godColor the GodColor
	 * @throws SQLException the SQL exception
	 */
	public void insertGodColor(GodColor godColor) {
		getSession1().insert("com.plgrim.ncp.base.insertGodColor", godColor);
	}
	
	/**
	 * 상품 색상 수정.
	 *
	 * @param godColor the GodColor
	 * @throws SQLException the SQL exception
	 */
	public int updateGodColor(GodColor godColor) {
		return getSession1().update("com.plgrim.ncp.base.updateGodColor", godColor);
	}
	
	/**
	 * 상품 색상 삭제.
	 *
	 * @param godColor the GodColor
	 * @return the GodColor
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodColor(GodColor godColor) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodColor", godColor);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

