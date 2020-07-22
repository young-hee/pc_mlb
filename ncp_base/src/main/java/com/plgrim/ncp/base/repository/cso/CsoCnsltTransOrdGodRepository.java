
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTransOrdGod;

/**
 * The Class CsoCnsltTransOrdGodRepository.
 */
@Repository
public class CsoCnsltTransOrdGodRepository extends AbstractRepository {
	

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
	 * 고객서비스 상담 이관 주문 상품 상세 조회.
	 *
	 * @param csoCnsltTransOrdGod the CsoCnsltTransOrdGod
	 * @return the CsoCnsltTransOrdGod
	 * @throws SQLException the SQL exception
	 */
	public CsoCnsltTransOrdGod selectCsoCnsltTransOrdGod(CsoCnsltTransOrdGod csoCnsltTransOrdGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoCnsltTransOrdGod", csoCnsltTransOrdGod);
	}
	
	/**
	 * 고객서비스 상담 이관 주문 상품 등록.
	 *
	 * @param csoCnsltTransOrdGod the CsoCnsltTransOrdGod
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoCnsltTransOrdGod(CsoCnsltTransOrdGod csoCnsltTransOrdGod) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoCnsltTransOrdGod", csoCnsltTransOrdGod);
	}
	
	/**
	 * 고객서비스 상담 이관 주문 상품 수정.
	 *
	 * @param csoCnsltTransOrdGod the CsoCnsltTransOrdGod
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoCnsltTransOrdGod(CsoCnsltTransOrdGod csoCnsltTransOrdGod) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoCnsltTransOrdGod", csoCnsltTransOrdGod);
	}
	
	/**
	 * 고객서비스 상담 이관 주문 상품 삭제.
	 *
	 * @param csoCnsltTransOrdGod the CsoCnsltTransOrdGod
	 * @return the CsoCnsltTransOrdGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoCnsltTransOrdGod(CsoCnsltTransOrdGod csoCnsltTransOrdGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoCnsltTransOrdGod", csoCnsltTransOrdGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

