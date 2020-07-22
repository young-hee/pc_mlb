
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
package com.plgrim.ncp.base.repository.com;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.com.ComQdlvOper;

/**
 * The Class ComQdlvOperRepository.
 */
@Repository
public class ComQdlvOperRepository extends AbstractRepository {
	

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
	 * 업체 퀵배송 운영 상세 조회.
	 *
	 * @param comQdlvOper the ComQdlvOper
	 * @return the ComQdlvOper
	 * @throws SQLException the SQL exception
	 */
	public ComQdlvOper selectComQdlvOper(ComQdlvOper comQdlvOper) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComQdlvOper", comQdlvOper);
	}
	
	/**
	 * 업체 퀵배송 운영 등록.
	 *
	 * @param comQdlvOper the ComQdlvOper
	 * @throws SQLException the SQL exception
	 */
	public void insertComQdlvOper(ComQdlvOper comQdlvOper) {
		getSession1().insert("com.plgrim.ncp.base.insertComQdlvOper", comQdlvOper);
	}
	
	/**
	 * 업체 퀵배송 운영 수정.
	 *
	 * @param comQdlvOper the ComQdlvOper
	 * @throws SQLException the SQL exception
	 */
	public int updateComQdlvOper(ComQdlvOper comQdlvOper) {
		return getSession1().update("com.plgrim.ncp.base.updateComQdlvOper", comQdlvOper);
	}
	
	/**
	 * 업체 퀵배송 운영 삭제.
	 *
	 * @param comQdlvOper the ComQdlvOper
	 * @return the ComQdlvOper
	 * @throws SQLException the SQL exception
	 */
	public int deleteComQdlvOper(ComQdlvOper comQdlvOper) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComQdlvOper", comQdlvOper);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

