
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
import com.plgrim.ncp.base.entities.datasource1.com.ComStdOptCnnc;

/**
 * The Class ComStdOptCnncRepository.
 */
@Repository
public class ComStdOptCnncRepository extends AbstractRepository {
	

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
	 * 업체 표준 옵션 연결 상세 조회.
	 *
	 * @param comStdOptCnnc the ComStdOptCnnc
	 * @return the ComStdOptCnnc
	 * @throws SQLException the SQL exception
	 */
	public ComStdOptCnnc selectComStdOptCnnc(ComStdOptCnnc comStdOptCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComStdOptCnnc", comStdOptCnnc);
	}
	
	/**
	 * 업체 표준 옵션 연결 등록.
	 *
	 * @param comStdOptCnnc the ComStdOptCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertComStdOptCnnc(ComStdOptCnnc comStdOptCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertComStdOptCnnc", comStdOptCnnc);
	}
	
	/**
	 * 업체 표준 옵션 연결 수정.
	 *
	 * @param comStdOptCnnc the ComStdOptCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateComStdOptCnnc(ComStdOptCnnc comStdOptCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateComStdOptCnnc", comStdOptCnnc);
	}
	
	/**
	 * 업체 표준 옵션 연결 삭제.
	 *
	 * @param comStdOptCnnc the ComStdOptCnnc
	 * @return the ComStdOptCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteComStdOptCnnc(ComStdOptCnnc comStdOptCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComStdOptCnnc", comStdOptCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

