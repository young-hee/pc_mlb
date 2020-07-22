
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtAplGod;

/**
 * The Class EvtAplGodRepository.
 */
@Repository
public class EvtAplGodRepository extends AbstractRepository {
	

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
	 * 이벤트 적용 상품 상세 조회.
	 *
	 * @param evtAplGod the EvtAplGod
	 * @return the EvtAplGod
	 * @throws SQLException the SQL exception
	 */
	public EvtAplGod selectEvtAplGod(EvtAplGod evtAplGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtAplGod", evtAplGod);
	}
	
	/**
	 * 이벤트 적용 상품 등록.
	 *
	 * @param evtAplGod the EvtAplGod
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtAplGod(EvtAplGod evtAplGod) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtAplGod", evtAplGod);
	}
	
	/**
	 * 이벤트 적용 상품 수정.
	 *
	 * @param evtAplGod the EvtAplGod
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtAplGod(EvtAplGod evtAplGod) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtAplGod", evtAplGod);
	}
	
	/**
	 * 이벤트 적용 상품 삭제.
	 *
	 * @param evtAplGod the EvtAplGod
	 * @return the EvtAplGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtAplGod(EvtAplGod evtAplGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtAplGod", evtAplGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

