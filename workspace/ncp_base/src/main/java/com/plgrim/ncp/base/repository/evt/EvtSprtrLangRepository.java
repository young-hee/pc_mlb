
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtSprtrLang;

/**
 * The Class EvtSprtrLangRepository.
 */
@Repository
public class EvtSprtrLangRepository extends AbstractRepository {
	

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
	 * 이벤트 구분자 언어 상세 조회.
	 *
	 * @param evtSprtrLang the EvtSprtrLang
	 * @return the EvtSprtrLang
	 * @throws SQLException the SQL exception
	 */
	public EvtSprtrLang selectEvtSprtrLang(EvtSprtrLang evtSprtrLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtSprtrLang", evtSprtrLang);
	}
	
	/**
	 * 이벤트 구분자 언어 등록.
	 *
	 * @param evtSprtrLang the EvtSprtrLang
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtSprtrLang(EvtSprtrLang evtSprtrLang) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtSprtrLang", evtSprtrLang);
	}
	
	/**
	 * 이벤트 구분자 언어 수정.
	 *
	 * @param evtSprtrLang the EvtSprtrLang
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtSprtrLang(EvtSprtrLang evtSprtrLang) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtSprtrLang", evtSprtrLang);
	}
	
	/**
	 * 이벤트 구분자 언어 삭제.
	 *
	 * @param evtSprtrLang the EvtSprtrLang
	 * @return the EvtSprtrLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtSprtrLang(EvtSprtrLang evtSprtrLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtSprtrLang", evtSprtrLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

