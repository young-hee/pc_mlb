
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
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNmHist;

/**
 * The Class GodLangbyGodNmHistRepository.
 */
@Repository
public class GodLangbyGodNmHistRepository extends AbstractRepository {
	

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
	 * 상품 언어별 상품 명 이력 상세 조회.
	 *
	 * @param godLangbyGodNmHist the GodLangbyGodNmHist
	 * @return the GodLangbyGodNmHist
	 * @throws SQLException the SQL exception
	 */
	public GodLangbyGodNmHist selectGodLangbyGodNmHist(GodLangbyGodNmHist godLangbyGodNmHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodLangbyGodNmHist", godLangbyGodNmHist);
	}
	
	/**
	 * 상품 언어별 상품 명 이력 등록.
	 *
	 * @param godLangbyGodNmHist the GodLangbyGodNmHist
	 * @throws SQLException the SQL exception
	 */
	public void insertGodLangbyGodNmHist(GodLangbyGodNmHist godLangbyGodNmHist) {
		getSession1().insert("com.plgrim.ncp.base.insertGodLangbyGodNmHist", godLangbyGodNmHist);
	}
	
	/**
	 * 상품 언어별 상품 명 이력 수정.
	 *
	 * @param godLangbyGodNmHist the GodLangbyGodNmHist
	 * @throws SQLException the SQL exception
	 */
	public int updateGodLangbyGodNmHist(GodLangbyGodNmHist godLangbyGodNmHist) {
		return getSession1().update("com.plgrim.ncp.base.updateGodLangbyGodNmHist", godLangbyGodNmHist);
	}
	
	/**
	 * 상품 언어별 상품 명 이력 삭제.
	 *
	 * @param godLangbyGodNmHist the GodLangbyGodNmHist
	 * @return the GodLangbyGodNmHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodLangbyGodNmHist(GodLangbyGodNmHist godLangbyGodNmHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodLangbyGodNmHist", godLangbyGodNmHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

