
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
import com.plgrim.ncp.base.entities.datasource1.god.GodAffComExclsGod;

/**
 * The Class GodAffComExclsGodRepository.
 */
@Repository
public class GodAffComExclsGodRepository extends AbstractRepository {
	

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
	 * 상품 제휴 업체 제외 상품 상세 조회.
	 *
	 * @param godAffComExclsGod the GodAffComExclsGod
	 * @return the GodAffComExclsGod
	 * @throws SQLException the SQL exception
	 */
	public GodAffComExclsGod selectGodAffComExclsGod(GodAffComExclsGod godAffComExclsGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodAffComExclsGod", godAffComExclsGod);
	}
	
	/**
	 * 상품 제휴 업체 제외 상품 등록.
	 *
	 * @param godAffComExclsGod the GodAffComExclsGod
	 * @throws SQLException the SQL exception
	 */
	public void insertGodAffComExclsGod(GodAffComExclsGod godAffComExclsGod) {
		getSession1().insert("com.plgrim.ncp.base.insertGodAffComExclsGod", godAffComExclsGod);
	}
	
	/**
	 * 상품 제휴 업체 제외 상품 수정.
	 *
	 * @param godAffComExclsGod the GodAffComExclsGod
	 * @throws SQLException the SQL exception
	 */
	public int updateGodAffComExclsGod(GodAffComExclsGod godAffComExclsGod) {
		return getSession1().update("com.plgrim.ncp.base.updateGodAffComExclsGod", godAffComExclsGod);
	}
	
	/**
	 * 상품 제휴 업체 제외 상품 삭제.
	 *
	 * @param godAffComExclsGod the GodAffComExclsGod
	 * @return the GodAffComExclsGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodAffComExclsGod(GodAffComExclsGod godAffComExclsGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodAffComExclsGod", godAffComExclsGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

