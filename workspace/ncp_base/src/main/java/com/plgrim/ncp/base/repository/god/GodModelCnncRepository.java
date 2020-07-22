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
 * Neps(양태훈)			20150713                      
 */
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodModelCnnc;

/**
 * The Class GodModelCnncRepository.
 */
@Repository
public class GodModelCnncRepository extends AbstractRepository {
	

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
	 * 상품 모델 연결 상세 조회.
	 *
	 * @param godModelCnnc the GodModelCnnc
	 * @return the GodModelCnnc
	 * @throws SQLException the SQL exception
	 */
	public GodModelCnnc selectGodModelCnnc(GodModelCnnc godModelCnnc) throws Exception {
		GodModelCnnc result = getSession1().selectOne(
		        "com.plgrim.ncp.base.selectGodModelCnnc", godModelCnnc);
		return result;
	}
	
	/**
	 * 상품 모델 연결 등록.
	 *
	 * @param godModelCnnc the GodModelCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertGodModelCnnc(GodModelCnnc godModelCnnc) throws Exception {
		getSession1().insert("com.plgrim.ncp.base.insertGodModelCnnc", godModelCnnc);
	}
	
	/**
	 * 상품 모델 연결 수정.
	 *
	 * @param godModelCnnc the GodModelCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateGodModelCnnc(GodModelCnnc godModelCnnc) throws Exception {
		//업데이트 후에 데이터를 다시 조회 한다.
		return getSession1().update("com.plgrim.ncp.base.updateGodModelCnnc", godModelCnnc);
	}
	
	/**
	 * 상품 모델 연결 삭제.
	 *
	 * @param godModelCnnc the GodModelCnnc
	 * @return the GodModelCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodModelCnnc(GodModelCnnc godModelCnnc) throws Exception {
		//업데이트 후에 데이터를 다시 조회 한다.
		return getSession1().delete("com.plgrim.ncp.base.deleteGodModelCnnc", godModelCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}
