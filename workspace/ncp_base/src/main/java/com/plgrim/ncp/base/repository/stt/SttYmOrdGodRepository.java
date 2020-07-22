
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
import com.plgrim.ncp.base.entities.datasource1.stt.SttYmOrdGod;

/**
 * The Class SttYmOrdGodRepository.
 */
@Repository
public class SttYmOrdGodRepository extends AbstractRepository {
	

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
	 * 통계 년월 주문 상품 상세 조회.
	 *
	 * @param sttYmOrdGod the SttYmOrdGod
	 * @return the SttYmOrdGod
	 * @throws SQLException the SQL exception
	 */
	public SttYmOrdGod selectSttYmOrdGod(SttYmOrdGod sttYmOrdGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSttYmOrdGod", sttYmOrdGod);
	}
	
	/**
	 * 통계 년월 주문 상품 등록.
	 *
	 * @param sttYmOrdGod the SttYmOrdGod
	 * @throws SQLException the SQL exception
	 */
	public void insertSttYmOrdGod(SttYmOrdGod sttYmOrdGod) {
		getSession1().insert("com.plgrim.ncp.base.insertSttYmOrdGod", sttYmOrdGod);
	}
	
	/**
	 * 통계 년월 주문 상품 수정.
	 *
	 * @param sttYmOrdGod the SttYmOrdGod
	 * @throws SQLException the SQL exception
	 */
	public int updateSttYmOrdGod(SttYmOrdGod sttYmOrdGod) {
		return getSession1().update("com.plgrim.ncp.base.updateSttYmOrdGod", sttYmOrdGod);
	}
	
	/**
	 * 통계 년월 주문 상품 삭제.
	 *
	 * @param sttYmOrdGod the SttYmOrdGod
	 * @return the SttYmOrdGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteSttYmOrdGod(SttYmOrdGod sttYmOrdGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSttYmOrdGod", sttYmOrdGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

