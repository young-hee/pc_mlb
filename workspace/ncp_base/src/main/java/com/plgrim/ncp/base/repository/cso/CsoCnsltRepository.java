
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnslt;

/**
 * The Class CsoCnsltRepository.
 */
@Repository
public class CsoCnsltRepository extends AbstractRepository {
	

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
	 * 고객서비스 상담 상세 조회.
	 *
	 * @param csoCnslt the CsoCnslt
	 * @return the CsoCnslt
	 * @throws SQLException the SQL exception
	 */
	public CsoCnslt selectCsoCnslt(CsoCnslt csoCnslt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoCnslt", csoCnslt);
	}
	
	/**
	 * 고객서비스 상담 등록.
	 *
	 * @param csoCnslt the CsoCnslt
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoCnslt(CsoCnslt csoCnslt) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoCnslt", csoCnslt);
	}
	
	/**
	 * 고객서비스 상담 수정.
	 *
	 * @param csoCnslt the CsoCnslt
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoCnslt(CsoCnslt csoCnslt) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoCnslt", csoCnslt);
	}
	
	/**
	 * 고객서비스 상담 삭제.
	 *
	 * @param csoCnslt the CsoCnslt
	 * @return the CsoCnslt
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoCnslt(CsoCnslt csoCnslt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoCnslt", csoCnslt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

