
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
import com.plgrim.ncp.base.entities.datasource1.god.GodLndrDscr;

/**
 * The Class GodLndrDscrRepository.
 */
@Repository
public class GodLndrDscrRepository extends AbstractRepository {
	

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
	 * 상품 세탁 설명 상세 조회.
	 *
	 * @param godLndrDscr the GodLndrDscr
	 * @return the GodLndrDscr
	 * @throws SQLException the SQL exception
	 */
	public GodLndrDscr selectGodLndrDscr(GodLndrDscr godLndrDscr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodLndrDscr", godLndrDscr);
	}
	
	/**
	 * 상품 세탁 설명 등록.
	 *
	 * @param godLndrDscr the GodLndrDscr
	 * @throws SQLException the SQL exception
	 */
	public void insertGodLndrDscr(GodLndrDscr godLndrDscr) {
		getSession1().insert("com.plgrim.ncp.base.insertGodLndrDscr", godLndrDscr);
	}
	
	/**
	 * 상품 세탁 설명 수정.
	 *
	 * @param godLndrDscr the GodLndrDscr
	 * @throws SQLException the SQL exception
	 */
	public int updateGodLndrDscr(GodLndrDscr godLndrDscr) {
		return getSession1().update("com.plgrim.ncp.base.updateGodLndrDscr", godLndrDscr);
	}
	
	/**
	 * 상품 세탁 설명 삭제.
	 *
	 * @param godLndrDscr the GodLndrDscr
	 * @return the GodLndrDscr
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodLndrDscr(GodLndrDscr godLndrDscr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodLndrDscr", godLndrDscr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

