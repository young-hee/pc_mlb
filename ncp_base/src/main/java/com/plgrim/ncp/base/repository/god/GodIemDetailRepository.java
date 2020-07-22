
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
import com.plgrim.ncp.base.entities.datasource1.god.GodIemDetail;

/**
 * The Class GodIemDetailRepository.
 */
@Repository
public class GodIemDetailRepository extends AbstractRepository {
	

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
	 * 상품 항목 상세 상세 조회.
	 *
	 * @param godIemDetail the GodIemDetail
	 * @return the GodIemDetail
	 * @throws SQLException the SQL exception
	 */
	public GodIemDetail selectGodIemDetail(GodIemDetail godIemDetail) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodIemDetail", godIemDetail);
	}
	
	/**
	 * 상품 항목 상세 등록.
	 *
	 * @param godIemDetail the GodIemDetail
	 * @throws SQLException the SQL exception
	 */
	public void insertGodIemDetail(GodIemDetail godIemDetail) {
		getSession1().insert("com.plgrim.ncp.base.insertGodIemDetail", godIemDetail);
	}
	
	/**
	 * 상품 항목 상세 수정.
	 *
	 * @param godIemDetail the GodIemDetail
	 * @throws SQLException the SQL exception
	 */
	public int updateGodIemDetail(GodIemDetail godIemDetail) {
		return getSession1().update("com.plgrim.ncp.base.updateGodIemDetail", godIemDetail);
	}
	
	/**
	 * 상품 항목 상세 삭제.
	 *
	 * @param godIemDetail the GodIemDetail
	 * @return the GodIemDetail
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodIemDetail(GodIemDetail godIemDetail) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodIemDetail", godIemDetail);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

