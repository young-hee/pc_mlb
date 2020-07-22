
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;

/**
 * The Class MbrCrtfcRepository.
 */
@Repository
public class MbrCrtfcRepository extends AbstractRepository {
	

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
	 * 회원 인증 상세 조회.
	 *
	 * @param mbrCrtfc the MbrCrtfc
	 * @return the MbrCrtfc
	 * @throws SQLException the SQL exception
	 */
	public MbrCrtfc selectMbrCrtfc(MbrCrtfc mbrCrtfc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrCrtfc", mbrCrtfc);
	}
	
	/**
	 * 회원 인증 등록.
	 *
	 * @param mbrCrtfc the MbrCrtfc
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrCrtfc(MbrCrtfc mbrCrtfc) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrCrtfc", mbrCrtfc);
	}
	
	/**
	 * 회원 인증 수정.
	 *
	 * @param mbrCrtfc the MbrCrtfc
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrCrtfc(MbrCrtfc mbrCrtfc) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrCrtfc", mbrCrtfc);
	}
	
	/**
	 * 회원 인증 삭제.
	 *
	 * @param mbrCrtfc the MbrCrtfc
	 * @return the MbrCrtfc
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrCrtfc(MbrCrtfc mbrCrtfc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrCrtfc", mbrCrtfc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

