
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
import com.plgrim.ncp.base.entities.datasource1.inf.InfAffOrdErr;

/**
 * The Class InfAffOrdErrRepository.
 */
@Repository
public class InfAffOrdErrRepository extends AbstractRepository {
	

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
	 * 인터페이스 제휴 주문 오류 상세 조회.
	 *
	 * @param infAffOrdErr the InfAffOrdErr
	 * @return the InfAffOrdErr
	 * @throws SQLException the SQL exception
	 */
	public InfAffOrdErr selectInfAffOrdErr(InfAffOrdErr infAffOrdErr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfAffOrdErr", infAffOrdErr);
	}
	
	/**
	 * 인터페이스 제휴 주문 오류 등록.
	 *
	 * @param infAffOrdErr the InfAffOrdErr
	 * @throws SQLException the SQL exception
	 */
	public void insertInfAffOrdErr(InfAffOrdErr infAffOrdErr) {
		getSession1().insert("com.plgrim.ncp.base.insertInfAffOrdErr", infAffOrdErr);
	}
	
	/**
	 * 인터페이스 제휴 주문 오류 수정.
	 *
	 * @param infAffOrdErr the InfAffOrdErr
	 * @throws SQLException the SQL exception
	 */
	public int updateInfAffOrdErr(InfAffOrdErr infAffOrdErr) {
		return getSession1().update("com.plgrim.ncp.base.updateInfAffOrdErr", infAffOrdErr);
	}
	
	/**
	 * 인터페이스 제휴 주문 오류 삭제.
	 *
	 * @param infAffOrdErr the InfAffOrdErr
	 * @return the InfAffOrdErr
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfAffOrdErr(InfAffOrdErr infAffOrdErr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfAffOrdErr", infAffOrdErr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

