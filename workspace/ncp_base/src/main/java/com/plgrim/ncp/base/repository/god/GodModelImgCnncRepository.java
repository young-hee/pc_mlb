
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
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodModelImgCnnc;

/**
 * The Class GodModelImgCnncRepository.
 */
@Repository
public class GodModelImgCnncRepository extends AbstractRepository {
	

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
	 * 상품 모델 이미지 연결 상세 조회.
	 *
	 * @param godModelImgCnnc the GodModelImgCnnc
	 * @return the GodModelImgCnnc
	 * @throws SQLException the SQL exception
	 */
	public GodModelImgCnnc selectGodModelImgCnnc(GodModelImgCnnc godModelImgCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodModelImgCnnc", godModelImgCnnc);
	}
	
	/**
	 * 상품 모델 이미지 연결 등록.
	 *
	 * @param godModelImgCnnc the GodModelImgCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertGodModelImgCnnc(GodModelImgCnnc godModelImgCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertGodModelImgCnnc", godModelImgCnnc);
	}
	
	/**
	 * 상품 모델 이미지 연결 수정.
	 *
	 * @param godModelImgCnnc the GodModelImgCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateGodModelImgCnnc(GodModelImgCnnc godModelImgCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateGodModelImgCnnc", godModelImgCnnc);
	}
	
	/**
	 * 상품 모델 이미지 연결 삭제.
	 *
	 * @param godModelImgCnnc the GodModelImgCnnc
	 * @return the GodModelImgCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodModelImgCnnc(GodModelImgCnnc godModelImgCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodModelImgCnnc", godModelImgCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

