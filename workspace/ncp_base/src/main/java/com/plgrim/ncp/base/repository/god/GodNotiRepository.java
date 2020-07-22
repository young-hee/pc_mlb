
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
import com.plgrim.ncp.base.entities.datasource1.god.GodNoti;

/**
 * The Class GodNotiRepository.
 */
@Repository
public class GodNotiRepository extends AbstractRepository {
	

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
	 * 상품 공지 상세 조회.
	 *
	 * @param godNoti the GodNoti
	 * @return the GodNoti
	 * @throws SQLException the SQL exception
	 */
	public GodNoti selectGodNoti(GodNoti godNoti) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodNoti", godNoti);
	}
	
	/**
	 * 상품 공지 등록.
	 *
	 * @param godNoti the GodNoti
	 * @throws SQLException the SQL exception
	 */
	public void insertGodNoti(GodNoti godNoti) {
		getSession1().insert("com.plgrim.ncp.base.insertGodNoti", godNoti);
	}
	
	/**
	 * 상품 공지 수정.
	 *
	 * @param godNoti the GodNoti
	 * @throws SQLException the SQL exception
	 */
	public int updateGodNoti(GodNoti godNoti) {
		return getSession1().update("com.plgrim.ncp.base.updateGodNoti", godNoti);
	}
	
	/**
	 * 상품 공지 삭제.
	 *
	 * @param godNoti the GodNoti
	 * @return the GodNoti
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodNoti(GodNoti godNoti) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodNoti", godNoti);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

