
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
import com.plgrim.ncp.base.entities.datasource1.com.Com;

/**
 * The Class ComRepository.
 */
@Repository
public class ComRepository extends AbstractRepository {
	

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
	 * 업체 상세 조회.
	 *
	 * @param com the Com
	 * @return the Com
	 * @throws SQLException the SQL exception
	 */
	public Com selectCom(Com com) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCom", com);
	}
	
	/**
	 * 업체 등록.
	 *
	 * @param com the Com
	 * @throws SQLException the SQL exception
	 */
	public void insertCom(Com com) {
		getSession1().insert("com.plgrim.ncp.base.insertCom", com);
	}
	
	/**
	 * 업체 수정.
	 *
	 * @param com the Com
	 * @throws SQLException the SQL exception
	 */
	public int updateCom(Com com) {
		return getSession1().update("com.plgrim.ncp.base.updateCom", com);
	}
	
	/**
	 * 업체 삭제.
	 *
	 * @param com the Com
	 * @return the Com
	 * @throws SQLException the SQL exception
	 */
	public int deleteCom(Com com) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCom", com);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

