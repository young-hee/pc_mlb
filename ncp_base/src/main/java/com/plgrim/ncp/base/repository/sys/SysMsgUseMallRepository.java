
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
package com.plgrim.ncp.base.repository.sys;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMsgUseMall;

/**
 * The Class SysMsgUseMallRepository.
 */
@Repository
public class SysMsgUseMallRepository extends AbstractRepository {
	

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
	 * 시스템 메시지 사용 몰 상세 조회.
	 *
	 * @param sysMsgUseMall the SysMsgUseMall
	 * @return the SysMsgUseMall
	 * @throws SQLException the SQL exception
	 */
	public SysMsgUseMall selectSysMsgUseMall(SysMsgUseMall sysMsgUseMall) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysMsgUseMall", sysMsgUseMall);
	}
	
	/**
	 * 시스템 메시지 사용 몰 등록.
	 *
	 * @param sysMsgUseMall the SysMsgUseMall
	 * @throws SQLException the SQL exception
	 */
	public void insertSysMsgUseMall(SysMsgUseMall sysMsgUseMall) {
		getSession1().insert("com.plgrim.ncp.base.insertSysMsgUseMall", sysMsgUseMall);
	}
	
	/**
	 * 시스템 메시지 사용 몰 수정.
	 *
	 * @param sysMsgUseMall the SysMsgUseMall
	 * @throws SQLException the SQL exception
	 */
	public int updateSysMsgUseMall(SysMsgUseMall sysMsgUseMall) {
		return getSession1().update("com.plgrim.ncp.base.updateSysMsgUseMall", sysMsgUseMall);
	}
	
	/**
	 * 시스템 메시지 사용 몰 삭제.
	 *
	 * @param sysMsgUseMall the SysMsgUseMall
	 * @return the SysMsgUseMall
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysMsgUseMall(SysMsgUseMall sysMsgUseMall) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysMsgUseMall", sysMsgUseMall);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

