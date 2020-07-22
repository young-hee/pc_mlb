
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
package com.plgrim.ncp.base.repository.com;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.com.ComBrnd;

/**
 * The Class ComBrndRepository.
 */
@Repository
public class ComBrndRepository extends AbstractRepository {
	

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
	 * 업체 브랜드 상세 조회.
	 *
	 * @param comBrnd the ComBrnd
	 * @return the ComBrnd
	 * @throws SQLException the SQL exception
	 */
	public ComBrnd selectComBrnd(ComBrnd comBrnd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComBrnd", comBrnd);
	}
	
	/**
	 * 업체 브랜드 등록.
	 *
	 * @param comBrnd the ComBrnd
	 * @throws SQLException the SQL exception
	 */
	public void insertComBrnd(ComBrnd comBrnd) {
		getSession1().insert("com.plgrim.ncp.base.insertComBrnd", comBrnd);
	}
	
	/**
	 * 업체 브랜드 수정.
	 *
	 * @param comBrnd the ComBrnd
	 * @throws SQLException the SQL exception
	 */
	public int updateComBrnd(ComBrnd comBrnd) {
		return getSession1().update("com.plgrim.ncp.base.updateComBrnd", comBrnd);
	}
	
	/**
	 * 업체 브랜드 삭제.
	 *
	 * @param comBrnd the ComBrnd
	 * @return the ComBrnd
	 * @throws SQLException the SQL exception
	 */
	public int deleteComBrnd(ComBrnd comBrnd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComBrnd", comBrnd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

