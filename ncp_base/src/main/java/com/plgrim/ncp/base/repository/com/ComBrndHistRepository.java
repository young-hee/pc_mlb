
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
import com.plgrim.ncp.base.entities.datasource1.com.ComBrndHist;

/**
 * The Class ComBrndHistRepository.
 */
@Repository
public class ComBrndHistRepository extends AbstractRepository {
	

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
	 * 업체 브랜드 이력 상세 조회.
	 *
	 * @param comBrndHist the ComBrndHist
	 * @return the ComBrndHist
	 * @throws SQLException the SQL exception
	 */
	public ComBrndHist selectComBrndHist(ComBrndHist comBrndHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComBrndHist", comBrndHist);
	}
	
	/**
	 * 업체 브랜드 이력 등록.
	 *
	 * @param comBrndHist the ComBrndHist
	 * @throws SQLException the SQL exception
	 */
	public void insertComBrndHist(ComBrndHist comBrndHist) {
		getSession1().insert("com.plgrim.ncp.base.insertComBrndHist", comBrndHist);
	}
	
	/**
	 * 업체 브랜드 이력 수정.
	 *
	 * @param comBrndHist the ComBrndHist
	 * @throws SQLException the SQL exception
	 */
	public int updateComBrndHist(ComBrndHist comBrndHist) {
		return getSession1().update("com.plgrim.ncp.base.updateComBrndHist", comBrndHist);
	}
	
	/**
	 * 업체 브랜드 이력 삭제.
	 *
	 * @param comBrndHist the ComBrndHist
	 * @return the ComBrndHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteComBrndHist(ComBrndHist comBrndHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComBrndHist", comBrndHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

