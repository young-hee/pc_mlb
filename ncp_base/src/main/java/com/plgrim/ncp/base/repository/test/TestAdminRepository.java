
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
package com.plgrim.ncp.base.repository.test;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.test.TestAdmin;

/**
 * The Class TestAdminRepository.
 */
@Repository
public class TestAdminRepository extends AbstractRepository {
	

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
	 * 테스트 관리자 상세 조회.
	 *
	 * @param testAdmin the TestAdmin
	 * @return the TestAdmin
	 * @throws SQLException the SQL exception
	 */
	public TestAdmin selectTestAdmin(TestAdmin testAdmin) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectTestAdmin", testAdmin);
	}
	
	/**
	 * 테스트 관리자 등록.
	 *
	 * @param testAdmin the TestAdmin
	 * @throws SQLException the SQL exception
	 */
	public void insertTestAdmin(TestAdmin testAdmin) {
		getSession1().insert("com.plgrim.ncp.base.insertTestAdmin", testAdmin);
	}
	
	/**
	 * 테스트 관리자 수정.
	 *
	 * @param testAdmin the TestAdmin
	 * @throws SQLException the SQL exception
	 */
	public int updateTestAdmin(TestAdmin testAdmin) {
		return getSession1().update("com.plgrim.ncp.base.updateTestAdmin", testAdmin);
	}
	
	/**
	 * 테스트 관리자 삭제.
	 *
	 * @param testAdmin the TestAdmin
	 * @return the TestAdmin
	 * @throws SQLException the SQL exception
	 */
	public int deleteTestAdmin(TestAdmin testAdmin) {
		return getSession1().delete("com.plgrim.ncp.base.deleteTestAdmin", testAdmin);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

