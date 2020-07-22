
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
import com.plgrim.ncp.base.entities.datasource1.god.GodRelate;

/**
 * The Class GodRelateRepository.
 */
@Repository
public class GodRelateRepository extends AbstractRepository {
	

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
	 * 상품 연관 상세 조회.
	 *
	 * @param godRelate the GodRelate
	 * @return the GodRelate
	 * @throws SQLException the SQL exception
	 */
	public GodRelate selectGodRelate(GodRelate godRelate) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodRelate", godRelate);
	}
	
	/**
	 * 상품 연관 등록.
	 *
	 * @param godRelate the GodRelate
	 * @throws SQLException the SQL exception
	 */
	public void insertGodRelate(GodRelate godRelate) {
		getSession1().insert("com.plgrim.ncp.base.insertGodRelate", godRelate);
	}
	
	/**
	 * 상품 연관 수정.
	 *
	 * @param godRelate the GodRelate
	 * @throws SQLException the SQL exception
	 */
	public int updateGodRelate(GodRelate godRelate) {
		return getSession1().update("com.plgrim.ncp.base.updateGodRelate", godRelate);
	}
	
	/**
	 * 상품 연관 삭제.
	 *
	 * @param godRelate the GodRelate
	 * @return the GodRelate
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodRelate(GodRelate godRelate) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodRelate", godRelate);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

