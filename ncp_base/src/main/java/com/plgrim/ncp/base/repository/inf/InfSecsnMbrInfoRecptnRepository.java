
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
import com.plgrim.ncp.base.entities.datasource1.inf.InfSecsnMbrInfoRecptn;

/**
 * The Class InfSecsnMbrInfoRecptnRepository.
 */
@Repository
public class InfSecsnMbrInfoRecptnRepository extends AbstractRepository {
	

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
	 * 인터페이스 탈퇴 회원 정보 수신 상세 조회.
	 *
	 * @param infSecsnMbrInfoRecptn the InfSecsnMbrInfoRecptn
	 * @return the InfSecsnMbrInfoRecptn
	 * @throws SQLException the SQL exception
	 */
	public InfSecsnMbrInfoRecptn selectInfSecsnMbrInfoRecptn(InfSecsnMbrInfoRecptn infSecsnMbrInfoRecptn) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfSecsnMbrInfoRecptn", infSecsnMbrInfoRecptn);
	}
	
	/**
	 * 인터페이스 탈퇴 회원 정보 수신 등록.
	 *
	 * @param infSecsnMbrInfoRecptn the InfSecsnMbrInfoRecptn
	 * @throws SQLException the SQL exception
	 */
	public void insertInfSecsnMbrInfoRecptn(InfSecsnMbrInfoRecptn infSecsnMbrInfoRecptn) {
		getSession1().insert("com.plgrim.ncp.base.insertInfSecsnMbrInfoRecptn", infSecsnMbrInfoRecptn);
	}
	
	/**
	 * 인터페이스 탈퇴 회원 정보 수신 수정.
	 *
	 * @param infSecsnMbrInfoRecptn the InfSecsnMbrInfoRecptn
	 * @throws SQLException the SQL exception
	 */
	public int updateInfSecsnMbrInfoRecptn(InfSecsnMbrInfoRecptn infSecsnMbrInfoRecptn) {
		return getSession1().update("com.plgrim.ncp.base.updateInfSecsnMbrInfoRecptn", infSecsnMbrInfoRecptn);
	}
	
	/**
	 * 인터페이스 탈퇴 회원 정보 수신 삭제.
	 *
	 * @param infSecsnMbrInfoRecptn the InfSecsnMbrInfoRecptn
	 * @return the InfSecsnMbrInfoRecptn
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfSecsnMbrInfoRecptn(InfSecsnMbrInfoRecptn infSecsnMbrInfoRecptn) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfSecsnMbrInfoRecptn", infSecsnMbrInfoRecptn);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

