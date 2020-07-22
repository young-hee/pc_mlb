
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
import com.plgrim.ncp.base.entities.datasource1.god.GodColorGrpLang;

/**
 * The Class GodColorGrpLangRepository.
 */
@Repository
public class GodColorGrpLangRepository extends AbstractRepository {
	

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
	 * 상품 색상 그룹 언어 상세 조회.
	 *
	 * @param godColorGrpLang the GodColorGrpLang
	 * @return the GodColorGrpLang
	 * @throws SQLException the SQL exception
	 */
	public GodColorGrpLang selectGodColorGrpLang(GodColorGrpLang godColorGrpLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodColorGrpLang", godColorGrpLang);
	}
	
	/**
	 * 상품 색상 그룹 언어 등록.
	 *
	 * @param godColorGrpLang the GodColorGrpLang
	 * @throws SQLException the SQL exception
	 */
	public void insertGodColorGrpLang(GodColorGrpLang godColorGrpLang) {
		getSession1().insert("com.plgrim.ncp.base.insertGodColorGrpLang", godColorGrpLang);
	}
	
	/**
	 * 상품 색상 그룹 언어 수정.
	 *
	 * @param godColorGrpLang the GodColorGrpLang
	 * @throws SQLException the SQL exception
	 */
	public int updateGodColorGrpLang(GodColorGrpLang godColorGrpLang) {
		return getSession1().update("com.plgrim.ncp.base.updateGodColorGrpLang", godColorGrpLang);
	}
	
	/**
	 * 상품 색상 그룹 언어 삭제.
	 *
	 * @param godColorGrpLang the GodColorGrpLang
	 * @return the GodColorGrpLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodColorGrpLang(GodColorGrpLang godColorGrpLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodColorGrpLang", godColorGrpLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

