
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnef;

/**
 * The Class MbrBnefRepository.
 */
@Repository
public class MbrBnefRepository extends AbstractRepository {
	

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
	 * 회원 혜택 상세 조회.
	 *
	 * @param mbrBnef the MbrBnef
	 * @return the MbrBnef
	 * @throws SQLException the SQL exception
	 */
	public MbrBnef selectMbrBnef(MbrBnef mbrBnef) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrBnef", mbrBnef);
	}
	
	/**
	 * 회원 혜택 등록.
	 *
	 * @param mbrBnef the MbrBnef
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrBnef(MbrBnef mbrBnef) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrBnef", mbrBnef);
	}
	
	/**
	 * 회원 혜택 수정.
	 *
	 * @param mbrBnef the MbrBnef
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrBnef(MbrBnef mbrBnef) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrBnef", mbrBnef);
	}
	
	/**
	 * 회원 혜택 삭제.
	 *
	 * @param mbrBnef the MbrBnef
	 * @return the MbrBnef
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrBnef(MbrBnef mbrBnef) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrBnef", mbrBnef);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

