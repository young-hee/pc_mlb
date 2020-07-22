
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
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;

/**
 * The Class PrmCpnRepository.
 */
@Repository
public class PrmCpnRepository extends AbstractRepository {
	

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
	 * 프로모션 쿠폰 상세 조회.
	 *
	 * @param prmCpn the PrmCpn
	 * @return the PrmCpn
	 * @throws SQLException the SQL exception
	 */
	public PrmCpn selectPrmCpn(PrmCpn prmCpn) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPrmCpn", prmCpn);
	}
	
	/**
	 * 프로모션 쿠폰 등록.
	 *
	 * @param prmCpn the PrmCpn
	 * @throws SQLException the SQL exception
	 */
	public void insertPrmCpn(PrmCpn prmCpn) {
		getSession1().insert("com.plgrim.ncp.base.insertPrmCpn", prmCpn);
	}
	
	/**
	 * 프로모션 쿠폰 수정.
	 *
	 * @param prmCpn the PrmCpn
	 * @throws SQLException the SQL exception
	 */
	public int updatePrmCpn(PrmCpn prmCpn) {
		return getSession1().update("com.plgrim.ncp.base.updatePrmCpn", prmCpn);
	}
	
	/**
	 * 프로모션 쿠폰 삭제.
	 *
	 * @param prmCpn the PrmCpn
	 * @return the PrmCpn
	 * @throws SQLException the SQL exception
	 */
	public int deletePrmCpn(PrmCpn prmCpn) {
		return getSession1().delete("com.plgrim.ncp.base.deletePrmCpn", prmCpn);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

