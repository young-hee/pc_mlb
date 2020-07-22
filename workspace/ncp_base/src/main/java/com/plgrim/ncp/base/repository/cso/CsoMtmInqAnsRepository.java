
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqAns;

/**
 * The Class CsoMtmInqAnsRepository.
 */
@Repository
public class CsoMtmInqAnsRepository extends AbstractRepository {
	

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
	 * 고객서비스 일대일 문의 답변 상세 조회.
	 *
	 * @param csoMtmInqAns the CsoMtmInqAns
	 * @return the CsoMtmInqAns
	 * @throws SQLException the SQL exception
	 */
	public CsoMtmInqAns selectCsoMtmInqAns(CsoMtmInqAns csoMtmInqAns) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoMtmInqAns", csoMtmInqAns);
	}
	
	/**
	 * 고객서비스 일대일 문의 답변 등록.
	 *
	 * @param csoMtmInqAns the CsoMtmInqAns
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoMtmInqAns(CsoMtmInqAns csoMtmInqAns) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoMtmInqAns", csoMtmInqAns);
	}
	
	/**
	 * 고객서비스 일대일 문의 답변 수정.
	 *
	 * @param csoMtmInqAns the CsoMtmInqAns
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoMtmInqAns(CsoMtmInqAns csoMtmInqAns) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoMtmInqAns", csoMtmInqAns);
	}
	
	/**
	 * 고객서비스 일대일 문의 답변 삭제.
	 *
	 * @param csoMtmInqAns the CsoMtmInqAns
	 * @return the CsoMtmInqAns
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoMtmInqAns(CsoMtmInqAns csoMtmInqAns) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoMtmInqAns", csoMtmInqAns);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

