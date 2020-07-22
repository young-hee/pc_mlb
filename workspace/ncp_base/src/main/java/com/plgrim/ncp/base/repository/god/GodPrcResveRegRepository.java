
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
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodPrcResveReg;

/**
 * The Class GodPrcResveRegRepository.
 */
@Repository
public class GodPrcResveRegRepository extends AbstractRepository {
	

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
	 * 상품 가격 예약 등록 상세 조회.
	 *
	 * @param godPrcResveReg the GodPrcResveReg
	 * @return the GodPrcResveReg
	 * @throws SQLException the SQL exception
	 */
	public GodPrcResveReg selectGodPrcResveReg(GodPrcResveReg godPrcResveReg) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodPrcResveReg", godPrcResveReg);
	}
	
	/**
	 * 상품 가격 예약 등록 등록.
	 *
	 * @param godPrcResveReg the GodPrcResveReg
	 * @throws SQLException the SQL exception
	 */
	public void insertGodPrcResveReg(GodPrcResveReg godPrcResveReg) {
		getSession1().insert("com.plgrim.ncp.base.insertGodPrcResveReg", godPrcResveReg);
	}
	
	/**
	 * 상품 가격 예약 등록 수정.
	 *
	 * @param godPrcResveReg the GodPrcResveReg
	 * @throws SQLException the SQL exception
	 */
	public int updateGodPrcResveReg(GodPrcResveReg godPrcResveReg) {
		return getSession1().update("com.plgrim.ncp.base.updateGodPrcResveReg", godPrcResveReg);
	}
	
	/**
	 * 상품 가격 예약 등록 삭제.
	 *
	 * @param godPrcResveReg the GodPrcResveReg
	 * @return the GodPrcResveReg
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodPrcResveReg(GodPrcResveReg godPrcResveReg) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodPrcResveReg", godPrcResveReg);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

