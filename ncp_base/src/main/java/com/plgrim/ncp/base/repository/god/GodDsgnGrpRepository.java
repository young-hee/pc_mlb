
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
import com.plgrim.ncp.base.entities.datasource1.god.GodDsgnGrp;

/**
 * The Class GodDsgnGrpRepository.
 */
@Repository
public class GodDsgnGrpRepository extends AbstractRepository {
	

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
	 * 상품 디자인 그룹 상세 조회.
	 *
	 * @param godDsgnGrp the GodDsgnGrp
	 * @return the GodDsgnGrp
	 * @throws SQLException the SQL exception
	 */
	public GodDsgnGrp selectGodDsgnGrp(GodDsgnGrp godDsgnGrp) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodDsgnGrp", godDsgnGrp);
	}
	
	/**
	 * 상품 디자인 그룹 등록.
	 *
	 * @param godDsgnGrp the GodDsgnGrp
	 * @throws SQLException the SQL exception
	 */
	public void insertGodDsgnGrp(GodDsgnGrp godDsgnGrp) {
		getSession1().insert("com.plgrim.ncp.base.insertGodDsgnGrp", godDsgnGrp);
	}
	
	/**
	 * 상품 디자인 그룹 수정.
	 *
	 * @param godDsgnGrp the GodDsgnGrp
	 * @throws SQLException the SQL exception
	 */
	public int updateGodDsgnGrp(GodDsgnGrp godDsgnGrp) {
		return getSession1().update("com.plgrim.ncp.base.updateGodDsgnGrp", godDsgnGrp);
	}
	
	/**
	 * 상품 디자인 그룹 삭제.
	 *
	 * @param godDsgnGrp the GodDsgnGrp
	 * @return the GodDsgnGrp
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodDsgnGrp(GodDsgnGrp godDsgnGrp) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodDsgnGrp", godDsgnGrp);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

