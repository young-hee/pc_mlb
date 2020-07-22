
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtDspTgt;

/**
 * The Class EvtDspTgtRepository.
 */
@Repository
public class EvtDspTgtRepository extends AbstractRepository {
	

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
	 * 이벤트 전시 대상 상세 조회.
	 *
	 * @param evtDspTgt the EvtDspTgt
	 * @return the EvtDspTgt
	 * @throws SQLException the SQL exception
	 */
	public EvtDspTgt selectEvtDspTgt(EvtDspTgt evtDspTgt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtDspTgt", evtDspTgt);
	}
	
	/**
	 * 이벤트 전시 대상 등록.
	 *
	 * @param evtDspTgt the EvtDspTgt
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtDspTgt(EvtDspTgt evtDspTgt) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtDspTgt", evtDspTgt);
	}
	
	/**
	 * 이벤트 전시 대상 수정.
	 *
	 * @param evtDspTgt the EvtDspTgt
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtDspTgt(EvtDspTgt evtDspTgt) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtDspTgt", evtDspTgt);
	}
	
	/**
	 * 이벤트 전시 대상 삭제.
	 *
	 * @param evtDspTgt the EvtDspTgt
	 * @return the EvtDspTgt
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtDspTgt(EvtDspTgt evtDspTgt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtDspTgt", evtDspTgt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

