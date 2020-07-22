
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
import com.plgrim.ncp.base.entities.datasource1.ord.OrdErpTrnsmis;

/**
 * The Class OrdErpTrnsmisRepository.
 */
@Repository
public class OrdErpTrnsmisRepository extends AbstractRepository {
	

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
	 * 주문 ERP 전송 상세 조회.
	 *
	 * @param ordErpTrnsmis the OrdErpTrnsmis
	 * @return the OrdErpTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public OrdErpTrnsmis selectOrdErpTrnsmis(OrdErpTrnsmis ordErpTrnsmis) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectOrdErpTrnsmis", ordErpTrnsmis);
	}
	
	/**
	 * 주문 ERP 전송 등록.
	 *
	 * @param ordErpTrnsmis the OrdErpTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdErpTrnsmis(OrdErpTrnsmis ordErpTrnsmis) {
		getSession1().insert("com.plgrim.ncp.base.insertOrdErpTrnsmis", ordErpTrnsmis);
	}
	
	/**
	 * 주문 ERP 전송 수정.
	 *
	 * @param ordErpTrnsmis the OrdErpTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public int updateOrdErpTrnsmis(OrdErpTrnsmis ordErpTrnsmis) {
		return getSession1().update("com.plgrim.ncp.base.updateOrdErpTrnsmis", ordErpTrnsmis);
	}
	
	/**
	 * 주문 ERP 전송 삭제.
	 *
	 * @param ordErpTrnsmis the OrdErpTrnsmis
	 * @return the OrdErpTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public int deleteOrdErpTrnsmis(OrdErpTrnsmis ordErpTrnsmis) {
		return getSession1().delete("com.plgrim.ncp.base.deleteOrdErpTrnsmis", ordErpTrnsmis);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

