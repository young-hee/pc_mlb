
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
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnCrtfcCd;

/**
 * The Class PrmCpnCrtfcCdRepository.
 */
@Repository
public class PrmCpnCrtfcCdRepository extends AbstractRepository {
	

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
	 * 프로모션 쿠폰 인증 코드 상세 조회.
	 *
	 * @param prmCpnCrtfcCd the PrmCpnCrtfcCd
	 * @return the PrmCpnCrtfcCd
	 * @throws SQLException the SQL exception
	 */
	public PrmCpnCrtfcCd selectPrmCpnCrtfcCd(PrmCpnCrtfcCd prmCpnCrtfcCd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPrmCpnCrtfcCd", prmCpnCrtfcCd);
	}
	
	/**
	 * 프로모션 쿠폰 인증 코드 등록.
	 *
	 * @param prmCpnCrtfcCd the PrmCpnCrtfcCd
	 * @throws SQLException the SQL exception
	 */
	public void insertPrmCpnCrtfcCd(PrmCpnCrtfcCd prmCpnCrtfcCd) {
		getSession1().insert("com.plgrim.ncp.base.insertPrmCpnCrtfcCd", prmCpnCrtfcCd);
	}
	
	/**
	 * 프로모션 쿠폰 인증 코드 수정.
	 *
	 * @param prmCpnCrtfcCd the PrmCpnCrtfcCd
	 * @throws SQLException the SQL exception
	 */
	public int updatePrmCpnCrtfcCd(PrmCpnCrtfcCd prmCpnCrtfcCd) {
		return getSession1().update("com.plgrim.ncp.base.updatePrmCpnCrtfcCd", prmCpnCrtfcCd);
	}
	
	/**
	 * 프로모션 쿠폰 인증 코드 삭제.
	 *
	 * @param prmCpnCrtfcCd the PrmCpnCrtfcCd
	 * @return the PrmCpnCrtfcCd
	 * @throws SQLException the SQL exception
	 */
	public int deletePrmCpnCrtfcCd(PrmCpnCrtfcCd prmCpnCrtfcCd) {
		return getSession1().delete("com.plgrim.ncp.base.deletePrmCpnCrtfcCd", prmCpnCrtfcCd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

