
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
 * Generator(Generator)		2018-07-12                      
 */
package com.plgrim.ncp.base.repository.inf;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodItm;

/**
 * The Class InfGodItmRepository.
 */
@Repository
public class InfGodItmRepository extends AbstractRepository {
	

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
	 * 인터페이스 상품 단품 상세 조회.
	 *
	 * @param infGodItm the InfGodItm
	 * @return the InfGodItm
	 * @throws SQLException the SQL exception
	 */
	public InfGodItm selectInfGodItm(InfGodItm infGodItm) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfGodItm", infGodItm);
	}
	
	/**
	 * 인터페이스 상품 단품 등록.
	 *
	 * @param infGodItm the InfGodItm
	 * @throws SQLException the SQL exception
	 */
	public void insertInfGodItm(InfGodItm infGodItm) {
		getSession1().insert("com.plgrim.ncp.base.insertInfGodItm", infGodItm);
	}
	
	/**
	 * 인터페이스 상품 단품 수정.
	 *
	 * @param infGodItm the InfGodItm
	 * @throws SQLException the SQL exception
	 */
	public int updateInfGodItm(InfGodItm infGodItm) {
		return getSession1().update("com.plgrim.ncp.base.updateInfGodItm", infGodItm);
	}
	
	/**
	 * 인터페이스 상품 단품 삭제.
	 *
	 * @param infGodItm the InfGodItm
	 * @return the InfGodItm
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfGodItm(InfGodItm infGodItm) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfGodItm", infGodItm);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

