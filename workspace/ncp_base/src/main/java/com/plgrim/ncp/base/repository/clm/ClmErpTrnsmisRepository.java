
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
import com.plgrim.ncp.base.entities.datasource1.clm.ClmErpTrnsmis;

/**
 * The Class ClmErpTrnsmisRepository.
 */
@Repository
public class ClmErpTrnsmisRepository extends AbstractRepository {
	

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
	 * 클레임 ERP 전송 상세 조회.
	 *
	 * @param clmErpTrnsmis the ClmErpTrnsmis
	 * @return the ClmErpTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public ClmErpTrnsmis selectClmErpTrnsmis(ClmErpTrnsmis clmErpTrnsmis) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectClmErpTrnsmis", clmErpTrnsmis);
	}
	
	/**
	 * 클레임 ERP 전송 등록.
	 *
	 * @param clmErpTrnsmis the ClmErpTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public void insertClmErpTrnsmis(ClmErpTrnsmis clmErpTrnsmis) {
		getSession1().insert("com.plgrim.ncp.base.insertClmErpTrnsmis", clmErpTrnsmis);
	}
	
	/**
	 * 클레임 ERP 전송 수정.
	 *
	 * @param clmErpTrnsmis the ClmErpTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public int updateClmErpTrnsmis(ClmErpTrnsmis clmErpTrnsmis) {
		return getSession1().update("com.plgrim.ncp.base.updateClmErpTrnsmis", clmErpTrnsmis);
	}
	
	/**
	 * 클레임 ERP 전송 삭제.
	 *
	 * @param clmErpTrnsmis the ClmErpTrnsmis
	 * @return the ClmErpTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public int deleteClmErpTrnsmis(ClmErpTrnsmis clmErpTrnsmis) {
		return getSession1().delete("com.plgrim.ncp.base.deleteClmErpTrnsmis", clmErpTrnsmis);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

