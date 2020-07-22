
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
import com.plgrim.ncp.base.entities.datasource1.god.GodModelImg;

/**
 * The Class GodModelImgRepository.
 */
@Repository
public class GodModelImgRepository extends AbstractRepository {
	

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
	 * 상품 모델 이미지 상세 조회.
	 *
	 * @param godModelImg the GodModelImg
	 * @return the GodModelImg
	 * @throws SQLException the SQL exception
	 */
	public GodModelImg selectGodModelImg(GodModelImg godModelImg) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodModelImg", godModelImg);
	}
	
	/**
	 * 상품 모델 이미지 등록.
	 *
	 * @param godModelImg the GodModelImg
	 * @throws SQLException the SQL exception
	 */
	public void insertGodModelImg(GodModelImg godModelImg) {
		getSession1().insert("com.plgrim.ncp.base.insertGodModelImg", godModelImg);
	}
	
	/**
	 * 상품 모델 이미지 수정.
	 *
	 * @param godModelImg the GodModelImg
	 * @throws SQLException the SQL exception
	 */
	public int updateGodModelImg(GodModelImg godModelImg) {
		return getSession1().update("com.plgrim.ncp.base.updateGodModelImg", godModelImg);
	}
	
	/**
	 * 상품 모델 이미지 삭제.
	 *
	 * @param godModelImg the GodModelImg
	 * @return the GodModelImg
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodModelImg(GodModelImg godModelImg) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodModelImg", godModelImg);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

