
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
import com.plgrim.ncp.base.entities.datasource1.god.GodOptVal;

/**
 * The Class GodOptValRepository.
 */
@Repository
public class GodOptValRepository extends AbstractRepository {
	

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
	 * 상품 옵션 값 상세 조회.
	 *
	 * @param godOptVal the GodOptVal
	 * @return the GodOptVal
	 * @throws SQLException the SQL exception
	 */
	public GodOptVal selectGodOptVal(GodOptVal godOptVal) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodOptVal", godOptVal);
	}
	
	/**
	 * 상품 옵션 값 등록.
	 *
	 * @param godOptVal the GodOptVal
	 * @throws SQLException the SQL exception
	 */
	public void insertGodOptVal(GodOptVal godOptVal) {
		getSession1().insert("com.plgrim.ncp.base.insertGodOptVal", godOptVal);
	}
	
	/**
	 * 상품 옵션 값 수정.
	 *
	 * @param godOptVal the GodOptVal
	 * @throws SQLException the SQL exception
	 */
	public int updateGodOptVal(GodOptVal godOptVal) {
		return getSession1().update("com.plgrim.ncp.base.updateGodOptVal", godOptVal);
	}
	
	/**
	 * 상품 옵션 값 삭제.
	 *
	 * @param godOptVal the GodOptVal
	 * @return the GodOptVal
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodOptVal(GodOptVal godOptVal) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodOptVal", godOptVal);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

