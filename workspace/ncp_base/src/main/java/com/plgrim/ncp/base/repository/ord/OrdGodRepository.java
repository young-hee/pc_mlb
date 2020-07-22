
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
package com.plgrim.ncp.base.repository.ord;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;

/**
 * The Class OrdGodRepository.
 */
@Repository
public class OrdGodRepository extends AbstractRepository {
	

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
	 * 주문 상품 상세 조회.
	 *
	 * @param ordGod the OrdGod
	 * @return the OrdGod
	 * @throws SQLException the SQL exception
	 */
	public OrdGod selectOrdGod(OrdGod ordGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectOrdGod", ordGod);
	}
	
	/**
	 * 주문 상품 등록.
	 *
	 * @param ordGod the OrdGod
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdGod(OrdGod ordGod) {
		getSession1().insert("com.plgrim.ncp.base.insertOrdGod", ordGod);
	}
	
	/**
	 * 주문 상품 수정.
	 *
	 * @param ordGod the OrdGod
	 * @throws SQLException the SQL exception
	 */
	public int updateOrdGod(OrdGod ordGod) {
		return getSession1().update("com.plgrim.ncp.base.updateOrdGod", ordGod);
	}
	
	/**
	 * 주문 상품 삭제.
	 *
	 * @param ordGod the OrdGod
	 * @return the OrdGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteOrdGod(OrdGod ordGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteOrdGod", ordGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

