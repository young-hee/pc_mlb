
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPrize;

/**
 * The Class EvtPrizeRepository.
 */
@Repository
public class EvtPrizeRepository extends AbstractRepository {
	

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
	 * 이벤트 당첨 상세 조회.
	 *
	 * @param evtPrize the EvtPrize
	 * @return the EvtPrize
	 * @throws SQLException the SQL exception
	 */
	public EvtPrize selectEvtPrize(EvtPrize evtPrize) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtPrize", evtPrize);
	}
	
	/**
	 * 이벤트 당첨 등록.
	 *
	 * @param evtPrize the EvtPrize
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtPrize(EvtPrize evtPrize) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtPrize", evtPrize);
	}
	
	/**
	 * 이벤트 당첨 수정.
	 *
	 * @param evtPrize the EvtPrize
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtPrize(EvtPrize evtPrize) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtPrize", evtPrize);
	}
	
	/**
	 * 이벤트 당첨 삭제.
	 *
	 * @param evtPrize the EvtPrize
	 * @return the EvtPrize
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtPrize(EvtPrize evtPrize) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtPrize", evtPrize);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

