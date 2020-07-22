
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoChgcallPhonDetl;

/**
 * The Class CsoChgcallPhonDetlRepository.
 */
@Repository
public class CsoChgcallPhonDetlRepository extends AbstractRepository {
	

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
	 * 고객서비스 호전환 전화번호 내역 상세 조회.
	 *
	 * @param csoChgcallPhonDetl the CsoChgcallPhonDetl
	 * @return the CsoChgcallPhonDetl
	 * @throws SQLException the SQL exception
	 */
	public CsoChgcallPhonDetl selectCsoChgcallPhonDetl(CsoChgcallPhonDetl csoChgcallPhonDetl) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoChgcallPhonDetl", csoChgcallPhonDetl);
	}
	
	/**
	 * 고객서비스 호전환 전화번호 내역 등록.
	 *
	 * @param csoChgcallPhonDetl the CsoChgcallPhonDetl
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoChgcallPhonDetl(CsoChgcallPhonDetl csoChgcallPhonDetl) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoChgcallPhonDetl", csoChgcallPhonDetl);
	}
	
	/**
	 * 고객서비스 호전환 전화번호 내역 수정.
	 *
	 * @param csoChgcallPhonDetl the CsoChgcallPhonDetl
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoChgcallPhonDetl(CsoChgcallPhonDetl csoChgcallPhonDetl) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoChgcallPhonDetl", csoChgcallPhonDetl);
	}
	
	/**
	 * 고객서비스 호전환 전화번호 내역 삭제.
	 *
	 * @param csoChgcallPhonDetl the CsoChgcallPhonDetl
	 * @return the CsoChgcallPhonDetl
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoChgcallPhonDetl(CsoChgcallPhonDetl csoChgcallPhonDetl) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoChgcallPhonDetl", csoChgcallPhonDetl);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

