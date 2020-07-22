
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysSmsEmailTxt;

/**
 * The Class SysSmsEmailTxtRepository.
 */
@Repository
public class SysSmsEmailTxtRepository extends AbstractRepository {
	

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
	 * 시스템 SMS 이메일 문구 상세 조회.
	 *
	 * @param sysSmsEmailTxt the SysSmsEmailTxt
	 * @return the SysSmsEmailTxt
	 * @throws SQLException the SQL exception
	 */
	public SysSmsEmailTxt selectSysSmsEmailTxt(SysSmsEmailTxt sysSmsEmailTxt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysSmsEmailTxt", sysSmsEmailTxt);
	}
	
	/**
	 * 시스템 SMS 이메일 문구 등록.
	 *
	 * @param sysSmsEmailTxt the SysSmsEmailTxt
	 * @throws SQLException the SQL exception
	 */
	public void insertSysSmsEmailTxt(SysSmsEmailTxt sysSmsEmailTxt) {
		getSession1().insert("com.plgrim.ncp.base.insertSysSmsEmailTxt", sysSmsEmailTxt);
	}
	
	/**
	 * 시스템 SMS 이메일 문구 수정.
	 *
	 * @param sysSmsEmailTxt the SysSmsEmailTxt
	 * @throws SQLException the SQL exception
	 */
	public int updateSysSmsEmailTxt(SysSmsEmailTxt sysSmsEmailTxt) {
		return getSession1().update("com.plgrim.ncp.base.updateSysSmsEmailTxt", sysSmsEmailTxt);
	}
	
	/**
	 * 시스템 SMS 이메일 문구 삭제.
	 *
	 * @param sysSmsEmailTxt the SysSmsEmailTxt
	 * @return the SysSmsEmailTxt
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysSmsEmailTxt(SysSmsEmailTxt sysSmsEmailTxt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysSmsEmailTxt", sysSmsEmailTxt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

