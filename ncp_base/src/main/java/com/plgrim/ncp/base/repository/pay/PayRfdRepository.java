
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
import com.plgrim.ncp.base.entities.datasource1.pay.PayRfd;

/**
 * The Class PayRfdRepository.
 */
@Repository
public class PayRfdRepository extends AbstractRepository {
	

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
	 * 결제 환불 상세 조회.
	 *
	 * @param payRfd the PayRfd
	 * @return the PayRfd
	 * @throws SQLException the SQL exception
	 */
	public PayRfd selectPayRfd(PayRfd payRfd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPayRfd", payRfd);
	}
	
	/**
	 * 결제 환불 등록.
	 *
	 * @param payRfd the PayRfd
	 * @throws SQLException the SQL exception
	 */
	public void insertPayRfd(PayRfd payRfd) {
		getSession1().insert("com.plgrim.ncp.base.insertPayRfd", payRfd);
	}
	
	/**
	 * 결제 환불 수정.
	 *
	 * @param payRfd the PayRfd
	 * @throws SQLException the SQL exception
	 */
	public int updatePayRfd(PayRfd payRfd) {
		return getSession1().update("com.plgrim.ncp.base.updatePayRfd", payRfd);
	}
	
	/**
	 * 결제 환불 삭제.
	 *
	 * @param payRfd the PayRfd
	 * @return the PayRfd
	 * @throws SQLException the SQL exception
	 */
	public int deletePayRfd(PayRfd payRfd) {
		return getSession1().delete("com.plgrim.ncp.base.deletePayRfd", payRfd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

