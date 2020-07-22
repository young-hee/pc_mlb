
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndHist;

/**
 * The Class SysBrndHistRepository.
 */
@Repository
public class SysBrndHistRepository extends AbstractRepository {
	

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
	 * 시스템 브랜드 이력 상세 조회.
	 *
	 * @param sysBrndHist the SysBrndHist
	 * @return the SysBrndHist
	 * @throws SQLException the SQL exception
	 */
	public SysBrndHist selectSysBrndHist(SysBrndHist sysBrndHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysBrndHist", sysBrndHist);
	}
	
	/**
	 * 시스템 브랜드 이력 등록.
	 *
	 * @param sysBrndHist the SysBrndHist
	 * @throws SQLException the SQL exception
	 */
	public void insertSysBrndHist(SysBrndHist sysBrndHist) {
		getSession1().insert("com.plgrim.ncp.base.insertSysBrndHist", sysBrndHist);
	}
	
	/**
	 * 시스템 브랜드 이력 수정.
	 *
	 * @param sysBrndHist the SysBrndHist
	 * @throws SQLException the SQL exception
	 */
	public int updateSysBrndHist(SysBrndHist sysBrndHist) {
		return getSession1().update("com.plgrim.ncp.base.updateSysBrndHist", sysBrndHist);
	}
	
	/**
	 * 시스템 브랜드 이력 삭제.
	 *
	 * @param sysBrndHist the SysBrndHist
	 * @return the SysBrndHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysBrndHist(SysBrndHist sysBrndHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysBrndHist", sysBrndHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

