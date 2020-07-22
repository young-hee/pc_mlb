
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
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyTagResve;

/**
 * The Class GodLangbyTagResveRepository.
 */
@Repository
public class GodLangbyTagResveRepository extends AbstractRepository {
	

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
	 * 상품 언어별 태그 예약 상세 조회.
	 *
	 * @param godLangbyTagResve the GodLangbyTagResve
	 * @return the GodLangbyTagResve
	 * @throws SQLException the SQL exception
	 */
	public GodLangbyTagResve selectGodLangbyTagResve(GodLangbyTagResve godLangbyTagResve) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodLangbyTagResve", godLangbyTagResve);
	}
	
	/**
	 * 상품 언어별 태그 예약 등록.
	 *
	 * @param godLangbyTagResve the GodLangbyTagResve
	 * @throws SQLException the SQL exception
	 */
	public void insertGodLangbyTagResve(GodLangbyTagResve godLangbyTagResve) {
		getSession1().insert("com.plgrim.ncp.base.insertGodLangbyTagResve", godLangbyTagResve);
	}
	
	/**
	 * 상품 언어별 태그 예약 수정.
	 *
	 * @param godLangbyTagResve the GodLangbyTagResve
	 * @throws SQLException the SQL exception
	 */
	public int updateGodLangbyTagResve(GodLangbyTagResve godLangbyTagResve) {
		return getSession1().update("com.plgrim.ncp.base.updateGodLangbyTagResve", godLangbyTagResve);
	}
	
	/**
	 * 상품 언어별 태그 예약 삭제.
	 *
	 * @param godLangbyTagResve the GodLangbyTagResve
	 * @return the GodLangbyTagResve
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodLangbyTagResve(GodLangbyTagResve godLangbyTagResve) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodLangbyTagResve", godLangbyTagResve);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

