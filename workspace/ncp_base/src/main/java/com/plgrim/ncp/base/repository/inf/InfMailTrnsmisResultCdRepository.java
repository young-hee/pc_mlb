
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
import com.plgrim.ncp.base.entities.datasource1.inf.InfMailTrnsmisResultCd;

/**
 * The Class InfMailTrnsmisResultCdRepository.
 */
@Repository
public class InfMailTrnsmisResultCdRepository extends AbstractRepository {
	

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
	 * 인터페이스 메일 전송 결과 코드 상세 조회.
	 *
	 * @param infMailTrnsmisResultCd the InfMailTrnsmisResultCd
	 * @return the InfMailTrnsmisResultCd
	 * @throws SQLException the SQL exception
	 */
	public InfMailTrnsmisResultCd selectInfMailTrnsmisResultCd(InfMailTrnsmisResultCd infMailTrnsmisResultCd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfMailTrnsmisResultCd", infMailTrnsmisResultCd);
	}
	
	/**
	 * 인터페이스 메일 전송 결과 코드 등록.
	 *
	 * @param infMailTrnsmisResultCd the InfMailTrnsmisResultCd
	 * @throws SQLException the SQL exception
	 */
	public void insertInfMailTrnsmisResultCd(InfMailTrnsmisResultCd infMailTrnsmisResultCd) {
		getSession1().insert("com.plgrim.ncp.base.insertInfMailTrnsmisResultCd", infMailTrnsmisResultCd);
	}
	
	/**
	 * 인터페이스 메일 전송 결과 코드 수정.
	 *
	 * @param infMailTrnsmisResultCd the InfMailTrnsmisResultCd
	 * @throws SQLException the SQL exception
	 */
	public int updateInfMailTrnsmisResultCd(InfMailTrnsmisResultCd infMailTrnsmisResultCd) {
		return getSession1().update("com.plgrim.ncp.base.updateInfMailTrnsmisResultCd", infMailTrnsmisResultCd);
	}
	
	/**
	 * 인터페이스 메일 전송 결과 코드 삭제.
	 *
	 * @param infMailTrnsmisResultCd the InfMailTrnsmisResultCd
	 * @return the InfMailTrnsmisResultCd
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfMailTrnsmisResultCd(InfMailTrnsmisResultCd infMailTrnsmisResultCd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfMailTrnsmisResultCd", infMailTrnsmisResultCd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

