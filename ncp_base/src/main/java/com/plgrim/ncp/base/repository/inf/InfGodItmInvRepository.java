
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
 * Generator(Generator)		2018-05-31                      
 */
package com.plgrim.ncp.base.repository.inf;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodItmInv;

/**
 * The Class InfGodItmInvRepository.
 */
@Repository
public class InfGodItmInvRepository extends AbstractRepository {
	

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
	 * 인터페이스 상품 단품 재고 상세 조회.
	 *
	 * @param infGodItmInv the InfGodItmInv
	 * @return the InfGodItmInv
	 * @throws SQLException the SQL exception
	 */
	public InfGodItmInv selectInfGodItmInv(InfGodItmInv infGodItmInv) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfGodItmInv", infGodItmInv);
	}
	
	/**
	 * 인터페이스 상품 단품 재고 등록.
	 *
	 * @param infGodItmInv the InfGodItmInv
	 * @throws SQLException the SQL exception
	 */
	public void insertInfGodItmInv(InfGodItmInv infGodItmInv) {
		getSession1().insert("com.plgrim.ncp.base.insertInfGodItmInv", infGodItmInv);
	}
	
	/**
	 * 인터페이스 상품 단품 재고 수정.
	 *
	 * @param infGodItmInv the InfGodItmInv
	 * @throws SQLException the SQL exception
	 */
	public int updateInfGodItmInv(InfGodItmInv infGodItmInv) {
		return getSession1().update("com.plgrim.ncp.base.updateInfGodItmInv", infGodItmInv);
	}
	
	/**
	 * 인터페이스 상품 단품 재고 삭제.
	 *
	 * @param infGodItmInv the InfGodItmInv
	 * @return the InfGodItmInv
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfGodItmInv(InfGodItmInv infGodItmInv) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfGodItmInv", infGodItmInv);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

