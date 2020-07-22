
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
 * Generator(Generator)		2018-06-04                      
 */
package com.plgrim.ncp.base.repository.cso;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInq;

/**
 * The Class CsoOrgztOrdAffInqRepository.
 */
@Repository
public class CsoOrgztOrdAffInqRepository extends AbstractRepository {
	

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
	 * 고객서비스 단체 주문 제휴 문의 상세 조회.
	 *
	 * @param csoOrgztOrdAffInq the CsoOrgztOrdAffInq
	 * @return the CsoOrgztOrdAffInq
	 * @throws SQLException the SQL exception
	 */
	public CsoOrgztOrdAffInq selectCsoOrgztOrdAffInq(CsoOrgztOrdAffInq csoOrgztOrdAffInq) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoOrgztOrdAffInq", csoOrgztOrdAffInq);
	}
	
	/**
	 * 고객서비스 단체 주문 제휴 문의 등록.
	 *
	 * @param csoOrgztOrdAffInq the CsoOrgztOrdAffInq
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoOrgztOrdAffInq(CsoOrgztOrdAffInq csoOrgztOrdAffInq) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoOrgztOrdAffInq", csoOrgztOrdAffInq);
	}
	
	/**
	 * 고객서비스 단체 주문 제휴 문의 수정.
	 *
	 * @param csoOrgztOrdAffInq the CsoOrgztOrdAffInq
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoOrgztOrdAffInq(CsoOrgztOrdAffInq csoOrgztOrdAffInq) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoOrgztOrdAffInq", csoOrgztOrdAffInq);
	}
	
	/**
	 * 고객서비스 단체 주문 제휴 문의 삭제.
	 *
	 * @param csoOrgztOrdAffInq the CsoOrgztOrdAffInq
	 * @return the CsoOrgztOrdAffInq
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoOrgztOrdAffInq(CsoOrgztOrdAffInq csoOrgztOrdAffInq) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoOrgztOrdAffInq", csoOrgztOrdAffInq);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

