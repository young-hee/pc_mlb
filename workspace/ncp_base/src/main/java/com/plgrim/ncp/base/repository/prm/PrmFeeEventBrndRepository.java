
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
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEventBrnd;

/**
 * The Class PrmFeeEventBrndRepository.
 */
@Repository
public class PrmFeeEventBrndRepository extends AbstractRepository {
	

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
	 * 프로모션 수수료 행사 브랜드 상세 조회.
	 *
	 * @param prmFeeEventBrnd the PrmFeeEventBrnd
	 * @return the PrmFeeEventBrnd
	 * @throws SQLException the SQL exception
	 */
	public PrmFeeEventBrnd selectPrmFeeEventBrnd(PrmFeeEventBrnd prmFeeEventBrnd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPrmFeeEventBrnd", prmFeeEventBrnd);
	}
	
	/**
	 * 프로모션 수수료 행사 브랜드 등록.
	 *
	 * @param prmFeeEventBrnd the PrmFeeEventBrnd
	 * @throws SQLException the SQL exception
	 */
	public void insertPrmFeeEventBrnd(PrmFeeEventBrnd prmFeeEventBrnd) {
		getSession1().insert("com.plgrim.ncp.base.insertPrmFeeEventBrnd", prmFeeEventBrnd);
	}
	
	/**
	 * 프로모션 수수료 행사 브랜드 수정.
	 *
	 * @param prmFeeEventBrnd the PrmFeeEventBrnd
	 * @throws SQLException the SQL exception
	 */
	public int updatePrmFeeEventBrnd(PrmFeeEventBrnd prmFeeEventBrnd) {
		return getSession1().update("com.plgrim.ncp.base.updatePrmFeeEventBrnd", prmFeeEventBrnd);
	}
	
	/**
	 * 프로모션 수수료 행사 브랜드 삭제.
	 *
	 * @param prmFeeEventBrnd the PrmFeeEventBrnd
	 * @return the PrmFeeEventBrnd
	 * @throws SQLException the SQL exception
	 */
	public int deletePrmFeeEventBrnd(PrmFeeEventBrnd prmFeeEventBrnd) {
		return getSession1().delete("com.plgrim.ncp.base.deletePrmFeeEventBrnd", prmFeeEventBrnd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

