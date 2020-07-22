
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
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;

/**
 * The Class InfOrdGodErpDstbRepository.
 */
@Repository
public class InfOrdGodErpDstbRepository extends AbstractRepository {
	

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
	 * 인터페이스 주문 상품 ERP 분배 상세 조회.
	 *
	 * @param infOrdGodErpDstb the InfOrdGodErpDstb
	 * @return the InfOrdGodErpDstb
	 * @throws SQLException the SQL exception
	 */
	public InfOrdGodErpDstb selectInfOrdGodErpDstb(InfOrdGodErpDstb infOrdGodErpDstb) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfOrdGodErpDstb", infOrdGodErpDstb);
	}
	
	/**
	 * 인터페이스 주문 상품 ERP 분배 등록.
	 *
	 * @param infOrdGodErpDstb the InfOrdGodErpDstb
	 * @throws SQLException the SQL exception
	 */
	public void insertInfOrdGodErpDstb(InfOrdGodErpDstb infOrdGodErpDstb) {
		getSession1().insert("com.plgrim.ncp.base.insertInfOrdGodErpDstb", infOrdGodErpDstb);
	}
	
	/**
	 * 인터페이스 주문 상품 ERP 분배 수정.
	 *
	 * @param infOrdGodErpDstb the InfOrdGodErpDstb
	 * @throws SQLException the SQL exception
	 */
	public int updateInfOrdGodErpDstb(InfOrdGodErpDstb infOrdGodErpDstb) {
		return getSession1().update("com.plgrim.ncp.base.updateInfOrdGodErpDstb", infOrdGodErpDstb);
	}
	
	/**
	 * 인터페이스 주문 상품 ERP 분배 삭제.
	 *
	 * @param infOrdGodErpDstb the InfOrdGodErpDstb
	 * @return the InfOrdGodErpDstb
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfOrdGodErpDstb(InfOrdGodErpDstb infOrdGodErpDstb) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfOrdGodErpDstb", infOrdGodErpDstb);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

