
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;

/**
 * The Class MbrDlvspRepository.
 */
@Repository
public class MbrDlvspRepository extends AbstractRepository {
	

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
	 * 회원 배송지 상세 조회.
	 *
	 * @param mbrDlvsp the MbrDlvsp
	 * @return the MbrDlvsp
	 * @throws SQLException the SQL exception
	 */
	public MbrDlvsp selectMbrDlvsp(MbrDlvsp mbrDlvsp) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrDlvsp", mbrDlvsp);
	}
	
	/**
	 * 회원 배송지 등록.
	 *
	 * @param mbrDlvsp the MbrDlvsp
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrDlvsp(MbrDlvsp mbrDlvsp) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrDlvsp", mbrDlvsp);
	}
	
	/**
	 * 회원 배송지 수정.
	 *
	 * @param mbrDlvsp the MbrDlvsp
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrDlvsp(MbrDlvsp mbrDlvsp) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrDlvsp", mbrDlvsp);
	}
	
	/**
	 * 회원 배송지 삭제.
	 *
	 * @param mbrDlvsp the MbrDlvsp
	 * @return the MbrDlvsp
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrDlvsp(MbrDlvsp mbrDlvsp) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrDlvsp", mbrDlvsp);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

