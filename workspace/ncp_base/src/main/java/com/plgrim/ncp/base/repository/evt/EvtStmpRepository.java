
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtStmp;

/**
 * The Class EvtStmpRepository.
 */
@Repository
public class EvtStmpRepository extends AbstractRepository {
	

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
	 * 이벤트 스탬프 상세 조회.
	 *
	 * @param evtStmp the EvtStmp
	 * @return the EvtStmp
	 * @throws SQLException the SQL exception
	 */
	public EvtStmp selectEvtStmp(EvtStmp evtStmp) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtStmp", evtStmp);
	}
	
	/**
	 * 이벤트 스탬프 등록.
	 *
	 * @param evtStmp the EvtStmp
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtStmp(EvtStmp evtStmp) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtStmp", evtStmp);
	}
	
	/**
	 * 이벤트 스탬프 수정.
	 *
	 * @param evtStmp the EvtStmp
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtStmp(EvtStmp evtStmp) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtStmp", evtStmp);
	}
	
	/**
	 * 이벤트 스탬프 삭제.
	 *
	 * @param evtStmp the EvtStmp
	 * @return the EvtStmp
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtStmp(EvtStmp evtStmp) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtStmp", evtStmp);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

