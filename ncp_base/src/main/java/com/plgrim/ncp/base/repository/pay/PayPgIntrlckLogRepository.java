
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
import com.plgrim.ncp.base.entities.datasource1.pay.PayPgIntrlckLog;

/**
 * The Class PayPgIntrlckLogRepository.
 */
@Repository
public class PayPgIntrlckLogRepository extends AbstractRepository {
	

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
	 * 결제 PG 연동 로그 상세 조회.
	 *
	 * @param payPgIntrlckLog the PayPgIntrlckLog
	 * @return the PayPgIntrlckLog
	 * @throws SQLException the SQL exception
	 */
	public PayPgIntrlckLog selectPayPgIntrlckLog(PayPgIntrlckLog payPgIntrlckLog) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPayPgIntrlckLog", payPgIntrlckLog);
	}
	
	/**
	 * 결제 PG 연동 로그 등록.
	 *
	 * @param payPgIntrlckLog the PayPgIntrlckLog
	 * @throws SQLException the SQL exception
	 */
	public void insertPayPgIntrlckLog(PayPgIntrlckLog payPgIntrlckLog) {
		getSession1().insert("com.plgrim.ncp.base.insertPayPgIntrlckLog", payPgIntrlckLog);
	}
	
	/**
	 * 결제 PG 연동 로그 수정.
	 *
	 * @param payPgIntrlckLog the PayPgIntrlckLog
	 * @throws SQLException the SQL exception
	 */
	public int updatePayPgIntrlckLog(PayPgIntrlckLog payPgIntrlckLog) {
		return getSession1().update("com.plgrim.ncp.base.updatePayPgIntrlckLog", payPgIntrlckLog);
	}
	
	/**
	 * 결제 PG 연동 로그 삭제.
	 *
	 * @param payPgIntrlckLog the PayPgIntrlckLog
	 * @return the PayPgIntrlckLog
	 * @throws SQLException the SQL exception
	 */
	public int deletePayPgIntrlckLog(PayPgIntrlckLog payPgIntrlckLog) {
		return getSession1().delete("com.plgrim.ncp.base.deletePayPgIntrlckLog", payPgIntrlckLog);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

