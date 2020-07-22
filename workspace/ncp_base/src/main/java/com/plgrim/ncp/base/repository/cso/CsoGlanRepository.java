
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGlan;

/**
 * The Class CsoGlanRepository.
 */
@Repository
public class CsoGlanRepository extends AbstractRepository {
	

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
	 * 고객서비스 바로가기 상세 조회.
	 *
	 * @param csoGlan the CsoGlan
	 * @return the CsoGlan
	 * @throws SQLException the SQL exception
	 */
	public CsoGlan selectCsoGlan(CsoGlan csoGlan) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoGlan", csoGlan);
	}
	
	/**
	 * 고객서비스 바로가기 등록.
	 *
	 * @param csoGlan the CsoGlan
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoGlan(CsoGlan csoGlan) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoGlan", csoGlan);
	}
	
	/**
	 * 고객서비스 바로가기 수정.
	 *
	 * @param csoGlan the CsoGlan
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoGlan(CsoGlan csoGlan) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoGlan", csoGlan);
	}
	
	/**
	 * 고객서비스 바로가기 삭제.
	 *
	 * @param csoGlan the CsoGlan
	 * @return the CsoGlan
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoGlan(CsoGlan csoGlan) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoGlan", csoGlan);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

