
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
 * Generator(Generator)		2019-10-10                      
 */
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodCnncGrpGod;

/**
 * The Class GodCnncGrpGodRepository.
 */
@Repository
public class GodCnncGrpGodRepository extends AbstractRepository {
	

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
	 * 상품 연결 상품 상세 조회.
	 *
	 * @param godCnncGrpGod the GodCnncGrpGod
	 * @return the GodCnncGod
	 * @throws SQLException the SQL exception
	 */
	public GodCnncGrpGod selectGodCnncGrpGod(GodCnncGrpGod godCnncGrpGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodCnncGrpGod", godCnncGrpGod);
	}
	
	/**
	 * 상품 연결 상품 등록.
	 *
	 * @param godCnncGrpGod the GodCnncGrpGod
	 * @throws SQLException the SQL exception
	 */
	public void insertGodCnncGrpGod(GodCnncGrpGod godCnncGrpGod) {
		getSession1().insert("com.plgrim.ncp.base.insertGodCnncGrpGod", godCnncGrpGod);
	}
	
	/**
	 * 상품 연결 상품 수정.
	 *
	 * @param godCnncGrpGod the GodCnncGrpGod
	 * @throws SQLException the SQL exception
	 */
	public int updateGodCnncGrpGod(GodCnncGrpGod godCnncGrpGod) {
		return getSession1().update("com.plgrim.ncp.base.updateGodCnncGrpGod", godCnncGrpGod);
	}
	
	/**
	 * 상품 연결 상품 삭제.
	 *
	 * @param godCnncGrpGod the GodCnncGrpGod
	 * @return the GodCnncGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodCnncGrpGod(GodCnncGrpGod godCnncGrpGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodCnncGrpGod", godCnncGrpGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}
