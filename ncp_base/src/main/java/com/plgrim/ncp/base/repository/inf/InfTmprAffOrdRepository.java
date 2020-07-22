
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
import com.plgrim.ncp.base.entities.datasource1.inf.InfTmprAffOrd;

/**
 * The Class InfTmprAffOrdRepository.
 */
@Repository
public class InfTmprAffOrdRepository extends AbstractRepository {
	

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
	 * 인터페이스 임시 제휴 주문 상세 조회.
	 *
	 * @param infTmprAffOrd the InfTmprAffOrd
	 * @return the InfTmprAffOrd
	 * @throws SQLException the SQL exception
	 */
	public InfTmprAffOrd selectInfTmprAffOrd(InfTmprAffOrd infTmprAffOrd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfTmprAffOrd", infTmprAffOrd);
	}
	
	/**
	 * 인터페이스 임시 제휴 주문 등록.
	 *
	 * @param infTmprAffOrd the InfTmprAffOrd
	 * @throws SQLException the SQL exception
	 */
	public void insertInfTmprAffOrd(InfTmprAffOrd infTmprAffOrd) {
		getSession1().insert("com.plgrim.ncp.base.insertInfTmprAffOrd", infTmprAffOrd);
	}
	
	/**
	 * 인터페이스 임시 제휴 주문 수정.
	 *
	 * @param infTmprAffOrd the InfTmprAffOrd
	 * @throws SQLException the SQL exception
	 */
	public int updateInfTmprAffOrd(InfTmprAffOrd infTmprAffOrd) {
		return getSession1().update("com.plgrim.ncp.base.updateInfTmprAffOrd", infTmprAffOrd);
	}
	
	/**
	 * 인터페이스 임시 제휴 주문 삭제.
	 *
	 * @param infTmprAffOrd the InfTmprAffOrd
	 * @return the InfTmprAffOrd
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfTmprAffOrd(InfTmprAffOrd infTmprAffOrd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfTmprAffOrd", infTmprAffOrd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

