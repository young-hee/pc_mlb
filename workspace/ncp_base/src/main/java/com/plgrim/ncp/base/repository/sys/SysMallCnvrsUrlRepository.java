
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysMallCnvrsUrl;

/**
 * The Class SysMallCnvrsUrlRepository.
 */
@Repository
public class SysMallCnvrsUrlRepository extends AbstractRepository {
	

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
	 * 시스템 몰 전환 URL 상세 조회.
	 *
	 * @param sysMallCnvrsUrl the SysMallCnvrsUrl
	 * @return the SysMallCnvrsUrl
	 * @throws SQLException the SQL exception
	 */
	public SysMallCnvrsUrl selectSysMallCnvrsUrl(SysMallCnvrsUrl sysMallCnvrsUrl) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysMallCnvrsUrl", sysMallCnvrsUrl);
	}
	
	/**
	 * 시스템 몰 전환 URL 등록.
	 *
	 * @param sysMallCnvrsUrl the SysMallCnvrsUrl
	 * @throws SQLException the SQL exception
	 */
	public void insertSysMallCnvrsUrl(SysMallCnvrsUrl sysMallCnvrsUrl) {
		getSession1().insert("com.plgrim.ncp.base.insertSysMallCnvrsUrl", sysMallCnvrsUrl);
	}
	
	/**
	 * 시스템 몰 전환 URL 수정.
	 *
	 * @param sysMallCnvrsUrl the SysMallCnvrsUrl
	 * @throws SQLException the SQL exception
	 */
	public int updateSysMallCnvrsUrl(SysMallCnvrsUrl sysMallCnvrsUrl) {
		return getSession1().update("com.plgrim.ncp.base.updateSysMallCnvrsUrl", sysMallCnvrsUrl);
	}
	
	/**
	 * 시스템 몰 전환 URL 삭제.
	 *
	 * @param sysMallCnvrsUrl the SysMallCnvrsUrl
	 * @return the SysMallCnvrsUrl
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysMallCnvrsUrl(SysMallCnvrsUrl sysMallCnvrsUrl) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysMallCnvrsUrl", sysMallCnvrsUrl);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

