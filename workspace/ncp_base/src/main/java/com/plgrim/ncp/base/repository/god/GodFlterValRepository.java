
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
import com.plgrim.ncp.base.entities.datasource1.god.GodFlterVal;

/**
 * The Class GodFlterValRepository.
 */
@Repository
public class GodFlterValRepository extends AbstractRepository {
	

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
	 * 상품 필터 값 상세 조회.
	 *
	 * @param godFlterVal the GodFlterVal
	 * @return the GodFlterVal
	 * @throws SQLException the SQL exception
	 */
	public GodFlterVal selectGodFlterVal(GodFlterVal godFlterVal) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodFlterVal", godFlterVal);
	}
	
	/**
	 * 상품 필터 값 등록.
	 *
	 * @param godFlterVal the GodFlterVal
	 * @throws SQLException the SQL exception
	 */
	public void insertGodFlterVal(GodFlterVal godFlterVal) {
		getSession1().insert("com.plgrim.ncp.base.insertGodFlterVal", godFlterVal);
	}
	
	/**
	 * 상품 필터 값 수정.
	 *
	 * @param godFlterVal the GodFlterVal
	 * @throws SQLException the SQL exception
	 */
	public int updateGodFlterVal(GodFlterVal godFlterVal) {
		return getSession1().update("com.plgrim.ncp.base.updateGodFlterVal", godFlterVal);
	}
	
	/**
	 * 상품 필터 값 삭제.
	 *
	 * @param godFlterVal the GodFlterVal
	 * @return the GodFlterVal
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodFlterVal(GodFlterVal godFlterVal) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodFlterVal", godFlterVal);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

