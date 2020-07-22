
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
package com.plgrim.ncp.base.repository.com;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.com.ComQdlvHoldy;

/**
 * The Class ComQdlvHoldyRepository.
 */
@Repository
public class ComQdlvHoldyRepository extends AbstractRepository {
	

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
	 * 업체 퀵배송 휴일 상세 조회.
	 *
	 * @param comQdlvHoldy the ComQdlvHoldy
	 * @return the ComQdlvHoldy
	 * @throws SQLException the SQL exception
	 */
	public ComQdlvHoldy selectComQdlvHoldy(ComQdlvHoldy comQdlvHoldy) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComQdlvHoldy", comQdlvHoldy);
	}
	
	/**
	 * 업체 퀵배송 휴일 등록.
	 *
	 * @param comQdlvHoldy the ComQdlvHoldy
	 * @throws SQLException the SQL exception
	 */
	public void insertComQdlvHoldy(ComQdlvHoldy comQdlvHoldy) {
		getSession1().insert("com.plgrim.ncp.base.insertComQdlvHoldy", comQdlvHoldy);
	}
	
	/**
	 * 업체 퀵배송 휴일 수정.
	 *
	 * @param comQdlvHoldy the ComQdlvHoldy
	 * @throws SQLException the SQL exception
	 */
	public int updateComQdlvHoldy(ComQdlvHoldy comQdlvHoldy) {
		return getSession1().update("com.plgrim.ncp.base.updateComQdlvHoldy", comQdlvHoldy);
	}
	
	/**
	 * 업체 퀵배송 휴일 삭제.
	 *
	 * @param comQdlvHoldy the ComQdlvHoldy
	 * @return the ComQdlvHoldy
	 * @throws SQLException the SQL exception
	 */
	public int deleteComQdlvHoldy(ComQdlvHoldy comQdlvHoldy) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComQdlvHoldy", comQdlvHoldy);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

