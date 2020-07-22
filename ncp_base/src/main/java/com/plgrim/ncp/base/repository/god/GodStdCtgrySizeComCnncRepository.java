
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
import com.plgrim.ncp.base.entities.datasource1.god.GodStdCtgrySizeComCnnc;

/**
 * The Class GodStdCtgrySizeComCnncRepository.
 */
@Repository
public class GodStdCtgrySizeComCnncRepository extends AbstractRepository {
	

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
	 * 상품 표준 카테고리 사이즈 업체 연결 상세 조회.
	 *
	 * @param godStdCtgrySizeComCnnc the GodStdCtgrySizeComCnnc
	 * @return the GodStdCtgrySizeComCnnc
	 * @throws SQLException the SQL exception
	 */
	public GodStdCtgrySizeComCnnc selectGodStdCtgrySizeComCnnc(GodStdCtgrySizeComCnnc godStdCtgrySizeComCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodStdCtgrySizeComCnnc", godStdCtgrySizeComCnnc);
	}
	
	/**
	 * 상품 표준 카테고리 사이즈 업체 연결 등록.
	 *
	 * @param godStdCtgrySizeComCnnc the GodStdCtgrySizeComCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertGodStdCtgrySizeComCnnc(GodStdCtgrySizeComCnnc godStdCtgrySizeComCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertGodStdCtgrySizeComCnnc", godStdCtgrySizeComCnnc);
	}
	
	/**
	 * 상품 표준 카테고리 사이즈 업체 연결 수정.
	 *
	 * @param godStdCtgrySizeComCnnc the GodStdCtgrySizeComCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateGodStdCtgrySizeComCnnc(GodStdCtgrySizeComCnnc godStdCtgrySizeComCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateGodStdCtgrySizeComCnnc", godStdCtgrySizeComCnnc);
	}
	
	/**
	 * 상품 표준 카테고리 사이즈 업체 연결 삭제.
	 *
	 * @param godStdCtgrySizeComCnnc the GodStdCtgrySizeComCnnc
	 * @return the GodStdCtgrySizeComCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodStdCtgrySizeComCnnc(GodStdCtgrySizeComCnnc godStdCtgrySizeComCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodStdCtgrySizeComCnnc", godStdCtgrySizeComCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

