
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtNmbrApplcn;

/**
 * The Class EvtNmbrApplcnRepository.
 */
@Repository
public class EvtNmbrApplcnRepository extends AbstractRepository {
	

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
	 * 이벤트 비회원 응모 상세 조회.
	 *
	 * @param evtNmbrApplcn the EvtNmbrApplcn
	 * @return the EvtNmbrApplcn
	 * @throws SQLException the SQL exception
	 */
	public EvtNmbrApplcn selectEvtNmbrApplcn(EvtNmbrApplcn evtNmbrApplcn) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtNmbrApplcn", evtNmbrApplcn);
	}
	
	/**
	 * 이벤트 비회원 응모 등록.
	 *
	 * @param evtNmbrApplcn the EvtNmbrApplcn
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtNmbrApplcn(EvtNmbrApplcn evtNmbrApplcn) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtNmbrApplcn", evtNmbrApplcn);
	}
	
	/**
	 * 이벤트 비회원 응모 수정.
	 *
	 * @param evtNmbrApplcn the EvtNmbrApplcn
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtNmbrApplcn(EvtNmbrApplcn evtNmbrApplcn) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtNmbrApplcn", evtNmbrApplcn);
	}
	
	/**
	 * 이벤트 비회원 응모 삭제.
	 *
	 * @param evtNmbrApplcn the EvtNmbrApplcn
	 * @return the EvtNmbrApplcn
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtNmbrApplcn(EvtNmbrApplcn evtNmbrApplcn) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtNmbrApplcn", evtNmbrApplcn);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

