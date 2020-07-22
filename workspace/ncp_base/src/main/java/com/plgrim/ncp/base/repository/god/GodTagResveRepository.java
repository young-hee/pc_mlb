
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
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResve;

/**
 * The Class GodTagResveRepository.
 */
@Repository
public class GodTagResveRepository extends AbstractRepository {
	

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
	 * 상품 태그 예약 상세 조회.
	 *
	 * @param godTagResve the GodTagResve
	 * @return the GodTagResve
	 * @throws SQLException the SQL exception
	 */
	public GodTagResve selectGodTagResve(GodTagResve godTagResve) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodTagResve", godTagResve);
	}
	
	/**
	 * 상품 태그 예약 등록.
	 *
	 * @param godTagResve the GodTagResve
	 * @throws SQLException the SQL exception
	 */
	public void insertGodTagResve(GodTagResve godTagResve) {
		getSession1().insert("com.plgrim.ncp.base.insertGodTagResve", godTagResve);
	}
	
	/**
	 * 상품 태그 예약 수정.
	 *
	 * @param godTagResve the GodTagResve
	 * @throws SQLException the SQL exception
	 */
	public int updateGodTagResve(GodTagResve godTagResve) {
		return getSession1().update("com.plgrim.ncp.base.updateGodTagResve", godTagResve);
	}
	
	/**
	 * 상품 태그 예약 삭제.
	 *
	 * @param godTagResve the GodTagResve
	 * @return the GodTagResve
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodTagResve(GodTagResve godTagResve) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodTagResve", godTagResve);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

