
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstOrdGod;

/**
 * The Class CsoJobRequstOrdGodRepository.
 */
@Repository
public class CsoJobRequstOrdGodRepository extends AbstractRepository {
	

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
	 * 고객서비스 업무 요청 주문 상품 상세 조회.
	 *
	 * @param csoJobRequstOrdGod the CsoJobRequstOrdGod
	 * @return the CsoJobRequstOrdGod
	 * @throws SQLException the SQL exception
	 */
	public CsoJobRequstOrdGod selectCsoJobRequstOrdGod(CsoJobRequstOrdGod csoJobRequstOrdGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoJobRequstOrdGod", csoJobRequstOrdGod);
	}
	
	/**
	 * 고객서비스 업무 요청 주문 상품 등록.
	 *
	 * @param csoJobRequstOrdGod the CsoJobRequstOrdGod
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoJobRequstOrdGod(CsoJobRequstOrdGod csoJobRequstOrdGod) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoJobRequstOrdGod", csoJobRequstOrdGod);
	}
	
	/**
	 * 고객서비스 업무 요청 주문 상품 수정.
	 *
	 * @param csoJobRequstOrdGod the CsoJobRequstOrdGod
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoJobRequstOrdGod(CsoJobRequstOrdGod csoJobRequstOrdGod) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoJobRequstOrdGod", csoJobRequstOrdGod);
	}
	
	/**
	 * 고객서비스 업무 요청 주문 상품 삭제.
	 *
	 * @param csoJobRequstOrdGod the CsoJobRequstOrdGod
	 * @return the CsoJobRequstOrdGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoJobRequstOrdGod(CsoJobRequstOrdGod csoJobRequstOrdGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoJobRequstOrdGod", csoJobRequstOrdGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

