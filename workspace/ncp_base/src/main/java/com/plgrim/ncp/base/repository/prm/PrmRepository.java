
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
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;

/**
 * The Class PrmRepository.
 */
@Repository
public class PrmRepository extends AbstractRepository {
	

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
	 * 프로모션 상세 조회.
	 *
	 * @param prm the Prm
	 * @return the Prm
	 * @throws SQLException the SQL exception
	 */
	public Prm selectPrm(Prm prm) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPrm", prm);
	}
	
	/**
	 * 프로모션 등록.
	 *
	 * @param prm the Prm
	 * @throws SQLException the SQL exception
	 */
	public void insertPrm(Prm prm) {
		getSession1().insert("com.plgrim.ncp.base.insertPrm", prm);
	}
	
	/**
	 * 프로모션 수정.
	 *
	 * @param prm the Prm
	 * @throws SQLException the SQL exception
	 */
	public int updatePrm(Prm prm) {
		return getSession1().update("com.plgrim.ncp.base.updatePrm", prm);
	}
	
	/**
	 * 프로모션 삭제.
	 *
	 * @param prm the Prm
	 * @return the Prm
	 * @throws SQLException the SQL exception
	 */
	public int deletePrm(Prm prm) {
		return getSession1().delete("com.plgrim.ncp.base.deletePrm", prm);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

