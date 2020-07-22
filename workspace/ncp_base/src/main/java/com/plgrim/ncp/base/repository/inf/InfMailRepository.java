
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
 * Generator(Generator)		2018-07-18                      
 */
package com.plgrim.ncp.base.repository.inf;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.inf.InfMail;

/**
 * The Class InfMailRepository.
 */
@Repository
public class InfMailRepository extends AbstractRepository {
	

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
	 * 인터페이스 메일 상세 조회.
	 *
	 * @param infMail the InfMail
	 * @return the InfMail
	 * @throws SQLException the SQL exception
	 */
	public InfMail selectInfMail(InfMail infMail) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfMail", infMail);
	}
	
	/**
	 * 인터페이스 메일 등록.
	 *
	 * @param infMail the InfMail
	 * @throws SQLException the SQL exception
	 */
	public void insertInfMail(InfMail infMail) {
		getSession1().insert("com.plgrim.ncp.base.insertInfMail", infMail);
	}
	
	/**
	 * 인터페이스 메일 수정.
	 *
	 * @param infMail the InfMail
	 * @throws SQLException the SQL exception
	 */
	public int updateInfMail(InfMail infMail) {
		return getSession1().update("com.plgrim.ncp.base.updateInfMail", infMail);
	}
	
	/**
	 * 인터페이스 메일 삭제.
	 *
	 * @param infMail the InfMail
	 * @return the InfMail
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfMail(InfMail infMail) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfMail", infMail);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

