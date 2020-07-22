
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
 * Generator(Generator)		2018-06-22                      
 */
package com.plgrim.ncp.base.repository.lgs;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;

/**
 * The Class LgsDlivyDrctGodRepository.
 */
@Repository
public class LgsDlivyDrctGodRepository extends AbstractRepository {
	

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
	 * 물류 출고지시 상품 상세 조회.
	 *
	 * @param lgsDlivyDrctGod the LgsDlivyDrctGod
	 * @return the LgsDlivyDrctGod
	 * @throws SQLException the SQL exception
	 */
	public LgsDlivyDrctGod selectLgsDlivyDrctGod(LgsDlivyDrctGod lgsDlivyDrctGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectLgsDlivyDrctGod", lgsDlivyDrctGod);
	}
	
	/**
	 * 물류 출고지시 상품 등록.
	 *
	 * @param lgsDlivyDrctGod the LgsDlivyDrctGod
	 * @throws SQLException the SQL exception
	 */
	public void insertLgsDlivyDrctGod(LgsDlivyDrctGod lgsDlivyDrctGod) {
		getSession1().insert("com.plgrim.ncp.base.insertLgsDlivyDrctGod", lgsDlivyDrctGod);
	}
	
	/**
	 * 물류 출고지시 상품 수정.
	 *
	 * @param lgsDlivyDrctGod the LgsDlivyDrctGod
	 * @throws SQLException the SQL exception
	 */
	public int updateLgsDlivyDrctGod(LgsDlivyDrctGod lgsDlivyDrctGod) {
		return getSession1().update("com.plgrim.ncp.base.updateLgsDlivyDrctGod", lgsDlivyDrctGod);
	}
	
	/**
	 * 물류 출고지시 상품 삭제.
	 *
	 * @param lgsDlivyDrctGod the LgsDlivyDrctGod
	 * @return the LgsDlivyDrctGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteLgsDlivyDrctGod(LgsDlivyDrctGod lgsDlivyDrctGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteLgsDlivyDrctGod", lgsDlivyDrctGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

