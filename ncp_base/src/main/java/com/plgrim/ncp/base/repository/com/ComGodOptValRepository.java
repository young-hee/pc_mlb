
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
import com.plgrim.ncp.base.entities.datasource1.com.ComGodOptVal;

/**
 * The Class ComGodOptValRepository.
 */
@Repository
public class ComGodOptValRepository extends AbstractRepository {
	

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
	 * 업체 상품 옵션 값 상세 조회.
	 *
	 * @param comGodOptVal the ComGodOptVal
	 * @return the ComGodOptVal
	 * @throws SQLException the SQL exception
	 */
	public ComGodOptVal selectComGodOptVal(ComGodOptVal comGodOptVal) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComGodOptVal", comGodOptVal);
	}
	
	/**
	 * 업체 상품 옵션 값 등록.
	 *
	 * @param comGodOptVal the ComGodOptVal
	 * @throws SQLException the SQL exception
	 */
	public void insertComGodOptVal(ComGodOptVal comGodOptVal) {
		getSession1().insert("com.plgrim.ncp.base.insertComGodOptVal", comGodOptVal);
	}
	
	/**
	 * 업체 상품 옵션 값 수정.
	 *
	 * @param comGodOptVal the ComGodOptVal
	 * @throws SQLException the SQL exception
	 */
	public int updateComGodOptVal(ComGodOptVal comGodOptVal) {
		return getSession1().update("com.plgrim.ncp.base.updateComGodOptVal", comGodOptVal);
	}
	
	/**
	 * 업체 상품 옵션 값 삭제.
	 *
	 * @param comGodOptVal the ComGodOptVal
	 * @return the ComGodOptVal
	 * @throws SQLException the SQL exception
	 */
	public int deleteComGodOptVal(ComGodOptVal comGodOptVal) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComGodOptVal", comGodOptVal);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

