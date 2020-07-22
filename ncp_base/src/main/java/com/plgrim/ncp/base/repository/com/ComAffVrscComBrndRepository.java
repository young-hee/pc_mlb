
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
import com.plgrim.ncp.base.entities.datasource1.com.ComAffVrscComBrnd;

/**
 * The Class ComAffVrscComBrndRepository.
 */
@Repository
public class ComAffVrscComBrndRepository extends AbstractRepository {
	

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
	 * 업체 제휴 대행 업체 브랜드 상세 조회.
	 *
	 * @param comAffVrscComBrnd the ComAffVrscComBrnd
	 * @return the ComAffVrscComBrnd
	 * @throws SQLException the SQL exception
	 */
	public ComAffVrscComBrnd selectComAffVrscComBrnd(ComAffVrscComBrnd comAffVrscComBrnd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComAffVrscComBrnd", comAffVrscComBrnd);
	}
	
	/**
	 * 업체 제휴 대행 업체 브랜드 등록.
	 *
	 * @param comAffVrscComBrnd the ComAffVrscComBrnd
	 * @throws SQLException the SQL exception
	 */
	public void insertComAffVrscComBrnd(ComAffVrscComBrnd comAffVrscComBrnd) {
		getSession1().insert("com.plgrim.ncp.base.insertComAffVrscComBrnd", comAffVrscComBrnd);
	}
	
	/**
	 * 업체 제휴 대행 업체 브랜드 수정.
	 *
	 * @param comAffVrscComBrnd the ComAffVrscComBrnd
	 * @throws SQLException the SQL exception
	 */
	public int updateComAffVrscComBrnd(ComAffVrscComBrnd comAffVrscComBrnd) {
		return getSession1().update("com.plgrim.ncp.base.updateComAffVrscComBrnd", comAffVrscComBrnd);
	}
	
	/**
	 * 업체 제휴 대행 업체 브랜드 삭제.
	 *
	 * @param comAffVrscComBrnd the ComAffVrscComBrnd
	 * @return the ComAffVrscComBrnd
	 * @throws SQLException the SQL exception
	 */
	public int deleteComAffVrscComBrnd(ComAffVrscComBrnd comAffVrscComBrnd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComAffVrscComBrnd", comAffVrscComBrnd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

