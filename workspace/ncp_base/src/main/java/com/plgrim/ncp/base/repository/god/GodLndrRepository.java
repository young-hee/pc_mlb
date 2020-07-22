
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
import com.plgrim.ncp.base.entities.datasource1.god.GodLndr;

/**
 * The Class GodLndrRepository.
 */
@Repository
public class GodLndrRepository extends AbstractRepository {
	

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
	 * 상품 세탁 상세 조회.
	 *
	 * @param godLndr the GodLndr
	 * @return the GodLndr
	 * @throws SQLException the SQL exception
	 */
	public GodLndr selectGodLndr(GodLndr godLndr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodLndr", godLndr);
	}
	
	/**
	 * 상품 세탁 등록.
	 *
	 * @param godLndr the GodLndr
	 * @throws SQLException the SQL exception
	 */
	public void insertGodLndr(GodLndr godLndr) {
		getSession1().insert("com.plgrim.ncp.base.insertGodLndr", godLndr);
	}
	
	/**
	 * 상품 세탁 수정.
	 *
	 * @param godLndr the GodLndr
	 * @throws SQLException the SQL exception
	 */
	public int updateGodLndr(GodLndr godLndr) {
		return getSession1().update("com.plgrim.ncp.base.updateGodLndr", godLndr);
	}
	
	/**
	 * 상품 세탁 삭제.
	 *
	 * @param godLndr the GodLndr
	 * @return the GodLndr
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodLndr(GodLndr godLndr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodLndr", godLndr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

