
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
import com.plgrim.ncp.base.entities.datasource1.inf.InfDrmcMbrInfoRecptn;

/**
 * The Class InfDrmcMbrInfoRecptnRepository.
 */
@Repository
public class InfDrmcMbrInfoRecptnRepository extends AbstractRepository {
	

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
	 * 인터페이스 휴면 회원 정보 수신 상세 조회.
	 *
	 * @param infDrmcMbrInfoRecptn the InfDrmcMbrInfoRecptn
	 * @return the InfDrmcMbrInfoRecptn
	 * @throws SQLException the SQL exception
	 */
	public InfDrmcMbrInfoRecptn selectInfDrmcMbrInfoRecptn(InfDrmcMbrInfoRecptn infDrmcMbrInfoRecptn) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfDrmcMbrInfoRecptn", infDrmcMbrInfoRecptn);
	}
	
	/**
	 * 인터페이스 휴면 회원 정보 수신 등록.
	 *
	 * @param infDrmcMbrInfoRecptn the InfDrmcMbrInfoRecptn
	 * @throws SQLException the SQL exception
	 */
	public void insertInfDrmcMbrInfoRecptn(InfDrmcMbrInfoRecptn infDrmcMbrInfoRecptn) {
		getSession1().insert("com.plgrim.ncp.base.insertInfDrmcMbrInfoRecptn", infDrmcMbrInfoRecptn);
	}
	
	/**
	 * 인터페이스 휴면 회원 정보 수신 수정.
	 *
	 * @param infDrmcMbrInfoRecptn the InfDrmcMbrInfoRecptn
	 * @throws SQLException the SQL exception
	 */
	public int updateInfDrmcMbrInfoRecptn(InfDrmcMbrInfoRecptn infDrmcMbrInfoRecptn) {
		return getSession1().update("com.plgrim.ncp.base.updateInfDrmcMbrInfoRecptn", infDrmcMbrInfoRecptn);
	}
	
	/**
	 * 인터페이스 휴면 회원 정보 수신 삭제.
	 *
	 * @param infDrmcMbrInfoRecptn the InfDrmcMbrInfoRecptn
	 * @return the InfDrmcMbrInfoRecptn
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfDrmcMbrInfoRecptn(InfDrmcMbrInfoRecptn infDrmcMbrInfoRecptn) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfDrmcMbrInfoRecptn", infDrmcMbrInfoRecptn);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

