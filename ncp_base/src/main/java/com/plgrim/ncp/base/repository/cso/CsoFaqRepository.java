
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoFaq;

/**
 * The Class CsoFaqRepository.
 */
@Repository
public class CsoFaqRepository extends AbstractRepository {
	

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
	 * 고객서비스 FAQ 상세 조회.
	 *
	 * @param csoFaq the CsoFaq
	 * @return the CsoFaq
	 * @throws SQLException the SQL exception
	 */
	public CsoFaq selectCsoFaq(CsoFaq csoFaq) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoFaq", csoFaq);
	}
	
	/**
	 * 고객서비스 FAQ 등록.
	 *
	 * @param csoFaq the CsoFaq
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoFaq(CsoFaq csoFaq) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoFaq", csoFaq);
	}
	
	/**
	 * 고객서비스 FAQ 수정.
	 *
	 * @param csoFaq the CsoFaq
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoFaq(CsoFaq csoFaq) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoFaq", csoFaq);
	}
	
	/**
	 * 고객서비스 FAQ 삭제.
	 *
	 * @param csoFaq the CsoFaq
	 * @return the CsoFaq
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoFaq(CsoFaq csoFaq) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoFaq", csoFaq);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

