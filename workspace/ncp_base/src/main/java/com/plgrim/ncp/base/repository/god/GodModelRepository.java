
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
import com.plgrim.ncp.base.entities.datasource1.god.GodModel;

/**
 * The Class GodModelRepository.
 */
@Repository
public class GodModelRepository extends AbstractRepository {
	

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
	 * 상품 모델 상세 조회.
	 *
	 * @param godModel the GodModel
	 * @return the GodModel
	 * @throws SQLException the SQL exception
	 */
	public GodModel selectGodModel(GodModel godModel) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodModel", godModel);
	}
	
	/**
	 * 상품 모델 등록.
	 *
	 * @param godModel the GodModel
	 * @throws SQLException the SQL exception
	 */
	public void insertGodModel(GodModel godModel) {
		getSession1().insert("com.plgrim.ncp.base.insertGodModel", godModel);
	}
	
	/**
	 * 상품 모델 수정.
	 *
	 * @param godModel the GodModel
	 * @throws SQLException the SQL exception
	 */
	public int updateGodModel(GodModel godModel) {
		return getSession1().update("com.plgrim.ncp.base.updateGodModel", godModel);
	}
	
	/**
	 * 상품 모델 삭제.
	 *
	 * @param godModel the GodModel
	 * @return the GodModel
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodModel(GodModel godModel) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodModel", godModel);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

