
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
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodOptMod;

/**
 * The Class OrdGodOptModRepository.
 */
@Repository
public class OrdGodOptModRepository extends AbstractRepository {
	

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
	 * 주문 상품 옵션 변경 상세 조회.
	 *
	 * @param ordGodOptMod the OrdGodOptMod
	 * @return the OrdGodOptMod
	 * @throws SQLException the SQL exception
	 */
	public OrdGodOptMod selectOrdGodOptMod(OrdGodOptMod ordGodOptMod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectOrdGodOptMod", ordGodOptMod);
	}
	
	/**
	 * 주문 상품 옵션 변경 등록.
	 *
	 * @param ordGodOptMod the OrdGodOptMod
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdGodOptMod(OrdGodOptMod ordGodOptMod) {
		getSession1().insert("com.plgrim.ncp.base.insertOrdGodOptMod", ordGodOptMod);
	}
	
	/**
	 * 주문 상품 옵션 변경 수정.
	 *
	 * @param ordGodOptMod the OrdGodOptMod
	 * @throws SQLException the SQL exception
	 */
	public int updateOrdGodOptMod(OrdGodOptMod ordGodOptMod) {
		return getSession1().update("com.plgrim.ncp.base.updateOrdGodOptMod", ordGodOptMod);
	}
	
	/**
	 * 주문 상품 옵션 변경 삭제.
	 *
	 * @param ordGodOptMod the OrdGodOptMod
	 * @return the OrdGodOptMod
	 * @throws SQLException the SQL exception
	 */
	public int deleteOrdGodOptMod(OrdGodOptMod ordGodOptMod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteOrdGodOptMod", ordGodOptMod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

