
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrndFeeHist;

/**
 * The Class SysShopBrndFeeHistRepository.
 */
@Repository
public class SysShopBrndFeeHistRepository extends AbstractRepository {
	

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
	 * 시스템 매장 브랜드 수수료 이력 상세 조회.
	 *
	 * @param sysShopBrndFeeHist the SysShopBrndFeeHist
	 * @return the SysShopBrndFeeHist
	 * @throws SQLException the SQL exception
	 */
	public SysShopBrndFeeHist selectSysShopBrndFeeHist(SysShopBrndFeeHist sysShopBrndFeeHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysShopBrndFeeHist", sysShopBrndFeeHist);
	}
	
	/**
	 * 시스템 매장 브랜드 수수료 이력 등록.
	 *
	 * @param sysShopBrndFeeHist the SysShopBrndFeeHist
	 * @throws SQLException the SQL exception
	 */
	public void insertSysShopBrndFeeHist(SysShopBrndFeeHist sysShopBrndFeeHist) {
		getSession1().insert("com.plgrim.ncp.base.insertSysShopBrndFeeHist", sysShopBrndFeeHist);
	}
	
	/**
	 * 시스템 매장 브랜드 수수료 이력 수정.
	 *
	 * @param sysShopBrndFeeHist the SysShopBrndFeeHist
	 * @throws SQLException the SQL exception
	 */
	public int updateSysShopBrndFeeHist(SysShopBrndFeeHist sysShopBrndFeeHist) {
		return getSession1().update("com.plgrim.ncp.base.updateSysShopBrndFeeHist", sysShopBrndFeeHist);
	}
	
	/**
	 * 시스템 매장 브랜드 수수료 이력 삭제.
	 *
	 * @param sysShopBrndFeeHist the SysShopBrndFeeHist
	 * @return the SysShopBrndFeeHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysShopBrndFeeHist(SysShopBrndFeeHist sysShopBrndFeeHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysShopBrndFeeHist", sysShopBrndFeeHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

