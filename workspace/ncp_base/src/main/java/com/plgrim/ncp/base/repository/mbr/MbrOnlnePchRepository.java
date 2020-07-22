
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrOnlnePch;

/**
 * The Class MbrOnlnePchRepository.
 */
@Repository
public class MbrOnlnePchRepository extends AbstractRepository {
	

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
	 * 회원 온라인 구매 상세 조회.
	 *
	 * @param mbrOnlnePch the MbrOnlnePch
	 * @return the MbrOnlnePch
	 * @throws SQLException the SQL exception
	 */
	public MbrOnlnePch selectMbrOnlnePch(MbrOnlnePch mbrOnlnePch) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrOnlnePch", mbrOnlnePch);
	}
	
	/**
	 * 회원 온라인 구매 등록.
	 *
	 * @param mbrOnlnePch the MbrOnlnePch
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrOnlnePch(MbrOnlnePch mbrOnlnePch) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrOnlnePch", mbrOnlnePch);
	}
	
	/**
	 * 회원 온라인 구매 수정.
	 *
	 * @param mbrOnlnePch the MbrOnlnePch
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrOnlnePch(MbrOnlnePch mbrOnlnePch) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrOnlnePch", mbrOnlnePch);
	}
	
	/**
	 * 회원 온라인 구매 삭제.
	 *
	 * @param mbrOnlnePch the MbrOnlnePch
	 * @return the MbrOnlnePch
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrOnlnePch(MbrOnlnePch mbrOnlnePch) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrOnlnePch", mbrOnlnePch);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

