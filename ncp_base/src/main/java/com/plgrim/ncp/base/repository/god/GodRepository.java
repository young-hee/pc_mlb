
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
 * Generator(Generator)		2018-07-12                      
 */
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.God;

/**
 * The Class GodRepository.
 */
@Repository
public class GodRepository extends AbstractRepository {
	

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
	 * 상품 상세 조회.
	 *
	 * @param god the God
	 * @return the God
	 * @throws SQLException the SQL exception
	 */
	public God selectGod(God god) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGod", god);
	}
	
	/**
	 * 상품 등록.
	 *
	 * @param god the God
	 * @throws SQLException the SQL exception
	 */
	public void insertGod(God god) {
		getSession1().insert("com.plgrim.ncp.base.insertGod", god);
	}
	
	/**
	 * 상품 수정.
	 *
	 * @param god the God
	 * @throws SQLException the SQL exception
	 */
	public int updateGod(God god) {
		return getSession1().update("com.plgrim.ncp.base.updateGod", god);
	}
	
	/**
	 * 상품 삭제.
	 *
	 * @param god the God
	 * @return the God
	 * @throws SQLException the SQL exception
	 */
	public int deleteGod(God god) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGod", god);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

