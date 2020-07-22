
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
 * Generator(Generator)		2018-04-13                      
 */
package com.plgrim.ncp.base.repository.ord;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource3.ord.OrdFrgnrIdtCardEncrtKey;

/**
 * The Class OrdFrgnrIdtCardEncrtKeyRepository.
 */
@Repository
public class OrdFrgnrIdtCardEncrtKeyRepository extends AbstractRepository {
	

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
	 * 주문 외국인 신분증 암호화 키 상세 조회.
	 *
	 * @param ordFrgnrIdtCardEncrtKey the OrdFrgnrIdtCardEncrtKey
	 * @return the OrdFrgnrIdtCardEncrtKey
	 * @throws SQLException the SQL exception
	 */
	public OrdFrgnrIdtCardEncrtKey selectOrdFrgnrIdtCardEncrtKey(OrdFrgnrIdtCardEncrtKey ordFrgnrIdtCardEncrtKey) throws Exception {
		return getSession3().selectOne("com.plgrim.ncp.base.selectOrdFrgnrIdtCardEncrtKey", ordFrgnrIdtCardEncrtKey);
	}
	
	/**
	 * 주문 외국인 신분증 암호화 키 등록.
	 *
	 * @param ordFrgnrIdtCardEncrtKey the OrdFrgnrIdtCardEncrtKey
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdFrgnrIdtCardEncrtKey(OrdFrgnrIdtCardEncrtKey ordFrgnrIdtCardEncrtKey) throws Exception {
		getSession3().insert("com.plgrim.ncp.base.insertOrdFrgnrIdtCardEncrtKey", ordFrgnrIdtCardEncrtKey);
	}
	
	/**
	 * 주문 외국인 신분증 암호화 키 수정.
	 *
	 * @param ordFrgnrIdtCardEncrtKey the OrdFrgnrIdtCardEncrtKey
	 * @throws SQLException the SQL exception
	 */
	public int updateOrdFrgnrIdtCardEncrtKey(OrdFrgnrIdtCardEncrtKey ordFrgnrIdtCardEncrtKey) throws Exception {
		return getSession3().update("com.plgrim.ncp.base.updateOrdFrgnrIdtCardEncrtKey", ordFrgnrIdtCardEncrtKey);
	}
	
	/**
	 * 주문 외국인 신분증 암호화 키 삭제.
	 *
	 * @param ordFrgnrIdtCardEncrtKey the OrdFrgnrIdtCardEncrtKey
	 * @return the OrdFrgnrIdtCardEncrtKey
	 * @throws SQLException the SQL exception
	 */
	public int deleteOrdFrgnrIdtCardEncrtKey(OrdFrgnrIdtCardEncrtKey ordFrgnrIdtCardEncrtKey) throws Exception {
		return getSession3().delete("com.plgrim.ncp.base.deleteOrdFrgnrIdtCardEncrtKey", ordFrgnrIdtCardEncrtKey);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

