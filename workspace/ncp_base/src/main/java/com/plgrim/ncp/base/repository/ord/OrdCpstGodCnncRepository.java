
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
import com.plgrim.ncp.base.entities.datasource1.ord.OrdCpstGodCnnc;

/**
 * The Class OrdCpstGodCnncRepository.
 */
@Repository
public class OrdCpstGodCnncRepository extends AbstractRepository {
	

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
	 * 주문 구성 상품 연결 상세 조회.
	 *
	 * @param ordCpstGodCnnc the OrdCpstGodCnnc
	 * @return the OrdCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public OrdCpstGodCnnc selectOrdCpstGodCnnc(OrdCpstGodCnnc ordCpstGodCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectOrdCpstGodCnnc", ordCpstGodCnnc);
	}
	
	/**
	 * 주문 구성 상품 연결 등록.
	 *
	 * @param ordCpstGodCnnc the OrdCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdCpstGodCnnc(OrdCpstGodCnnc ordCpstGodCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertOrdCpstGodCnnc", ordCpstGodCnnc);
	}
	
	/**
	 * 주문 구성 상품 연결 수정.
	 *
	 * @param ordCpstGodCnnc the OrdCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateOrdCpstGodCnnc(OrdCpstGodCnnc ordCpstGodCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateOrdCpstGodCnnc", ordCpstGodCnnc);
	}
	
	/**
	 * 주문 구성 상품 연결 삭제.
	 *
	 * @param ordCpstGodCnnc the OrdCpstGodCnnc
	 * @return the OrdCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteOrdCpstGodCnnc(OrdCpstGodCnnc ordCpstGodCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteOrdCpstGodCnnc", ordCpstGodCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

