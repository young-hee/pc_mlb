
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
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyCpstGodNm;

/**
 * The Class GodLangbyCpstGodNmRepository.
 */
@Repository
public class GodLangbyCpstGodNmRepository extends AbstractRepository {
	

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
	 * 상품 언어별 구성 상품 명 상세 조회.
	 *
	 * @param godLangbyCpstGodNm the GodLangbyCpstGodNm
	 * @return the GodLangbyCpstGodNm
	 * @throws SQLException the SQL exception
	 */
	public GodLangbyCpstGodNm selectGodLangbyCpstGodNm(GodLangbyCpstGodNm godLangbyCpstGodNm) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodLangbyCpstGodNm", godLangbyCpstGodNm);
	}
	
	/**
	 * 상품 언어별 구성 상품 명 등록.
	 *
	 * @param godLangbyCpstGodNm the GodLangbyCpstGodNm
	 * @throws SQLException the SQL exception
	 */
	public void insertGodLangbyCpstGodNm(GodLangbyCpstGodNm godLangbyCpstGodNm) {
		getSession1().insert("com.plgrim.ncp.base.insertGodLangbyCpstGodNm", godLangbyCpstGodNm);
	}
	
	/**
	 * 상품 언어별 구성 상품 명 수정.
	 *
	 * @param godLangbyCpstGodNm the GodLangbyCpstGodNm
	 * @throws SQLException the SQL exception
	 */
	public int updateGodLangbyCpstGodNm(GodLangbyCpstGodNm godLangbyCpstGodNm) {
		return getSession1().update("com.plgrim.ncp.base.updateGodLangbyCpstGodNm", godLangbyCpstGodNm);
	}
	
	/**
	 * 상품 언어별 구성 상품 명 삭제.
	 *
	 * @param godLangbyCpstGodNm the GodLangbyCpstGodNm
	 * @return the GodLangbyCpstGodNm
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodLangbyCpstGodNm(GodLangbyCpstGodNm godLangbyCpstGodNm) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodLangbyCpstGodNm", godLangbyCpstGodNm);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

