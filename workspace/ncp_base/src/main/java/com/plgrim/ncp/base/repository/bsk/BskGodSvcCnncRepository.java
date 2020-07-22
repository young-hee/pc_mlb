
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
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodSvcCnnc;

/**
 * The Class BskGodSvcCnncRepository.
 */
@Repository
public class BskGodSvcCnncRepository extends AbstractRepository {
	

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
	 * 장바구니 상품 서비스 연결 상세 조회.
	 *
	 * @param bskGodSvcCnnc the BskGodSvcCnnc
	 * @return the BskGodSvcCnnc
	 * @throws SQLException the SQL exception
	 */
	public BskGodSvcCnnc selectBskGodSvcCnnc(BskGodSvcCnnc bskGodSvcCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectBskGodSvcCnnc", bskGodSvcCnnc);
	}
	
	/**
	 * 장바구니 상품 서비스 연결 등록.
	 *
	 * @param bskGodSvcCnnc the BskGodSvcCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertBskGodSvcCnnc(BskGodSvcCnnc bskGodSvcCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertBskGodSvcCnnc", bskGodSvcCnnc);
	}
	
	/**
	 * 장바구니 상품 서비스 연결 수정.
	 *
	 * @param bskGodSvcCnnc the BskGodSvcCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateBskGodSvcCnnc(BskGodSvcCnnc bskGodSvcCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateBskGodSvcCnnc", bskGodSvcCnnc);
	}
	
	/**
	 * 장바구니 상품 서비스 연결 삭제.
	 *
	 * @param bskGodSvcCnnc the BskGodSvcCnnc
	 * @return the BskGodSvcCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteBskGodSvcCnnc(BskGodSvcCnnc bskGodSvcCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteBskGodSvcCnnc", bskGodSvcCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

