
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoUsefJobDetail;

/**
 * The Class MbrPsnlInfoUsefJobDetailRepository.
 */
@Repository
public class MbrPsnlInfoUsefJobDetailRepository extends AbstractRepository {
	

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
	 * 회원 개인정보 이용 업무 상세 상세 조회.
	 *
	 * @param mbrPsnlInfoUsefJobDetail the MbrPsnlInfoUsefJobDetail
	 * @return the MbrPsnlInfoUsefJobDetail
	 * @throws SQLException the SQL exception
	 */
	public MbrPsnlInfoUsefJobDetail selectMbrPsnlInfoUsefJobDetail(MbrPsnlInfoUsefJobDetail mbrPsnlInfoUsefJobDetail) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrPsnlInfoUsefJobDetail", mbrPsnlInfoUsefJobDetail);
	}
	
	/**
	 * 회원 개인정보 이용 업무 상세 등록.
	 *
	 * @param mbrPsnlInfoUsefJobDetail the MbrPsnlInfoUsefJobDetail
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrPsnlInfoUsefJobDetail(MbrPsnlInfoUsefJobDetail mbrPsnlInfoUsefJobDetail) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrPsnlInfoUsefJobDetail", mbrPsnlInfoUsefJobDetail);
	}
	
	/**
	 * 회원 개인정보 이용 업무 상세 수정.
	 *
	 * @param mbrPsnlInfoUsefJobDetail the MbrPsnlInfoUsefJobDetail
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrPsnlInfoUsefJobDetail(MbrPsnlInfoUsefJobDetail mbrPsnlInfoUsefJobDetail) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrPsnlInfoUsefJobDetail", mbrPsnlInfoUsefJobDetail);
	}
	
	/**
	 * 회원 개인정보 이용 업무 상세 삭제.
	 *
	 * @param mbrPsnlInfoUsefJobDetail the MbrPsnlInfoUsefJobDetail
	 * @return the MbrPsnlInfoUsefJobDetail
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrPsnlInfoUsefJobDetail(MbrPsnlInfoUsefJobDetail mbrPsnlInfoUsefJobDetail) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrPsnlInfoUsefJobDetail", mbrPsnlInfoUsefJobDetail);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

