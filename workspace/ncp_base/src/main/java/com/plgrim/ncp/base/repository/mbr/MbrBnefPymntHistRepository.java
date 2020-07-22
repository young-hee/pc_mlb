
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefPymntHist;

/**
 * The Class MbrBnefPymntHistRepository.
 */
@Repository
public class MbrBnefPymntHistRepository extends AbstractRepository {
	

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
	 * 회원 혜택 지급 이력 상세 조회.
	 *
	 * @param mbrBnefPymntHist the MbrBnefPymntHist
	 * @return the MbrBnefPymntHist
	 * @throws SQLException the SQL exception
	 */
	public MbrBnefPymntHist selectMbrBnefPymntHist(MbrBnefPymntHist mbrBnefPymntHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrBnefPymntHist", mbrBnefPymntHist);
	}
	
	/**
	 * 회원 혜택 지급 이력 등록.
	 *
	 * @param mbrBnefPymntHist the MbrBnefPymntHist
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrBnefPymntHist(MbrBnefPymntHist mbrBnefPymntHist) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrBnefPymntHist", mbrBnefPymntHist);
	}
	
	/**
	 * 회원 혜택 지급 이력 수정.
	 *
	 * @param mbrBnefPymntHist the MbrBnefPymntHist
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrBnefPymntHist(MbrBnefPymntHist mbrBnefPymntHist) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrBnefPymntHist", mbrBnefPymntHist);
	}
	
	/**
	 * 회원 혜택 지급 이력 삭제.
	 *
	 * @param mbrBnefPymntHist the MbrBnefPymntHist
	 * @return the MbrBnefPymntHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrBnefPymntHist(MbrBnefPymntHist mbrBnefPymntHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrBnefPymntHist", mbrBnefPymntHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

