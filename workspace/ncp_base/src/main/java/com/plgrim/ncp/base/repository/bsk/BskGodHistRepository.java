
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
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodHist;

/**
 * The Class BskGodHistRepository.
 */
@Repository
public class BskGodHistRepository extends AbstractRepository {
	

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
	 * 장바구니 상품 이력 상세 조회.
	 *
	 * @param bskGodHist the BskGodHist
	 * @return the BskGodHist
	 * @throws SQLException the SQL exception
	 */
	public BskGodHist selectBskGodHist(BskGodHist bskGodHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectBskGodHist", bskGodHist);
	}
	
	/**
	 * 장바구니 상품 이력 등록.
	 *
	 * @param bskGodHist the BskGodHist
	 * @throws SQLException the SQL exception
	 */
	public void insertBskGodHist(BskGodHist bskGodHist) {
		getSession1().insert("com.plgrim.ncp.base.insertBskGodHist", bskGodHist);
	}
	
	/**
	 * 장바구니 상품 이력 수정.
	 *
	 * @param bskGodHist the BskGodHist
	 * @throws SQLException the SQL exception
	 */
	public int updateBskGodHist(BskGodHist bskGodHist) {
		return getSession1().update("com.plgrim.ncp.base.updateBskGodHist", bskGodHist);
	}
	
	/**
	 * 장바구니 상품 이력 삭제.
	 *
	 * @param bskGodHist the BskGodHist
	 * @return the BskGodHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteBskGodHist(BskGodHist bskGodHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteBskGodHist", bskGodHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

