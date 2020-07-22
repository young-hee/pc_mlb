
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoPromscl;

/**
 * The Class CsoPromsclRepository.
 */
@Repository
public class CsoPromsclRepository extends AbstractRepository {
	

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
	 * 고객서비스 약속콜 상세 조회.
	 *
	 * @param csoPromscl the CsoPromscl
	 * @return the CsoPromscl
	 * @throws SQLException the SQL exception
	 */
	public CsoPromscl selectCsoPromscl(CsoPromscl csoPromscl) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoPromscl", csoPromscl);
	}
	
	/**
	 * 고객서비스 약속콜 등록.
	 *
	 * @param csoPromscl the CsoPromscl
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoPromscl(CsoPromscl csoPromscl) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoPromscl", csoPromscl);
	}
	
	/**
	 * 고객서비스 약속콜 수정.
	 *
	 * @param csoPromscl the CsoPromscl
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoPromscl(CsoPromscl csoPromscl) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoPromscl", csoPromscl);
	}
	
	/**
	 * 고객서비스 약속콜 삭제.
	 *
	 * @param csoPromscl the CsoPromscl
	 * @return the CsoPromscl
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoPromscl(CsoPromscl csoPromscl) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoPromscl", csoPromscl);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

