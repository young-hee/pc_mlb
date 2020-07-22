
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
import com.plgrim.ncp.base.entities.datasource1.com.ComReqst;

/**
 * The Class ComReqstRepository.
 */
@Repository
public class ComReqstRepository extends AbstractRepository {
	

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
	 * 업체 신청 상세 조회.
	 *
	 * @param comReqst the ComReqst
	 * @return the ComReqst
	 * @throws SQLException the SQL exception
	 */
	public ComReqst selectComReqst(ComReqst comReqst) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComReqst", comReqst);
	}
	
	/**
	 * 업체 신청 등록.
	 *
	 * @param comReqst the ComReqst
	 * @throws SQLException the SQL exception
	 */
	public void insertComReqst(ComReqst comReqst) {
		getSession1().insert("com.plgrim.ncp.base.insertComReqst", comReqst);
	}
	
	/**
	 * 업체 신청 수정.
	 *
	 * @param comReqst the ComReqst
	 * @throws SQLException the SQL exception
	 */
	public int updateComReqst(ComReqst comReqst) {
		return getSession1().update("com.plgrim.ncp.base.updateComReqst", comReqst);
	}
	
	/**
	 * 업체 신청 삭제.
	 *
	 * @param comReqst the ComReqst
	 * @return the ComReqst
	 * @throws SQLException the SQL exception
	 */
	public int deleteComReqst(ComReqst comReqst) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComReqst", comReqst);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

