
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
 * Generator(Generator)		2018-06-11                      
 */
package com.plgrim.ncp.base.repository.inf;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.inf.InfMbrGrdInfoRecptn;

/**
 * The Class InfMbrGrdInfoRecptnRepository.
 */
@Repository
public class InfMbrGrdInfoRecptnRepository extends AbstractRepository {
	

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
	 * 인터페이스 회원 등급 정보 수신 상세 조회.
	 *
	 * @param infMbrGrdInfoRecptn the InfMbrGrdInfoRecptn
	 * @return the InfMbrGrdInfoRecptn
	 * @throws SQLException the SQL exception
	 */
	public InfMbrGrdInfoRecptn selectInfMbrGrdInfoRecptn(InfMbrGrdInfoRecptn infMbrGrdInfoRecptn) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfMbrGrdInfoRecptn", infMbrGrdInfoRecptn);
	}
	
	/**
	 * 인터페이스 회원 등급 정보 수신 등록.
	 *
	 * @param infMbrGrdInfoRecptn the InfMbrGrdInfoRecptn
	 * @throws SQLException the SQL exception
	 */
	public void insertInfMbrGrdInfoRecptn(InfMbrGrdInfoRecptn infMbrGrdInfoRecptn) {
		getSession1().insert("com.plgrim.ncp.base.insertInfMbrGrdInfoRecptn", infMbrGrdInfoRecptn);
	}
	
	/**
	 * 인터페이스 회원 등급 정보 수신 수정.
	 *
	 * @param infMbrGrdInfoRecptn the InfMbrGrdInfoRecptn
	 * @throws SQLException the SQL exception
	 */
	public int updateInfMbrGrdInfoRecptn(InfMbrGrdInfoRecptn infMbrGrdInfoRecptn) {
		return getSession1().update("com.plgrim.ncp.base.updateInfMbrGrdInfoRecptn", infMbrGrdInfoRecptn);
	}
	
	/**
	 * 인터페이스 회원 등급 정보 수신 삭제.
	 *
	 * @param infMbrGrdInfoRecptn the InfMbrGrdInfoRecptn
	 * @return the InfMbrGrdInfoRecptn
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfMbrGrdInfoRecptn(InfMbrGrdInfoRecptn infMbrGrdInfoRecptn) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfMbrGrdInfoRecptn", infMbrGrdInfoRecptn);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

