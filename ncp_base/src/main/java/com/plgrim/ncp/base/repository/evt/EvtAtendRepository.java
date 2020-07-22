
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtAtend;

/**
 * The Class EvtAtendRepository.
 */
@Repository
public class EvtAtendRepository extends AbstractRepository {
	

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
	 * 이벤트 출석 상세 조회.
	 *
	 * @param evtAtend the EvtAtend
	 * @return the EvtAtend
	 * @throws SQLException the SQL exception
	 */
	public EvtAtend selectEvtAtend(EvtAtend evtAtend) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtAtend", evtAtend);
	}
	
	/**
	 * 이벤트 출석 등록.
	 *
	 * @param evtAtend the EvtAtend
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtAtend(EvtAtend evtAtend) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtAtend", evtAtend);
	}
	
	/**
	 * 이벤트 출석 수정.
	 *
	 * @param evtAtend the EvtAtend
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtAtend(EvtAtend evtAtend) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtAtend", evtAtend);
	}
	
	/**
	 * 이벤트 출석 삭제.
	 *
	 * @param evtAtend the EvtAtend
	 * @return the EvtAtend
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtAtend(EvtAtend evtAtend) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtAtend", evtAtend);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

