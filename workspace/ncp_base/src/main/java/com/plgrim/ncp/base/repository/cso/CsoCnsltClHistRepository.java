
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltClHist;

/**
 * The Class CsoCnsltClHistRepository.
 */
@Repository
public class CsoCnsltClHistRepository extends AbstractRepository {
	

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
	 * 고객서비스 상담 콜 이력 상세 조회.
	 *
	 * @param csoCnsltClHist the CsoCnsltClHist
	 * @return the CsoCnsltClHist
	 * @throws SQLException the SQL exception
	 */
	public CsoCnsltClHist selectCsoCnsltClHist(CsoCnsltClHist csoCnsltClHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoCnsltClHist", csoCnsltClHist);
	}
	
	/**
	 * 고객서비스 상담 콜 이력 등록.
	 *
	 * @param csoCnsltClHist the CsoCnsltClHist
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoCnsltClHist(CsoCnsltClHist csoCnsltClHist) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoCnsltClHist", csoCnsltClHist);
	}
	
	/**
	 * 고객서비스 상담 콜 이력 수정.
	 *
	 * @param csoCnsltClHist the CsoCnsltClHist
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoCnsltClHist(CsoCnsltClHist csoCnsltClHist) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoCnsltClHist", csoCnsltClHist);
	}
	
	/**
	 * 고객서비스 상담 콜 이력 삭제.
	 *
	 * @param csoCnsltClHist the CsoCnsltClHist
	 * @return the CsoCnsltClHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoCnsltClHist(CsoCnsltClHist csoCnsltClHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoCnsltClHist", csoCnsltClHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

