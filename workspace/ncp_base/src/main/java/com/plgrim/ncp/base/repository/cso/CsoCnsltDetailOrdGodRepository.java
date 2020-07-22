
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetailOrdGod;

/**
 * The Class CsoCnsltDetailOrdGodRepository.
 */
@Repository
public class CsoCnsltDetailOrdGodRepository extends AbstractRepository {
	

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
	 * 고객서비스 상담 상세 주문 상품 상세 조회.
	 *
	 * @param csoCnsltDetailOrdGod the CsoCnsltDetailOrdGod
	 * @return the CsoCnsltDetailOrdGod
	 * @throws SQLException the SQL exception
	 */
	public CsoCnsltDetailOrdGod selectCsoCnsltDetailOrdGod(CsoCnsltDetailOrdGod csoCnsltDetailOrdGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoCnsltDetailOrdGod", csoCnsltDetailOrdGod);
	}
	
	/**
	 * 고객서비스 상담 상세 주문 상품 등록.
	 *
	 * @param csoCnsltDetailOrdGod the CsoCnsltDetailOrdGod
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoCnsltDetailOrdGod(CsoCnsltDetailOrdGod csoCnsltDetailOrdGod) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoCnsltDetailOrdGod", csoCnsltDetailOrdGod);
	}
	
	/**
	 * 고객서비스 상담 상세 주문 상품 수정.
	 *
	 * @param csoCnsltDetailOrdGod the CsoCnsltDetailOrdGod
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoCnsltDetailOrdGod(CsoCnsltDetailOrdGod csoCnsltDetailOrdGod) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoCnsltDetailOrdGod", csoCnsltDetailOrdGod);
	}
	
	/**
	 * 고객서비스 상담 상세 주문 상품 삭제.
	 *
	 * @param csoCnsltDetailOrdGod the CsoCnsltDetailOrdGod
	 * @return the CsoCnsltDetailOrdGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoCnsltDetailOrdGod(CsoCnsltDetailOrdGod csoCnsltDetailOrdGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoCnsltDetailOrdGod", csoCnsltDetailOrdGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

