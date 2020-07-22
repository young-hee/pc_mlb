
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetailHist;

/**
 * The Class CsoCnsltDetailHistRepository.
 */
@Repository
public class CsoCnsltDetailHistRepository extends AbstractRepository {
	

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
	 * 고객서비스 상담 상세 이력 상세 조회.
	 *
	 * @param csoCnsltDetailHist the CsoCnsltDetailHist
	 * @return the CsoCnsltDetailHist
	 * @throws SQLException the SQL exception
	 */
	public CsoCnsltDetailHist selectCsoCnsltDetailHist(CsoCnsltDetailHist csoCnsltDetailHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoCnsltDetailHist", csoCnsltDetailHist);
	}
	
	/**
	 * 고객서비스 상담 상세 이력 등록.
	 *
	 * @param csoCnsltDetailHist the CsoCnsltDetailHist
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoCnsltDetailHist(CsoCnsltDetailHist csoCnsltDetailHist) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoCnsltDetailHist", csoCnsltDetailHist);
	}
	
	/**
	 * 고객서비스 상담 상세 이력 수정.
	 *
	 * @param csoCnsltDetailHist the CsoCnsltDetailHist
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoCnsltDetailHist(CsoCnsltDetailHist csoCnsltDetailHist) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoCnsltDetailHist", csoCnsltDetailHist);
	}
	
	/**
	 * 고객서비스 상담 상세 이력 삭제.
	 *
	 * @param csoCnsltDetailHist the CsoCnsltDetailHist
	 * @return the CsoCnsltDetailHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoCnsltDetailHist(CsoCnsltDetailHist csoCnsltDetailHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoCnsltDetailHist", csoCnsltDetailHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

