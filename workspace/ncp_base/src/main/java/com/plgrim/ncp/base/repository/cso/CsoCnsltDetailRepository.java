
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetail;

/**
 * The Class CsoCnsltDetailRepository.
 */
@Repository
public class CsoCnsltDetailRepository extends AbstractRepository {
	

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
	 * 고객서비스 상담 상세 상세 조회.
	 *
	 * @param csoCnsltDetail the CsoCnsltDetail
	 * @return the CsoCnsltDetail
	 * @throws SQLException the SQL exception
	 */
	public CsoCnsltDetail selectCsoCnsltDetail(CsoCnsltDetail csoCnsltDetail) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoCnsltDetail", csoCnsltDetail);
	}
	
	/**
	 * 고객서비스 상담 상세 등록.
	 *
	 * @param csoCnsltDetail the CsoCnsltDetail
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoCnsltDetail(CsoCnsltDetail csoCnsltDetail) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoCnsltDetail", csoCnsltDetail);
	}
	
	/**
	 * 고객서비스 상담 상세 수정.
	 *
	 * @param csoCnsltDetail the CsoCnsltDetail
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoCnsltDetail(CsoCnsltDetail csoCnsltDetail) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoCnsltDetail", csoCnsltDetail);
	}
	
	/**
	 * 고객서비스 상담 상세 삭제.
	 *
	 * @param csoCnsltDetail the CsoCnsltDetail
	 * @return the CsoCnsltDetail
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoCnsltDetail(CsoCnsltDetail csoCnsltDetail) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoCnsltDetail", csoCnsltDetail);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

