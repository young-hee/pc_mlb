
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
package com.plgrim.ncp.base.repository.ord;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;

/**
 * The Class OrdRepository.
 */
@Repository
public class OrdRepository extends AbstractRepository {
	

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
	 * 주문 상세 조회.
	 *
	 * @param ord the Ord
	 * @return the Ord
	 * @throws SQLException the SQL exception
	 */
	public Ord selectOrd(Ord ord) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectOrd", ord);
	}
	
	/**
	 * 주문 등록.
	 *
	 * @param ord the Ord
	 * @throws SQLException the SQL exception
	 */
	public void insertOrd(Ord ord) {
		getSession1().insert("com.plgrim.ncp.base.insertOrd", ord);
	}
	
	/**
	 * 주문 수정.
	 *
	 * @param ord the Ord
	 * @throws SQLException the SQL exception
	 */
	public int updateOrd(Ord ord) {
		return getSession1().update("com.plgrim.ncp.base.updateOrd", ord);
	}
	
	/**
	 * 주문 삭제.
	 *
	 * @param ord the Ord
	 * @return the Ord
	 * @throws SQLException the SQL exception
	 */
	public int deleteOrd(Ord ord) {
		return getSession1().delete("com.plgrim.ncp.base.deleteOrd", ord);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

