
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
 * Generator(Generator)		2018-06-03                      
 */
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;

/**
 * The Class GodItmRepository.
 */
@Repository
public class GodItmRepository extends AbstractRepository {
	

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
	 * 상품 단품 상세 조회.
	 *
	 * @param godItm the GodItm
	 * @return the GodItm
	 * @throws SQLException the SQL exception
	 */
	public GodItm selectGodItm(GodItm godItm) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodItm", godItm);
	}
	
	/**
	 * 상품 단품 등록.
	 *
	 * @param godItm the GodItm
	 * @throws SQLException the SQL exception
	 */
	public void insertGodItm(GodItm godItm) {
		getSession1().insert("com.plgrim.ncp.base.insertGodItm", godItm);
	}
	
	/**
	 * 상품 단품 수정.
	 *
	 * @param godItm the GodItm
	 * @throws SQLException the SQL exception
	 */
	public int updateGodItm(GodItm godItm) {
		return getSession1().update("com.plgrim.ncp.base.updateGodItm", godItm);
	}
	
	/**
	 * 상품 단품 삭제.
	 *
	 * @param godItm the GodItm
	 * @return the GodItm
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodItm(GodItm godItm) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodItm", godItm);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

