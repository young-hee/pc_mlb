
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
import com.plgrim.ncp.base.entities.datasource1.god.GodIcon;

/**
 * The Class GodIconRepository.
 */
@Repository
public class GodIconRepository extends AbstractRepository {
	

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
	 * 상품 아이콘 상세 조회.
	 *
	 * @param godIcon the GodIcon
	 * @return the GodIcon
	 * @throws SQLException the SQL exception
	 */
	public GodIcon selectGodIcon(GodIcon godIcon) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodIcon", godIcon);
	}
	
	/**
	 * 상품 아이콘 등록.
	 *
	 * @param godIcon the GodIcon
	 * @throws SQLException the SQL exception
	 */
	public void insertGodIcon(GodIcon godIcon) {
		getSession1().insert("com.plgrim.ncp.base.insertGodIcon", godIcon);
	}
	
	/**
	 * 상품 아이콘 수정.
	 *
	 * @param godIcon the GodIcon
	 * @throws SQLException the SQL exception
	 */
	public int updateGodIcon(GodIcon godIcon) {
		return getSession1().update("com.plgrim.ncp.base.updateGodIcon", godIcon);
	}
	
	/**
	 * 상품 아이콘 삭제.
	 *
	 * @param godIcon the GodIcon
	 * @return the GodIcon
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodIcon(GodIcon godIcon) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodIcon", godIcon);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

