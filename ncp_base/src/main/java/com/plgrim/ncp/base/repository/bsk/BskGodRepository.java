
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
package com.plgrim.ncp.base.repository.bsk;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;

/**
 * The Class BskGodRepository.
 */
@Repository
public class BskGodRepository extends AbstractRepository {
	

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
	 * 장바구니 상품 상세 조회.
	 *
	 * @param bskGod the BskGod
	 * @return the BskGod
	 * @throws SQLException the SQL exception
	 */
	public BskGod selectBskGod(BskGod bskGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectBskGod", bskGod);
	}
	
	/**
	 * 장바구니 상품 등록.
	 *
	 * @param bskGod the BskGod
	 * @throws SQLException the SQL exception
	 */
	public void insertBskGod(BskGod bskGod) {
		getSession1().insert("com.plgrim.ncp.base.insertBskGod", bskGod);
	}
	
	/**
	 * 장바구니 상품 수정.
	 *
	 * @param bskGod the BskGod
	 * @throws SQLException the SQL exception
	 */
	public int updateBskGod(BskGod bskGod) {
		return getSession1().update("com.plgrim.ncp.base.updateBskGod", bskGod);
	}
	
	/**
	 * 장바구니 상품 삭제.
	 *
	 * @param bskGod the BskGod
	 * @return the BskGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteBskGod(BskGod bskGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteBskGod", bskGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

