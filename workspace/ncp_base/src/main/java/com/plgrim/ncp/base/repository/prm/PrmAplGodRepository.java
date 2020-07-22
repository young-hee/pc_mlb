
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
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplGod;

/**
 * The Class PrmAplGodRepository.
 */
@Repository
public class PrmAplGodRepository extends AbstractRepository {
	

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
	 * 프로모션 적용 상품 상세 조회.
	 *
	 * @param prmAplGod the PrmAplGod
	 * @return the PrmAplGod
	 * @throws SQLException the SQL exception
	 */
	public PrmAplGod selectPrmAplGod(PrmAplGod prmAplGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPrmAplGod", prmAplGod);
	}
	
	/**
	 * 프로모션 적용 상품 등록.
	 *
	 * @param prmAplGod the PrmAplGod
	 * @throws SQLException the SQL exception
	 */
	public void insertPrmAplGod(PrmAplGod prmAplGod) {
		getSession1().insert("com.plgrim.ncp.base.insertPrmAplGod", prmAplGod);
	}
	
	/**
	 * 프로모션 적용 상품 수정.
	 *
	 * @param prmAplGod the PrmAplGod
	 * @throws SQLException the SQL exception
	 */
	public int updatePrmAplGod(PrmAplGod prmAplGod) {
		return getSession1().update("com.plgrim.ncp.base.updatePrmAplGod", prmAplGod);
	}
	
	/**
	 * 프로모션 적용 상품 삭제.
	 *
	 * @param prmAplGod the PrmAplGod
	 * @return the PrmAplGod
	 * @throws SQLException the SQL exception
	 */
	public int deletePrmAplGod(PrmAplGod prmAplGod) {
		return getSession1().delete("com.plgrim.ncp.base.deletePrmAplGod", prmAplGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

