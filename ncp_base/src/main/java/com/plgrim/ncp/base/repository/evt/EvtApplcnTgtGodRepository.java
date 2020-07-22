
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcnTgtGod;

/**
 * The Class EvtApplcnTgtGodRepository.
 */
@Repository
public class EvtApplcnTgtGodRepository extends AbstractRepository {
	

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
	 * 이벤트 응모 대상 상품 상세 조회.
	 *
	 * @param evtApplcnTgtGod the EvtApplcnTgtGod
	 * @return the EvtApplcnTgtGod
	 * @throws SQLException the SQL exception
	 */
	public EvtApplcnTgtGod selectEvtApplcnTgtGod(EvtApplcnTgtGod evtApplcnTgtGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtApplcnTgtGod", evtApplcnTgtGod);
	}
	
	/**
	 * 이벤트 응모 대상 상품 등록.
	 *
	 * @param evtApplcnTgtGod the EvtApplcnTgtGod
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtApplcnTgtGod(EvtApplcnTgtGod evtApplcnTgtGod) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtApplcnTgtGod", evtApplcnTgtGod);
	}
	
	/**
	 * 이벤트 응모 대상 상품 수정.
	 *
	 * @param evtApplcnTgtGod the EvtApplcnTgtGod
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtApplcnTgtGod(EvtApplcnTgtGod evtApplcnTgtGod) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtApplcnTgtGod", evtApplcnTgtGod);
	}
	
	/**
	 * 이벤트 응모 대상 상품 삭제.
	 *
	 * @param evtApplcnTgtGod the EvtApplcnTgtGod
	 * @return the EvtApplcnTgtGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtApplcnTgtGod(EvtApplcnTgtGod evtApplcnTgtGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtApplcnTgtGod", evtApplcnTgtGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

