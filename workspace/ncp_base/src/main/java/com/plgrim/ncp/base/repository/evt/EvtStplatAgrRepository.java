
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtStplatAgr;

/**
 * The Class EvtStplatAgrRepository.
 */
@Repository
public class EvtStplatAgrRepository extends AbstractRepository {
	

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
	 * 이벤트 약관 동의 상세 조회.
	 *
	 * @param evtStplatAgr the EvtStplatAgr
	 * @return the EvtStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public EvtStplatAgr selectEvtStplatAgr(EvtStplatAgr evtStplatAgr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtStplatAgr", evtStplatAgr);
	}
	
	/**
	 * 이벤트 약관 동의 등록.
	 *
	 * @param evtStplatAgr the EvtStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtStplatAgr(EvtStplatAgr evtStplatAgr) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtStplatAgr", evtStplatAgr);
	}
	
	/**
	 * 이벤트 약관 동의 수정.
	 *
	 * @param evtStplatAgr the EvtStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtStplatAgr(EvtStplatAgr evtStplatAgr) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtStplatAgr", evtStplatAgr);
	}
	
	/**
	 * 이벤트 약관 동의 삭제.
	 *
	 * @param evtStplatAgr the EvtStplatAgr
	 * @return the EvtStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtStplatAgr(EvtStplatAgr evtStplatAgr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtStplatAgr", evtStplatAgr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

