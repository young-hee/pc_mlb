
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
import com.plgrim.ncp.base.entities.datasource1.inf.InfAllAddrLnmInfo;

/**
 * The Class InfAllAddrLnmInfoRepository.
 */
@Repository
public class InfAllAddrLnmInfoRepository extends AbstractRepository {
	

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
	 * 인터페이스 전체 주소 지번 정보 상세 조회.
	 *
	 * @param infAllAddrLnmInfo the InfAllAddrLnmInfo
	 * @return the InfAllAddrLnmInfo
	 * @throws SQLException the SQL exception
	 */
	public InfAllAddrLnmInfo selectInfAllAddrLnmInfo(InfAllAddrLnmInfo infAllAddrLnmInfo) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfAllAddrLnmInfo", infAllAddrLnmInfo);
	}
	
	/**
	 * 인터페이스 전체 주소 지번 정보 등록.
	 *
	 * @param infAllAddrLnmInfo the InfAllAddrLnmInfo
	 * @throws SQLException the SQL exception
	 */
	public void insertInfAllAddrLnmInfo(InfAllAddrLnmInfo infAllAddrLnmInfo) {
		getSession1().insert("com.plgrim.ncp.base.insertInfAllAddrLnmInfo", infAllAddrLnmInfo);
	}
	
	/**
	 * 인터페이스 전체 주소 지번 정보 수정.
	 *
	 * @param infAllAddrLnmInfo the InfAllAddrLnmInfo
	 * @throws SQLException the SQL exception
	 */
	public int updateInfAllAddrLnmInfo(InfAllAddrLnmInfo infAllAddrLnmInfo) {
		return getSession1().update("com.plgrim.ncp.base.updateInfAllAddrLnmInfo", infAllAddrLnmInfo);
	}
	
	/**
	 * 인터페이스 전체 주소 지번 정보 삭제.
	 *
	 * @param infAllAddrLnmInfo the InfAllAddrLnmInfo
	 * @return the InfAllAddrLnmInfo
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfAllAddrLnmInfo(InfAllAddrLnmInfo infAllAddrLnmInfo) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfAllAddrLnmInfo", infAllAddrLnmInfo);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

