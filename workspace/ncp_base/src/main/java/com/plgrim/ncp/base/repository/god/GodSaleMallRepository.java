
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
import com.plgrim.ncp.base.entities.datasource1.god.GodSaleMall;

/**
 * The Class GodSaleMallRepository.
 */
@Repository
public class GodSaleMallRepository extends AbstractRepository {
	

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
	 * 상품 판매 몰 상세 조회.
	 *
	 * @param godSaleMall the GodSaleMall
	 * @return the GodSaleMall
	 * @throws SQLException the SQL exception
	 */
	public GodSaleMall selectGodSaleMall(GodSaleMall godSaleMall) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodSaleMall", godSaleMall);
	}
	
	/**
	 * 상품 판매 몰 등록.
	 *
	 * @param godSaleMall the GodSaleMall
	 * @throws SQLException the SQL exception
	 */
	public void insertGodSaleMall(GodSaleMall godSaleMall) {
		getSession1().insert("com.plgrim.ncp.base.insertGodSaleMall", godSaleMall);
	}
	
	/**
	 * 상품 판매 몰 수정.
	 *
	 * @param godSaleMall the GodSaleMall
	 * @throws SQLException the SQL exception
	 */
	public int updateGodSaleMall(GodSaleMall godSaleMall) {
		return getSession1().update("com.plgrim.ncp.base.updateGodSaleMall", godSaleMall);
	}
	
	/**
	 * 상품 판매 몰 삭제.
	 *
	 * @param godSaleMall the GodSaleMall
	 * @return the GodSaleMall
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodSaleMall(GodSaleMall godSaleMall) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodSaleMall", godSaleMall);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

