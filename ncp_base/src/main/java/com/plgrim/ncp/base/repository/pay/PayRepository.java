
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
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;

/**
 * The Class PayRepository.
 */
@Repository
public class PayRepository extends AbstractRepository {
	

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
	 * 결제 상세 조회.
	 *
	 * @param pay the Pay
	 * @return the Pay
	 * @throws SQLException the SQL exception
	 */
	public Pay selectPay(Pay pay) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPay", pay);
	}
	
	/**
	 * 결제 등록.
	 *
	 * @param pay the Pay
	 * @throws SQLException the SQL exception
	 */
	public void insertPay(Pay pay) {
		getSession1().insert("com.plgrim.ncp.base.insertPay", pay);
	}
	
	/**
	 * 결제 수정.
	 *
	 * @param pay the Pay
	 * @throws SQLException the SQL exception
	 */
	public int updatePay(Pay pay) {
		return getSession1().update("com.plgrim.ncp.base.updatePay", pay);
	}
	
	/**
	 * 결제 삭제.
	 *
	 * @param pay the Pay
	 * @return the Pay
	 * @throws SQLException the SQL exception
	 */
	public int deletePay(Pay pay) {
		return getSession1().delete("com.plgrim.ncp.base.deletePay", pay);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

