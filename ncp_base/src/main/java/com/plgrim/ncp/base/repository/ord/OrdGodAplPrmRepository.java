
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
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrm;

/**
 * The Class OrdGodAplPrmRepository.
 */
@Repository
public class OrdGodAplPrmRepository extends AbstractRepository {
	

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
	 * 주문 상품 적용 프로모션 상세 조회.
	 *
	 * @param ordGodAplPrm the OrdGodAplPrm
	 * @return the OrdGodAplPrm
	 * @throws SQLException the SQL exception
	 */
	public OrdGodAplPrm selectOrdGodAplPrm(OrdGodAplPrm ordGodAplPrm) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectOrdGodAplPrm", ordGodAplPrm);
	}
	
	/**
	 * 주문 상품 적용 프로모션 등록.
	 *
	 * @param ordGodAplPrm the OrdGodAplPrm
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdGodAplPrm(OrdGodAplPrm ordGodAplPrm) {
		getSession1().insert("com.plgrim.ncp.base.insertOrdGodAplPrm", ordGodAplPrm);
	}
	
	/**
	 * 주문 상품 적용 프로모션 수정.
	 *
	 * @param ordGodAplPrm the OrdGodAplPrm
	 * @throws SQLException the SQL exception
	 */
	public int updateOrdGodAplPrm(OrdGodAplPrm ordGodAplPrm) {
		return getSession1().update("com.plgrim.ncp.base.updateOrdGodAplPrm", ordGodAplPrm);
	}
	
	/**
	 * 주문 상품 적용 프로모션 삭제.
	 *
	 * @param ordGodAplPrm the OrdGodAplPrm
	 * @return the OrdGodAplPrm
	 * @throws SQLException the SQL exception
	 */
	public int deleteOrdGodAplPrm(OrdGodAplPrm ordGodAplPrm) {
		return getSession1().delete("com.plgrim.ncp.base.deleteOrdGodAplPrm", ordGodAplPrm);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

