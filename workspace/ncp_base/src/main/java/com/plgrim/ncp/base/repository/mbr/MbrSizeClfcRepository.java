
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
package com.plgrim.ncp.base.repository.mbr;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSizeClfc;

/**
 * The Class MbrSizeClfcRepository.
 */
@Repository
public class MbrSizeClfcRepository extends AbstractRepository {
	

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
	 * 회원 사이즈 분류 상세 조회.
	 *
	 * @param mbrSizeClfc the MbrSizeClfc
	 * @return the MbrSizeClfc
	 * @throws SQLException the SQL exception
	 */
	public MbrSizeClfc selectMbrSizeClfc(MbrSizeClfc mbrSizeClfc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrSizeClfc", mbrSizeClfc);
	}
	
	/**
	 * 회원 사이즈 분류 등록.
	 *
	 * @param mbrSizeClfc the MbrSizeClfc
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrSizeClfc(MbrSizeClfc mbrSizeClfc) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrSizeClfc", mbrSizeClfc);
	}
	
	/**
	 * 회원 사이즈 분류 수정.
	 *
	 * @param mbrSizeClfc the MbrSizeClfc
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrSizeClfc(MbrSizeClfc mbrSizeClfc) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrSizeClfc", mbrSizeClfc);
	}
	
	/**
	 * 회원 사이즈 분류 삭제.
	 *
	 * @param mbrSizeClfc the MbrSizeClfc
	 * @return the MbrSizeClfc
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrSizeClfc(MbrSizeClfc mbrSizeClfc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrSizeClfc", mbrSizeClfc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

