
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPreferPayMn;

/**
 * The Class MbrPreferPayMnRepository.
 */
@Repository
public class MbrPreferPayMnRepository extends AbstractRepository {
	

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
	 * 회원 선호 결제 수단 상세 조회.
	 *
	 * @param mbrPreferPayMn the MbrPreferPayMn
	 * @return the MbrPreferPayMn
	 * @throws SQLException the SQL exception
	 */
	public MbrPreferPayMn selectMbrPreferPayMn(MbrPreferPayMn mbrPreferPayMn) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrPreferPayMn", mbrPreferPayMn);
	}
	
	/**
	 * 회원 선호 결제 수단 등록.
	 *
	 * @param mbrPreferPayMn the MbrPreferPayMn
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrPreferPayMn(MbrPreferPayMn mbrPreferPayMn) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrPreferPayMn", mbrPreferPayMn);
	}
	
	/**
	 * 회원 선호 결제 수단 수정.
	 *
	 * @param mbrPreferPayMn the MbrPreferPayMn
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrPreferPayMn(MbrPreferPayMn mbrPreferPayMn) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrPreferPayMn", mbrPreferPayMn);
	}
	
	/**
	 * 회원 선호 결제 수단 삭제.
	 *
	 * @param mbrPreferPayMn the MbrPreferPayMn
	 * @return the MbrPreferPayMn
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrPreferPayMn(MbrPreferPayMn mbrPreferPayMn) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrPreferPayMn", mbrPreferPayMn);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

