
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
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiGodCnnc;

/**
 * The Class GodNotiGodCnncRepository.
 */
@Repository
public class GodNotiGodCnncRepository extends AbstractRepository {
	

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
	 * 상품 공지 상품 연결 상세 조회.
	 *
	 * @param godNotiGodCnnc the GodNotiGodCnnc
	 * @return the GodNotiGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public GodNotiGodCnnc selectGodNotiGodCnnc(GodNotiGodCnnc godNotiGodCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodNotiGodCnnc", godNotiGodCnnc);
	}
	
	/**
	 * 상품 공지 상품 연결 등록.
	 *
	 * @param godNotiGodCnnc the GodNotiGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertGodNotiGodCnnc(GodNotiGodCnnc godNotiGodCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertGodNotiGodCnnc", godNotiGodCnnc);
	}
	
	/**
	 * 상품 공지 상품 연결 수정.
	 *
	 * @param godNotiGodCnnc the GodNotiGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateGodNotiGodCnnc(GodNotiGodCnnc godNotiGodCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateGodNotiGodCnnc", godNotiGodCnnc);
	}
	
	/**
	 * 상품 공지 상품 연결 삭제.
	 *
	 * @param godNotiGodCnnc the GodNotiGodCnnc
	 * @return the GodNotiGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodNotiGodCnnc(GodNotiGodCnnc godNotiGodCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodNotiGodCnnc", godNotiGodCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

