
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoUsefMbd;

/**
 * The Class MbrPsnlInfoUsefMbdRepository.
 */
@Repository
public class MbrPsnlInfoUsefMbdRepository extends AbstractRepository {
	

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
	 * 회원 개인정보 이용 주체 상세 조회.
	 *
	 * @param mbrPsnlInfoUsefMbd the MbrPsnlInfoUsefMbd
	 * @return the MbrPsnlInfoUsefMbd
	 * @throws SQLException the SQL exception
	 */
	public MbrPsnlInfoUsefMbd selectMbrPsnlInfoUsefMbd(MbrPsnlInfoUsefMbd mbrPsnlInfoUsefMbd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrPsnlInfoUsefMbd", mbrPsnlInfoUsefMbd);
	}
	
	/**
	 * 회원 개인정보 이용 주체 등록.
	 *
	 * @param mbrPsnlInfoUsefMbd the MbrPsnlInfoUsefMbd
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrPsnlInfoUsefMbd(MbrPsnlInfoUsefMbd mbrPsnlInfoUsefMbd) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrPsnlInfoUsefMbd", mbrPsnlInfoUsefMbd);
	}
	
	/**
	 * 회원 개인정보 이용 주체 수정.
	 *
	 * @param mbrPsnlInfoUsefMbd the MbrPsnlInfoUsefMbd
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrPsnlInfoUsefMbd(MbrPsnlInfoUsefMbd mbrPsnlInfoUsefMbd) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrPsnlInfoUsefMbd", mbrPsnlInfoUsefMbd);
	}
	
	/**
	 * 회원 개인정보 이용 주체 삭제.
	 *
	 * @param mbrPsnlInfoUsefMbd the MbrPsnlInfoUsefMbd
	 * @return the MbrPsnlInfoUsefMbd
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrPsnlInfoUsefMbd(MbrPsnlInfoUsefMbd mbrPsnlInfoUsefMbd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrPsnlInfoUsefMbd", mbrPsnlInfoUsefMbd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

