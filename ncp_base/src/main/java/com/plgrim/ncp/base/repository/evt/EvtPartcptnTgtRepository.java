
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPartcptnTgt;

/**
 * The Class EvtPartcptnTgtRepository.
 */
@Repository
public class EvtPartcptnTgtRepository extends AbstractRepository {
	

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
	 * 이벤트 참여 대상 상세 조회.
	 *
	 * @param evtPartcptnTgt the EvtPartcptnTgt
	 * @return the EvtPartcptnTgt
	 * @throws SQLException the SQL exception
	 */
	public EvtPartcptnTgt selectEvtPartcptnTgt(EvtPartcptnTgt evtPartcptnTgt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtPartcptnTgt", evtPartcptnTgt);
	}
	
	/**
	 * 이벤트 참여 대상 등록.
	 *
	 * @param evtPartcptnTgt the EvtPartcptnTgt
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtPartcptnTgt(EvtPartcptnTgt evtPartcptnTgt) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtPartcptnTgt", evtPartcptnTgt);
	}
	
	/**
	 * 이벤트 참여 대상 수정.
	 *
	 * @param evtPartcptnTgt the EvtPartcptnTgt
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtPartcptnTgt(EvtPartcptnTgt evtPartcptnTgt) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtPartcptnTgt", evtPartcptnTgt);
	}
	
	/**
	 * 이벤트 참여 대상 삭제.
	 *
	 * @param evtPartcptnTgt the EvtPartcptnTgt
	 * @return the EvtPartcptnTgt
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtPartcptnTgt(EvtPartcptnTgt evtPartcptnTgt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtPartcptnTgt", evtPartcptnTgt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

