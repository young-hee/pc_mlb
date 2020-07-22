
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
import com.plgrim.ncp.base.entities.datasource1.god.GodAditDetail;

/**
 * The Class GodAditDetailRepository.
 */
@Repository
public class GodAditDetailRepository extends AbstractRepository {
	

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
	 * 상품 추가 상세 상세 조회.
	 *
	 * @param godAditDetail the GodAditDetail
	 * @return the GodAditDetail
	 * @throws SQLException the SQL exception
	 */
	public GodAditDetail selectGodAditDetail(GodAditDetail godAditDetail) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodAditDetail", godAditDetail);
	}
	
	/**
	 * 상품 추가 상세 등록.
	 *
	 * @param godAditDetail the GodAditDetail
	 * @throws SQLException the SQL exception
	 */
	public void insertGodAditDetail(GodAditDetail godAditDetail) {
		getSession1().insert("com.plgrim.ncp.base.insertGodAditDetail", godAditDetail);
	}
	
	/**
	 * 상품 추가 상세 수정.
	 *
	 * @param godAditDetail the GodAditDetail
	 * @throws SQLException the SQL exception
	 */
	public int updateGodAditDetail(GodAditDetail godAditDetail) {
		return getSession1().update("com.plgrim.ncp.base.updateGodAditDetail", godAditDetail);
	}
	
	/**
	 * 상품 추가 상세 삭제.
	 *
	 * @param godAditDetail the GodAditDetail
	 * @return the GodAditDetail
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodAditDetail(GodAditDetail godAditDetail) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodAditDetail", godAditDetail);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

