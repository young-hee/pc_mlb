
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
import com.plgrim.ncp.base.entities.datasource1.com.ComChrger;

/**
 * The Class ComChrgerRepository.
 */
@Repository
public class ComChrgerRepository extends AbstractRepository {
	

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
	 * 업체 담당자 상세 조회.
	 *
	 * @param comChrger the ComChrger
	 * @return the ComChrger
	 * @throws SQLException the SQL exception
	 */
	public ComChrger selectComChrger(ComChrger comChrger) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComChrger", comChrger);
	}
	
	/**
	 * 업체 담당자 등록.
	 *
	 * @param comChrger the ComChrger
	 * @throws SQLException the SQL exception
	 */
	public void insertComChrger(ComChrger comChrger) {
		getSession1().insert("com.plgrim.ncp.base.insertComChrger", comChrger);
	}
	
	/**
	 * 업체 담당자 수정.
	 *
	 * @param comChrger the ComChrger
	 * @throws SQLException the SQL exception
	 */
	public int updateComChrger(ComChrger comChrger) {
		return getSession1().update("com.plgrim.ncp.base.updateComChrger", comChrger);
	}
	
	/**
	 * 업체 담당자 삭제.
	 *
	 * @param comChrger the ComChrger
	 * @return the ComChrger
	 * @throws SQLException the SQL exception
	 */
	public int deleteComChrger(ComChrger comChrger) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComChrger", comChrger);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

