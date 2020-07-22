
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
import com.plgrim.ncp.base.entities.datasource1.bsk.BskWishlst;

/**
 * The Class BskWishlstRepository.
 */
@Repository
public class BskWishlstRepository extends AbstractRepository {
	

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
	 * 장바구니 위시리스트 상세 조회.
	 *
	 * @param bskWishlst the BskWishlst
	 * @return the BskWishlst
	 * @throws SQLException the SQL exception
	 */
	public BskWishlst selectBskWishlst(BskWishlst bskWishlst) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectBskWishlst", bskWishlst);
	}
	
	/**
	 * 장바구니 위시리스트 등록.
	 *
	 * @param bskWishlst the BskWishlst
	 * @throws SQLException the SQL exception
	 */
	public void insertBskWishlst(BskWishlst bskWishlst) {
		getSession1().insert("com.plgrim.ncp.base.insertBskWishlst", bskWishlst);
	}
	
	/**
	 * 장바구니 위시리스트 수정.
	 *
	 * @param bskWishlst the BskWishlst
	 * @throws SQLException the SQL exception
	 */
	public int updateBskWishlst(BskWishlst bskWishlst) {
		return getSession1().update("com.plgrim.ncp.base.updateBskWishlst", bskWishlst);
	}
	
	/**
	 * 장바구니 위시리스트 삭제.
	 *
	 * @param bskWishlst the BskWishlst
	 * @return the BskWishlst
	 * @throws SQLException the SQL exception
	 */
	public int deleteBskWishlst(BskWishlst bskWishlst) {
		return getSession1().delete("com.plgrim.ncp.base.deleteBskWishlst", bskWishlst);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

