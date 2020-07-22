
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
import com.plgrim.ncp.base.entities.datasource1.com.ComRelateBrnd;

/**
 * The Class ComRelateBrndRepository.
 */
@Repository
public class ComRelateBrndRepository extends AbstractRepository {
	

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
	 * 업체 연관 브랜드 상세 조회.
	 *
	 * @param comRelateBrnd the ComRelateBrnd
	 * @return the ComRelateBrnd
	 * @throws SQLException the SQL exception
	 */
	public ComRelateBrnd selectComRelateBrnd(ComRelateBrnd comRelateBrnd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComRelateBrnd", comRelateBrnd);
	}
	
	/**
	 * 업체 연관 브랜드 등록.
	 *
	 * @param comRelateBrnd the ComRelateBrnd
	 * @throws SQLException the SQL exception
	 */
	public void insertComRelateBrnd(ComRelateBrnd comRelateBrnd) {
		getSession1().insert("com.plgrim.ncp.base.insertComRelateBrnd", comRelateBrnd);
	}
	
	/**
	 * 업체 연관 브랜드 수정.
	 *
	 * @param comRelateBrnd the ComRelateBrnd
	 * @throws SQLException the SQL exception
	 */
	public int updateComRelateBrnd(ComRelateBrnd comRelateBrnd) {
		return getSession1().update("com.plgrim.ncp.base.updateComRelateBrnd", comRelateBrnd);
	}
	
	/**
	 * 업체 연관 브랜드 삭제.
	 *
	 * @param comRelateBrnd the ComRelateBrnd
	 * @return the ComRelateBrnd
	 * @throws SQLException the SQL exception
	 */
	public int deleteComRelateBrnd(ComRelateBrnd comRelateBrnd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComRelateBrnd", comRelateBrnd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

