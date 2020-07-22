
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
package com.plgrim.ncp.base.repository.prm;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmLang;

/**
 * The Class PrmLangRepository.
 */
@Repository
public class PrmLangRepository extends AbstractRepository {
	

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
	 * 프로모션 언어 상세 조회.
	 *
	 * @param prmLang the PrmLang
	 * @return the PrmLang
	 * @throws SQLException the SQL exception
	 */
	public PrmLang selectPrmLang(PrmLang prmLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPrmLang", prmLang);
	}
	
	/**
	 * 프로모션 언어 등록.
	 *
	 * @param prmLang the PrmLang
	 * @throws SQLException the SQL exception
	 */
	public void insertPrmLang(PrmLang prmLang) {
		getSession1().insert("com.plgrim.ncp.base.insertPrmLang", prmLang);
	}
	
	/**
	 * 프로모션 언어 수정.
	 *
	 * @param prmLang the PrmLang
	 * @throws SQLException the SQL exception
	 */
	public int updatePrmLang(PrmLang prmLang) {
		return getSession1().update("com.plgrim.ncp.base.updatePrmLang", prmLang);
	}
	
	/**
	 * 프로모션 언어 삭제.
	 *
	 * @param prmLang the PrmLang
	 * @return the PrmLang
	 * @throws SQLException the SQL exception
	 */
	public int deletePrmLang(PrmLang prmLang) {
		return getSession1().delete("com.plgrim.ncp.base.deletePrmLang", prmLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

