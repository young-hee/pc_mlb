
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
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEventExclsGod;

/**
 * The Class PrmFeeEventExclsGodRepository.
 */
@Repository
public class PrmFeeEventExclsGodRepository extends AbstractRepository {
	

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
	 * 프로모션 수수료 행사 제외 상품 상세 조회.
	 *
	 * @param prmFeeEventExclsGod the PrmFeeEventExclsGod
	 * @return the PrmFeeEventExclsGod
	 * @throws SQLException the SQL exception
	 */
	public PrmFeeEventExclsGod selectPrmFeeEventExclsGod(PrmFeeEventExclsGod prmFeeEventExclsGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPrmFeeEventExclsGod", prmFeeEventExclsGod);
	}
	
	/**
	 * 프로모션 수수료 행사 제외 상품 등록.
	 *
	 * @param prmFeeEventExclsGod the PrmFeeEventExclsGod
	 * @throws SQLException the SQL exception
	 */
	public void insertPrmFeeEventExclsGod(PrmFeeEventExclsGod prmFeeEventExclsGod) {
		getSession1().insert("com.plgrim.ncp.base.insertPrmFeeEventExclsGod", prmFeeEventExclsGod);
	}
	
	/**
	 * 프로모션 수수료 행사 제외 상품 수정.
	 *
	 * @param prmFeeEventExclsGod the PrmFeeEventExclsGod
	 * @throws SQLException the SQL exception
	 */
	public int updatePrmFeeEventExclsGod(PrmFeeEventExclsGod prmFeeEventExclsGod) {
		return getSession1().update("com.plgrim.ncp.base.updatePrmFeeEventExclsGod", prmFeeEventExclsGod);
	}
	
	/**
	 * 프로모션 수수료 행사 제외 상품 삭제.
	 *
	 * @param prmFeeEventExclsGod the PrmFeeEventExclsGod
	 * @return the PrmFeeEventExclsGod
	 * @throws SQLException the SQL exception
	 */
	public int deletePrmFeeEventExclsGod(PrmFeeEventExclsGod prmFeeEventExclsGod) {
		return getSession1().delete("com.plgrim.ncp.base.deletePrmFeeEventExclsGod", prmFeeEventExclsGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

