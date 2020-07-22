
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
import com.plgrim.ncp.base.entities.datasource1.god.GodStdCtgryLang;

/**
 * The Class GodStdCtgryLangRepository.
 */
@Repository
public class GodStdCtgryLangRepository extends AbstractRepository {
	

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
	 * 상품 표준 카테고리 언어 상세 조회.
	 *
	 * @param godStdCtgryLang the GodStdCtgryLang
	 * @return the GodStdCtgryLang
	 * @throws SQLException the SQL exception
	 */
	public GodStdCtgryLang selectGodStdCtgryLang(GodStdCtgryLang godStdCtgryLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodStdCtgryLang", godStdCtgryLang);
	}
	
	/**
	 * 상품 표준 카테고리 언어 등록.
	 *
	 * @param godStdCtgryLang the GodStdCtgryLang
	 * @throws SQLException the SQL exception
	 */
	public void insertGodStdCtgryLang(GodStdCtgryLang godStdCtgryLang) {
		getSession1().insert("com.plgrim.ncp.base.insertGodStdCtgryLang", godStdCtgryLang);
	}
	
	/**
	 * 상품 표준 카테고리 언어 수정.
	 *
	 * @param godStdCtgryLang the GodStdCtgryLang
	 * @throws SQLException the SQL exception
	 */
	public int updateGodStdCtgryLang(GodStdCtgryLang godStdCtgryLang) {
		return getSession1().update("com.plgrim.ncp.base.updateGodStdCtgryLang", godStdCtgryLang);
	}
	
	/**
	 * 상품 표준 카테고리 언어 삭제.
	 *
	 * @param godStdCtgryLang the GodStdCtgryLang
	 * @return the GodStdCtgryLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodStdCtgryLang(GodStdCtgryLang godStdCtgryLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodStdCtgryLang", godStdCtgryLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

