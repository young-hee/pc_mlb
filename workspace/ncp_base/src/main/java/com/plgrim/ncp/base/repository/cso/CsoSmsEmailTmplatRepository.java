
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoSmsEmailTmplat;

/**
 * The Class CsoSmsEmailTmplatRepository.
 */
@Repository
public class CsoSmsEmailTmplatRepository extends AbstractRepository {
	

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
	 * 고객서비스 SMS 이메일 템플릿 상세 조회.
	 *
	 * @param csoSmsEmailTmplat the CsoSmsEmailTmplat
	 * @return the CsoSmsEmailTmplat
	 * @throws SQLException the SQL exception
	 */
	public CsoSmsEmailTmplat selectCsoSmsEmailTmplat(CsoSmsEmailTmplat csoSmsEmailTmplat) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoSmsEmailTmplat", csoSmsEmailTmplat);
	}
	
	/**
	 * 고객서비스 SMS 이메일 템플릿 등록.
	 *
	 * @param csoSmsEmailTmplat the CsoSmsEmailTmplat
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoSmsEmailTmplat(CsoSmsEmailTmplat csoSmsEmailTmplat) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoSmsEmailTmplat", csoSmsEmailTmplat);
	}
	
	/**
	 * 고객서비스 SMS 이메일 템플릿 수정.
	 *
	 * @param csoSmsEmailTmplat the CsoSmsEmailTmplat
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoSmsEmailTmplat(CsoSmsEmailTmplat csoSmsEmailTmplat) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoSmsEmailTmplat", csoSmsEmailTmplat);
	}
	
	/**
	 * 고객서비스 SMS 이메일 템플릿 삭제.
	 *
	 * @param csoSmsEmailTmplat the CsoSmsEmailTmplat
	 * @return the CsoSmsEmailTmplat
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoSmsEmailTmplat(CsoSmsEmailTmplat csoSmsEmailTmplat) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoSmsEmailTmplat", csoSmsEmailTmplat);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

