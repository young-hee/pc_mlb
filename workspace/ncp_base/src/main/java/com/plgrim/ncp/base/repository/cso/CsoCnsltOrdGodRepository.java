
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltOrdGod;

/**
 * The Class CsoCnsltOrdGodRepository.
 */
@Repository
public class CsoCnsltOrdGodRepository extends AbstractRepository {
	

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
	 * 고객서비스 상담 주문 상품 상세 조회.
	 *
	 * @param csoCnsltOrdGod the CsoCnsltOrdGod
	 * @return the CsoCnsltOrdGod
	 * @throws SQLException the SQL exception
	 */
	public CsoCnsltOrdGod selectCsoCnsltOrdGod(CsoCnsltOrdGod csoCnsltOrdGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoCnsltOrdGod", csoCnsltOrdGod);
	}
	
	/**
	 * 고객서비스 상담 주문 상품 등록.
	 *
	 * @param csoCnsltOrdGod the CsoCnsltOrdGod
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoCnsltOrdGod(CsoCnsltOrdGod csoCnsltOrdGod) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoCnsltOrdGod", csoCnsltOrdGod);
	}
	
	/**
	 * 고객서비스 상담 주문 상품 수정.
	 *
	 * @param csoCnsltOrdGod the CsoCnsltOrdGod
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoCnsltOrdGod(CsoCnsltOrdGod csoCnsltOrdGod) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoCnsltOrdGod", csoCnsltOrdGod);
	}
	
	/**
	 * 고객서비스 상담 주문 상품 삭제.
	 *
	 * @param csoCnsltOrdGod the CsoCnsltOrdGod
	 * @return the CsoCnsltOrdGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoCnsltOrdGod(CsoCnsltOrdGod csoCnsltOrdGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoCnsltOrdGod", csoCnsltOrdGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

