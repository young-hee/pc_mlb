
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
 * Generator(Generator)		2018-05-27                      
 */
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndrImg;

/**
 * The Class GodLndrImgRepository.
 */
@Repository
public class GodLndrImgRepository extends AbstractRepository {
	

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
	 * 상품 세탁 이미지 상세 조회.
	 *
	 * @param godLndrImg the GodLndrImg
	 * @return the GodLndrImg
	 * @throws SQLException the SQL exception
	 */
	public GodLndrImg selectGodLndrImg(GodLndrImg godLndrImg) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodLndrImg", godLndrImg);
	}
	
	/**
	 * 상품 세탁 이미지 등록.
	 *
	 * @param godLndrImg the GodLndrImg
	 * @throws SQLException the SQL exception
	 */
	public void insertGodLndrImg(GodLndrImg godLndrImg) {
		getSession1().insert("com.plgrim.ncp.base.insertGodLndrImg", godLndrImg);
	}
	
	/**
	 * 상품 세탁 이미지 수정.
	 *
	 * @param godLndrImg the GodLndrImg
	 * @throws SQLException the SQL exception
	 */
	public int updateGodLndrImg(GodLndrImg godLndrImg) {
		return getSession1().update("com.plgrim.ncp.base.updateGodLndrImg", godLndrImg);
	}
	
	/**
	 * 상품 세탁 이미지 삭제.
	 *
	 * @param godLndrImg the GodLndrImg
	 * @return the GodLndrImg
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodLndrImg(GodLndrImg godLndrImg) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodLndrImg", godLndrImg);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

