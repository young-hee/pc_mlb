
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
import com.plgrim.ncp.base.entities.datasource1.inf.InfRltmGodInv;

/**
 * The Class InfRltmGodInvRepository.
 */
@Repository
public class InfRltmGodInvRepository extends AbstractRepository {
	

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
	 * 인터페이스 실시간 상품 재고 상세 조회.
	 *
	 * @param infRltmGodInv the InfRltmGodInv
	 * @return the InfRltmGodInv
	 * @throws SQLException the SQL exception
	 */
	public InfRltmGodInv selectInfRltmGodInv(InfRltmGodInv infRltmGodInv) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfRltmGodInv", infRltmGodInv);
	}
	
	/**
	 * 인터페이스 실시간 상품 재고 등록.
	 *
	 * @param infRltmGodInv the InfRltmGodInv
	 * @throws SQLException the SQL exception
	 */
	public void insertInfRltmGodInv(InfRltmGodInv infRltmGodInv) {
		getSession1().insert("com.plgrim.ncp.base.insertInfRltmGodInv", infRltmGodInv);
	}
	
	/**
	 * 인터페이스 실시간 상품 재고 수정.
	 *
	 * @param infRltmGodInv the InfRltmGodInv
	 * @throws SQLException the SQL exception
	 */
	public int updateInfRltmGodInv(InfRltmGodInv infRltmGodInv) {
		return getSession1().update("com.plgrim.ncp.base.updateInfRltmGodInv", infRltmGodInv);
	}
	
	/**
	 * 인터페이스 실시간 상품 재고 삭제.
	 *
	 * @param infRltmGodInv the InfRltmGodInv
	 * @return the InfRltmGodInv
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfRltmGodInv(InfRltmGodInv infRltmGodInv) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfRltmGodInv", infRltmGodInv);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

