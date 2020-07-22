
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
import com.plgrim.ncp.base.entities.datasource1.inf.InfAllAddrBuldInfo;

/**
 * The Class InfAllAddrBuldInfoRepository.
 */
@Repository
public class InfAllAddrBuldInfoRepository extends AbstractRepository {
	

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
	 * 인터페이스 전체 주소 건물 정보 상세 조회.
	 *
	 * @param infAllAddrBuldInfo the InfAllAddrBuldInfo
	 * @return the InfAllAddrBuldInfo
	 * @throws SQLException the SQL exception
	 */
	public InfAllAddrBuldInfo selectInfAllAddrBuldInfo(InfAllAddrBuldInfo infAllAddrBuldInfo) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfAllAddrBuldInfo", infAllAddrBuldInfo);
	}
	
	/**
	 * 인터페이스 전체 주소 건물 정보 등록.
	 *
	 * @param infAllAddrBuldInfo the InfAllAddrBuldInfo
	 * @throws SQLException the SQL exception
	 */
	public void insertInfAllAddrBuldInfo(InfAllAddrBuldInfo infAllAddrBuldInfo) {
		getSession1().insert("com.plgrim.ncp.base.insertInfAllAddrBuldInfo", infAllAddrBuldInfo);
	}
	
	/**
	 * 인터페이스 전체 주소 건물 정보 수정.
	 *
	 * @param infAllAddrBuldInfo the InfAllAddrBuldInfo
	 * @throws SQLException the SQL exception
	 */
	public int updateInfAllAddrBuldInfo(InfAllAddrBuldInfo infAllAddrBuldInfo) {
		return getSession1().update("com.plgrim.ncp.base.updateInfAllAddrBuldInfo", infAllAddrBuldInfo);
	}
	
	/**
	 * 인터페이스 전체 주소 건물 정보 삭제.
	 *
	 * @param infAllAddrBuldInfo the InfAllAddrBuldInfo
	 * @return the InfAllAddrBuldInfo
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfAllAddrBuldInfo(InfAllAddrBuldInfo infAllAddrBuldInfo) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfAllAddrBuldInfo", infAllAddrBuldInfo);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

