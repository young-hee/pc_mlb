
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
 * Generator(Generator)		2018-04-19                      
 */
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodStdCtgrySizeCnnc;

/**
 * The Class GodStdCtgrySizeCnncRepository.
 */
@Repository
public class GodStdCtgrySizeCnncRepository extends AbstractRepository {
	

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
	 * 상품 표준 카테고리 사이즈 연결 상세 조회.
	 *
	 * @param godStdCtgrySizeCnnc the GodStdCtgrySizeCnnc
	 * @return the GodStdCtgrySizeCnnc
	 * @throws SQLException the SQL exception
	 */
	public GodStdCtgrySizeCnnc selectGodStdCtgrySizeCnnc(GodStdCtgrySizeCnnc godStdCtgrySizeCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodStdCtgrySizeCnnc", godStdCtgrySizeCnnc);
	}
	
	/**
	 * 상품 표준 카테고리 사이즈 연결 등록.
	 *
	 * @param godStdCtgrySizeCnnc the GodStdCtgrySizeCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertGodStdCtgrySizeCnnc(GodStdCtgrySizeCnnc godStdCtgrySizeCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertGodStdCtgrySizeCnnc", godStdCtgrySizeCnnc);
	}
	
	/**
	 * 상품 표준 카테고리 사이즈 연결 수정.
	 *
	 * @param godStdCtgrySizeCnnc the GodStdCtgrySizeCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateGodStdCtgrySizeCnnc(GodStdCtgrySizeCnnc godStdCtgrySizeCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateGodStdCtgrySizeCnnc", godStdCtgrySizeCnnc);
	}
	
	/**
	 * 상품 표준 카테고리 사이즈 연결 삭제.
	 *
	 * @param godStdCtgrySizeCnnc the GodStdCtgrySizeCnnc
	 * @return the GodStdCtgrySizeCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodStdCtgrySizeCnnc(GodStdCtgrySizeCnnc godStdCtgrySizeCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodStdCtgrySizeCnnc", godStdCtgrySizeCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

