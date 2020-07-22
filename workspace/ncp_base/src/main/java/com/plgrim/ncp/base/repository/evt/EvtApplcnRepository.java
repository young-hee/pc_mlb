
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;

/**
 * The Class EvtApplcnRepository.
 */
@Repository
public class EvtApplcnRepository extends AbstractRepository {
	

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
	 * 이벤트 응모 상세 조회.
	 *
	 * @param evtApplcn the EvtApplcn
	 * @return the EvtApplcn
	 * @throws SQLException the SQL exception
	 */
	public EvtApplcn selectEvtApplcn(EvtApplcn evtApplcn) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtApplcn", evtApplcn);
	}
	
	/**
	 * 이벤트 응모 등록.
	 *
	 * @param evtApplcn the EvtApplcn
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtApplcn(EvtApplcn evtApplcn) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtApplcn", evtApplcn);
	}
	
	/**
	 * 이벤트 응모 수정.
	 *
	 * @param evtApplcn the EvtApplcn
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtApplcn(EvtApplcn evtApplcn) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtApplcn", evtApplcn);
	}
	
	/**
	 * 이벤트 응모 삭제.
	 *
	 * @param evtApplcn the EvtApplcn
	 * @return the EvtApplcn
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtApplcn(EvtApplcn evtApplcn) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtApplcn", evtApplcn);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

