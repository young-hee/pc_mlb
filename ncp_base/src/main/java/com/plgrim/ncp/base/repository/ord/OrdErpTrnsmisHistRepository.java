
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
import com.plgrim.ncp.base.entities.datasource1.ord.OrdErpTrnsmisHist;

/**
 * The Class OrdErpTrnsmisHistRepository.
 */
@Repository
public class OrdErpTrnsmisHistRepository extends AbstractRepository {
	

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
	 * 주문 ERP 전송 이력 상세 조회.
	 *
	 * @param ordErpTrnsmisHist the OrdErpTrnsmisHist
	 * @return the OrdErpTrnsmisHist
	 * @throws SQLException the SQL exception
	 */
	public OrdErpTrnsmisHist selectOrdErpTrnsmisHist(OrdErpTrnsmisHist ordErpTrnsmisHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectOrdErpTrnsmisHist", ordErpTrnsmisHist);
	}
	
	/**
	 * 주문 ERP 전송 이력 등록.
	 *
	 * @param ordErpTrnsmisHist the OrdErpTrnsmisHist
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdErpTrnsmisHist(OrdErpTrnsmisHist ordErpTrnsmisHist) {
		getSession1().insert("com.plgrim.ncp.base.insertOrdErpTrnsmisHist", ordErpTrnsmisHist);
	}
	
	/**
	 * 주문 ERP 전송 이력 수정.
	 *
	 * @param ordErpTrnsmisHist the OrdErpTrnsmisHist
	 * @throws SQLException the SQL exception
	 */
	public int updateOrdErpTrnsmisHist(OrdErpTrnsmisHist ordErpTrnsmisHist) {
		return getSession1().update("com.plgrim.ncp.base.updateOrdErpTrnsmisHist", ordErpTrnsmisHist);
	}
	
	/**
	 * 주문 ERP 전송 이력 삭제.
	 *
	 * @param ordErpTrnsmisHist the OrdErpTrnsmisHist
	 * @return the OrdErpTrnsmisHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteOrdErpTrnsmisHist(OrdErpTrnsmisHist ordErpTrnsmisHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteOrdErpTrnsmisHist", ordErpTrnsmisHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

