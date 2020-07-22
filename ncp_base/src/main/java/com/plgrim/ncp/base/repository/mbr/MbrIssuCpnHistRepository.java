
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
 * Generator(Generator)		2018-06-28                      
 */
package com.plgrim.ncp.base.repository.mbr;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpnHist;

/**
 * The Class MbrIssuCpnHistRepository.
 */
@Repository
public class MbrIssuCpnHistRepository extends AbstractRepository {
	

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
	 * 회원 발급 쿠폰 이력 상세 조회.
	 *
	 * @param mbrIssuCpnHist the MbrIssuCpnHist
	 * @return the MbrIssuCpnHist
	 * @throws SQLException the SQL exception
	 */
	public MbrIssuCpnHist selectMbrIssuCpnHist(MbrIssuCpnHist mbrIssuCpnHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrIssuCpnHist", mbrIssuCpnHist);
	}
	
	/**
	 * 회원 발급 쿠폰 이력 등록.
	 *
	 * @param mbrIssuCpnHist the MbrIssuCpnHist
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrIssuCpnHist(MbrIssuCpnHist mbrIssuCpnHist) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrIssuCpnHist", mbrIssuCpnHist);
	}
	
	/**
	 * 회원 발급 쿠폰 이력 수정.
	 *
	 * @param mbrIssuCpnHist the MbrIssuCpnHist
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrIssuCpnHist(MbrIssuCpnHist mbrIssuCpnHist) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrIssuCpnHist", mbrIssuCpnHist);
	}
	
	/**
	 * 회원 발급 쿠폰 이력 삭제.
	 *
	 * @param mbrIssuCpnHist the MbrIssuCpnHist
	 * @return the MbrIssuCpnHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrIssuCpnHist(MbrIssuCpnHist mbrIssuCpnHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrIssuCpnHist", mbrIssuCpnHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

