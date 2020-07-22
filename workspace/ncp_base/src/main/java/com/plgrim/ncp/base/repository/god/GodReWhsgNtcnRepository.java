
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
import com.plgrim.ncp.base.entities.datasource1.god.GodReWhsgNtcn;

/**
 * The Class GodReWhsgNtcnRepository.
 */
@Repository
public class GodReWhsgNtcnRepository extends AbstractRepository {
	

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
	 * 상품 재입고 알림 상세 조회.
	 *
	 * @param godReWhsgNtcn the GodReWhsgNtcn
	 * @return the GodReWhsgNtcn
	 * @throws SQLException the SQL exception
	 */
	public GodReWhsgNtcn selectGodReWhsgNtcn(GodReWhsgNtcn godReWhsgNtcn) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodReWhsgNtcn", godReWhsgNtcn);
	}
	
	/**
	 * 상품 재입고 알림 등록.
	 *
	 * @param godReWhsgNtcn the GodReWhsgNtcn
	 * @throws SQLException the SQL exception
	 */
	public void insertGodReWhsgNtcn(GodReWhsgNtcn godReWhsgNtcn) {
		getSession1().insert("com.plgrim.ncp.base.insertGodReWhsgNtcn", godReWhsgNtcn);
	}
	
	/**
	 * 상품 재입고 알림 수정.
	 *
	 * @param godReWhsgNtcn the GodReWhsgNtcn
	 * @throws SQLException the SQL exception
	 */
	public int updateGodReWhsgNtcn(GodReWhsgNtcn godReWhsgNtcn) {
		return getSession1().update("com.plgrim.ncp.base.updateGodReWhsgNtcn", godReWhsgNtcn);
	}
	
	/**
	 * 상품 재입고 알림 삭제.
	 *
	 * @param godReWhsgNtcn the GodReWhsgNtcn
	 * @return the GodReWhsgNtcn
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodReWhsgNtcn(GodReWhsgNtcn godReWhsgNtcn) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodReWhsgNtcn", godReWhsgNtcn);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

