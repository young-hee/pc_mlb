
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
import com.plgrim.ncp.base.entities.datasource1.prm.PrmBundleDcCnd;

/**
 * The Class PrmBundleDcCndRepository.
 */
@Repository
public class PrmBundleDcCndRepository extends AbstractRepository {
	

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
	 * 프로모션 묶음 할인 조건 상세 조회.
	 *
	 * @param prmBundleDcCnd the PrmBundleDcCnd
	 * @return the PrmBundleDcCnd
	 * @throws SQLException the SQL exception
	 */
	public PrmBundleDcCnd selectPrmBundleDcCnd(PrmBundleDcCnd prmBundleDcCnd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPrmBundleDcCnd", prmBundleDcCnd);
	}
	
	/**
	 * 프로모션 묶음 할인 조건 등록.
	 *
	 * @param prmBundleDcCnd the PrmBundleDcCnd
	 * @throws SQLException the SQL exception
	 */
	public void insertPrmBundleDcCnd(PrmBundleDcCnd prmBundleDcCnd) {
		getSession1().insert("com.plgrim.ncp.base.insertPrmBundleDcCnd", prmBundleDcCnd);
	}
	
	/**
	 * 프로모션 묶음 할인 조건 수정.
	 *
	 * @param prmBundleDcCnd the PrmBundleDcCnd
	 * @throws SQLException the SQL exception
	 */
	public int updatePrmBundleDcCnd(PrmBundleDcCnd prmBundleDcCnd) {
		return getSession1().update("com.plgrim.ncp.base.updatePrmBundleDcCnd", prmBundleDcCnd);
	}
	
	/**
	 * 프로모션 묶음 할인 조건 삭제.
	 *
	 * @param prmBundleDcCnd the PrmBundleDcCnd
	 * @return the PrmBundleDcCnd
	 * @throws SQLException the SQL exception
	 */
	public int deletePrmBundleDcCnd(PrmBundleDcCnd prmBundleDcCnd) {
		return getSession1().delete("com.plgrim.ncp.base.deletePrmBundleDcCnd", prmBundleDcCnd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

