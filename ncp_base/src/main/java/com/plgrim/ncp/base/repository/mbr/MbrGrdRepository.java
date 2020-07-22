
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrGrd;

/**
 * The Class MbrGrdRepository.
 */
@Repository
public class MbrGrdRepository extends AbstractRepository {
	

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
	 * 회원 등급 상세 조회.
	 *
	 * @param mbrGrd the MbrGrd
	 * @return the MbrGrd
	 * @throws SQLException the SQL exception
	 */
	public MbrGrd selectMbrGrd(MbrGrd mbrGrd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrGrd", mbrGrd);
	}
	
	/**
	 * 회원 등급 등록.
	 *
	 * @param mbrGrd the MbrGrd
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrGrd(MbrGrd mbrGrd) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrGrd", mbrGrd);
	}
	
	/**
	 * 회원 등급 수정.
	 *
	 * @param mbrGrd the MbrGrd
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrGrd(MbrGrd mbrGrd) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrGrd", mbrGrd);
	}
	
	/**
	 * 회원 등급 삭제.
	 *
	 * @param mbrGrd the MbrGrd
	 * @return the MbrGrd
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrGrd(MbrGrd mbrGrd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrGrd", mbrGrd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

