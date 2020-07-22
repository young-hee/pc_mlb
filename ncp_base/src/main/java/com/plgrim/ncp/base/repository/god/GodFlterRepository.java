
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
 * Generator(Generator)		2018-04-19                      
 */
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodFlter;

/**
 * The Class GodFlterRepository.
 */
@Repository
public class GodFlterRepository extends AbstractRepository {
	

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
	 * 상품 필터 상세 조회.
	 *
	 * @param godFlter the GodFlter
	 * @return the GodFlter
	 * @throws SQLException the SQL exception
	 */
	public GodFlter selectGodFlter(GodFlter godFlter) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodFlter", godFlter);
	}
	
	/**
	 * 상품 필터 등록.
	 *
	 * @param godFlter the GodFlter
	 * @throws SQLException the SQL exception
	 */
	public void insertGodFlter(GodFlter godFlter) {
		getSession1().insert("com.plgrim.ncp.base.insertGodFlter", godFlter);
	}
	
	/**
	 * 상품 필터 수정.
	 *
	 * @param godFlter the GodFlter
	 * @throws SQLException the SQL exception
	 */
	public int updateGodFlter(GodFlter godFlter) {
		return getSession1().update("com.plgrim.ncp.base.updateGodFlter", godFlter);
	}
	
	/**
	 * 상품 필터 삭제.
	 *
	 * @param godFlter the GodFlter
	 * @return the GodFlter
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodFlter(GodFlter godFlter) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodFlter", godFlter);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

