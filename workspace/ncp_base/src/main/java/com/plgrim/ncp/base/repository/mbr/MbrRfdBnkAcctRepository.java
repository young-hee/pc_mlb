
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
package com.plgrim.ncp.base.repository.mbr;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRfdBnkAcct;

/**
 * The Class MbrRfdBnkAcctRepository.
 */
@Repository
public class MbrRfdBnkAcctRepository extends AbstractRepository {
	

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
	 * 회원 환불 계좌 상세 조회.
	 *
	 * @param mbrRfdBnkAcct the MbrRfdBnkAcct
	 * @return the MbrRfdBnkAcct
	 * @throws SQLException the SQL exception
	 */
	public MbrRfdBnkAcct selectMbrRfdBnkAcct(MbrRfdBnkAcct mbrRfdBnkAcct) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrRfdBnkAcct", mbrRfdBnkAcct);
	}
	
	/**
	 * 회원 환불 계좌 등록.
	 *
	 * @param mbrRfdBnkAcct the MbrRfdBnkAcct
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrRfdBnkAcct(MbrRfdBnkAcct mbrRfdBnkAcct) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrRfdBnkAcct", mbrRfdBnkAcct);
	}
	
	/**
	 * 회원 환불 계좌 수정.
	 *
	 * @param mbrRfdBnkAcct the MbrRfdBnkAcct
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrRfdBnkAcct(MbrRfdBnkAcct mbrRfdBnkAcct) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrRfdBnkAcct", mbrRfdBnkAcct);
	}
	
	/**
	 * 회원 환불 계좌 삭제.
	 *
	 * @param mbrRfdBnkAcct the MbrRfdBnkAcct
	 * @return the MbrRfdBnkAcct
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrRfdBnkAcct(MbrRfdBnkAcct mbrRfdBnkAcct) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrRfdBnkAcct", mbrRfdBnkAcct);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

