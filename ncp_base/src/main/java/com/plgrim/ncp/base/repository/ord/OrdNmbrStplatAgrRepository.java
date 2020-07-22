
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
import com.plgrim.ncp.base.entities.datasource1.ord.OrdNmbrStplatAgr;

/**
 * The Class OrdNmbrStplatAgrRepository.
 */
@Repository
public class OrdNmbrStplatAgrRepository extends AbstractRepository {
	

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
	 * 주문 비회원 약관 동의 상세 조회.
	 *
	 * @param ordNmbrStplatAgr the OrdNmbrStplatAgr
	 * @return the OrdNmbrStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public OrdNmbrStplatAgr selectOrdNmbrStplatAgr(OrdNmbrStplatAgr ordNmbrStplatAgr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectOrdNmbrStplatAgr", ordNmbrStplatAgr);
	}
	
	/**
	 * 주문 비회원 약관 동의 등록.
	 *
	 * @param ordNmbrStplatAgr the OrdNmbrStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdNmbrStplatAgr(OrdNmbrStplatAgr ordNmbrStplatAgr) {
		getSession1().insert("com.plgrim.ncp.base.insertOrdNmbrStplatAgr", ordNmbrStplatAgr);
	}
	
	/**
	 * 주문 비회원 약관 동의 수정.
	 *
	 * @param ordNmbrStplatAgr the OrdNmbrStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public int updateOrdNmbrStplatAgr(OrdNmbrStplatAgr ordNmbrStplatAgr) {
		return getSession1().update("com.plgrim.ncp.base.updateOrdNmbrStplatAgr", ordNmbrStplatAgr);
	}
	
	/**
	 * 주문 비회원 약관 동의 삭제.
	 *
	 * @param ordNmbrStplatAgr the OrdNmbrStplatAgr
	 * @return the OrdNmbrStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public int deleteOrdNmbrStplatAgr(OrdNmbrStplatAgr ordNmbrStplatAgr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteOrdNmbrStplatAgr", ordNmbrStplatAgr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

