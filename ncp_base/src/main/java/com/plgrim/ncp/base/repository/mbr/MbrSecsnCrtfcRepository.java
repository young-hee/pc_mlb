
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSecsnCrtfc;

/**
 * The Class MbrSecsnCrtfcRepository.
 */
@Repository
public class MbrSecsnCrtfcRepository extends AbstractRepository {
	

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
	 * 회원 탈퇴 인증 상세 조회.
	 *
	 * @param mbrSecsnCrtfc the MbrSecsnCrtfc
	 * @return the MbrSecsnCrtfc
	 * @throws SQLException the SQL exception
	 */
	public MbrSecsnCrtfc selectMbrSecsnCrtfc(MbrSecsnCrtfc mbrSecsnCrtfc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrSecsnCrtfc", mbrSecsnCrtfc);
	}
	
	/**
	 * 회원 탈퇴 인증 등록.
	 *
	 * @param mbrSecsnCrtfc the MbrSecsnCrtfc
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrSecsnCrtfc(MbrSecsnCrtfc mbrSecsnCrtfc) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrSecsnCrtfc", mbrSecsnCrtfc);
	}
	
	/**
	 * 회원 탈퇴 인증 수정.
	 *
	 * @param mbrSecsnCrtfc the MbrSecsnCrtfc
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrSecsnCrtfc(MbrSecsnCrtfc mbrSecsnCrtfc) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrSecsnCrtfc", mbrSecsnCrtfc);
	}
	
	/**
	 * 회원 탈퇴 인증 삭제.
	 *
	 * @param mbrSecsnCrtfc the MbrSecsnCrtfc
	 * @return the MbrSecsnCrtfc
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrSecsnCrtfc(MbrSecsnCrtfc mbrSecsnCrtfc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrSecsnCrtfc", mbrSecsnCrtfc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

