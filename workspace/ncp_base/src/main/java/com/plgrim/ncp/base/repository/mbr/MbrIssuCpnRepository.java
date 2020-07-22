
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;

/**
 * The Class MbrIssuCpnRepository.
 */
@Repository
public class MbrIssuCpnRepository extends AbstractRepository {
	

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
	 * 회원 발급 쿠폰 상세 조회.
	 *
	 * @param mbrIssuCpn the MbrIssuCpn
	 * @return the MbrIssuCpn
	 * @throws SQLException the SQL exception
	 */
	public MbrIssuCpn selectMbrIssuCpn(MbrIssuCpn mbrIssuCpn) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrIssuCpn", mbrIssuCpn);
	}
	
	/**
	 * 회원 발급 쿠폰 등록.
	 *
	 * @param mbrIssuCpn the MbrIssuCpn
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrIssuCpn(MbrIssuCpn mbrIssuCpn) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrIssuCpn", mbrIssuCpn);
	}
	
	/**
	 * 회원 발급 쿠폰 수정.
	 *
	 * @param mbrIssuCpn the MbrIssuCpn
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrIssuCpn(MbrIssuCpn mbrIssuCpn) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrIssuCpn", mbrIssuCpn);
	}
	
	/**
	 * 회원 발급 쿠폰 삭제.
	 *
	 * @param mbrIssuCpn the MbrIssuCpn
	 * @return the MbrIssuCpn
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrIssuCpn(MbrIssuCpn mbrIssuCpn) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrIssuCpn", mbrIssuCpn);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

