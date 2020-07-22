
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequst;

/**
 * The Class CsoJobRequstRepository.
 */
@Repository
public class CsoJobRequstRepository extends AbstractRepository {
	

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
	 * 고객서비스 업무 요청 상세 조회.
	 *
	 * @param csoJobRequst the CsoJobRequst
	 * @return the CsoJobRequst
	 * @throws SQLException the SQL exception
	 */
	public CsoJobRequst selectCsoJobRequst(CsoJobRequst csoJobRequst) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoJobRequst", csoJobRequst);
	}
	
	/**
	 * 고객서비스 업무 요청 등록.
	 *
	 * @param csoJobRequst the CsoJobRequst
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoJobRequst(CsoJobRequst csoJobRequst) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoJobRequst", csoJobRequst);
	}
	
	/**
	 * 고객서비스 업무 요청 수정.
	 *
	 * @param csoJobRequst the CsoJobRequst
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoJobRequst(CsoJobRequst csoJobRequst) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoJobRequst", csoJobRequst);
	}
	
	/**
	 * 고객서비스 업무 요청 삭제.
	 *
	 * @param csoJobRequst the CsoJobRequst
	 * @return the CsoJobRequst
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoJobRequst(CsoJobRequst csoJobRequst) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoJobRequst", csoJobRequst);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

