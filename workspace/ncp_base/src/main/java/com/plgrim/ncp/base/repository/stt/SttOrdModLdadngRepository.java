
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
import com.plgrim.ncp.base.entities.datasource1.stt.SttOrdModLdadng;

/**
 * The Class SttOrdModLdadngRepository.
 */
@Repository
public class SttOrdModLdadngRepository extends AbstractRepository {
	

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
	 * 통계 주문 변경 적재 상세 조회.
	 *
	 * @param sttOrdModLdadng the SttOrdModLdadng
	 * @return the SttOrdModLdadng
	 * @throws SQLException the SQL exception
	 */
	public SttOrdModLdadng selectSttOrdModLdadng(SttOrdModLdadng sttOrdModLdadng) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSttOrdModLdadng", sttOrdModLdadng);
	}
	
	/**
	 * 통계 주문 변경 적재 등록.
	 *
	 * @param sttOrdModLdadng the SttOrdModLdadng
	 * @throws SQLException the SQL exception
	 */
	public void insertSttOrdModLdadng(SttOrdModLdadng sttOrdModLdadng) {
		getSession1().insert("com.plgrim.ncp.base.insertSttOrdModLdadng", sttOrdModLdadng);
	}
	
	/**
	 * 통계 주문 변경 적재 수정.
	 *
	 * @param sttOrdModLdadng the SttOrdModLdadng
	 * @throws SQLException the SQL exception
	 */
	public int updateSttOrdModLdadng(SttOrdModLdadng sttOrdModLdadng) {
		return getSession1().update("com.plgrim.ncp.base.updateSttOrdModLdadng", sttOrdModLdadng);
	}
	
	/**
	 * 통계 주문 변경 적재 삭제.
	 *
	 * @param sttOrdModLdadng the SttOrdModLdadng
	 * @return the SttOrdModLdadng
	 * @throws SQLException the SQL exception
	 */
	public int deleteSttOrdModLdadng(SttOrdModLdadng sttOrdModLdadng) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSttOrdModLdadng", sttOrdModLdadng);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

