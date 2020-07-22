
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
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodHist;

/**
 * The Class LgsDlivyDrctGodHistRepository.
 */
@Repository
public class LgsDlivyDrctGodHistRepository extends AbstractRepository {
	

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
	 * 물류 출고지시 상품 이력 상세 조회.
	 *
	 * @param lgsDlivyDrctGodHist the LgsDlivyDrctGodHist
	 * @return the LgsDlivyDrctGodHist
	 * @throws SQLException the SQL exception
	 */
	public LgsDlivyDrctGodHist selectLgsDlivyDrctGodHist(LgsDlivyDrctGodHist lgsDlivyDrctGodHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectLgsDlivyDrctGodHist", lgsDlivyDrctGodHist);
	}
	
	/**
	 * 물류 출고지시 상품 이력 등록.
	 *
	 * @param lgsDlivyDrctGodHist the LgsDlivyDrctGodHist
	 * @throws SQLException the SQL exception
	 */
	public void insertLgsDlivyDrctGodHist(LgsDlivyDrctGodHist lgsDlivyDrctGodHist) {
		getSession1().insert("com.plgrim.ncp.base.insertLgsDlivyDrctGodHist", lgsDlivyDrctGodHist);
	}
	
	/**
	 * 물류 출고지시 상품 이력 수정.
	 *
	 * @param lgsDlivyDrctGodHist the LgsDlivyDrctGodHist
	 * @throws SQLException the SQL exception
	 */
	public int updateLgsDlivyDrctGodHist(LgsDlivyDrctGodHist lgsDlivyDrctGodHist) {
		return getSession1().update("com.plgrim.ncp.base.updateLgsDlivyDrctGodHist", lgsDlivyDrctGodHist);
	}
	
	/**
	 * 물류 출고지시 상품 이력 삭제.
	 *
	 * @param lgsDlivyDrctGodHist the LgsDlivyDrctGodHist
	 * @return the LgsDlivyDrctGodHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteLgsDlivyDrctGodHist(LgsDlivyDrctGodHist lgsDlivyDrctGodHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteLgsDlivyDrctGodHist", lgsDlivyDrctGodHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

