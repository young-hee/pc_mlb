
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysFoNotiMall;

/**
 * The Class SysFoNotiMallRepository.
 */
@Repository
public class SysFoNotiMallRepository extends AbstractRepository {
	

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
	 * 시스템 FO 공지 몰 상세 조회.
	 *
	 * @param sysFoNotiMall the SysFoNotiMall
	 * @return the SysFoNotiMall
	 * @throws SQLException the SQL exception
	 */
	public SysFoNotiMall selectSysFoNotiMall(SysFoNotiMall sysFoNotiMall) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysFoNotiMall", sysFoNotiMall);
	}
	
	/**
	 * 시스템 FO 공지 몰 등록.
	 *
	 * @param sysFoNotiMall the SysFoNotiMall
	 * @throws SQLException the SQL exception
	 */
	public void insertSysFoNotiMall(SysFoNotiMall sysFoNotiMall) {
		getSession1().insert("com.plgrim.ncp.base.insertSysFoNotiMall", sysFoNotiMall);
	}
	
	/**
	 * 시스템 FO 공지 몰 수정.
	 *
	 * @param sysFoNotiMall the SysFoNotiMall
	 * @throws SQLException the SQL exception
	 */
	public int updateSysFoNotiMall(SysFoNotiMall sysFoNotiMall) {
		return getSession1().update("com.plgrim.ncp.base.updateSysFoNotiMall", sysFoNotiMall);
	}
	
	/**
	 * 시스템 FO 공지 몰 삭제.
	 *
	 * @param sysFoNotiMall the SysFoNotiMall
	 * @return the SysFoNotiMall
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysFoNotiMall(SysFoNotiMall sysFoNotiMall) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysFoNotiMall", sysFoNotiMall);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

