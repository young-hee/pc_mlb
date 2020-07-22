
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
package com.plgrim.ncp.base.repository.clm;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.clm.Clm;

/**
 * The Class ClmRepository.
 */
@Repository
public class ClmRepository extends AbstractRepository {
	

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
	 * 클레임 상세 조회.
	 *
	 * @param clm the Clm
	 * @return the Clm
	 * @throws SQLException the SQL exception
	 */
	public Clm selectClm(Clm clm) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectClm", clm);
	}
	
	/**
	 * 클레임 등록.
	 *
	 * @param clm the Clm
	 * @throws SQLException the SQL exception
	 */
	public void insertClm(Clm clm) {
		getSession1().insert("com.plgrim.ncp.base.insertClm", clm);
	}
	
	/**
	 * 클레임 수정.
	 *
	 * @param clm the Clm
	 * @throws SQLException the SQL exception
	 */
	public int updateClm(Clm clm) {
		return getSession1().update("com.plgrim.ncp.base.updateClm", clm);
	}
	
	/**
	 * 클레임 삭제.
	 *
	 * @param clm the Clm
	 * @return the Clm
	 * @throws SQLException the SQL exception
	 */
	public int deleteClm(Clm clm) {
		return getSession1().delete("com.plgrim.ncp.base.deleteClm", clm);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

