
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
package com.plgrim.ncp.base.repository.pay;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.pay.PayEscr;

/**
 * The Class PayEscrRepository.
 */
@Repository
public class PayEscrRepository extends AbstractRepository {
	

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
	 * 결제 에스크로 상세 조회.
	 *
	 * @param payEscr the PayEscr
	 * @return the PayEscr
	 * @throws SQLException the SQL exception
	 */
	public PayEscr selectPayEscr(PayEscr payEscr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPayEscr", payEscr);
	}
	
	/**
	 * 결제 에스크로 등록.
	 *
	 * @param payEscr the PayEscr
	 * @throws SQLException the SQL exception
	 */
	public void insertPayEscr(PayEscr payEscr) {
		getSession1().insert("com.plgrim.ncp.base.insertPayEscr", payEscr);
	}
	
	/**
	 * 결제 에스크로 수정.
	 *
	 * @param payEscr the PayEscr
	 * @throws SQLException the SQL exception
	 */
	public int updatePayEscr(PayEscr payEscr) {
		return getSession1().update("com.plgrim.ncp.base.updatePayEscr", payEscr);
	}
	
	/**
	 * 결제 에스크로 삭제.
	 *
	 * @param payEscr the PayEscr
	 * @return the PayEscr
	 * @throws SQLException the SQL exception
	 */
	public int deletePayEscr(PayEscr payEscr) {
		return getSession1().delete("com.plgrim.ncp.base.deletePayEscr", payEscr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

