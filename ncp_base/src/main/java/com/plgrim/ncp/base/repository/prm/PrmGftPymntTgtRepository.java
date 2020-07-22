
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
import com.plgrim.ncp.base.entities.datasource1.prm.PrmGftPymntTgt;

/**
 * The Class PrmGftPymntTgtRepository.
 */
@Repository
public class PrmGftPymntTgtRepository extends AbstractRepository {
	

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
	 * 프로모션 사은품 지급 대상 상세 조회.
	 *
	 * @param prmGftPymntTgt the PrmGftPymntTgt
	 * @return the PrmGftPymntTgt
	 * @throws SQLException the SQL exception
	 */
	public PrmGftPymntTgt selectPrmGftPymntTgt(PrmGftPymntTgt prmGftPymntTgt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPrmGftPymntTgt", prmGftPymntTgt);
	}
	
	/**
	 * 프로모션 사은품 지급 대상 등록.
	 *
	 * @param prmGftPymntTgt the PrmGftPymntTgt
	 * @throws SQLException the SQL exception
	 */
	public void insertPrmGftPymntTgt(PrmGftPymntTgt prmGftPymntTgt) {
		getSession1().insert("com.plgrim.ncp.base.insertPrmGftPymntTgt", prmGftPymntTgt);
	}
	
	/**
	 * 프로모션 사은품 지급 대상 수정.
	 *
	 * @param prmGftPymntTgt the PrmGftPymntTgt
	 * @throws SQLException the SQL exception
	 */
	public int updatePrmGftPymntTgt(PrmGftPymntTgt prmGftPymntTgt) {
		return getSession1().update("com.plgrim.ncp.base.updatePrmGftPymntTgt", prmGftPymntTgt);
	}
	
	/**
	 * 프로모션 사은품 지급 대상 삭제.
	 *
	 * @param prmGftPymntTgt the PrmGftPymntTgt
	 * @return the PrmGftPymntTgt
	 * @throws SQLException the SQL exception
	 */
	public int deletePrmGftPymntTgt(PrmGftPymntTgt prmGftPymntTgt) {
		return getSession1().delete("com.plgrim.ncp.base.deletePrmGftPymntTgt", prmGftPymntTgt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

