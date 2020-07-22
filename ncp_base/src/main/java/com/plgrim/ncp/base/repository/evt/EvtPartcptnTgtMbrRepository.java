
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPartcptnTgtMbr;

/**
 * The Class EvtPartcptnTgtMbrRepository.
 */
@Repository
public class EvtPartcptnTgtMbrRepository extends AbstractRepository {
	

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
	 * 이벤트 참여 대상 회원 상세 조회.
	 *
	 * @param evtPartcptnTgtMbr the EvtPartcptnTgtMbr
	 * @return the EvtPartcptnTgtMbr
	 * @throws SQLException the SQL exception
	 */
	public EvtPartcptnTgtMbr selectEvtPartcptnTgtMbr(EvtPartcptnTgtMbr evtPartcptnTgtMbr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtPartcptnTgtMbr", evtPartcptnTgtMbr);
	}
	
	/**
	 * 이벤트 참여 대상 회원 등록.
	 *
	 * @param evtPartcptnTgtMbr the EvtPartcptnTgtMbr
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtPartcptnTgtMbr(EvtPartcptnTgtMbr evtPartcptnTgtMbr) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtPartcptnTgtMbr", evtPartcptnTgtMbr);
	}
	
	/**
	 * 이벤트 참여 대상 회원 수정.
	 *
	 * @param evtPartcptnTgtMbr the EvtPartcptnTgtMbr
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtPartcptnTgtMbr(EvtPartcptnTgtMbr evtPartcptnTgtMbr) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtPartcptnTgtMbr", evtPartcptnTgtMbr);
	}
	
	/**
	 * 이벤트 참여 대상 회원 삭제.
	 *
	 * @param evtPartcptnTgtMbr the EvtPartcptnTgtMbr
	 * @return the EvtPartcptnTgtMbr
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtPartcptnTgtMbr(EvtPartcptnTgtMbr evtPartcptnTgtMbr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtPartcptnTgtMbr", evtPartcptnTgtMbr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

