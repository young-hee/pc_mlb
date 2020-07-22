
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
package com.plgrim.ncp.base.repository.cso;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGodInq;

/**
 * The Class CsoGodInqRepository.
 */
@Repository
public class CsoGodInqRepository extends AbstractRepository {
	

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
	 * 고객서비스 상품 문의 상세 조회.
	 *
	 * @param csoGodInq the CsoGodInq
	 * @return the CsoGodInq
	 * @throws SQLException the SQL exception
	 */
	public CsoGodInq selectCsoGodInq(CsoGodInq csoGodInq) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoGodInq", csoGodInq);
	}
	
	/**
	 * 고객서비스 상품 문의 등록.
	 *
	 * @param csoGodInq the CsoGodInq
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoGodInq(CsoGodInq csoGodInq) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoGodInq", csoGodInq);
	}
	
	/**
	 * 고객서비스 상품 문의 수정.
	 *
	 * @param csoGodInq the CsoGodInq
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoGodInq(CsoGodInq csoGodInq) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoGodInq", csoGodInq);
	}
	
	/**
	 * 고객서비스 상품 문의 삭제.
	 *
	 * @param csoGodInq the CsoGodInq
	 * @return the CsoGodInq
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoGodInq(CsoGodInq csoGodInq) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoGodInq", csoGodInq);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

