
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstAns;

/**
 * The Class CsoJobRequstAnsRepository.
 */
@Repository
public class CsoJobRequstAnsRepository extends AbstractRepository {
	

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
	 * 고객서비스 업무 요청 답변 상세 조회.
	 *
	 * @param csoJobRequstAns the CsoJobRequstAns
	 * @return the CsoJobRequstAns
	 * @throws SQLException the SQL exception
	 */
	public CsoJobRequstAns selectCsoJobRequstAns(CsoJobRequstAns csoJobRequstAns) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoJobRequstAns", csoJobRequstAns);
	}
	
	/**
	 * 고객서비스 업무 요청 답변 등록.
	 *
	 * @param csoJobRequstAns the CsoJobRequstAns
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoJobRequstAns(CsoJobRequstAns csoJobRequstAns) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoJobRequstAns", csoJobRequstAns);
	}
	
	/**
	 * 고객서비스 업무 요청 답변 수정.
	 *
	 * @param csoJobRequstAns the CsoJobRequstAns
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoJobRequstAns(CsoJobRequstAns csoJobRequstAns) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoJobRequstAns", csoJobRequstAns);
	}
	
	/**
	 * 고객서비스 업무 요청 답변 삭제.
	 *
	 * @param csoJobRequstAns the CsoJobRequstAns
	 * @return the CsoJobRequstAns
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoJobRequstAns(CsoJobRequstAns csoJobRequstAns) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoJobRequstAns", csoJobRequstAns);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

