
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
 * Generator(Generator)		2019-11-18                      
 */
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiDspCtgryCnnc;

/**
 * The Class GodNotiDspCtgryCnncRepository.
 */
@Repository
public class GodNotiDspCtgryCnncRepository extends AbstractRepository {
	

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
	 * 상품 공지 전시 카테고리 연결 상세 조회.
	 *
	 * @param godNotiDspCtgryCnnc the GodNotiDspCtgryCnnc
	 * @return the GodNotiDspCtgryCnnc
	 * @throws SQLException the SQL exception
	 */
	public GodNotiDspCtgryCnnc selectGodNotiDspCtgryCnnc(GodNotiDspCtgryCnnc godNotiDspCtgryCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodNotiDspCtgryCnnc", godNotiDspCtgryCnnc);
	}
	
	/**
	 * 상품 공지 전시 카테고리 연결 등록.
	 *
	 * @param godNotiDspCtgryCnnc the GodNotiDspCtgryCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertGodNotiDspCtgryCnnc(GodNotiDspCtgryCnnc godNotiDspCtgryCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertGodNotiDspCtgryCnnc", godNotiDspCtgryCnnc);
	}
	
	/**
	 * 상품 공지 전시 카테고리 연결 수정.
	 *
	 * @param godNotiDspCtgryCnnc the GodNotiDspCtgryCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateGodNotiDspCtgryCnnc(GodNotiDspCtgryCnnc godNotiDspCtgryCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateGodNotiDspCtgryCnnc", godNotiDspCtgryCnnc);
	}
	
	/**
	 * 상품 공지 전시 카테고리 연결 삭제.
	 *
	 * @param godNotiDspCtgryCnnc the GodNotiDspCtgryCnnc
	 * @return the GodNotiDspCtgryCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodNotiDspCtgryCnnc(GodNotiDspCtgryCnnc godNotiDspCtgryCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodNotiDspCtgryCnnc", godNotiDspCtgryCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

