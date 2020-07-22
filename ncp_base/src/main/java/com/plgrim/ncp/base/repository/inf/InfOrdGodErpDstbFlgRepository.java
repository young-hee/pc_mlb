
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
package com.plgrim.ncp.base.repository.inf;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbFlg;

/**
 * The Class InfOrdGodErpDstbFlgRepository.
 */
@Repository
public class InfOrdGodErpDstbFlgRepository extends AbstractRepository {
	

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
	 * 인터페이스 주문 상품 ERP 분배 플래그 상세 조회.
	 *
	 * @param infOrdGodErpDstbFlg the InfOrdGodErpDstbFlg
	 * @return the InfOrdGodErpDstbFlg
	 * @throws SQLException the SQL exception
	 */
	public InfOrdGodErpDstbFlg selectInfOrdGodErpDstbFlg(InfOrdGodErpDstbFlg infOrdGodErpDstbFlg) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfOrdGodErpDstbFlg", infOrdGodErpDstbFlg);
	}
	
	/**
	 * 인터페이스 주문 상품 ERP 분배 플래그 등록.
	 *
	 * @param infOrdGodErpDstbFlg the InfOrdGodErpDstbFlg
	 * @throws SQLException the SQL exception
	 */
	public void insertInfOrdGodErpDstbFlg(InfOrdGodErpDstbFlg infOrdGodErpDstbFlg) {
		getSession1().insert("com.plgrim.ncp.base.insertInfOrdGodErpDstbFlg", infOrdGodErpDstbFlg);
	}
	
	/**
	 * 인터페이스 주문 상품 ERP 분배 플래그 수정.
	 *
	 * @param infOrdGodErpDstbFlg the InfOrdGodErpDstbFlg
	 * @throws SQLException the SQL exception
	 */
	public int updateInfOrdGodErpDstbFlg(InfOrdGodErpDstbFlg infOrdGodErpDstbFlg) {
		return getSession1().update("com.plgrim.ncp.base.updateInfOrdGodErpDstbFlg", infOrdGodErpDstbFlg);
	}
	
	/**
	 * 인터페이스 주문 상품 ERP 분배 플래그 삭제.
	 *
	 * @param infOrdGodErpDstbFlg the InfOrdGodErpDstbFlg
	 * @return the InfOrdGodErpDstbFlg
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfOrdGodErpDstbFlg(InfOrdGodErpDstbFlg infOrdGodErpDstbFlg) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfOrdGodErpDstbFlg", infOrdGodErpDstbFlg);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

