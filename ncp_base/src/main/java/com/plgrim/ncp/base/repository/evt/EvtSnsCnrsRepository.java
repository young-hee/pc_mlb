
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtSnsCnrs;

/**
 * The Class EvtSnsCnrsRepository.
 */
@Repository
public class EvtSnsCnrsRepository extends AbstractRepository {
	

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
	 * 이벤트 SNS 공유 상세 조회.
	 *
	 * @param evtSnsCnrs the EvtSnsCnrs
	 * @return the EvtSnsCnrs
	 * @throws SQLException the SQL exception
	 */
	public EvtSnsCnrs selectEvtSnsCnrs(EvtSnsCnrs evtSnsCnrs) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtSnsCnrs", evtSnsCnrs);
	}
	
	/**
	 * 이벤트 SNS 공유 등록.
	 *
	 * @param evtSnsCnrs the EvtSnsCnrs
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtSnsCnrs(EvtSnsCnrs evtSnsCnrs) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtSnsCnrs", evtSnsCnrs);
	}
	
	/**
	 * 이벤트 SNS 공유 수정.
	 *
	 * @param evtSnsCnrs the EvtSnsCnrs
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtSnsCnrs(EvtSnsCnrs evtSnsCnrs) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtSnsCnrs", evtSnsCnrs);
	}
	
	/**
	 * 이벤트 SNS 공유 삭제.
	 *
	 * @param evtSnsCnrs the EvtSnsCnrs
	 * @return the EvtSnsCnrs
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtSnsCnrs(EvtSnsCnrs evtSnsCnrs) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtSnsCnrs", evtSnsCnrs);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

