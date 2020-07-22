
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
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;

/**
 * The Class GodLangbyGodNmRepository.
 */
@Repository
public class GodLangbyGodNmRepository extends AbstractRepository {
	

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
	 * 상품 언어별 상품 명 상세 조회.
	 *
	 * @param godLangbyGodNm the GodLangbyGodNm
	 * @return the GodLangbyGodNm
	 * @throws SQLException the SQL exception
	 */
	public GodLangbyGodNm selectGodLangbyGodNm(GodLangbyGodNm godLangbyGodNm) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodLangbyGodNm", godLangbyGodNm);
	}
	
	/**
	 * 상품 언어별 상품 명 등록.
	 *
	 * @param godLangbyGodNm the GodLangbyGodNm
	 * @throws SQLException the SQL exception
	 */
	public void insertGodLangbyGodNm(GodLangbyGodNm godLangbyGodNm) {
		getSession1().insert("com.plgrim.ncp.base.insertGodLangbyGodNm", godLangbyGodNm);
	}
	
	/**
	 * 상품 언어별 상품 명 수정.
	 *
	 * @param godLangbyGodNm the GodLangbyGodNm
	 * @throws SQLException the SQL exception
	 */
	public int updateGodLangbyGodNm(GodLangbyGodNm godLangbyGodNm) {
		return getSession1().update("com.plgrim.ncp.base.updateGodLangbyGodNm", godLangbyGodNm);
	}
	
	/**
	 * 상품 언어별 상품 명 삭제.
	 *
	 * @param godLangbyGodNm the GodLangbyGodNm
	 * @return the GodLangbyGodNm
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodLangbyGodNm(GodLangbyGodNm godLangbyGodNm) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodLangbyGodNm", godLangbyGodNm);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

