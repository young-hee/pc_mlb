
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
package com.plgrim.ncp.base.repository.em;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.em.EmTranMms;

/**
 * The Class EmTranMmsRepository.
 */
@Repository
public class EmTranMmsRepository extends AbstractRepository {
	

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
	 * EM_TRAN_MMS 상세 조회.
	 *
	 * @param emTranMms the EmTranMms
	 * @return the EmTranMms
	 * @throws SQLException the SQL exception
	 */
	public EmTranMms selectEmTranMms(EmTranMms emTranMms) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEmTranMms", emTranMms);
	}
	
	/**
	 * EM_TRAN_MMS 등록.
	 *
	 * @param emTranMms the EmTranMms
	 * @throws SQLException the SQL exception
	 */
	public void insertEmTranMms(EmTranMms emTranMms) {
		getSession1().insert("com.plgrim.ncp.base.insertEmTranMms", emTranMms);
	}
	
	/**
	 * EM_TRAN_MMS 수정.
	 *
	 * @param emTranMms the EmTranMms
	 * @throws SQLException the SQL exception
	 */
	public int updateEmTranMms(EmTranMms emTranMms) {
		return getSession1().update("com.plgrim.ncp.base.updateEmTranMms", emTranMms);
	}
	
	/**
	 * EM_TRAN_MMS 삭제.
	 *
	 * @param emTranMms the EmTranMms
	 * @return the EmTranMms
	 * @throws SQLException the SQL exception
	 */
	public int deleteEmTranMms(EmTranMms emTranMms) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEmTranMms", emTranMms);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

