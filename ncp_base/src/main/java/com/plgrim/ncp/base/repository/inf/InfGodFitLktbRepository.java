
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
 * Generator(Generator)		2019-11-15                      
 */
package com.plgrim.ncp.base.repository.inf;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodFitLktb;

/**
 * The Class InfGodFitLktbRepository.
 */
@Repository
public class InfGodFitLktbRepository extends AbstractRepository {
	

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
	 * 상품 핏 조견표 상세 조회.
	 *
	 * @param infGodFitLktb the InfGodFitLktb
	 * @return the InfGodFitLktb
	 * @throws SQLException the SQL exception
	 */
	public InfGodFitLktb selectInfGodFitLktb(InfGodFitLktb infGodFitLktb) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfGodFitLktb", infGodFitLktb);
	}
	
	/**
	 * 상품 핏 조견표 등록.
	 *
	 * @param infGodFitLktb the InfGodFitLktb
	 * @throws SQLException the SQL exception
	 */
	public void insertInfGodFitLktb(InfGodFitLktb infGodFitLktb) {
		getSession1().insert("com.plgrim.ncp.base.insertInfGodFitLktb", infGodFitLktb);
	}
	
	/**
	 * 상품 핏 조견표 수정.
	 *
	 * @param infGodFitLktb the InfGodFitLktb
	 * @throws SQLException the SQL exception
	 */
	public int updateInfGodFitLktb(InfGodFitLktb infGodFitLktb) {
		return getSession1().update("com.plgrim.ncp.base.updateInfGodFitLktb", infGodFitLktb);
	}
	
	/**
	 * 상품 핏 조견표 삭제.
	 *
	 * @param infGodFitLktb the InfGodFitLktb
	 * @return the InfGodFitLktb
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfGodFitLktb(InfGodFitLktb infGodFitLktb) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfGodFitLktb", infGodFitLktb);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

