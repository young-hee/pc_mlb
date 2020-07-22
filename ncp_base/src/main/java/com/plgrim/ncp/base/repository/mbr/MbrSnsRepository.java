
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
package com.plgrim.ncp.base.repository.mbr;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSns;

/**
 * The Class MbrSnsRepository.
 */
@Repository
public class MbrSnsRepository extends AbstractRepository {
	

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
	 * 회원 SNS 상세 조회.
	 *
	 * @param mbrSns the MbrSns
	 * @return the MbrSns
	 * @throws SQLException the SQL exception
	 */
	public MbrSns selectMbrSns(MbrSns mbrSns) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrSns", mbrSns);
	}
	
	/**
	 * 회원 SNS 등록.
	 *
	 * @param mbrSns the MbrSns
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrSns(MbrSns mbrSns) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrSns", mbrSns);
	}
	
	/**
	 * 회원 SNS 수정.
	 *
	 * @param mbrSns the MbrSns
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrSns(MbrSns mbrSns) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrSns", mbrSns);
	}
	
	/**
	 * 회원 SNS 삭제.
	 *
	 * @param mbrSns the MbrSns
	 * @return the MbrSns
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrSns(MbrSns mbrSns) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrSns", mbrSns);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

