
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
import com.plgrim.ncp.base.entities.datasource1.em.EmTran;

/**
 * The Class EmTranRepository.
 */
@Repository
public class EmTranRepository extends AbstractRepository {
	

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
	 * EM_TRAN 상세 조회.
	 *
	 * @param emTran the EmTran
	 * @return the EmTran
	 * @throws SQLException the SQL exception
	 */
	public EmTran selectEmTran(EmTran emTran) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEmTran", emTran);
	}
	
	/**
	 * EM_TRAN 등록.
	 *
	 * @param emTran the EmTran
	 * @throws SQLException the SQL exception
	 */
	public void insertEmTran(EmTran emTran) {
		getSession1().insert("com.plgrim.ncp.base.insertEmTran", emTran);
	}
	
	/**
	 * EM_TRAN 수정.
	 *
	 * @param emTran the EmTran
	 * @throws SQLException the SQL exception
	 */
	public int updateEmTran(EmTran emTran) {
		return getSession1().update("com.plgrim.ncp.base.updateEmTran", emTran);
	}
	
	/**
	 * EM_TRAN 삭제.
	 *
	 * @param emTran the EmTran
	 * @return the EmTran
	 * @throws SQLException the SQL exception
	 */
	public int deleteEmTran(EmTran emTran) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEmTran", emTran);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

