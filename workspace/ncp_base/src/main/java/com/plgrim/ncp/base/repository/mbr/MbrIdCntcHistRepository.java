
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntcHist;

/**
 * The Class MbrIdCntcHistRepository.
 */
@Repository
public class MbrIdCntcHistRepository extends AbstractRepository {
	

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
	 * 회원 ID 연계 이력 상세 조회.
	 *
	 * @param mbrIdCntcHist the MbrIdCntcHist
	 * @return the MbrIdCntcHist
	 * @throws SQLException the SQL exception
	 */
	public MbrIdCntcHist selectMbrIdCntcHist(MbrIdCntcHist mbrIdCntcHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrIdCntcHist", mbrIdCntcHist);
	}
	
	/**
	 * 회원 ID 연계 이력 등록.
	 *
	 * @param mbrIdCntcHist the MbrIdCntcHist
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrIdCntcHist(MbrIdCntcHist mbrIdCntcHist) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrIdCntcHist", mbrIdCntcHist);
	}
	
	/**
	 * 회원 ID 연계 이력 수정.
	 *
	 * @param mbrIdCntcHist the MbrIdCntcHist
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrIdCntcHist(MbrIdCntcHist mbrIdCntcHist) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrIdCntcHist", mbrIdCntcHist);
	}
	
	/**
	 * 회원 ID 연계 이력 삭제.
	 *
	 * @param mbrIdCntcHist the MbrIdCntcHist
	 * @return the MbrIdCntcHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrIdCntcHist(MbrIdCntcHist mbrIdCntcHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrIdCntcHist", mbrIdCntcHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

