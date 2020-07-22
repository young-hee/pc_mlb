/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 * jackie(jackie)			20150609                      
 */
package com.plgrim.ncp.base.repository.ord;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdErpPntTrnsmis;

/**
 * The Class OrdErpPntTrnsmisRepository.
 */
@Repository
public class OrdErpPntTrnsmisRepository extends AbstractRepository {
	

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
	 * 주문 ERP 포인트 전송 상세 조회.
	 *
	 * @param ordErpPntTrnsmis the OrdErpPntTrnsmis
	 * @return the OrdErpPntTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public OrdErpPntTrnsmis selectOrdErpPntTrnsmis(OrdErpPntTrnsmis ordErpPntTrnsmis) throws Exception {
		OrdErpPntTrnsmis result = getSession1().selectOne(
		        "com.plgrim.ncp.base.selectOrdErpPntTrnsmis", ordErpPntTrnsmis);
		return result;
	}
	
	/**
	 * 주문 ERP 포인트 전송 등록.
	 *
	 * @param ordErpPntTrnsmis the OrdErpPntTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdErpPntTrnsmis(OrdErpPntTrnsmis ordErpPntTrnsmis) throws Exception {
		getSession1().insert("com.plgrim.ncp.base.insertOrdErpPntTrnsmis", ordErpPntTrnsmis);
	}
	
	/**
	 * 주문 ERP 포인트 전송 수정.
	 *
	 * @param ordErpPntTrnsmis the OrdErpPntTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public int updateOrdErpPntTrnsmis(OrdErpPntTrnsmis ordErpPntTrnsmis) throws Exception {
		//업데이트 후에 데이터를 다시 조회 한다.
		return getSession1().update("com.plgrim.ncp.base.updateOrdErpPntTrnsmis", ordErpPntTrnsmis);
	}
	
	/**
	 * 주문 ERP 포인트 전송 삭제.
	 *
	 * @param ordErpPntTrnsmis the OrdErpPntTrnsmis
	 * @return the OrdErpPntTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public int deleteOrdErpPntTrnsmis(OrdErpPntTrnsmis ordErpPntTrnsmis) throws Exception {
		//업데이트 후에 데이터를 다시 조회 한다.
		return getSession1().delete("com.plgrim.ncp.base.deleteOrdErpPntTrnsmis", ordErpPntTrnsmis);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}
