
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
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplPd;

/**
 * The Class PrmAplPdRepository.
 */
@Repository
public class PrmAplPdRepository extends AbstractRepository {
	

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
	 * 프로모션 적용 기간 상세 조회.
	 *
	 * @param prmAplPd the PrmAplPd
	 * @return the PrmAplPd
	 * @throws SQLException the SQL exception
	 */
	public PrmAplPd selectPrmAplPd(PrmAplPd prmAplPd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPrmAplPd", prmAplPd);
	}
	
	/**
	 * 프로모션 적용 기간 등록.
	 *
	 * @param prmAplPd the PrmAplPd
	 * @throws SQLException the SQL exception
	 */
	public void insertPrmAplPd(PrmAplPd prmAplPd) {
		getSession1().insert("com.plgrim.ncp.base.insertPrmAplPd", prmAplPd);
	}
	
	/**
	 * 프로모션 적용 기간 수정.
	 *
	 * @param prmAplPd the PrmAplPd
	 * @throws SQLException the SQL exception
	 */
	public int updatePrmAplPd(PrmAplPd prmAplPd) {
		return getSession1().update("com.plgrim.ncp.base.updatePrmAplPd", prmAplPd);
	}
	
	/**
	 * 프로모션 적용 기간 삭제.
	 *
	 * @param prmAplPd the PrmAplPd
	 * @return the PrmAplPd
	 * @throws SQLException the SQL exception
	 */
	public int deletePrmAplPd(PrmAplPd prmAplPd) {
		return getSession1().delete("com.plgrim.ncp.base.deletePrmAplPd", prmAplPd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

