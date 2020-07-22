
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
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplTgt;

/**
 * The Class PrmAplTgtRepository.
 */
@Repository
public class PrmAplTgtRepository extends AbstractRepository {
	

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
	 * 프로모션 적용 대상 상세 조회.
	 *
	 * @param prmAplTgt the PrmAplTgt
	 * @return the PrmAplTgt
	 * @throws SQLException the SQL exception
	 */
	public PrmAplTgt selectPrmAplTgt(PrmAplTgt prmAplTgt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPrmAplTgt", prmAplTgt);
	}
	
	/**
	 * 프로모션 적용 대상 등록.
	 *
	 * @param prmAplTgt the PrmAplTgt
	 * @throws SQLException the SQL exception
	 */
	public void insertPrmAplTgt(PrmAplTgt prmAplTgt) {
		getSession1().insert("com.plgrim.ncp.base.insertPrmAplTgt", prmAplTgt);
	}
	
	/**
	 * 프로모션 적용 대상 수정.
	 *
	 * @param prmAplTgt the PrmAplTgt
	 * @throws SQLException the SQL exception
	 */
	public int updatePrmAplTgt(PrmAplTgt prmAplTgt) {
		return getSession1().update("com.plgrim.ncp.base.updatePrmAplTgt", prmAplTgt);
	}
	
	/**
	 * 프로모션 적용 대상 삭제.
	 *
	 * @param prmAplTgt the PrmAplTgt
	 * @return the PrmAplTgt
	 * @throws SQLException the SQL exception
	 */
	public int deletePrmAplTgt(PrmAplTgt prmAplTgt) {
		return getSession1().delete("com.plgrim.ncp.base.deletePrmAplTgt", prmAplTgt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

