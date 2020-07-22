
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
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodModelSize;

/**
 * The Class GodModelSizeRepository.
 */
@Repository
public class GodModelSizeRepository extends AbstractRepository {
	

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
	 * 상품 모델 사이즈 상세 조회.
	 *
	 * @param godModelSize the GodModelSize
	 * @return the GodModelSize
	 * @throws SQLException the SQL exception
	 */
	public GodModelSize selectGodModelSize(GodModelSize godModelSize) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodModelSize", godModelSize);
	}
	
	/**
	 * 상품 모델 사이즈 등록.
	 *
	 * @param godModelSize the GodModelSize
	 * @throws SQLException the SQL exception
	 */
	public void insertGodModelSize(GodModelSize godModelSize) {
		getSession1().insert("com.plgrim.ncp.base.insertGodModelSize", godModelSize);
	}
	
	/**
	 * 상품 모델 사이즈 수정.
	 *
	 * @param godModelSize the GodModelSize
	 * @throws SQLException the SQL exception
	 */
	public int updateGodModelSize(GodModelSize godModelSize) {
		return getSession1().update("com.plgrim.ncp.base.updateGodModelSize", godModelSize);
	}
	
	/**
	 * 상품 모델 사이즈 삭제.
	 *
	 * @param godModelSize the GodModelSize
	 * @return the GodModelSize
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodModelSize(GodModelSize godModelSize) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodModelSize", godModelSize);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

