
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetailJobTp;

/**
 * The Class CsoCnsltDetailJobTpRepository.
 */
@Repository
public class CsoCnsltDetailJobTpRepository extends AbstractRepository {
	

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
	 * 고객서비스 상담 상세 업무 유형 상세 조회.
	 *
	 * @param csoCnsltDetailJobTp the CsoCnsltDetailJobTp
	 * @return the CsoCnsltDetailJobTp
	 * @throws SQLException the SQL exception
	 */
	public CsoCnsltDetailJobTp selectCsoCnsltDetailJobTp(CsoCnsltDetailJobTp csoCnsltDetailJobTp) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoCnsltDetailJobTp", csoCnsltDetailJobTp);
	}
	
	/**
	 * 고객서비스 상담 상세 업무 유형 등록.
	 *
	 * @param csoCnsltDetailJobTp the CsoCnsltDetailJobTp
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoCnsltDetailJobTp(CsoCnsltDetailJobTp csoCnsltDetailJobTp) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoCnsltDetailJobTp", csoCnsltDetailJobTp);
	}
	
	/**
	 * 고객서비스 상담 상세 업무 유형 수정.
	 *
	 * @param csoCnsltDetailJobTp the CsoCnsltDetailJobTp
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoCnsltDetailJobTp(CsoCnsltDetailJobTp csoCnsltDetailJobTp) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoCnsltDetailJobTp", csoCnsltDetailJobTp);
	}
	
	/**
	 * 고객서비스 상담 상세 업무 유형 삭제.
	 *
	 * @param csoCnsltDetailJobTp the CsoCnsltDetailJobTp
	 * @return the CsoCnsltDetailJobTp
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoCnsltDetailJobTp(CsoCnsltDetailJobTp csoCnsltDetailJobTp) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoCnsltDetailJobTp", csoCnsltDetailJobTp);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

