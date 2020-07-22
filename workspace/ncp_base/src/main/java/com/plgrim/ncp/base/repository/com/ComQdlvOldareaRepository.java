
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
import com.plgrim.ncp.base.entities.datasource1.com.ComQdlvOldarea;

/**
 * The Class ComQdlvOldareaRepository.
 */
@Repository
public class ComQdlvOldareaRepository extends AbstractRepository {
	

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
	 * 업체 퀵배송 구지역 상세 조회.
	 *
	 * @param comQdlvOldarea the ComQdlvOldarea
	 * @return the ComQdlvOldarea
	 * @throws SQLException the SQL exception
	 */
	public ComQdlvOldarea selectComQdlvOldarea(ComQdlvOldarea comQdlvOldarea) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComQdlvOldarea", comQdlvOldarea);
	}
	
	/**
	 * 업체 퀵배송 구지역 등록.
	 *
	 * @param comQdlvOldarea the ComQdlvOldarea
	 * @throws SQLException the SQL exception
	 */
	public void insertComQdlvOldarea(ComQdlvOldarea comQdlvOldarea) {
		getSession1().insert("com.plgrim.ncp.base.insertComQdlvOldarea", comQdlvOldarea);
	}
	
	/**
	 * 업체 퀵배송 구지역 수정.
	 *
	 * @param comQdlvOldarea the ComQdlvOldarea
	 * @throws SQLException the SQL exception
	 */
	public int updateComQdlvOldarea(ComQdlvOldarea comQdlvOldarea) {
		return getSession1().update("com.plgrim.ncp.base.updateComQdlvOldarea", comQdlvOldarea);
	}
	
	/**
	 * 업체 퀵배송 구지역 삭제.
	 *
	 * @param comQdlvOldarea the ComQdlvOldarea
	 * @return the ComQdlvOldarea
	 * @throws SQLException the SQL exception
	 */
	public int deleteComQdlvOldarea(ComQdlvOldarea comQdlvOldarea) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComQdlvOldarea", comQdlvOldarea);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

