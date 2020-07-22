
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
import com.plgrim.ncp.base.entities.datasource1.god.GodSizeGd;

/**
 * The Class GodSizeGdRepository.
 */
@Repository
public class GodSizeGdRepository extends AbstractRepository {
	

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
	 * 상품 사이즈 가이드 상세 조회.
	 *
	 * @param godSizeGd the GodSizeGd
	 * @return the GodSizeGd
	 * @throws SQLException the SQL exception
	 */
	public GodSizeGd selectGodSizeGd(GodSizeGd godSizeGd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodSizeGd", godSizeGd);
	}
	
	/**
	 * 상품 사이즈 가이드 등록.
	 *
	 * @param godSizeGd the GodSizeGd
	 * @throws SQLException the SQL exception
	 */
	public void insertGodSizeGd(GodSizeGd godSizeGd) {
		getSession1().insert("com.plgrim.ncp.base.insertGodSizeGd", godSizeGd);
	}
	
	/**
	 * 상품 사이즈 가이드 수정.
	 *
	 * @param godSizeGd the GodSizeGd
	 * @throws SQLException the SQL exception
	 */
	public int updateGodSizeGd(GodSizeGd godSizeGd) {
		return getSession1().update("com.plgrim.ncp.base.updateGodSizeGd", godSizeGd);
	}
	
	/**
	 * 상품 사이즈 가이드 삭제.
	 *
	 * @param godSizeGd the GodSizeGd
	 * @return the GodSizeGd
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodSizeGd(GodSizeGd godSizeGd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodSizeGd", godSizeGd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

