
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
import com.plgrim.ncp.base.entities.datasource1.com.ComAffComBrndHist;

/**
 * The Class ComAffComBrndHistRepository.
 */
@Repository
public class ComAffComBrndHistRepository extends AbstractRepository {
	

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
	 * 업체 제휴 업체 브랜드 이력 상세 조회.
	 *
	 * @param comAffComBrndHist the ComAffComBrndHist
	 * @return the ComAffComBrndHist
	 * @throws SQLException the SQL exception
	 */
	public ComAffComBrndHist selectComAffComBrndHist(ComAffComBrndHist comAffComBrndHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComAffComBrndHist", comAffComBrndHist);
	}
	
	/**
	 * 업체 제휴 업체 브랜드 이력 등록.
	 *
	 * @param comAffComBrndHist the ComAffComBrndHist
	 * @throws SQLException the SQL exception
	 */
	public void insertComAffComBrndHist(ComAffComBrndHist comAffComBrndHist) {
		getSession1().insert("com.plgrim.ncp.base.insertComAffComBrndHist", comAffComBrndHist);
	}
	
	/**
	 * 업체 제휴 업체 브랜드 이력 수정.
	 *
	 * @param comAffComBrndHist the ComAffComBrndHist
	 * @throws SQLException the SQL exception
	 */
	public int updateComAffComBrndHist(ComAffComBrndHist comAffComBrndHist) {
		return getSession1().update("com.plgrim.ncp.base.updateComAffComBrndHist", comAffComBrndHist);
	}
	
	/**
	 * 업체 제휴 업체 브랜드 이력 삭제.
	 *
	 * @param comAffComBrndHist the ComAffComBrndHist
	 * @return the ComAffComBrndHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteComAffComBrndHist(ComAffComBrndHist comAffComBrndHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComAffComBrndHist", comAffComBrndHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

