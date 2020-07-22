
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
import com.plgrim.ncp.base.entities.datasource1.god.GodStdCtgry;

/**
 * The Class GodStdCtgryRepository.
 */
@Repository
public class GodStdCtgryRepository extends AbstractRepository {
	

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
	 * 상품 표준 카테고리 상세 조회.
	 *
	 * @param godStdCtgry the GodStdCtgry
	 * @return the GodStdCtgry
	 * @throws SQLException the SQL exception
	 */
	public GodStdCtgry selectGodStdCtgry(GodStdCtgry godStdCtgry) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodStdCtgry", godStdCtgry);
	}
	
	/**
	 * 상품 표준 카테고리 등록.
	 *
	 * @param godStdCtgry the GodStdCtgry
	 * @throws SQLException the SQL exception
	 */
	public void insertGodStdCtgry(GodStdCtgry godStdCtgry) {
		getSession1().insert("com.plgrim.ncp.base.insertGodStdCtgry", godStdCtgry);
	}
	
	/**
	 * 상품 표준 카테고리 수정.
	 *
	 * @param godStdCtgry the GodStdCtgry
	 * @throws SQLException the SQL exception
	 */
	public int updateGodStdCtgry(GodStdCtgry godStdCtgry) {
		return getSession1().update("com.plgrim.ncp.base.updateGodStdCtgry", godStdCtgry);
	}
	
	/**
	 * 상품 표준 카테고리 삭제.
	 *
	 * @param godStdCtgry the GodStdCtgry
	 * @return the GodStdCtgry
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodStdCtgry(GodStdCtgry godStdCtgry) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodStdCtgry", godStdCtgry);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

