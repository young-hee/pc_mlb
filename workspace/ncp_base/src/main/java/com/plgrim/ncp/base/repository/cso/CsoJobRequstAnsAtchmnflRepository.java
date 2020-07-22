
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstAnsAtchmnfl;

/**
 * The Class CsoJobRequstAnsAtchmnflRepository.
 */
@Repository
public class CsoJobRequstAnsAtchmnflRepository extends AbstractRepository {
	

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
	 * 고객서비스 업무 요청 답변 첨부파일 상세 조회.
	 *
	 * @param csoJobRequstAnsAtchmnfl the CsoJobRequstAnsAtchmnfl
	 * @return the CsoJobRequstAnsAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public CsoJobRequstAnsAtchmnfl selectCsoJobRequstAnsAtchmnfl(CsoJobRequstAnsAtchmnfl csoJobRequstAnsAtchmnfl) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoJobRequstAnsAtchmnfl", csoJobRequstAnsAtchmnfl);
	}
	
	/**
	 * 고객서비스 업무 요청 답변 첨부파일 등록.
	 *
	 * @param csoJobRequstAnsAtchmnfl the CsoJobRequstAnsAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoJobRequstAnsAtchmnfl(CsoJobRequstAnsAtchmnfl csoJobRequstAnsAtchmnfl) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoJobRequstAnsAtchmnfl", csoJobRequstAnsAtchmnfl);
	}
	
	/**
	 * 고객서비스 업무 요청 답변 첨부파일 수정.
	 *
	 * @param csoJobRequstAnsAtchmnfl the CsoJobRequstAnsAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoJobRequstAnsAtchmnfl(CsoJobRequstAnsAtchmnfl csoJobRequstAnsAtchmnfl) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoJobRequstAnsAtchmnfl", csoJobRequstAnsAtchmnfl);
	}
	
	/**
	 * 고객서비스 업무 요청 답변 첨부파일 삭제.
	 *
	 * @param csoJobRequstAnsAtchmnfl the CsoJobRequstAnsAtchmnfl
	 * @return the CsoJobRequstAnsAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoJobRequstAnsAtchmnfl(CsoJobRequstAnsAtchmnfl csoJobRequstAnsAtchmnfl) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoJobRequstAnsAtchmnfl", csoJobRequstAnsAtchmnfl);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

