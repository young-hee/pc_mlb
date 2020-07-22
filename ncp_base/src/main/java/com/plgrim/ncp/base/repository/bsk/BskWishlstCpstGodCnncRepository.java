
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
import com.plgrim.ncp.base.entities.datasource1.bsk.BskWishlstCpstGodCnnc;

/**
 * The Class BskWishlstCpstGodCnncRepository.
 */
@Repository
public class BskWishlstCpstGodCnncRepository extends AbstractRepository {
	

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
	 * 장바구니 위시리스트 구성 상품 연결 상세 조회.
	 *
	 * @param bskWishlstCpstGodCnnc the BskWishlstCpstGodCnnc
	 * @return the BskWishlstCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public BskWishlstCpstGodCnnc selectBskWishlstCpstGodCnnc(BskWishlstCpstGodCnnc bskWishlstCpstGodCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectBskWishlstCpstGodCnnc", bskWishlstCpstGodCnnc);
	}
	
	/**
	 * 장바구니 위시리스트 구성 상품 연결 등록.
	 *
	 * @param bskWishlstCpstGodCnnc the BskWishlstCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertBskWishlstCpstGodCnnc(BskWishlstCpstGodCnnc bskWishlstCpstGodCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertBskWishlstCpstGodCnnc", bskWishlstCpstGodCnnc);
	}
	
	/**
	 * 장바구니 위시리스트 구성 상품 연결 수정.
	 *
	 * @param bskWishlstCpstGodCnnc the BskWishlstCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateBskWishlstCpstGodCnnc(BskWishlstCpstGodCnnc bskWishlstCpstGodCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateBskWishlstCpstGodCnnc", bskWishlstCpstGodCnnc);
	}
	
	/**
	 * 장바구니 위시리스트 구성 상품 연결 삭제.
	 *
	 * @param bskWishlstCpstGodCnnc the BskWishlstCpstGodCnnc
	 * @return the BskWishlstCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteBskWishlstCpstGodCnnc(BskWishlstCpstGodCnnc bskWishlstCpstGodCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteBskWishlstCpstGodCnnc", bskWishlstCpstGodCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

