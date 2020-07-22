
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoUsefTgt;

/**
 * The Class MbrPsnlInfoUsefTgtRepository.
 */
@Repository
public class MbrPsnlInfoUsefTgtRepository extends AbstractRepository {
	

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
	 * 회원 개인정보 이용 대상 상세 조회.
	 *
	 * @param mbrPsnlInfoUsefTgt the MbrPsnlInfoUsefTgt
	 * @return the MbrPsnlInfoUsefTgt
	 * @throws SQLException the SQL exception
	 */
	public MbrPsnlInfoUsefTgt selectMbrPsnlInfoUsefTgt(MbrPsnlInfoUsefTgt mbrPsnlInfoUsefTgt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrPsnlInfoUsefTgt", mbrPsnlInfoUsefTgt);
	}
	
	/**
	 * 회원 개인정보 이용 대상 등록.
	 *
	 * @param mbrPsnlInfoUsefTgt the MbrPsnlInfoUsefTgt
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrPsnlInfoUsefTgt(MbrPsnlInfoUsefTgt mbrPsnlInfoUsefTgt) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrPsnlInfoUsefTgt", mbrPsnlInfoUsefTgt);
	}
	
	/**
	 * 회원 개인정보 이용 대상 수정.
	 *
	 * @param mbrPsnlInfoUsefTgt the MbrPsnlInfoUsefTgt
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrPsnlInfoUsefTgt(MbrPsnlInfoUsefTgt mbrPsnlInfoUsefTgt) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrPsnlInfoUsefTgt", mbrPsnlInfoUsefTgt);
	}
	
	/**
	 * 회원 개인정보 이용 대상 삭제.
	 *
	 * @param mbrPsnlInfoUsefTgt the MbrPsnlInfoUsefTgt
	 * @return the MbrPsnlInfoUsefTgt
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrPsnlInfoUsefTgt(MbrPsnlInfoUsefTgt mbrPsnlInfoUsefTgt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrPsnlInfoUsefTgt", mbrPsnlInfoUsefTgt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

