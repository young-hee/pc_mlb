
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
import com.plgrim.ncp.base.entities.datasource1.god.GodCpstGodCnnc;

/**
 * The Class GodCpstGodCnncRepository.
 */
@Repository
public class GodCpstGodCnncRepository extends AbstractRepository {
	

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
	 * 상품 구성 상품 연결 상세 조회.
	 *
	 * @param godCpstGodCnnc the GodCpstGodCnnc
	 * @return the GodCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public GodCpstGodCnnc selectGodCpstGodCnnc(GodCpstGodCnnc godCpstGodCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodCpstGodCnnc", godCpstGodCnnc);
	}
	
	/**
	 * 상품 구성 상품 연결 등록.
	 *
	 * @param godCpstGodCnnc the GodCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertGodCpstGodCnnc(GodCpstGodCnnc godCpstGodCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertGodCpstGodCnnc", godCpstGodCnnc);
	}
	
	/**
	 * 상품 구성 상품 연결 수정.
	 *
	 * @param godCpstGodCnnc the GodCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateGodCpstGodCnnc(GodCpstGodCnnc godCpstGodCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateGodCpstGodCnnc", godCpstGodCnnc);
	}
	
	/**
	 * 상품 구성 상품 연결 삭제.
	 *
	 * @param godCpstGodCnnc the GodCpstGodCnnc
	 * @return the GodCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodCpstGodCnnc(GodCpstGodCnnc godCpstGodCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodCpstGodCnnc", godCpstGodCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

