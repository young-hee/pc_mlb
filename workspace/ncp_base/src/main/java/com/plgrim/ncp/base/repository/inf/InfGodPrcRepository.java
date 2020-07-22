
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
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodPrc;

/**
 * The Class InfGodPrcRepository.
 */
@Repository
public class InfGodPrcRepository extends AbstractRepository {
	

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
	 * 인터페이스 상품 가격 상세 조회.
	 *
	 * @param infGodPrc the InfGodPrc
	 * @return the InfGodPrc
	 * @throws SQLException the SQL exception
	 */
	public InfGodPrc selectInfGodPrc(InfGodPrc infGodPrc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfGodPrc", infGodPrc);
	}
	
	/**
	 * 인터페이스 상품 가격 등록.
	 *
	 * @param infGodPrc the InfGodPrc
	 * @throws SQLException the SQL exception
	 */
	public void insertInfGodPrc(InfGodPrc infGodPrc) {
		getSession1().insert("com.plgrim.ncp.base.insertInfGodPrc", infGodPrc);
	}
	
	/**
	 * 인터페이스 상품 가격 수정.
	 *
	 * @param infGodPrc the InfGodPrc
	 * @throws SQLException the SQL exception
	 */
	public int updateInfGodPrc(InfGodPrc infGodPrc) {
		return getSession1().update("com.plgrim.ncp.base.updateInfGodPrc", infGodPrc);
	}
	
	/**
	 * 인터페이스 상품 가격 삭제.
	 *
	 * @param infGodPrc the InfGodPrc
	 * @return the InfGodPrc
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfGodPrc(InfGodPrc infGodPrc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfGodPrc", infGodPrc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

