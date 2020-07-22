
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
import com.plgrim.ncp.base.entities.datasource1.god.GodIconCnnc;

/**
 * The Class GodIconCnncRepository.
 */
@Repository
public class GodIconCnncRepository extends AbstractRepository {
	

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
	 * 상품 아이콘 연결 상세 조회.
	 *
	 * @param godIconCnnc the GodIconCnnc
	 * @return the GodIconCnnc
	 * @throws SQLException the SQL exception
	 */
	public GodIconCnnc selectGodIconCnnc(GodIconCnnc godIconCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodIconCnnc", godIconCnnc);
	}
	
	/**
	 * 상품 아이콘 연결 등록.
	 *
	 * @param godIconCnnc the GodIconCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertGodIconCnnc(GodIconCnnc godIconCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertGodIconCnnc", godIconCnnc);
	}
	
	/**
	 * 상품 아이콘 연결 수정.
	 *
	 * @param godIconCnnc the GodIconCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateGodIconCnnc(GodIconCnnc godIconCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateGodIconCnnc", godIconCnnc);
	}
	
	/**
	 * 상품 아이콘 연결 삭제.
	 *
	 * @param godIconCnnc the GodIconCnnc
	 * @return the GodIconCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodIconCnnc(GodIconCnnc godIconCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodIconCnnc", godIconCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

