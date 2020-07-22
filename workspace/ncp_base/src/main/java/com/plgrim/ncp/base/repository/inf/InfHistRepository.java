
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
package com.plgrim.ncp.base.repository.inf;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.inf.InfHist;

/**
 * The Class InfHistRepository.
 */
@Repository
public class InfHistRepository extends AbstractRepository {
	

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
	 * 인터페이스 이력 상세 조회.
	 *
	 * @param infHist the InfHist
	 * @return the InfHist
	 * @throws SQLException the SQL exception
	 */
	public InfHist selectInfHist(InfHist infHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfHist", infHist);
	}
	
	/**
	 * 인터페이스 이력 등록.
	 *
	 * @param infHist the InfHist
	 * @throws SQLException the SQL exception
	 */
	public void insertInfHist(InfHist infHist) {
		getSession1().insert("com.plgrim.ncp.base.insertInfHist", infHist);
	}
	
	/**
	 * 인터페이스 이력 수정.
	 *
	 * @param infHist the InfHist
	 * @throws SQLException the SQL exception
	 */
	public int updateInfHist(InfHist infHist) {
		return getSession1().update("com.plgrim.ncp.base.updateInfHist", infHist);
	}
	
	/**
	 * 인터페이스 이력 삭제.
	 *
	 * @param infHist the InfHist
	 * @return the InfHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfHist(InfHist infHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfHist", infHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

