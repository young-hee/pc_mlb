
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
import com.plgrim.ncp.base.entities.datasource1.com.ComReqstAtchmnfl;

/**
 * The Class ComReqstAtchmnflRepository.
 */
@Repository
public class ComReqstAtchmnflRepository extends AbstractRepository {
	

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
	 * 업체 신청 첨부파일 상세 조회.
	 *
	 * @param comReqstAtchmnfl the ComReqstAtchmnfl
	 * @return the ComReqstAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public ComReqstAtchmnfl selectComReqstAtchmnfl(ComReqstAtchmnfl comReqstAtchmnfl) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComReqstAtchmnfl", comReqstAtchmnfl);
	}
	
	/**
	 * 업체 신청 첨부파일 등록.
	 *
	 * @param comReqstAtchmnfl the ComReqstAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public void insertComReqstAtchmnfl(ComReqstAtchmnfl comReqstAtchmnfl) {
		getSession1().insert("com.plgrim.ncp.base.insertComReqstAtchmnfl", comReqstAtchmnfl);
	}
	
	/**
	 * 업체 신청 첨부파일 수정.
	 *
	 * @param comReqstAtchmnfl the ComReqstAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public int updateComReqstAtchmnfl(ComReqstAtchmnfl comReqstAtchmnfl) {
		return getSession1().update("com.plgrim.ncp.base.updateComReqstAtchmnfl", comReqstAtchmnfl);
	}
	
	/**
	 * 업체 신청 첨부파일 삭제.
	 *
	 * @param comReqstAtchmnfl the ComReqstAtchmnfl
	 * @return the ComReqstAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public int deleteComReqstAtchmnfl(ComReqstAtchmnfl comReqstAtchmnfl) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComReqstAtchmnfl", comReqstAtchmnfl);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

