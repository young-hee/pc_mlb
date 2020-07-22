
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
import com.plgrim.ncp.base.entities.datasource1.god.GodColorGrp;

/**
 * The Class GodColorGrpRepository.
 */
@Repository
public class GodColorGrpRepository extends AbstractRepository {
	

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
	 * 상품 색상 그룹 상세 조회.
	 *
	 * @param godColorGrp the GodColorGrp
	 * @return the GodColorGrp
	 * @throws SQLException the SQL exception
	 */
	public GodColorGrp selectGodColorGrp(GodColorGrp godColorGrp) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodColorGrp", godColorGrp);
	}
	
	/**
	 * 상품 색상 그룹 등록.
	 *
	 * @param godColorGrp the GodColorGrp
	 * @throws SQLException the SQL exception
	 */
	public void insertGodColorGrp(GodColorGrp godColorGrp) {
		getSession1().insert("com.plgrim.ncp.base.insertGodColorGrp", godColorGrp);
	}
	
	/**
	 * 상품 색상 그룹 수정.
	 *
	 * @param godColorGrp the GodColorGrp
	 * @throws SQLException the SQL exception
	 */
	public int updateGodColorGrp(GodColorGrp godColorGrp) {
		return getSession1().update("com.plgrim.ncp.base.updateGodColorGrp", godColorGrp);
	}
	
	/**
	 * 상품 색상 그룹 삭제.
	 *
	 * @param godColorGrp the GodColorGrp
	 * @return the GodColorGrp
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodColorGrp(GodColorGrp godColorGrp) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodColorGrp", godColorGrp);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

