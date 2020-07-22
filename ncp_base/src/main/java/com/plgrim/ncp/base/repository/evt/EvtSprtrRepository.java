
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtSprtr;

/**
 * The Class EvtSprtrRepository.
 */
@Repository
public class EvtSprtrRepository extends AbstractRepository {
	

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
	 * 이벤트 구분자 상세 조회.
	 *
	 * @param evtSprtr the EvtSprtr
	 * @return the EvtSprtr
	 * @throws SQLException the SQL exception
	 */
	public EvtSprtr selectEvtSprtr(EvtSprtr evtSprtr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtSprtr", evtSprtr);
	}
	
	/**
	 * 이벤트 구분자 등록.
	 *
	 * @param evtSprtr the EvtSprtr
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtSprtr(EvtSprtr evtSprtr) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtSprtr", evtSprtr);
	}
	
	/**
	 * 이벤트 구분자 수정.
	 *
	 * @param evtSprtr the EvtSprtr
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtSprtr(EvtSprtr evtSprtr) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtSprtr", evtSprtr);
	}
	
	/**
	 * 이벤트 구분자 삭제.
	 *
	 * @param evtSprtr the EvtSprtr
	 * @return the EvtSprtr
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtSprtr(EvtSprtr evtSprtr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtSprtr", evtSprtr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

