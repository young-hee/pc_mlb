
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
 * Generator(Generator)		2018-05-24                      
 */
package com.plgrim.ncp.base.repository.mbr;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;

/**
 * The Class MbrRepository.
 */
@Repository
public class MbrRepository extends AbstractRepository {
	

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
	 * 회원 상세 조회.
	 *
	 * @param mbr the Mbr
	 * @return the Mbr
	 * @throws SQLException the SQL exception
	 */
	public Mbr selectMbr(Mbr mbr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbr", mbr);
	}
	
	/**
	 * 회원 등록.
	 *
	 * @param mbr the Mbr
	 * @throws SQLException the SQL exception
	 */
	public void insertMbr(Mbr mbr) {
		getSession1().insert("com.plgrim.ncp.base.insertMbr", mbr);
	}
	
	/**
	 * 회원 수정.
	 *
	 * @param mbr the Mbr
	 * @throws SQLException the SQL exception
	 */
	public int updateMbr(Mbr mbr) {
		return getSession1().update("com.plgrim.ncp.base.updateMbr", mbr);
	}
	
	/**
	 * 회원 삭제.
	 *
	 * @param mbr the Mbr
	 * @return the Mbr
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbr(Mbr mbr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbr", mbr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

