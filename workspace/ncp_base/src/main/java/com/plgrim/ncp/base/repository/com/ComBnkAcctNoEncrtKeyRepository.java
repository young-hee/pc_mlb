
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
 * Generator(Generator)		2018-04-13                      
 */
package com.plgrim.ncp.base.repository.com;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource3.com.ComBnkAcctNoEncrtKey;

/**
 * The Class ComBnkAcctNoEncrtKeyRepository.
 */
@Repository
public class ComBnkAcctNoEncrtKeyRepository extends AbstractRepository {
	

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
	 * 업체 계좌번호 암호화 키 상세 조회.
	 *
	 * @param comBnkAcctNoEncrtKey the ComBnkAcctNoEncrtKey
	 * @return the ComBnkAcctNoEncrtKey
	 * @throws SQLException the SQL exception
	 */
	public ComBnkAcctNoEncrtKey selectComBnkAcctNoEncrtKey(ComBnkAcctNoEncrtKey comBnkAcctNoEncrtKey) throws Exception {
		return getSession3().selectOne("com.plgrim.ncp.base.selectComBnkAcctNoEncrtKey", comBnkAcctNoEncrtKey);
	}
	
	/**
	 * 업체 계좌번호 암호화 키 등록.
	 *
	 * @param comBnkAcctNoEncrtKey the ComBnkAcctNoEncrtKey
	 * @throws SQLException the SQL exception
	 */
	public void insertComBnkAcctNoEncrtKey(ComBnkAcctNoEncrtKey comBnkAcctNoEncrtKey) throws Exception {
		getSession3().insert("com.plgrim.ncp.base.insertComBnkAcctNoEncrtKey", comBnkAcctNoEncrtKey);
	}
	
	/**
	 * 업체 계좌번호 암호화 키 수정.
	 *
	 * @param comBnkAcctNoEncrtKey the ComBnkAcctNoEncrtKey
	 * @throws SQLException the SQL exception
	 */
	public int updateComBnkAcctNoEncrtKey(ComBnkAcctNoEncrtKey comBnkAcctNoEncrtKey) throws Exception {
		return getSession3().update("com.plgrim.ncp.base.updateComBnkAcctNoEncrtKey", comBnkAcctNoEncrtKey);
	}
	
	/**
	 * 업체 계좌번호 암호화 키 삭제.
	 *
	 * @param comBnkAcctNoEncrtKey the ComBnkAcctNoEncrtKey
	 * @return the ComBnkAcctNoEncrtKey
	 * @throws SQLException the SQL exception
	 */
	public int deleteComBnkAcctNoEncrtKey(ComBnkAcctNoEncrtKey comBnkAcctNoEncrtKey) throws Exception {
		return getSession3().delete("com.plgrim.ncp.base.deleteComBnkAcctNoEncrtKey", comBnkAcctNoEncrtKey);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

