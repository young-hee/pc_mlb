
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
import com.plgrim.ncp.base.entities.datasource1.inf.InfAllAddrRoadnmInfo;

/**
 * The Class InfAllAddrRoadnmInfoRepository.
 */
@Repository
public class InfAllAddrRoadnmInfoRepository extends AbstractRepository {
	

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
	 * 인터페이스 전체 주소 도로명 정보 상세 조회.
	 *
	 * @param infAllAddrRoadnmInfo the InfAllAddrRoadnmInfo
	 * @return the InfAllAddrRoadnmInfo
	 * @throws SQLException the SQL exception
	 */
	public InfAllAddrRoadnmInfo selectInfAllAddrRoadnmInfo(InfAllAddrRoadnmInfo infAllAddrRoadnmInfo) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfAllAddrRoadnmInfo", infAllAddrRoadnmInfo);
	}
	
	/**
	 * 인터페이스 전체 주소 도로명 정보 등록.
	 *
	 * @param infAllAddrRoadnmInfo the InfAllAddrRoadnmInfo
	 * @throws SQLException the SQL exception
	 */
	public void insertInfAllAddrRoadnmInfo(InfAllAddrRoadnmInfo infAllAddrRoadnmInfo) {
		getSession1().insert("com.plgrim.ncp.base.insertInfAllAddrRoadnmInfo", infAllAddrRoadnmInfo);
	}
	
	/**
	 * 인터페이스 전체 주소 도로명 정보 수정.
	 *
	 * @param infAllAddrRoadnmInfo the InfAllAddrRoadnmInfo
	 * @throws SQLException the SQL exception
	 */
	public int updateInfAllAddrRoadnmInfo(InfAllAddrRoadnmInfo infAllAddrRoadnmInfo) {
		return getSession1().update("com.plgrim.ncp.base.updateInfAllAddrRoadnmInfo", infAllAddrRoadnmInfo);
	}
	
	/**
	 * 인터페이스 전체 주소 도로명 정보 삭제.
	 *
	 * @param infAllAddrRoadnmInfo the InfAllAddrRoadnmInfo
	 * @return the InfAllAddrRoadnmInfo
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfAllAddrRoadnmInfo(InfAllAddrRoadnmInfo infAllAddrRoadnmInfo) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfAllAddrRoadnmInfo", infAllAddrRoadnmInfo);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

