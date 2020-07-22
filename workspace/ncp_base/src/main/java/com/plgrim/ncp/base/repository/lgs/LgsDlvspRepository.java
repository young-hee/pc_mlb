
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
package com.plgrim.ncp.base.repository.lgs;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;

/**
 * The Class LgsDlvspRepository.
 */
@Repository
public class LgsDlvspRepository extends AbstractRepository {
	

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
	 * 물류 배송지 상세 조회.
	 *
	 * @param lgsDlvsp the LgsDlvsp
	 * @return the LgsDlvsp
	 * @throws SQLException the SQL exception
	 */
	public LgsDlvsp selectLgsDlvsp(LgsDlvsp lgsDlvsp) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectLgsDlvsp", lgsDlvsp);
	}
	
	/**
	 * 물류 배송지 등록.
	 *
	 * @param lgsDlvsp the LgsDlvsp
	 * @throws SQLException the SQL exception
	 */
	public void insertLgsDlvsp(LgsDlvsp lgsDlvsp) {
		getSession1().insert("com.plgrim.ncp.base.insertLgsDlvsp", lgsDlvsp);
	}
	
	/**
	 * 물류 배송지 수정.
	 *
	 * @param lgsDlvsp the LgsDlvsp
	 * @throws SQLException the SQL exception
	 */
	public int updateLgsDlvsp(LgsDlvsp lgsDlvsp) {
		return getSession1().update("com.plgrim.ncp.base.updateLgsDlvsp", lgsDlvsp);
	}
	
	/**
	 * 물류 배송지 삭제.
	 *
	 * @param lgsDlvsp the LgsDlvsp
	 * @return the LgsDlvsp
	 * @throws SQLException the SQL exception
	 */
	public int deleteLgsDlvsp(LgsDlvsp lgsDlvsp) {
		return getSession1().delete("com.plgrim.ncp.base.deleteLgsDlvsp", lgsDlvsp);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

