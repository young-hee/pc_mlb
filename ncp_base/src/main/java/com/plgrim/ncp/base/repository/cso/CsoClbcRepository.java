
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoClbc;

/**
 * The Class CsoClbcRepository.
 */
@Repository
public class CsoClbcRepository extends AbstractRepository {
	

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
	 * 고객서비스 콜백 상세 조회.
	 *
	 * @param csoClbc the CsoClbc
	 * @return the CsoClbc
	 * @throws SQLException the SQL exception
	 */
	public CsoClbc selectCsoClbc(CsoClbc csoClbc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoClbc", csoClbc);
	}
	
	/**
	 * 고객서비스 콜백 등록.
	 *
	 * @param csoClbc the CsoClbc
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoClbc(CsoClbc csoClbc) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoClbc", csoClbc);
	}
	
	/**
	 * 고객서비스 콜백 수정.
	 *
	 * @param csoClbc the CsoClbc
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoClbc(CsoClbc csoClbc) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoClbc", csoClbc);
	}
	
	/**
	 * 고객서비스 콜백 삭제.
	 *
	 * @param csoClbc the CsoClbc
	 * @return the CsoClbc
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoClbc(CsoClbc csoClbc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoClbc", csoClbc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

