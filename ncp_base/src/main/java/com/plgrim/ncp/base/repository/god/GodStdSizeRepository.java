
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
import com.plgrim.ncp.base.entities.datasource1.god.GodStdSize;

/**
 * The Class GodStdSizeRepository.
 */
@Repository
public class GodStdSizeRepository extends AbstractRepository {
	

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
	 * 상품 표준 사이즈 상세 조회.
	 *
	 * @param godStdSize the GodStdSize
	 * @return the GodStdSize
	 * @throws SQLException the SQL exception
	 */
	public GodStdSize selectGodStdSize(GodStdSize godStdSize) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodStdSize", godStdSize);
	}
	
	/**
	 * 상품 표준 사이즈 등록.
	 *
	 * @param godStdSize the GodStdSize
	 * @throws SQLException the SQL exception
	 */
	public void insertGodStdSize(GodStdSize godStdSize) {
		getSession1().insert("com.plgrim.ncp.base.insertGodStdSize", godStdSize);
	}
	
	/**
	 * 상품 표준 사이즈 수정.
	 *
	 * @param godStdSize the GodStdSize
	 * @throws SQLException the SQL exception
	 */
	public int updateGodStdSize(GodStdSize godStdSize) {
		return getSession1().update("com.plgrim.ncp.base.updateGodStdSize", godStdSize);
	}
	
	/**
	 * 상품 표준 사이즈 삭제.
	 *
	 * @param godStdSize the GodStdSize
	 * @return the GodStdSize
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodStdSize(GodStdSize godStdSize) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodStdSize", godStdSize);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

