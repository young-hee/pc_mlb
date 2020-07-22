
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
package com.plgrim.ncp.base.repository.clm;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmErpTrnsmisHist;

/**
 * The Class ClmErpTrnsmisHistRepository.
 */
@Repository
public class ClmErpTrnsmisHistRepository extends AbstractRepository {
	

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
	 * 클레임 ERP 전송 이력 상세 조회.
	 *
	 * @param clmErpTrnsmisHist the ClmErpTrnsmisHist
	 * @return the ClmErpTrnsmisHist
	 * @throws SQLException the SQL exception
	 */
	public ClmErpTrnsmisHist selectClmErpTrnsmisHist(ClmErpTrnsmisHist clmErpTrnsmisHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectClmErpTrnsmisHist", clmErpTrnsmisHist);
	}
	
	/**
	 * 클레임 ERP 전송 이력 등록.
	 *
	 * @param clmErpTrnsmisHist the ClmErpTrnsmisHist
	 * @throws SQLException the SQL exception
	 */
	public void insertClmErpTrnsmisHist(ClmErpTrnsmisHist clmErpTrnsmisHist) {
		getSession1().insert("com.plgrim.ncp.base.insertClmErpTrnsmisHist", clmErpTrnsmisHist);
	}
	
	/**
	 * 클레임 ERP 전송 이력 수정.
	 *
	 * @param clmErpTrnsmisHist the ClmErpTrnsmisHist
	 * @throws SQLException the SQL exception
	 */
	public int updateClmErpTrnsmisHist(ClmErpTrnsmisHist clmErpTrnsmisHist) {
		return getSession1().update("com.plgrim.ncp.base.updateClmErpTrnsmisHist", clmErpTrnsmisHist);
	}
	
	/**
	 * 클레임 ERP 전송 이력 삭제.
	 *
	 * @param clmErpTrnsmisHist the ClmErpTrnsmisHist
	 * @return the ClmErpTrnsmisHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteClmErpTrnsmisHist(ClmErpTrnsmisHist clmErpTrnsmisHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteClmErpTrnsmisHist", clmErpTrnsmisHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

