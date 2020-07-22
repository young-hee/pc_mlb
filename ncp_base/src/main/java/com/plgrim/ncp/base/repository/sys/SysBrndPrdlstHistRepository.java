
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndPrdlstHist;

/**
 * The Class SysBrndPrdlstHistRepository.
 */
@Repository
public class SysBrndPrdlstHistRepository extends AbstractRepository {
	

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
	 * 시스템 브랜드 품목 이력 상세 조회.
	 *
	 * @param sysBrndPrdlstHist the SysBrndPrdlstHist
	 * @return the SysBrndPrdlstHist
	 * @throws SQLException the SQL exception
	 */
	public SysBrndPrdlstHist selectSysBrndPrdlstHist(SysBrndPrdlstHist sysBrndPrdlstHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysBrndPrdlstHist", sysBrndPrdlstHist);
	}
	
	/**
	 * 시스템 브랜드 품목 이력 등록.
	 *
	 * @param sysBrndPrdlstHist the SysBrndPrdlstHist
	 * @throws SQLException the SQL exception
	 */
	public void insertSysBrndPrdlstHist(SysBrndPrdlstHist sysBrndPrdlstHist) {
		getSession1().insert("com.plgrim.ncp.base.insertSysBrndPrdlstHist", sysBrndPrdlstHist);
	}
	
	/**
	 * 시스템 브랜드 품목 이력 수정.
	 *
	 * @param sysBrndPrdlstHist the SysBrndPrdlstHist
	 * @throws SQLException the SQL exception
	 */
	public int updateSysBrndPrdlstHist(SysBrndPrdlstHist sysBrndPrdlstHist) {
		return getSession1().update("com.plgrim.ncp.base.updateSysBrndPrdlstHist", sysBrndPrdlstHist);
	}
	
	/**
	 * 시스템 브랜드 품목 이력 삭제.
	 *
	 * @param sysBrndPrdlstHist the SysBrndPrdlstHist
	 * @return the SysBrndPrdlstHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysBrndPrdlstHist(SysBrndPrdlstHist sysBrndPrdlstHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysBrndPrdlstHist", sysBrndPrdlstHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

