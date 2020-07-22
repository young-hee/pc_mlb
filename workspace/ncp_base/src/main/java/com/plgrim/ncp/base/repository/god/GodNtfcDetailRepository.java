
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
 * Generator(Generator)		2018-11-08                      
 */
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodNtfcDetail;

/**
 * The Class GodNtfcDetailRepository.
 */
@Repository
public class GodNtfcDetailRepository extends AbstractRepository {
	

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
	 * 상품 고시 상세 상세 조회.
	 *
	 * @param godNtfcDetail the GodNtfcDetail
	 * @return the GodNtfcDetail
	 * @throws SQLException the SQL exception
	 */
	public GodNtfcDetail selectGodNtfcDetail(GodNtfcDetail godNtfcDetail) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodNtfcDetail", godNtfcDetail);
	}
	
	/**
	 * 상품 고시 상세 등록.
	 *
	 * @param godNtfcDetail the GodNtfcDetail
	 * @throws SQLException the SQL exception
	 */
	public void insertGodNtfcDetail(GodNtfcDetail godNtfcDetail) {
		getSession1().insert("com.plgrim.ncp.base.insertGodNtfcDetail", godNtfcDetail);
	}
	
	/**
	 * 상품 고시 상세 수정.
	 *
	 * @param godNtfcDetail the GodNtfcDetail
	 * @throws SQLException the SQL exception
	 */
	public int updateGodNtfcDetail(GodNtfcDetail godNtfcDetail) {
		return getSession1().update("com.plgrim.ncp.base.updateGodNtfcDetail", godNtfcDetail);
	}
	
	/**
	 * 상품 고시 상세 삭제.
	 *
	 * @param godNtfcDetail the GodNtfcDetail
	 * @return the GodNtfcDetail
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodNtfcDetail(GodNtfcDetail godNtfcDetail) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodNtfcDetail", godNtfcDetail);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

