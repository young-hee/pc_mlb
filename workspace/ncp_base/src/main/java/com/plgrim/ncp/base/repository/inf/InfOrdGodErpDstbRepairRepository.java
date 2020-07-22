
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
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbRepair;

/**
 * The Class InfOrdGodErpDstbRepairRepository.
 */
@Repository
public class InfOrdGodErpDstbRepairRepository extends AbstractRepository {
	

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
	 * 인터페이스 주문 상품 ERP 분배 수선 상세 조회.
	 *
	 * @param infOrdGodErpDstbRepair the InfOrdGodErpDstbRepair
	 * @return the InfOrdGodErpDstbRepair
	 * @throws SQLException the SQL exception
	 */
	public InfOrdGodErpDstbRepair selectInfOrdGodErpDstbRepair(InfOrdGodErpDstbRepair infOrdGodErpDstbRepair) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfOrdGodErpDstbRepair", infOrdGodErpDstbRepair);
	}
	
	/**
	 * 인터페이스 주문 상품 ERP 분배 수선 등록.
	 *
	 * @param infOrdGodErpDstbRepair the InfOrdGodErpDstbRepair
	 * @throws SQLException the SQL exception
	 */
	public void insertInfOrdGodErpDstbRepair(InfOrdGodErpDstbRepair infOrdGodErpDstbRepair) {
		getSession1().insert("com.plgrim.ncp.base.insertInfOrdGodErpDstbRepair", infOrdGodErpDstbRepair);
	}
	
	/**
	 * 인터페이스 주문 상품 ERP 분배 수선 수정.
	 *
	 * @param infOrdGodErpDstbRepair the InfOrdGodErpDstbRepair
	 * @throws SQLException the SQL exception
	 */
	public int updateInfOrdGodErpDstbRepair(InfOrdGodErpDstbRepair infOrdGodErpDstbRepair) {
		return getSession1().update("com.plgrim.ncp.base.updateInfOrdGodErpDstbRepair", infOrdGodErpDstbRepair);
	}
	
	/**
	 * 인터페이스 주문 상품 ERP 분배 수선 삭제.
	 *
	 * @param infOrdGodErpDstbRepair the InfOrdGodErpDstbRepair
	 * @return the InfOrdGodErpDstbRepair
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfOrdGodErpDstbRepair(InfOrdGodErpDstbRepair infOrdGodErpDstbRepair) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfOrdGodErpDstbRepair", infOrdGodErpDstbRepair);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

