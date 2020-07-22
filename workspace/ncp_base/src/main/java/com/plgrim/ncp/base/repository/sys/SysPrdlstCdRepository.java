
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysPrdlstCd;

/**
 * The Class SysPrdlstCdRepository.
 */
@Repository
public class SysPrdlstCdRepository extends AbstractRepository {
	

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
	 * 시스템 품목 코드 상세 조회.
	 *
	 * @param sysPrdlstCd the SysPrdlstCd
	 * @return the SysPrdlstCd
	 * @throws SQLException the SQL exception
	 */
	public SysPrdlstCd selectSysPrdlstCd(SysPrdlstCd sysPrdlstCd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysPrdlstCd", sysPrdlstCd);
	}
	
	/**
	 * 시스템 품목 코드 등록.
	 *
	 * @param sysPrdlstCd the SysPrdlstCd
	 * @throws SQLException the SQL exception
	 */
	public void insertSysPrdlstCd(SysPrdlstCd sysPrdlstCd) {
		getSession1().insert("com.plgrim.ncp.base.insertSysPrdlstCd", sysPrdlstCd);
	}
	
	/**
	 * 시스템 품목 코드 수정.
	 *
	 * @param sysPrdlstCd the SysPrdlstCd
	 * @throws SQLException the SQL exception
	 */
	public int updateSysPrdlstCd(SysPrdlstCd sysPrdlstCd) {
		return getSession1().update("com.plgrim.ncp.base.updateSysPrdlstCd", sysPrdlstCd);
	}
	
	/**
	 * 시스템 품목 코드 삭제.
	 *
	 * @param sysPrdlstCd the SysPrdlstCd
	 * @return the SysPrdlstCd
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysPrdlstCd(SysPrdlstCd sysPrdlstCd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysPrdlstCd", sysPrdlstCd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

