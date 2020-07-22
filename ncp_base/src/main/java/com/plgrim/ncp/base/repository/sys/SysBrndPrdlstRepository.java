
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
 * Generator(Generator)		2018-06-14                      
 */
package com.plgrim.ncp.base.repository.sys;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndPrdlst;

/**
 * The Class SysBrndPrdlstRepository.
 */
@Repository
public class SysBrndPrdlstRepository extends AbstractRepository {
	

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
	 * 시스템 브랜드 품목 상세 조회.
	 *
	 * @param sysBrndPrdlst the SysBrndPrdlst
	 * @return the SysBrndPrdlst
	 * @throws SQLException the SQL exception
	 */
	public SysBrndPrdlst selectSysBrndPrdlst(SysBrndPrdlst sysBrndPrdlst) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysBrndPrdlst", sysBrndPrdlst);
	}
	
	/**
	 * 시스템 브랜드 품목 등록.
	 *
	 * @param sysBrndPrdlst the SysBrndPrdlst
	 * @throws SQLException the SQL exception
	 */
	public void insertSysBrndPrdlst(SysBrndPrdlst sysBrndPrdlst) {
		getSession1().insert("com.plgrim.ncp.base.insertSysBrndPrdlst", sysBrndPrdlst);
	}
	
	/**
	 * 시스템 브랜드 품목 수정.
	 *
	 * @param sysBrndPrdlst the SysBrndPrdlst
	 * @throws SQLException the SQL exception
	 */
	public int updateSysBrndPrdlst(SysBrndPrdlst sysBrndPrdlst) {
		return getSession1().update("com.plgrim.ncp.base.updateSysBrndPrdlst", sysBrndPrdlst);
	}
	
	/**
	 * 시스템 브랜드 품목 삭제.
	 *
	 * @param sysBrndPrdlst the SysBrndPrdlst
	 * @return the SysBrndPrdlst
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysBrndPrdlst(SysBrndPrdlst sysBrndPrdlst) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysBrndPrdlst", sysBrndPrdlst);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

