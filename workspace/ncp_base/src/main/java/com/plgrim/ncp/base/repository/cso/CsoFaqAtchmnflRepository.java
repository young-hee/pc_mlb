
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoFaqAtchmnfl;

/**
 * The Class CsoFaqAtchmnflRepository.
 */
@Repository
public class CsoFaqAtchmnflRepository extends AbstractRepository {
	

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
	 * 고객서비스 FAQ 첨부파일 상세 조회.
	 *
	 * @param csoFaqAtchmnfl the CsoFaqAtchmnfl
	 * @return the CsoFaqAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public CsoFaqAtchmnfl selectCsoFaqAtchmnfl(CsoFaqAtchmnfl csoFaqAtchmnfl) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoFaqAtchmnfl", csoFaqAtchmnfl);
	}
	
	/**
	 * 고객서비스 FAQ 첨부파일 등록.
	 *
	 * @param csoFaqAtchmnfl the CsoFaqAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoFaqAtchmnfl(CsoFaqAtchmnfl csoFaqAtchmnfl) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoFaqAtchmnfl", csoFaqAtchmnfl);
	}
	
	/**
	 * 고객서비스 FAQ 첨부파일 수정.
	 *
	 * @param csoFaqAtchmnfl the CsoFaqAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoFaqAtchmnfl(CsoFaqAtchmnfl csoFaqAtchmnfl) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoFaqAtchmnfl", csoFaqAtchmnfl);
	}
	
	/**
	 * 고객서비스 FAQ 첨부파일 삭제.
	 *
	 * @param csoFaqAtchmnfl the CsoFaqAtchmnfl
	 * @return the CsoFaqAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoFaqAtchmnfl(CsoFaqAtchmnfl csoFaqAtchmnfl) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoFaqAtchmnfl", csoFaqAtchmnfl);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

