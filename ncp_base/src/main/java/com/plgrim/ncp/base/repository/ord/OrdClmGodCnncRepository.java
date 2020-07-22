
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
import com.plgrim.ncp.base.entities.datasource1.ord.OrdClmGodCnnc;

/**
 * The Class OrdClmGodCnncRepository.
 */
@Repository
public class OrdClmGodCnncRepository extends AbstractRepository {
	

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
	 * 주문 클레임 상품 연결 상세 조회.
	 *
	 * @param ordClmGodCnnc the OrdClmGodCnnc
	 * @return the OrdClmGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public OrdClmGodCnnc selectOrdClmGodCnnc(OrdClmGodCnnc ordClmGodCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectOrdClmGodCnnc", ordClmGodCnnc);
	}
	
	/**
	 * 주문 클레임 상품 연결 등록.
	 *
	 * @param ordClmGodCnnc the OrdClmGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdClmGodCnnc(OrdClmGodCnnc ordClmGodCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertOrdClmGodCnnc", ordClmGodCnnc);
	}
	
	/**
	 * 주문 클레임 상품 연결 수정.
	 *
	 * @param ordClmGodCnnc the OrdClmGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateOrdClmGodCnnc(OrdClmGodCnnc ordClmGodCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateOrdClmGodCnnc", ordClmGodCnnc);
	}
	
	/**
	 * 주문 클레임 상품 연결 삭제.
	 *
	 * @param ordClmGodCnnc the OrdClmGodCnnc
	 * @return the OrdClmGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteOrdClmGodCnnc(OrdClmGodCnnc ordClmGodCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteOrdClmGodCnnc", ordClmGodCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

