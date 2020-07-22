
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGodInqAns;

/**
 * The Class CsoGodInqAnsRepository.
 */
@Repository
public class CsoGodInqAnsRepository extends AbstractRepository {
	

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
	 * 고객서비스 상품 문의 답변 상세 조회.
	 *
	 * @param csoGodInqAns the CsoGodInqAns
	 * @return the CsoGodInqAns
	 * @throws SQLException the SQL exception
	 */
	public CsoGodInqAns selectCsoGodInqAns(CsoGodInqAns csoGodInqAns) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoGodInqAns", csoGodInqAns);
	}
	
	/**
	 * 고객서비스 상품 문의 답변 등록.
	 *
	 * @param csoGodInqAns the CsoGodInqAns
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoGodInqAns(CsoGodInqAns csoGodInqAns) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoGodInqAns", csoGodInqAns);
	}
	
	/**
	 * 고객서비스 상품 문의 답변 수정.
	 *
	 * @param csoGodInqAns the CsoGodInqAns
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoGodInqAns(CsoGodInqAns csoGodInqAns) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoGodInqAns", csoGodInqAns);
	}
	
	/**
	 * 고객서비스 상품 문의 답변 삭제.
	 *
	 * @param csoGodInqAns the CsoGodInqAns
	 * @return the CsoGodInqAns
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoGodInqAns(CsoGodInqAns csoGodInqAns) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoGodInqAns", csoGodInqAns);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

