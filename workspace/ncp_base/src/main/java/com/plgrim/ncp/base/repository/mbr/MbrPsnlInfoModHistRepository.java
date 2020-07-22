
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;

/**
 * The Class MbrPsnlInfoModHistRepository.
 */
@Repository
public class MbrPsnlInfoModHistRepository extends AbstractRepository {
	

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
	 * 회원 개인정보 변경 이력 상세 조회.
	 *
	 * @param mbrPsnlInfoModHist the MbrPsnlInfoModHist
	 * @return the MbrPsnlInfoModHist
	 * @throws SQLException the SQL exception
	 */
	public MbrPsnlInfoModHist selectMbrPsnlInfoModHist(MbrPsnlInfoModHist mbrPsnlInfoModHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrPsnlInfoModHist", mbrPsnlInfoModHist);
	}
	
	/**
	 * 회원 개인정보 변경 이력 등록.
	 *
	 * @param mbrPsnlInfoModHist the MbrPsnlInfoModHist
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrPsnlInfoModHist(MbrPsnlInfoModHist mbrPsnlInfoModHist) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrPsnlInfoModHist", mbrPsnlInfoModHist);
	}
	
	/**
	 * 회원 개인정보 변경 이력 수정.
	 *
	 * @param mbrPsnlInfoModHist the MbrPsnlInfoModHist
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrPsnlInfoModHist(MbrPsnlInfoModHist mbrPsnlInfoModHist) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrPsnlInfoModHist", mbrPsnlInfoModHist);
	}
	
	/**
	 * 회원 개인정보 변경 이력 삭제.
	 *
	 * @param mbrPsnlInfoModHist the MbrPsnlInfoModHist
	 * @return the MbrPsnlInfoModHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrPsnlInfoModHist(MbrPsnlInfoModHist mbrPsnlInfoModHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrPsnlInfoModHist", mbrPsnlInfoModHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

