
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
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;

/**
 * The Class GodImgRepository.
 */
@Repository
public class GodImgRepository extends AbstractRepository {
	

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
	 * 상품 이미지 상세 조회.
	 *
	 * @param godImg the GodImg
	 * @return the GodImg
	 * @throws SQLException the SQL exception
	 */
	public GodImg selectGodImg(GodImg godImg) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodImg", godImg);
	}
	
	/**
	 * 상품 이미지 등록.
	 *
	 * @param godImg the GodImg
	 * @throws SQLException the SQL exception
	 */
	public void insertGodImg(GodImg godImg) {
		getSession1().insert("com.plgrim.ncp.base.insertGodImg", godImg);
	}
	
	/**
	 * 상품 이미지 수정.
	 *
	 * @param godImg the GodImg
	 * @throws SQLException the SQL exception
	 */
	public int updateGodImg(GodImg godImg) {
		return getSession1().update("com.plgrim.ncp.base.updateGodImg", godImg);
	}
	
	/**
	 * 상품 이미지 삭제.
	 *
	 * @param godImg the GodImg
	 * @return the GodImg
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodImg(GodImg godImg) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodImg", godImg);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

