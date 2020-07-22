
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
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodFitLktbCnnc;

/**
 * The Class InfGodFitLktbCnncRepository.
 */
@Repository
public class InfGodFitLktbCnncRepository extends AbstractRepository {
	

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
	 * 상품 핏 조견표 연결 상세 조회.
	 *
	 * @param infGodFitLktbCnnc the InfGodFitLktbCnnc
	 * @return the InfGodFitLktbCnnc
	 * @throws SQLException the SQL exception
	 */
	public InfGodFitLktbCnnc selectInfGodFitLktbCnnc(InfGodFitLktbCnnc infGodFitLktbCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfGodFitLktbCnnc", infGodFitLktbCnnc);
	}
	
	/**
	 * 상품 핏 조견표 연결 등록.
	 *
	 * @param infGodFitLktbCnnc the InfGodFitLktbCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertInfGodFitLktbCnnc(InfGodFitLktbCnnc infGodFitLktbCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertInfGodFitLktbCnnc", infGodFitLktbCnnc);
	}
	
	/**
	 * 상품 핏 조견표 연결 수정.
	 *
	 * @param infGodFitLktbCnnc the InfGodFitLktbCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateInfGodFitLktbCnnc(InfGodFitLktbCnnc infGodFitLktbCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateInfGodFitLktbCnnc", infGodFitLktbCnnc);
	}
	
	/**
	 * 상품 핏 조견표 연결 삭제.
	 *
	 * @param infGodFitLktbCnnc the InfGodFitLktbCnnc
	 * @return the InfGodFitLktbCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfGodFitLktbCnnc(InfGodFitLktbCnnc infGodFitLktbCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfGodFitLktbCnnc", infGodFitLktbCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

