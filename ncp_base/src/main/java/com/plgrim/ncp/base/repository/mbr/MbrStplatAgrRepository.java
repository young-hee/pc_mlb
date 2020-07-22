
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrStplatAgr;

/**
 * The Class MbrStplatAgrRepository.
 */
@Repository
public class MbrStplatAgrRepository extends AbstractRepository {
	

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
	 * 회원 약관 동의 상세 조회.
	 *
	 * @param mbrStplatAgr the MbrStplatAgr
	 * @return the MbrStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public MbrStplatAgr selectMbrStplatAgr(MbrStplatAgr mbrStplatAgr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrStplatAgr", mbrStplatAgr);
	}
	
	/**
	 * 회원 약관 동의 등록.
	 *
	 * @param mbrStplatAgr the MbrStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrStplatAgr(MbrStplatAgr mbrStplatAgr) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrStplatAgr", mbrStplatAgr);
	}
	
	/**
	 * 회원 약관 동의 수정.
	 *
	 * @param mbrStplatAgr the MbrStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrStplatAgr(MbrStplatAgr mbrStplatAgr) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrStplatAgr", mbrStplatAgr);
	}
	
	/**
	 * 회원 약관 동의 삭제.
	 *
	 * @param mbrStplatAgr the MbrStplatAgr
	 * @return the MbrStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrStplatAgr(MbrStplatAgr mbrStplatAgr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrStplatAgr", mbrStplatAgr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

