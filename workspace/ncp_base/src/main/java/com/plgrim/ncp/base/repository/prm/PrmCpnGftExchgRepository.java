
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
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnGftExchg;

/**
 * The Class PrmCpnGftExchgRepository.
 */
@Repository
public class PrmCpnGftExchgRepository extends AbstractRepository {
	

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
	 * 프로모션 쿠폰 사은품 교환 상세 조회.
	 *
	 * @param prmCpnGftExchg the PrmCpnGftExchg
	 * @return the PrmCpnGftExchg
	 * @throws SQLException the SQL exception
	 */
	public PrmCpnGftExchg selectPrmCpnGftExchg(PrmCpnGftExchg prmCpnGftExchg) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPrmCpnGftExchg", prmCpnGftExchg);
	}
	
	/**
	 * 프로모션 쿠폰 사은품 교환 등록.
	 *
	 * @param prmCpnGftExchg the PrmCpnGftExchg
	 * @throws SQLException the SQL exception
	 */
	public void insertPrmCpnGftExchg(PrmCpnGftExchg prmCpnGftExchg) {
		getSession1().insert("com.plgrim.ncp.base.insertPrmCpnGftExchg", prmCpnGftExchg);
	}
	
	/**
	 * 프로모션 쿠폰 사은품 교환 수정.
	 *
	 * @param prmCpnGftExchg the PrmCpnGftExchg
	 * @throws SQLException the SQL exception
	 */
	public int updatePrmCpnGftExchg(PrmCpnGftExchg prmCpnGftExchg) {
		return getSession1().update("com.plgrim.ncp.base.updatePrmCpnGftExchg", prmCpnGftExchg);
	}
	
	/**
	 * 프로모션 쿠폰 사은품 교환 삭제.
	 *
	 * @param prmCpnGftExchg the PrmCpnGftExchg
	 * @return the PrmCpnGftExchg
	 * @throws SQLException the SQL exception
	 */
	public int deletePrmCpnGftExchg(PrmCpnGftExchg prmCpnGftExchg) {
		return getSession1().delete("com.plgrim.ncp.base.deletePrmCpnGftExchg", prmCpnGftExchg);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

