
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
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;

/**
 * The Class EvtRepository.
 */
@Repository
public class EvtRepository extends AbstractRepository {
	

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
	 * 이벤트 상세 조회.
	 *
	 * @param evt the Evt
	 * @return the Evt
	 * @throws SQLException the SQL exception
	 */
	public Evt selectEvt(Evt evt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvt", evt);
	}
	
	/**
	 * 이벤트 등록.
	 *
	 * @param evt the Evt
	 * @throws SQLException the SQL exception
	 */
	public void insertEvt(Evt evt) {
		getSession1().insert("com.plgrim.ncp.base.insertEvt", evt);
	}
	
	/**
	 * 이벤트 수정.
	 *
	 * @param evt the Evt
	 * @throws SQLException the SQL exception
	 */
	public int updateEvt(Evt evt) {
		return getSession1().update("com.plgrim.ncp.base.updateEvt", evt);
	}
	
	/**
	 * 이벤트 삭제.
	 *
	 * @param evt the Evt
	 * @return the Evt
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvt(Evt evt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvt", evt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

