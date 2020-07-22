
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
package com.plgrim.ncp.base.repository.com;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.com.ComQdlvArea;

/**
 * The Class ComQdlvAreaRepository.
 */
@Repository
public class ComQdlvAreaRepository extends AbstractRepository {
	

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
	 * 업체 퀵배송 지역 상세 조회.
	 *
	 * @param comQdlvArea the ComQdlvArea
	 * @return the ComQdlvArea
	 * @throws SQLException the SQL exception
	 */
	public ComQdlvArea selectComQdlvArea(ComQdlvArea comQdlvArea) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComQdlvArea", comQdlvArea);
	}
	
	/**
	 * 업체 퀵배송 지역 등록.
	 *
	 * @param comQdlvArea the ComQdlvArea
	 * @throws SQLException the SQL exception
	 */
	public void insertComQdlvArea(ComQdlvArea comQdlvArea) {
		getSession1().insert("com.plgrim.ncp.base.insertComQdlvArea", comQdlvArea);
	}
	
	/**
	 * 업체 퀵배송 지역 수정.
	 *
	 * @param comQdlvArea the ComQdlvArea
	 * @throws SQLException the SQL exception
	 */
	public int updateComQdlvArea(ComQdlvArea comQdlvArea) {
		return getSession1().update("com.plgrim.ncp.base.updateComQdlvArea", comQdlvArea);
	}
	
	/**
	 * 업체 퀵배송 지역 삭제.
	 *
	 * @param comQdlvArea the ComQdlvArea
	 * @return the ComQdlvArea
	 * @throws SQLException the SQL exception
	 */
	public int deleteComQdlvArea(ComQdlvArea comQdlvArea) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComQdlvArea", comQdlvArea);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

