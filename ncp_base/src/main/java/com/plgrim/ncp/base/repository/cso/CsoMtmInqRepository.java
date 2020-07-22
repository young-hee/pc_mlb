
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
 * Generator(Generator)		2018-06-21                      
 */
package com.plgrim.ncp.base.repository.cso;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInq;

/**
 * The Class CsoMtmInqRepository.
 */
@Repository
public class CsoMtmInqRepository extends AbstractRepository {
	

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
	 * 고객서비스 일대일 문의 상세 조회.
	 *
	 * @param csoMtmInq the CsoMtmInq
	 * @return the CsoMtmInq
	 * @throws SQLException the SQL exception
	 */
	public CsoMtmInq selectCsoMtmInq(CsoMtmInq csoMtmInq) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoMtmInq", csoMtmInq);
	}
	
	/**
	 * 고객서비스 일대일 문의 등록.
	 *
	 * @param csoMtmInq the CsoMtmInq
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoMtmInq(CsoMtmInq csoMtmInq) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoMtmInq", csoMtmInq);
	}
	
	/**
	 * 고객서비스 일대일 문의 수정.
	 *
	 * @param csoMtmInq the CsoMtmInq
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoMtmInq(CsoMtmInq csoMtmInq) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoMtmInq", csoMtmInq);
	}
	
	/**
	 * 고객서비스 일대일 문의 삭제.
	 *
	 * @param csoMtmInq the CsoMtmInq
	 * @return the CsoMtmInq
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoMtmInq(CsoMtmInq csoMtmInq) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoMtmInq", csoMtmInq);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

