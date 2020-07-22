
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
import com.plgrim.ncp.base.entities.datasource1.inf.InfTmprLagQtyOrd;

/**
 * The Class InfTmprLagQtyOrdRepository.
 */
@Repository
public class InfTmprLagQtyOrdRepository extends AbstractRepository {
	

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
	 * 인터페이스 임시 대량 주문 상세 조회.
	 *
	 * @param infTmprLagQtyOrd the InfTmprLagQtyOrd
	 * @return the InfTmprLagQtyOrd
	 * @throws SQLException the SQL exception
	 */
	public InfTmprLagQtyOrd selectInfTmprLagQtyOrd(InfTmprLagQtyOrd infTmprLagQtyOrd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfTmprLagQtyOrd", infTmprLagQtyOrd);
	}
	
	/**
	 * 인터페이스 임시 대량 주문 등록.
	 *
	 * @param infTmprLagQtyOrd the InfTmprLagQtyOrd
	 * @throws SQLException the SQL exception
	 */
	public void insertInfTmprLagQtyOrd(InfTmprLagQtyOrd infTmprLagQtyOrd) {
		getSession1().insert("com.plgrim.ncp.base.insertInfTmprLagQtyOrd", infTmprLagQtyOrd);
	}
	
	/**
	 * 인터페이스 임시 대량 주문 수정.
	 *
	 * @param infTmprLagQtyOrd the InfTmprLagQtyOrd
	 * @throws SQLException the SQL exception
	 */
	public int updateInfTmprLagQtyOrd(InfTmprLagQtyOrd infTmprLagQtyOrd) {
		return getSession1().update("com.plgrim.ncp.base.updateInfTmprLagQtyOrd", infTmprLagQtyOrd);
	}
	
	/**
	 * 인터페이스 임시 대량 주문 삭제.
	 *
	 * @param infTmprLagQtyOrd the InfTmprLagQtyOrd
	 * @return the InfTmprLagQtyOrd
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfTmprLagQtyOrd(InfTmprLagQtyOrd infTmprLagQtyOrd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfTmprLagQtyOrd", infTmprLagQtyOrd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

