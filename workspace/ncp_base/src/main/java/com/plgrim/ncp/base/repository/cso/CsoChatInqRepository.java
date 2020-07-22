
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoChatInq;

/**
 * The Class CsoChatInqRepository.
 */
@Repository
public class CsoChatInqRepository extends AbstractRepository {
	

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
	 * 고객서비스 채팅 문의 상세 조회.
	 *
	 * @param csoChatInq the CsoChatInq
	 * @return the CsoChatInq
	 * @throws SQLException the SQL exception
	 */
	public CsoChatInq selectCsoChatInq(CsoChatInq csoChatInq) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoChatInq", csoChatInq);
	}
	
	/**
	 * 고객서비스 채팅 문의 등록.
	 *
	 * @param csoChatInq the CsoChatInq
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoChatInq(CsoChatInq csoChatInq) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoChatInq", csoChatInq);
	}
	
	/**
	 * 고객서비스 채팅 문의 수정.
	 *
	 * @param csoChatInq the CsoChatInq
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoChatInq(CsoChatInq csoChatInq) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoChatInq", csoChatInq);
	}
	
	/**
	 * 고객서비스 채팅 문의 삭제.
	 *
	 * @param csoChatInq the CsoChatInq
	 * @return the CsoChatInq
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoChatInq(CsoChatInq csoChatInq) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoChatInq", csoChatInq);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

