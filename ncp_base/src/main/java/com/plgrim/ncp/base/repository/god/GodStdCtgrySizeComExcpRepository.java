
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
import com.plgrim.ncp.base.entities.datasource1.god.GodStdCtgrySizeComExcp;

/**
 * The Class GodStdCtgrySizeComExcpRepository.
 */
@Repository
public class GodStdCtgrySizeComExcpRepository extends AbstractRepository {
	

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
	 * 상품 표준 카테고리 사이즈 업체 예외 상세 조회.
	 *
	 * @param godStdCtgrySizeComExcp the GodStdCtgrySizeComExcp
	 * @return the GodStdCtgrySizeComExcp
	 * @throws SQLException the SQL exception
	 */
	public GodStdCtgrySizeComExcp selectGodStdCtgrySizeComExcp(GodStdCtgrySizeComExcp godStdCtgrySizeComExcp) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodStdCtgrySizeComExcp", godStdCtgrySizeComExcp);
	}
	
	/**
	 * 상품 표준 카테고리 사이즈 업체 예외 등록.
	 *
	 * @param godStdCtgrySizeComExcp the GodStdCtgrySizeComExcp
	 * @throws SQLException the SQL exception
	 */
	public void insertGodStdCtgrySizeComExcp(GodStdCtgrySizeComExcp godStdCtgrySizeComExcp) {
		getSession1().insert("com.plgrim.ncp.base.insertGodStdCtgrySizeComExcp", godStdCtgrySizeComExcp);
	}
	
	/**
	 * 상품 표준 카테고리 사이즈 업체 예외 수정.
	 *
	 * @param godStdCtgrySizeComExcp the GodStdCtgrySizeComExcp
	 * @throws SQLException the SQL exception
	 */
	public int updateGodStdCtgrySizeComExcp(GodStdCtgrySizeComExcp godStdCtgrySizeComExcp) {
		return getSession1().update("com.plgrim.ncp.base.updateGodStdCtgrySizeComExcp", godStdCtgrySizeComExcp);
	}
	
	/**
	 * 상품 표준 카테고리 사이즈 업체 예외 삭제.
	 *
	 * @param godStdCtgrySizeComExcp the GodStdCtgrySizeComExcp
	 * @return the GodStdCtgrySizeComExcp
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodStdCtgrySizeComExcp(GodStdCtgrySizeComExcp godStdCtgrySizeComExcp) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodStdCtgrySizeComExcp", godStdCtgrySizeComExcp);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

