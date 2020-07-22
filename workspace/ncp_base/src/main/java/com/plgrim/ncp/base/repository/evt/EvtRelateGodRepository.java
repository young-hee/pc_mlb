
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
package com.plgrim.ncp.base.repository.evt;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtRelateGod;

/**
 * The Class EvtRelateGodRepository.
 */
@Repository
public class EvtRelateGodRepository extends AbstractRepository {
	

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
	 * 이벤트 연관 상품 상세 조회.
	 *
	 * @param evtRelateGod the EvtRelateGod
	 * @return the EvtRelateGod
	 * @throws SQLException the SQL exception
	 */
	public EvtRelateGod selectEvtRelateGod(EvtRelateGod evtRelateGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtRelateGod", evtRelateGod);
	}
	
	/**
	 * 이벤트 연관 상품 등록.
	 *
	 * @param evtRelateGod the EvtRelateGod
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtRelateGod(EvtRelateGod evtRelateGod) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtRelateGod", evtRelateGod);
	}
	
	/**
	 * 이벤트 연관 상품 수정.
	 *
	 * @param evtRelateGod the EvtRelateGod
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtRelateGod(EvtRelateGod evtRelateGod) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtRelateGod", evtRelateGod);
	}
	
	/**
	 * 이벤트 연관 상품 삭제.
	 *
	 * @param evtRelateGod the EvtRelateGod
	 * @return the EvtRelateGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtRelateGod(EvtRelateGod evtRelateGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtRelateGod", evtRelateGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

