
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstAtchmnfl;

/**
 * The Class CsoJobRequstAtchmnflRepository.
 */
@Repository
public class CsoJobRequstAtchmnflRepository extends AbstractRepository {
	

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
	 * 고객서비스 업무 요청 첨부파일 상세 조회.
	 *
	 * @param csoJobRequstAtchmnfl the CsoJobRequstAtchmnfl
	 * @return the CsoJobRequstAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public CsoJobRequstAtchmnfl selectCsoJobRequstAtchmnfl(CsoJobRequstAtchmnfl csoJobRequstAtchmnfl) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoJobRequstAtchmnfl", csoJobRequstAtchmnfl);
	}
	
	/**
	 * 고객서비스 업무 요청 첨부파일 등록.
	 *
	 * @param csoJobRequstAtchmnfl the CsoJobRequstAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoJobRequstAtchmnfl(CsoJobRequstAtchmnfl csoJobRequstAtchmnfl) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoJobRequstAtchmnfl", csoJobRequstAtchmnfl);
	}
	
	/**
	 * 고객서비스 업무 요청 첨부파일 수정.
	 *
	 * @param csoJobRequstAtchmnfl the CsoJobRequstAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoJobRequstAtchmnfl(CsoJobRequstAtchmnfl csoJobRequstAtchmnfl) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoJobRequstAtchmnfl", csoJobRequstAtchmnfl);
	}
	
	/**
	 * 고객서비스 업무 요청 첨부파일 삭제.
	 *
	 * @param csoJobRequstAtchmnfl the CsoJobRequstAtchmnfl
	 * @return the CsoJobRequstAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoJobRequstAtchmnfl(CsoJobRequstAtchmnfl csoJobRequstAtchmnfl) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoJobRequstAtchmnfl", csoJobRequstAtchmnfl);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

