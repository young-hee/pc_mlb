
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
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvl;

/**
 * The Class GodGodEvlRepository.
 */
@Repository
public class GodGodEvlRepository extends AbstractRepository {
	

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
	 * 상품 상품평 상세 조회.
	 *
	 * @param godGodEvl the GodGodEvl
	 * @return the GodGodEvl
	 * @throws SQLException the SQL exception
	 */
	public GodGodEvl selectGodGodEvl(GodGodEvl godGodEvl) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodGodEvl", godGodEvl);
	}
	
	/**
	 * 상품 상품평 등록.
	 *
	 * @param godGodEvl the GodGodEvl
	 * @throws SQLException the SQL exception
	 */
	public void insertGodGodEvl(GodGodEvl godGodEvl) {
		getSession1().insert("com.plgrim.ncp.base.insertGodGodEvl", godGodEvl);
	}
	
	/**
	 * 상품 상품평 수정.
	 *
	 * @param godGodEvl the GodGodEvl
	 * @throws SQLException the SQL exception
	 */
	public int updateGodGodEvl(GodGodEvl godGodEvl) {
		return getSession1().update("com.plgrim.ncp.base.updateGodGodEvl", godGodEvl);
	}
	
	/**
	 * 상품 상품평 삭제.
	 *
	 * @param godGodEvl the GodGodEvl
	 * @return the GodGodEvl
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodGodEvl(GodGodEvl godGodEvl) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodGodEvl", godGodEvl);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

