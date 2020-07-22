
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
 * Generator(Generator)		2018-06-26                      
 */
package com.plgrim.ncp.base.repository.inf;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodMatr;

/**
 * The Class InfGodMatrRepository.
 */
@Repository
public class InfGodMatrRepository extends AbstractRepository {
	

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
	 * 인터페이스 상품 소재 상세 조회.
	 *
	 * @param infGodMatr the InfGodMatr
	 * @return the InfGodMatr
	 * @throws SQLException the SQL exception
	 */
	public InfGodMatr selectInfGodMatr(InfGodMatr infGodMatr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfGodMatr", infGodMatr);
	}
	
	/**
	 * 인터페이스 상품 소재 등록.
	 *
	 * @param infGodMatr the InfGodMatr
	 * @throws SQLException the SQL exception
	 */
	public void insertInfGodMatr(InfGodMatr infGodMatr) {
		getSession1().insert("com.plgrim.ncp.base.insertInfGodMatr", infGodMatr);
	}
	
	/**
	 * 인터페이스 상품 소재 수정.
	 *
	 * @param infGodMatr the InfGodMatr
	 * @throws SQLException the SQL exception
	 */
	public int updateInfGodMatr(InfGodMatr infGodMatr) {
		return getSession1().update("com.plgrim.ncp.base.updateInfGodMatr", infGodMatr);
	}
	
	/**
	 * 인터페이스 상품 소재 삭제.
	 *
	 * @param infGodMatr the InfGodMatr
	 * @return the InfGodMatr
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfGodMatr(InfGodMatr infGodMatr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfGodMatr", infGodMatr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

