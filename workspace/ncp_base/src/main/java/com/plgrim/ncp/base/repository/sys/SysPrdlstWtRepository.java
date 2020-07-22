
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysPrdlstWt;

/**
 * The Class SysPrdlstWtRepository.
 */
@Repository
public class SysPrdlstWtRepository extends AbstractRepository {
	

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
	 * 시스템 품목 무게 상세 조회.
	 *
	 * @param sysPrdlstWt the SysPrdlstWt
	 * @return the SysPrdlstWt
	 * @throws SQLException the SQL exception
	 */
	public SysPrdlstWt selectSysPrdlstWt(SysPrdlstWt sysPrdlstWt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysPrdlstWt", sysPrdlstWt);
	}
	
	/**
	 * 시스템 품목 무게 등록.
	 *
	 * @param sysPrdlstWt the SysPrdlstWt
	 * @throws SQLException the SQL exception
	 */
	public void insertSysPrdlstWt(SysPrdlstWt sysPrdlstWt) {
		getSession1().insert("com.plgrim.ncp.base.insertSysPrdlstWt", sysPrdlstWt);
	}
	
	/**
	 * 시스템 품목 무게 수정.
	 *
	 * @param sysPrdlstWt the SysPrdlstWt
	 * @throws SQLException the SQL exception
	 */
	public int updateSysPrdlstWt(SysPrdlstWt sysPrdlstWt) {
		return getSession1().update("com.plgrim.ncp.base.updateSysPrdlstWt", sysPrdlstWt);
	}
	
	/**
	 * 시스템 품목 무게 삭제.
	 *
	 * @param sysPrdlstWt the SysPrdlstWt
	 * @return the SysPrdlstWt
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysPrdlstWt(SysPrdlstWt sysPrdlstWt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysPrdlstWt", sysPrdlstWt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

