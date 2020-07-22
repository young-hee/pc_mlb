
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
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiBrndCnnc;

/**
 * The Class GodNotiBrndCnncRepository.
 */
@Repository
public class GodNotiBrndCnncRepository extends AbstractRepository {
	

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
	 * 상품 공지 브랜드 연결 상세 조회.
	 *
	 * @param godNotiBrndCnnc the GodNotiBrndCnnc
	 * @return the GodNotiBrndCnnc
	 * @throws SQLException the SQL exception
	 */
	public GodNotiBrndCnnc selectGodNotiBrndCnnc(GodNotiBrndCnnc godNotiBrndCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodNotiBrndCnnc", godNotiBrndCnnc);
	}
	
	/**
	 * 상품 공지 브랜드 연결 등록.
	 *
	 * @param godNotiBrndCnnc the GodNotiBrndCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertGodNotiBrndCnnc(GodNotiBrndCnnc godNotiBrndCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertGodNotiBrndCnnc", godNotiBrndCnnc);
	}
	
	/**
	 * 상품 공지 브랜드 연결 수정.
	 *
	 * @param godNotiBrndCnnc the GodNotiBrndCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateGodNotiBrndCnnc(GodNotiBrndCnnc godNotiBrndCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateGodNotiBrndCnnc", godNotiBrndCnnc);
	}
	
	/**
	 * 상품 공지 브랜드 연결 삭제.
	 *
	 * @param godNotiBrndCnnc the GodNotiBrndCnnc
	 * @return the GodNotiBrndCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodNotiBrndCnnc(GodNotiBrndCnnc godNotiBrndCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodNotiBrndCnnc", godNotiBrndCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

