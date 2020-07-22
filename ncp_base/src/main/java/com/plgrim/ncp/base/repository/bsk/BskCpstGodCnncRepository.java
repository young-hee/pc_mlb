
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
import com.plgrim.ncp.base.entities.datasource1.bsk.BskCpstGodCnnc;

/**
 * The Class BskCpstGodCnncRepository.
 */
@Repository
public class BskCpstGodCnncRepository extends AbstractRepository {
	

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
	 * 장바구니 구성 상품 연결 상세 조회.
	 *
	 * @param bskCpstGodCnnc the BskCpstGodCnnc
	 * @return the BskCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public BskCpstGodCnnc selectBskCpstGodCnnc(BskCpstGodCnnc bskCpstGodCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectBskCpstGodCnnc", bskCpstGodCnnc);
	}
	
	/**
	 * 장바구니 구성 상품 연결 등록.
	 *
	 * @param bskCpstGodCnnc the BskCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertBskCpstGodCnnc(BskCpstGodCnnc bskCpstGodCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertBskCpstGodCnnc", bskCpstGodCnnc);
	}
	
	/**
	 * 장바구니 구성 상품 연결 수정.
	 *
	 * @param bskCpstGodCnnc the BskCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateBskCpstGodCnnc(BskCpstGodCnnc bskCpstGodCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateBskCpstGodCnnc", bskCpstGodCnnc);
	}
	
	/**
	 * 장바구니 구성 상품 연결 삭제.
	 *
	 * @param bskCpstGodCnnc the BskCpstGodCnnc
	 * @return the BskCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteBskCpstGodCnnc(BskCpstGodCnnc bskCpstGodCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteBskCpstGodCnnc", bskCpstGodCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

