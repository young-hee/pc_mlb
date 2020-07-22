
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
package com.plgrim.ncp.base.repository.clm;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGod;

/**
 * The Class ClmWrhsGodRepository.
 */
@Repository
public class ClmWrhsGodRepository extends AbstractRepository {
	

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
	 * 클레임 입고 상품 상세 조회.
	 *
	 * @param clmWrhsGod the ClmWrhsGod
	 * @return the ClmWrhsGod
	 * @throws SQLException the SQL exception
	 */
	public ClmWrhsGod selectClmWrhsGod(ClmWrhsGod clmWrhsGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectClmWrhsGod", clmWrhsGod);
	}
	
	/**
	 * 클레임 입고 상품 등록.
	 *
	 * @param clmWrhsGod the ClmWrhsGod
	 * @throws SQLException the SQL exception
	 */
	public void insertClmWrhsGod(ClmWrhsGod clmWrhsGod) {
		getSession1().insert("com.plgrim.ncp.base.insertClmWrhsGod", clmWrhsGod);
	}
	
	/**
	 * 클레임 입고 상품 수정.
	 *
	 * @param clmWrhsGod the ClmWrhsGod
	 * @throws SQLException the SQL exception
	 */
	public int updateClmWrhsGod(ClmWrhsGod clmWrhsGod) {
		return getSession1().update("com.plgrim.ncp.base.updateClmWrhsGod", clmWrhsGod);
	}
	
	/**
	 * 클레임 입고 상품 삭제.
	 *
	 * @param clmWrhsGod the ClmWrhsGod
	 * @return the ClmWrhsGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteClmWrhsGod(ClmWrhsGod clmWrhsGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteClmWrhsGod", clmWrhsGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

