
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
import com.plgrim.ncp.base.entities.datasource1.inf.InfAffOrd;

/**
 * The Class InfAffOrdRepository.
 */
@Repository
public class InfAffOrdRepository extends AbstractRepository {
	

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
	 * 인터페이스 제휴 주문 상세 조회.
	 *
	 * @param infAffOrd the InfAffOrd
	 * @return the InfAffOrd
	 * @throws SQLException the SQL exception
	 */
	public InfAffOrd selectInfAffOrd(InfAffOrd infAffOrd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfAffOrd", infAffOrd);
	}
	
	/**
	 * 인터페이스 제휴 주문 등록.
	 *
	 * @param infAffOrd the InfAffOrd
	 * @throws SQLException the SQL exception
	 */
	public void insertInfAffOrd(InfAffOrd infAffOrd) {
		getSession1().insert("com.plgrim.ncp.base.insertInfAffOrd", infAffOrd);
	}
	
	/**
	 * 인터페이스 제휴 주문 수정.
	 *
	 * @param infAffOrd the InfAffOrd
	 * @throws SQLException the SQL exception
	 */
	public int updateInfAffOrd(InfAffOrd infAffOrd) {
		return getSession1().update("com.plgrim.ncp.base.updateInfAffOrd", infAffOrd);
	}
	
	/**
	 * 인터페이스 제휴 주문 삭제.
	 *
	 * @param infAffOrd the InfAffOrd
	 * @return the InfAffOrd
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfAffOrd(InfAffOrd infAffOrd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfAffOrd", infAffOrd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

