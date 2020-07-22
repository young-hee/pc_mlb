
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
 * Generator(Generator)		2018-04-19                      
 */
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlSize;

/**
 * The Class GodGodEvlSizeRepository.
 */
@Repository
public class GodGodEvlSizeRepository extends AbstractRepository {
	

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
	 * 상품 상품평 사이즈 상세 조회.
	 *
	 * @param godGodEvlSize the GodGodEvlSize
	 * @return the GodGodEvlSize
	 * @throws SQLException the SQL exception
	 */
	public GodGodEvlSize selectGodGodEvlSize(GodGodEvlSize godGodEvlSize) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodGodEvlSize", godGodEvlSize);
	}
	
	/**
	 * 상품 상품평 사이즈 등록.
	 *
	 * @param godGodEvlSize the GodGodEvlSize
	 * @throws SQLException the SQL exception
	 */
	public void insertGodGodEvlSize(GodGodEvlSize godGodEvlSize) {
		getSession1().insert("com.plgrim.ncp.base.insertGodGodEvlSize", godGodEvlSize);
	}
	
	/**
	 * 상품 상품평 사이즈 수정.
	 *
	 * @param godGodEvlSize the GodGodEvlSize
	 * @throws SQLException the SQL exception
	 */
	public int updateGodGodEvlSize(GodGodEvlSize godGodEvlSize) {
		return getSession1().update("com.plgrim.ncp.base.updateGodGodEvlSize", godGodEvlSize);
	}
	
	/**
	 * 상품 상품평 사이즈 삭제.
	 *
	 * @param godGodEvlSize the GodGodEvlSize
	 * @return the GodGodEvlSize
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodGodEvlSize(GodGodEvlSize godGodEvlSize) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodGodEvlSize", godGodEvlSize);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

