
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
import com.plgrim.ncp.base.entities.datasource1.com.ComReqstStplatAgr;

/**
 * The Class ComReqstStplatAgrRepository.
 */
@Repository
public class ComReqstStplatAgrRepository extends AbstractRepository {
	

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
	 * 업체 신청 약관 동의 상세 조회.
	 *
	 * @param comReqstStplatAgr the ComReqstStplatAgr
	 * @return the ComReqstStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public ComReqstStplatAgr selectComReqstStplatAgr(ComReqstStplatAgr comReqstStplatAgr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComReqstStplatAgr", comReqstStplatAgr);
	}
	
	/**
	 * 업체 신청 약관 동의 등록.
	 *
	 * @param comReqstStplatAgr the ComReqstStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public void insertComReqstStplatAgr(ComReqstStplatAgr comReqstStplatAgr) {
		getSession1().insert("com.plgrim.ncp.base.insertComReqstStplatAgr", comReqstStplatAgr);
	}
	
	/**
	 * 업체 신청 약관 동의 수정.
	 *
	 * @param comReqstStplatAgr the ComReqstStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public int updateComReqstStplatAgr(ComReqstStplatAgr comReqstStplatAgr) {
		return getSession1().update("com.plgrim.ncp.base.updateComReqstStplatAgr", comReqstStplatAgr);
	}
	
	/**
	 * 업체 신청 약관 동의 삭제.
	 *
	 * @param comReqstStplatAgr the ComReqstStplatAgr
	 * @return the ComReqstStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public int deleteComReqstStplatAgr(ComReqstStplatAgr comReqstStplatAgr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComReqstStplatAgr", comReqstStplatAgr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

