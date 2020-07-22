
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
import com.plgrim.ncp.base.entities.datasource1.god.GodColorLang;

/**
 * The Class GodColorLangRepository.
 */
@Repository
public class GodColorLangRepository extends AbstractRepository {
	

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
	 * 상품 색상 언어 상세 조회.
	 *
	 * @param godColorLang the GodColorLang
	 * @return the GodColorLang
	 * @throws SQLException the SQL exception
	 */
	public GodColorLang selectGodColorLang(GodColorLang godColorLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodColorLang", godColorLang);
	}
	
	/**
	 * 상품 색상 언어 등록.
	 *
	 * @param godColorLang the GodColorLang
	 * @throws SQLException the SQL exception
	 */
	public void insertGodColorLang(GodColorLang godColorLang) {
		getSession1().insert("com.plgrim.ncp.base.insertGodColorLang", godColorLang);
	}
	
	/**
	 * 상품 색상 언어 수정.
	 *
	 * @param godColorLang the GodColorLang
	 * @throws SQLException the SQL exception
	 */
	public int updateGodColorLang(GodColorLang godColorLang) {
		return getSession1().update("com.plgrim.ncp.base.updateGodColorLang", godColorLang);
	}
	
	/**
	 * 상품 색상 언어 삭제.
	 *
	 * @param godColorLang the GodColorLang
	 * @return the GodColorLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodColorLang(GodColorLang godColorLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodColorLang", godColorLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

