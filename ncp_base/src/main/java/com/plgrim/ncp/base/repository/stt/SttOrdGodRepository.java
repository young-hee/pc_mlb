
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
package com.plgrim.ncp.base.repository.stt;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.stt.SttOrdGod;

/**
 * The Class SttOrdGodRepository.
 */
@Repository
public class SttOrdGodRepository extends AbstractRepository {
	

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
	 * 통계 주문 상품 상세 조회.
	 *
	 * @param sttOrdGod the SttOrdGod
	 * @return the SttOrdGod
	 * @throws SQLException the SQL exception
	 */
	public SttOrdGod selectSttOrdGod(SttOrdGod sttOrdGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSttOrdGod", sttOrdGod);
	}
	
	/**
	 * 통계 주문 상품 등록.
	 *
	 * @param sttOrdGod the SttOrdGod
	 * @throws SQLException the SQL exception
	 */
	public void insertSttOrdGod(SttOrdGod sttOrdGod) {
		getSession1().insert("com.plgrim.ncp.base.insertSttOrdGod", sttOrdGod);
	}
	
	/**
	 * 통계 주문 상품 수정.
	 *
	 * @param sttOrdGod the SttOrdGod
	 * @throws SQLException the SQL exception
	 */
	public int updateSttOrdGod(SttOrdGod sttOrdGod) {
		return getSession1().update("com.plgrim.ncp.base.updateSttOrdGod", sttOrdGod);
	}
	
	/**
	 * 통계 주문 상품 삭제.
	 *
	 * @param sttOrdGod the SttOrdGod
	 * @return the SttOrdGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteSttOrdGod(SttOrdGod sttOrdGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSttOrdGod", sttOrdGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

