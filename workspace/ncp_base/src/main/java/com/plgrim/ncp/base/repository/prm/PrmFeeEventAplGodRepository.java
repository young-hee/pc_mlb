
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
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEventAplGod;

/**
 * The Class PrmFeeEventAplGodRepository.
 */
@Repository
public class PrmFeeEventAplGodRepository extends AbstractRepository {
	

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
	 * 프로모션 수수료 행사 적용 상품 상세 조회.
	 *
	 * @param prmFeeEventAplGod the PrmFeeEventAplGod
	 * @return the PrmFeeEventAplGod
	 * @throws SQLException the SQL exception
	 */
	public PrmFeeEventAplGod selectPrmFeeEventAplGod(PrmFeeEventAplGod prmFeeEventAplGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPrmFeeEventAplGod", prmFeeEventAplGod);
	}
	
	/**
	 * 프로모션 수수료 행사 적용 상품 등록.
	 *
	 * @param prmFeeEventAplGod the PrmFeeEventAplGod
	 * @throws SQLException the SQL exception
	 */
	public void insertPrmFeeEventAplGod(PrmFeeEventAplGod prmFeeEventAplGod) {
		getSession1().insert("com.plgrim.ncp.base.insertPrmFeeEventAplGod", prmFeeEventAplGod);
	}
	
	/**
	 * 프로모션 수수료 행사 적용 상품 수정.
	 *
	 * @param prmFeeEventAplGod the PrmFeeEventAplGod
	 * @throws SQLException the SQL exception
	 */
	public int updatePrmFeeEventAplGod(PrmFeeEventAplGod prmFeeEventAplGod) {
		return getSession1().update("com.plgrim.ncp.base.updatePrmFeeEventAplGod", prmFeeEventAplGod);
	}
	
	/**
	 * 프로모션 수수료 행사 적용 상품 삭제.
	 *
	 * @param prmFeeEventAplGod the PrmFeeEventAplGod
	 * @return the PrmFeeEventAplGod
	 * @throws SQLException the SQL exception
	 */
	public int deletePrmFeeEventAplGod(PrmFeeEventAplGod prmFeeEventAplGod) {
		return getSession1().delete("com.plgrim.ncp.base.deletePrmFeeEventAplGod", prmFeeEventAplGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

