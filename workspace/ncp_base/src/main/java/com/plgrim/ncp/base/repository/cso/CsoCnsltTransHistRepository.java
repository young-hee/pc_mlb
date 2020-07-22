
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTransHist;

/**
 * The Class CsoCnsltTransHistRepository.
 */
@Repository
public class CsoCnsltTransHistRepository extends AbstractRepository {
	

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
	 * 고객서비스 상담 이관 이력 상세 조회.
	 *
	 * @param csoCnsltTransHist the CsoCnsltTransHist
	 * @return the CsoCnsltTransHist
	 * @throws SQLException the SQL exception
	 */
	public CsoCnsltTransHist selectCsoCnsltTransHist(CsoCnsltTransHist csoCnsltTransHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoCnsltTransHist", csoCnsltTransHist);
	}
	
	/**
	 * 고객서비스 상담 이관 이력 등록.
	 *
	 * @param csoCnsltTransHist the CsoCnsltTransHist
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoCnsltTransHist(CsoCnsltTransHist csoCnsltTransHist) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoCnsltTransHist", csoCnsltTransHist);
	}
	
	/**
	 * 고객서비스 상담 이관 이력 수정.
	 *
	 * @param csoCnsltTransHist the CsoCnsltTransHist
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoCnsltTransHist(CsoCnsltTransHist csoCnsltTransHist) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoCnsltTransHist", csoCnsltTransHist);
	}
	
	/**
	 * 고객서비스 상담 이관 이력 삭제.
	 *
	 * @param csoCnsltTransHist the CsoCnsltTransHist
	 * @return the CsoCnsltTransHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoCnsltTransHist(CsoCnsltTransHist csoCnsltTransHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoCnsltTransHist", csoCnsltTransHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

