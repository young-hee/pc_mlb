
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
import com.plgrim.ncp.base.entities.datasource1.god.GodPrcResve;

/**
 * The Class GodPrcResveRepository.
 */
@Repository
public class GodPrcResveRepository extends AbstractRepository {
	

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
	 * 상품 가격 예약 상세 조회.
	 *
	 * @param godPrcResve the GodPrcResve
	 * @return the GodPrcResve
	 * @throws SQLException the SQL exception
	 */
	public GodPrcResve selectGodPrcResve(GodPrcResve godPrcResve) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodPrcResve", godPrcResve);
	}
	
	/**
	 * 상품 가격 예약 등록.
	 *
	 * @param godPrcResve the GodPrcResve
	 * @throws SQLException the SQL exception
	 */
	public void insertGodPrcResve(GodPrcResve godPrcResve) {
		getSession1().insert("com.plgrim.ncp.base.insertGodPrcResve", godPrcResve);
	}
	
	/**
	 * 상품 가격 예약 수정.
	 *
	 * @param godPrcResve the GodPrcResve
	 * @throws SQLException the SQL exception
	 */
	public int updateGodPrcResve(GodPrcResve godPrcResve) {
		return getSession1().update("com.plgrim.ncp.base.updateGodPrcResve", godPrcResve);
	}
	
	/**
	 * 상품 가격 예약 삭제.
	 *
	 * @param godPrcResve the GodPrcResve
	 * @return the GodPrcResve
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodPrcResve(GodPrcResve godPrcResve) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodPrcResve", godPrcResve);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

