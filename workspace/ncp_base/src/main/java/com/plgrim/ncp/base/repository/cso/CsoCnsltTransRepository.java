
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTrans;

/**
 * The Class CsoCnsltTransRepository.
 */
@Repository
public class CsoCnsltTransRepository extends AbstractRepository {
	

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
	 * 고객서비스 상담 이관 상세 조회.
	 *
	 * @param csoCnsltTrans the CsoCnsltTrans
	 * @return the CsoCnsltTrans
	 * @throws SQLException the SQL exception
	 */
	public CsoCnsltTrans selectCsoCnsltTrans(CsoCnsltTrans csoCnsltTrans) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoCnsltTrans", csoCnsltTrans);
	}
	
	/**
	 * 고객서비스 상담 이관 등록.
	 *
	 * @param csoCnsltTrans the CsoCnsltTrans
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoCnsltTrans(CsoCnsltTrans csoCnsltTrans) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoCnsltTrans", csoCnsltTrans);
	}
	
	/**
	 * 고객서비스 상담 이관 수정.
	 *
	 * @param csoCnsltTrans the CsoCnsltTrans
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoCnsltTrans(CsoCnsltTrans csoCnsltTrans) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoCnsltTrans", csoCnsltTrans);
	}
	
	/**
	 * 고객서비스 상담 이관 삭제.
	 *
	 * @param csoCnsltTrans the CsoCnsltTrans
	 * @return the CsoCnsltTrans
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoCnsltTrans(CsoCnsltTrans csoCnsltTrans) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoCnsltTrans", csoCnsltTrans);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

