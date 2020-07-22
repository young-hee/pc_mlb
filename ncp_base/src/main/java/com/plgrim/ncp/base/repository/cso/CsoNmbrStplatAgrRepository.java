
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
 * Generator(Generator)		2018-06-08                      
 */
package com.plgrim.ncp.base.repository.cso;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoNmbrStplatAgr;

/**
 * The Class CsoNmbrStplatAgrRepository.
 */
@Repository
public class CsoNmbrStplatAgrRepository extends AbstractRepository {
	

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
	 * 고객서비스 비회원 약관 동의 상세 조회.
	 *
	 * @param csoNmbrStplatAgr the CsoNmbrStplatAgr
	 * @return the CsoNmbrStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public CsoNmbrStplatAgr selectCsoNmbrStplatAgr(CsoNmbrStplatAgr csoNmbrStplatAgr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoNmbrStplatAgr", csoNmbrStplatAgr);
	}
	
	/**
	 * 고객서비스 비회원 약관 동의 등록.
	 *
	 * @param csoNmbrStplatAgr the CsoNmbrStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoNmbrStplatAgr(CsoNmbrStplatAgr csoNmbrStplatAgr) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoNmbrStplatAgr", csoNmbrStplatAgr);
	}
	
	/**
	 * 고객서비스 비회원 약관 동의 수정.
	 *
	 * @param csoNmbrStplatAgr the CsoNmbrStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoNmbrStplatAgr(CsoNmbrStplatAgr csoNmbrStplatAgr) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoNmbrStplatAgr", csoNmbrStplatAgr);
	}
	
	/**
	 * 고객서비스 비회원 약관 동의 삭제.
	 *
	 * @param csoNmbrStplatAgr the CsoNmbrStplatAgr
	 * @return the CsoNmbrStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoNmbrStplatAgr(CsoNmbrStplatAgr csoNmbrStplatAgr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoNmbrStplatAgr", csoNmbrStplatAgr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

