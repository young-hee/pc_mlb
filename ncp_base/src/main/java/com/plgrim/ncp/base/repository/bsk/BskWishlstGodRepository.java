
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
import com.plgrim.ncp.base.entities.datasource1.bsk.BskWishlstGod;

/**
 * The Class BskWishlstGodRepository.
 */
@Repository
public class BskWishlstGodRepository extends AbstractRepository {
	

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
	 * 장바구니 위시리스트 상품 상세 조회.
	 *
	 * @param bskWishlstGod the BskWishlstGod
	 * @return the BskWishlstGod
	 * @throws SQLException the SQL exception
	 */
	public BskWishlstGod selectBskWishlstGod(BskWishlstGod bskWishlstGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectBskWishlstGod", bskWishlstGod);
	}
	
	/**
	 * 장바구니 위시리스트 상품 등록.
	 *
	 * @param bskWishlstGod the BskWishlstGod
	 * @throws SQLException the SQL exception
	 */
	public void insertBskWishlstGod(BskWishlstGod bskWishlstGod) {
		getSession1().insert("com.plgrim.ncp.base.insertBskWishlstGod", bskWishlstGod);
	}
	
	/**
	 * 장바구니 위시리스트 상품 수정.
	 *
	 * @param bskWishlstGod the BskWishlstGod
	 * @throws SQLException the SQL exception
	 */
	public int updateBskWishlstGod(BskWishlstGod bskWishlstGod) {
		return getSession1().update("com.plgrim.ncp.base.updateBskWishlstGod", bskWishlstGod);
	}
	
	/**
	 * 장바구니 위시리스트 상품 삭제.
	 *
	 * @param bskWishlstGod the BskWishlstGod
	 * @return the BskWishlstGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteBskWishlstGod(BskWishlstGod bskWishlstGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteBskWishlstGod", bskWishlstGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

