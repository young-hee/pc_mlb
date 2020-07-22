
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqOrdGod;

/**
 * The Class CsoMtmInqOrdGodRepository.
 */
@Repository
public class CsoMtmInqOrdGodRepository extends AbstractRepository {
	

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
	 * 고객서비스 일대일 문의 주문 상품 상세 조회.
	 *
	 * @param csoMtmInqOrdGod the CsoMtmInqOrdGod
	 * @return the CsoMtmInqOrdGod
	 * @throws SQLException the SQL exception
	 */
	public CsoMtmInqOrdGod selectCsoMtmInqOrdGod(CsoMtmInqOrdGod csoMtmInqOrdGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoMtmInqOrdGod", csoMtmInqOrdGod);
	}
	
	/**
	 * 고객서비스 일대일 문의 주문 상품 등록.
	 *
	 * @param csoMtmInqOrdGod the CsoMtmInqOrdGod
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoMtmInqOrdGod(CsoMtmInqOrdGod csoMtmInqOrdGod) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoMtmInqOrdGod", csoMtmInqOrdGod);
	}
	
	/**
	 * 고객서비스 일대일 문의 주문 상품 수정.
	 *
	 * @param csoMtmInqOrdGod the CsoMtmInqOrdGod
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoMtmInqOrdGod(CsoMtmInqOrdGod csoMtmInqOrdGod) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoMtmInqOrdGod", csoMtmInqOrdGod);
	}
	
	/**
	 * 고객서비스 일대일 문의 주문 상품 삭제.
	 *
	 * @param csoMtmInqOrdGod the CsoMtmInqOrdGod
	 * @return the CsoMtmInqOrdGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoMtmInqOrdGod(CsoMtmInqOrdGod csoMtmInqOrdGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoMtmInqOrdGod", csoMtmInqOrdGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

