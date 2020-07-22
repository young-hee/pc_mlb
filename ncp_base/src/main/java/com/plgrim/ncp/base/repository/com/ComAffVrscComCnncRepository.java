
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
import com.plgrim.ncp.base.entities.datasource1.com.ComAffVrscComCnnc;

/**
 * The Class ComAffVrscComCnncRepository.
 */
@Repository
public class ComAffVrscComCnncRepository extends AbstractRepository {
	

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
	 * 업체 제휴 대행 업체 연결 상세 조회.
	 *
	 * @param comAffVrscComCnnc the ComAffVrscComCnnc
	 * @return the ComAffVrscComCnnc
	 * @throws SQLException the SQL exception
	 */
	public ComAffVrscComCnnc selectComAffVrscComCnnc(ComAffVrscComCnnc comAffVrscComCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComAffVrscComCnnc", comAffVrscComCnnc);
	}
	
	/**
	 * 업체 제휴 대행 업체 연결 등록.
	 *
	 * @param comAffVrscComCnnc the ComAffVrscComCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertComAffVrscComCnnc(ComAffVrscComCnnc comAffVrscComCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertComAffVrscComCnnc", comAffVrscComCnnc);
	}
	
	/**
	 * 업체 제휴 대행 업체 연결 수정.
	 *
	 * @param comAffVrscComCnnc the ComAffVrscComCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateComAffVrscComCnnc(ComAffVrscComCnnc comAffVrscComCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateComAffVrscComCnnc", comAffVrscComCnnc);
	}
	
	/**
	 * 업체 제휴 대행 업체 연결 삭제.
	 *
	 * @param comAffVrscComCnnc the ComAffVrscComCnnc
	 * @return the ComAffVrscComCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteComAffVrscComCnnc(ComAffVrscComCnnc comAffVrscComCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComAffVrscComCnnc", comAffVrscComCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

