
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
import com.plgrim.ncp.base.entities.datasource1.god.GodDetailImg;

/**
 * The Class GodDetailImgRepository.
 */
@Repository
public class GodDetailImgRepository extends AbstractRepository {
	

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
	 * 상품 상세 이미지 상세 조회.
	 *
	 * @param godDetailImg the GodDetailImg
	 * @return the GodDetailImg
	 * @throws SQLException the SQL exception
	 */
	public GodDetailImg selectGodDetailImg(GodDetailImg godDetailImg) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodDetailImg", godDetailImg);
	}
	
	/**
	 * 상품 상세 이미지 등록.
	 *
	 * @param godDetailImg the GodDetailImg
	 * @throws SQLException the SQL exception
	 */
	public void insertGodDetailImg(GodDetailImg godDetailImg) {
		getSession1().insert("com.plgrim.ncp.base.insertGodDetailImg", godDetailImg);
	}
	
	/**
	 * 상품 상세 이미지 수정.
	 *
	 * @param godDetailImg the GodDetailImg
	 * @throws SQLException the SQL exception
	 */
	public int updateGodDetailImg(GodDetailImg godDetailImg) {
		return getSession1().update("com.plgrim.ncp.base.updateGodDetailImg", godDetailImg);
	}
	
	/**
	 * 상품 상세 이미지 삭제.
	 *
	 * @param godDetailImg the GodDetailImg
	 * @return the GodDetailImg
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodDetailImg(GodDetailImg godDetailImg) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodDetailImg", godDetailImg);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

