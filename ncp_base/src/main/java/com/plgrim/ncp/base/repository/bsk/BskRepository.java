
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
package com.plgrim.ncp.base.repository.bsk;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.bsk.Bsk;

/**
 * The Class BskRepository.
 */
@Repository
public class BskRepository extends AbstractRepository {
	

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
	 * 장바구니 상세 조회.
	 *
	 * @param bsk the Bsk
	 * @return the Bsk
	 * @throws SQLException the SQL exception
	 */
	public Bsk selectBsk(Bsk bsk) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectBsk", bsk);
	}
	
	/**
	 * 장바구니 등록.
	 *
	 * @param bsk the Bsk
	 * @throws SQLException the SQL exception
	 */
	public void insertBsk(Bsk bsk) {
		getSession1().insert("com.plgrim.ncp.base.insertBsk", bsk);
	}
	
	/**
	 * 장바구니 수정.
	 *
	 * @param bsk the Bsk
	 * @throws SQLException the SQL exception
	 */
	public int updateBsk(Bsk bsk) {
		return getSession1().update("com.plgrim.ncp.base.updateBsk", bsk);
	}
	
	/**
	 * 장바구니 삭제.
	 *
	 * @param bsk the Bsk
	 * @return the Bsk
	 * @throws SQLException the SQL exception
	 */
	public int deleteBsk(Bsk bsk) {
		return getSession1().delete("com.plgrim.ncp.base.deleteBsk", bsk);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

