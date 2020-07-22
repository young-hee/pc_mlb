
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysWordDic;

/**
 * The Class SysWordDicRepository.
 */
@Repository
public class SysWordDicRepository extends AbstractRepository {
	

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
	 * 시스템 용어 사전 상세 조회.
	 *
	 * @param sysWordDic the SysWordDic
	 * @return the SysWordDic
	 * @throws SQLException the SQL exception
	 */
	public SysWordDic selectSysWordDic(SysWordDic sysWordDic) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysWordDic", sysWordDic);
	}
	
	/**
	 * 시스템 용어 사전 등록.
	 *
	 * @param sysWordDic the SysWordDic
	 * @throws SQLException the SQL exception
	 */
	public void insertSysWordDic(SysWordDic sysWordDic) {
		getSession1().insert("com.plgrim.ncp.base.insertSysWordDic", sysWordDic);
	}
	
	/**
	 * 시스템 용어 사전 수정.
	 *
	 * @param sysWordDic the SysWordDic
	 * @throws SQLException the SQL exception
	 */
	public int updateSysWordDic(SysWordDic sysWordDic) {
		return getSession1().update("com.plgrim.ncp.base.updateSysWordDic", sysWordDic);
	}
	
	/**
	 * 시스템 용어 사전 삭제.
	 *
	 * @param sysWordDic the SysWordDic
	 * @return the SysWordDic
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysWordDic(SysWordDic sysWordDic) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysWordDic", sysWordDic);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

