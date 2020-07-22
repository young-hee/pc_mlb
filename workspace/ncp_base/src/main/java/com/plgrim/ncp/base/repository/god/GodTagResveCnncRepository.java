
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
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResveCnnc;

/**
 * The Class GodTagResveCnncRepository.
 */
@Repository
public class GodTagResveCnncRepository extends AbstractRepository {
	

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
	 * 상품 태그 예약 연결 상세 조회.
	 *
	 * @param godTagResveCnnc the GodTagResveCnnc
	 * @return the GodTagResveCnnc
	 * @throws SQLException the SQL exception
	 */
	public GodTagResveCnnc selectGodTagResveCnnc(GodTagResveCnnc godTagResveCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodTagResveCnnc", godTagResveCnnc);
	}
	
	/**
	 * 상품 태그 예약 연결 등록.
	 *
	 * @param godTagResveCnnc the GodTagResveCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertGodTagResveCnnc(GodTagResveCnnc godTagResveCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertGodTagResveCnnc", godTagResveCnnc);
	}
	
	/**
	 * 상품 태그 예약 연결 수정.
	 *
	 * @param godTagResveCnnc the GodTagResveCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateGodTagResveCnnc(GodTagResveCnnc godTagResveCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateGodTagResveCnnc", godTagResveCnnc);
	}
	
	/**
	 * 상품 태그 예약 연결 삭제.
	 *
	 * @param godTagResveCnnc the GodTagResveCnnc
	 * @return the GodTagResveCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodTagResveCnnc(GodTagResveCnnc godTagResveCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodTagResveCnnc", godTagResveCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

