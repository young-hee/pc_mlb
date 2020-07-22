
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
package com.plgrim.ncp.base.repository.clm;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource3.clm.ClmBnkAcctNoEncrtKey;

/**
 * The Class ClmBnkAcctNoEncrtKeyRepository.
 */
@Repository
public class ClmBnkAcctNoEncrtKeyRepository extends AbstractRepository {
	

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
	 * 클레임 계좌번호 암호화 키 상세 조회.
	 *
	 * @param clmBnkAcctNoEncrtKey the ClmBnkAcctNoEncrtKey
	 * @return the ClmBnkAcctNoEncrtKey
	 * @throws SQLException the SQL exception
	 */
	public ClmBnkAcctNoEncrtKey selectClmBnkAcctNoEncrtKey(ClmBnkAcctNoEncrtKey clmBnkAcctNoEncrtKey) throws Exception {
		return getSession3().selectOne("com.plgrim.ncp.base.selectClmBnkAcctNoEncrtKey", clmBnkAcctNoEncrtKey);
	}
	
	/**
	 * 클레임 계좌번호 암호화 키 등록.
	 *
	 * @param clmBnkAcctNoEncrtKey the ClmBnkAcctNoEncrtKey
	 * @throws SQLException the SQL exception
	 */
	public void insertClmBnkAcctNoEncrtKey(ClmBnkAcctNoEncrtKey clmBnkAcctNoEncrtKey) throws Exception {
		getSession3().insert("com.plgrim.ncp.base.insertClmBnkAcctNoEncrtKey", clmBnkAcctNoEncrtKey);
	}
	
	/**
	 * 클레임 계좌번호 암호화 키 수정.
	 *
	 * @param clmBnkAcctNoEncrtKey the ClmBnkAcctNoEncrtKey
	 * @throws SQLException the SQL exception
	 */
	public int updateClmBnkAcctNoEncrtKey(ClmBnkAcctNoEncrtKey clmBnkAcctNoEncrtKey) throws Exception {
		return getSession3().update("com.plgrim.ncp.base.updateClmBnkAcctNoEncrtKey", clmBnkAcctNoEncrtKey);
	}
	
	/**
	 * 클레임 계좌번호 암호화 키 삭제.
	 *
	 * @param clmBnkAcctNoEncrtKey the ClmBnkAcctNoEncrtKey
	 * @return the ClmBnkAcctNoEncrtKey
	 * @throws SQLException the SQL exception
	 */
	public int deleteClmBnkAcctNoEncrtKey(ClmBnkAcctNoEncrtKey clmBnkAcctNoEncrtKey) throws Exception {
		return getSession3().delete("com.plgrim.ncp.base.deleteClmBnkAcctNoEncrtKey", clmBnkAcctNoEncrtKey);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

