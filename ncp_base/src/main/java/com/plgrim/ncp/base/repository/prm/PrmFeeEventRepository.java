
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
package com.plgrim.ncp.base.repository.prm;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEvent;

/**
 * The Class PrmFeeEventRepository.
 */
@Repository
public class PrmFeeEventRepository extends AbstractRepository {
	

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
	 * 프로모션 수수료 행사 상세 조회.
	 *
	 * @param prmFeeEvent the PrmFeeEvent
	 * @return the PrmFeeEvent
	 * @throws SQLException the SQL exception
	 */
	public PrmFeeEvent selectPrmFeeEvent(PrmFeeEvent prmFeeEvent) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPrmFeeEvent", prmFeeEvent);
	}
	
	/**
	 * 프로모션 수수료 행사 등록.
	 *
	 * @param prmFeeEvent the PrmFeeEvent
	 * @throws SQLException the SQL exception
	 */
	public void insertPrmFeeEvent(PrmFeeEvent prmFeeEvent) {
		getSession1().insert("com.plgrim.ncp.base.insertPrmFeeEvent", prmFeeEvent);
	}
	
	/**
	 * 프로모션 수수료 행사 수정.
	 *
	 * @param prmFeeEvent the PrmFeeEvent
	 * @throws SQLException the SQL exception
	 */
	public int updatePrmFeeEvent(PrmFeeEvent prmFeeEvent) {
		return getSession1().update("com.plgrim.ncp.base.updatePrmFeeEvent", prmFeeEvent);
	}
	
	/**
	 * 프로모션 수수료 행사 삭제.
	 *
	 * @param prmFeeEvent the PrmFeeEvent
	 * @return the PrmFeeEvent
	 * @throws SQLException the SQL exception
	 */
	public int deletePrmFeeEvent(PrmFeeEvent prmFeeEvent) {
		return getSession1().delete("com.plgrim.ncp.base.deletePrmFeeEvent", prmFeeEvent);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

