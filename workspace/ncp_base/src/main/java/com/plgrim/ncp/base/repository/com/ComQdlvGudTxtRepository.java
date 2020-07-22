
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
import com.plgrim.ncp.base.entities.datasource1.com.ComQdlvGudTxt;

/**
 * The Class ComQdlvGudTxtRepository.
 */
@Repository
public class ComQdlvGudTxtRepository extends AbstractRepository {
	

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
	 * 업체 퀵배송 안내 문구 상세 조회.
	 *
	 * @param comQdlvGudTxt the ComQdlvGudTxt
	 * @return the ComQdlvGudTxt
	 * @throws SQLException the SQL exception
	 */
	public ComQdlvGudTxt selectComQdlvGudTxt(ComQdlvGudTxt comQdlvGudTxt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComQdlvGudTxt", comQdlvGudTxt);
	}
	
	/**
	 * 업체 퀵배송 안내 문구 등록.
	 *
	 * @param comQdlvGudTxt the ComQdlvGudTxt
	 * @throws SQLException the SQL exception
	 */
	public void insertComQdlvGudTxt(ComQdlvGudTxt comQdlvGudTxt) {
		getSession1().insert("com.plgrim.ncp.base.insertComQdlvGudTxt", comQdlvGudTxt);
	}
	
	/**
	 * 업체 퀵배송 안내 문구 수정.
	 *
	 * @param comQdlvGudTxt the ComQdlvGudTxt
	 * @throws SQLException the SQL exception
	 */
	public int updateComQdlvGudTxt(ComQdlvGudTxt comQdlvGudTxt) {
		return getSession1().update("com.plgrim.ncp.base.updateComQdlvGudTxt", comQdlvGudTxt);
	}
	
	/**
	 * 업체 퀵배송 안내 문구 삭제.
	 *
	 * @param comQdlvGudTxt the ComQdlvGudTxt
	 * @return the ComQdlvGudTxt
	 * @throws SQLException the SQL exception
	 */
	public int deleteComQdlvGudTxt(ComQdlvGudTxt comQdlvGudTxt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComQdlvGudTxt", comQdlvGudTxt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

