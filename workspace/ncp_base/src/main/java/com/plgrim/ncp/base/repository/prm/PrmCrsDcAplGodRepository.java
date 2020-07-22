
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
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCrsDcAplGod;

/**
 * The Class PrmCrsDcAplGodRepository.
 */
@Repository
public class PrmCrsDcAplGodRepository extends AbstractRepository {
	

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
	 * 프로모션 교차 할인 적용 상품 상세 조회.
	 *
	 * @param prmCrsDcAplGod the PrmCrsDcAplGod
	 * @return the PrmCrsDcAplGod
	 * @throws SQLException the SQL exception
	 */
	public PrmCrsDcAplGod selectPrmCrsDcAplGod(PrmCrsDcAplGod prmCrsDcAplGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPrmCrsDcAplGod", prmCrsDcAplGod);
	}
	
	/**
	 * 프로모션 교차 할인 적용 상품 등록.
	 *
	 * @param prmCrsDcAplGod the PrmCrsDcAplGod
	 * @throws SQLException the SQL exception
	 */
	public void insertPrmCrsDcAplGod(PrmCrsDcAplGod prmCrsDcAplGod) {
		getSession1().insert("com.plgrim.ncp.base.insertPrmCrsDcAplGod", prmCrsDcAplGod);
	}
	
	/**
	 * 프로모션 교차 할인 적용 상품 수정.
	 *
	 * @param prmCrsDcAplGod the PrmCrsDcAplGod
	 * @throws SQLException the SQL exception
	 */
	public int updatePrmCrsDcAplGod(PrmCrsDcAplGod prmCrsDcAplGod) {
		return getSession1().update("com.plgrim.ncp.base.updatePrmCrsDcAplGod", prmCrsDcAplGod);
	}
	
	/**
	 * 프로모션 교차 할인 적용 상품 삭제.
	 *
	 * @param prmCrsDcAplGod the PrmCrsDcAplGod
	 * @return the PrmCrsDcAplGod
	 * @throws SQLException the SQL exception
	 */
	public int deletePrmCrsDcAplGod(PrmCrsDcAplGod prmCrsDcAplGod) {
		return getSession1().delete("com.plgrim.ncp.base.deletePrmCrsDcAplGod", prmCrsDcAplGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

