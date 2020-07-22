
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
import com.plgrim.ncp.base.entities.datasource1.com.ComGodOpt;

/**
 * The Class ComGodOptRepository.
 */
@Repository
public class ComGodOptRepository extends AbstractRepository {
	

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
	 * 업체 상품 옵션 상세 조회.
	 *
	 * @param comGodOpt the ComGodOpt
	 * @return the ComGodOpt
	 * @throws SQLException the SQL exception
	 */
	public ComGodOpt selectComGodOpt(ComGodOpt comGodOpt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComGodOpt", comGodOpt);
	}
	
	/**
	 * 업체 상품 옵션 등록.
	 *
	 * @param comGodOpt the ComGodOpt
	 * @throws SQLException the SQL exception
	 */
	public void insertComGodOpt(ComGodOpt comGodOpt) {
		getSession1().insert("com.plgrim.ncp.base.insertComGodOpt", comGodOpt);
	}
	
	/**
	 * 업체 상품 옵션 수정.
	 *
	 * @param comGodOpt the ComGodOpt
	 * @throws SQLException the SQL exception
	 */
	public int updateComGodOpt(ComGodOpt comGodOpt) {
		return getSession1().update("com.plgrim.ncp.base.updateComGodOpt", comGodOpt);
	}
	
	/**
	 * 업체 상품 옵션 삭제.
	 *
	 * @param comGodOpt the ComGodOpt
	 * @return the ComGodOpt
	 * @throws SQLException the SQL exception
	 */
	public int deleteComGodOpt(ComGodOpt comGodOpt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComGodOpt", comGodOpt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

