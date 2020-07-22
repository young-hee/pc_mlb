/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 * jackie(jackie)			20150616                      
 */
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodPrcResveRegCnnc;

/**
 * The Class GodPrcResveRegCnncRepository.
 */
@Repository
public class GodPrcResveRegCnncRepository extends AbstractRepository {
	

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
	 * 상품 가격 예약 등록 연결 상세 조회.
	 *
	 * @param godPrcResveRegCnnc the GodPrcResveRegCnnc
	 * @return the GodPrcResveRegCnnc
	 * @throws SQLException the SQL exception
	 */
	public GodPrcResveRegCnnc selectGodPrcResveRegCnnc(GodPrcResveRegCnnc godPrcResveRegCnnc) throws Exception {
		GodPrcResveRegCnnc result = getSession1().selectOne(
		        "com.plgrim.ncp.base.selectGodPrcResveRegCnnc", godPrcResveRegCnnc);
		return result;
	}
	
	/**
	 * 상품 가격 예약 등록 연결 등록.
	 *
	 * @param godPrcResveRegCnnc the GodPrcResveRegCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertGodPrcResveRegCnnc(GodPrcResveRegCnnc godPrcResveRegCnnc) throws Exception {
		getSession1().insert("com.plgrim.ncp.base.insertGodPrcResveRegCnnc", godPrcResveRegCnnc);
	}
	
	/**
	 * 상품 가격 예약 등록 연결 수정.
	 *
	 * @param godPrcResveRegCnnc the GodPrcResveRegCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateGodPrcResveRegCnnc(GodPrcResveRegCnnc godPrcResveRegCnnc) throws Exception {
		//업데이트 후에 데이터를 다시 조회 한다.
		return getSession1().update("com.plgrim.ncp.base.updateGodPrcResveRegCnnc", godPrcResveRegCnnc);
	}
	
	/**
	 * 상품 가격 예약 등록 연결 삭제.
	 *
	 * @param godPrcResveRegCnnc the GodPrcResveRegCnnc
	 * @return the GodPrcResveRegCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodPrcResveRegCnnc(GodPrcResveRegCnnc godPrcResveRegCnnc) throws Exception {
		//업데이트 후에 데이터를 다시 조회 한다.
		return getSession1().delete("com.plgrim.ncp.base.deleteGodPrcResveRegCnnc", godPrcResveRegCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}
