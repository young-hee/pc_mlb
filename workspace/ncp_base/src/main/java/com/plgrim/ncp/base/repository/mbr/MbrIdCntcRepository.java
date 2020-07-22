
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntc;

/**
 * The Class MbrIdCntcRepository.
 */
@Repository
public class MbrIdCntcRepository extends AbstractRepository {
	

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
	 * 회원 ID 연계 상세 조회.
	 *
	 * @param mbrIdCntc the MbrIdCntc
	 * @return the MbrIdCntc
	 * @throws SQLException the SQL exception
	 */
	public MbrIdCntc selectMbrIdCntc(MbrIdCntc mbrIdCntc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrIdCntc", mbrIdCntc);
	}
	
	/**
	 * 회원 ID 연계 등록.
	 *
	 * @param mbrIdCntc the MbrIdCntc
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrIdCntc(MbrIdCntc mbrIdCntc) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrIdCntc", mbrIdCntc);
	}
	
	/**
	 * 회원 ID 연계 수정.
	 *
	 * @param mbrIdCntc the MbrIdCntc
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrIdCntc(MbrIdCntc mbrIdCntc) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrIdCntc", mbrIdCntc);
	}
	
	/**
	 * 회원 ID 연계 삭제.
	 *
	 * @param mbrIdCntc the MbrIdCntc
	 * @return the MbrIdCntc
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrIdCntc(MbrIdCntc mbrIdCntc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrIdCntc", mbrIdCntc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

