
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysMallCntc;

/**
 * The Class SysMallCntcRepository.
 */
@Repository
public class SysMallCntcRepository extends AbstractRepository {
	

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
	 * 시스템 몰 연계 상세 조회.
	 *
	 * @param sysMallCntc the SysMallCntc
	 * @return the SysMallCntc
	 * @throws SQLException the SQL exception
	 */
	public SysMallCntc selectSysMallCntc(SysMallCntc sysMallCntc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysMallCntc", sysMallCntc);
	}
	
	/**
	 * 시스템 몰 연계 등록.
	 *
	 * @param sysMallCntc the SysMallCntc
	 * @throws SQLException the SQL exception
	 */
	public void insertSysMallCntc(SysMallCntc sysMallCntc) {
		getSession1().insert("com.plgrim.ncp.base.insertSysMallCntc", sysMallCntc);
	}
	
	/**
	 * 시스템 몰 연계 수정.
	 *
	 * @param sysMallCntc the SysMallCntc
	 * @throws SQLException the SQL exception
	 */
	public int updateSysMallCntc(SysMallCntc sysMallCntc) {
		return getSession1().update("com.plgrim.ncp.base.updateSysMallCntc", sysMallCntc);
	}
	
	/**
	 * 시스템 몰 연계 삭제.
	 *
	 * @param sysMallCntc the SysMallCntc
	 * @return the SysMallCntc
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysMallCntc(SysMallCntc sysMallCntc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysMallCntc", sysMallCntc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

