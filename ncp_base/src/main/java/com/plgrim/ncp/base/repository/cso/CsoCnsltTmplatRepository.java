
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTmplat;

/**
 * The Class CsoCnsltTmplatRepository.
 */
@Repository
public class CsoCnsltTmplatRepository extends AbstractRepository {
	

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
	 * 고객서비스 상담 템플릿 상세 조회.
	 *
	 * @param csoCnsltTmplat the CsoCnsltTmplat
	 * @return the CsoCnsltTmplat
	 * @throws SQLException the SQL exception
	 */
	public CsoCnsltTmplat selectCsoCnsltTmplat(CsoCnsltTmplat csoCnsltTmplat) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoCnsltTmplat", csoCnsltTmplat);
	}
	
	/**
	 * 고객서비스 상담 템플릿 등록.
	 *
	 * @param csoCnsltTmplat the CsoCnsltTmplat
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoCnsltTmplat(CsoCnsltTmplat csoCnsltTmplat) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoCnsltTmplat", csoCnsltTmplat);
	}
	
	/**
	 * 고객서비스 상담 템플릿 수정.
	 *
	 * @param csoCnsltTmplat the CsoCnsltTmplat
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoCnsltTmplat(CsoCnsltTmplat csoCnsltTmplat) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoCnsltTmplat", csoCnsltTmplat);
	}
	
	/**
	 * 고객서비스 상담 템플릿 삭제.
	 *
	 * @param csoCnsltTmplat the CsoCnsltTmplat
	 * @return the CsoCnsltTmplat
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoCnsltTmplat(CsoCnsltTmplat csoCnsltTmplat) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoCnsltTmplat", csoCnsltTmplat);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

