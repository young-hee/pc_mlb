
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
package com.plgrim.ncp.base.repository.cso;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInqAns;

/**
 * The Class CsoOrgztOrdAffInqAnsRepository.
 */
@Repository
public class CsoOrgztOrdAffInqAnsRepository extends AbstractRepository {
	

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
	 * 고객서비스 단체 주문 제휴 문의 답변 상세 조회.
	 *
	 * @param csoOrgztOrdAffInqAns the CsoOrgztOrdAffInqAns
	 * @return the CsoOrgztOrdAffInqAns
	 * @throws SQLException the SQL exception
	 */
	public CsoOrgztOrdAffInqAns selectCsoOrgztOrdAffInqAns(CsoOrgztOrdAffInqAns csoOrgztOrdAffInqAns) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoOrgztOrdAffInqAns", csoOrgztOrdAffInqAns);
	}
	
	/**
	 * 고객서비스 단체 주문 제휴 문의 답변 등록.
	 *
	 * @param csoOrgztOrdAffInqAns the CsoOrgztOrdAffInqAns
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoOrgztOrdAffInqAns(CsoOrgztOrdAffInqAns csoOrgztOrdAffInqAns) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoOrgztOrdAffInqAns", csoOrgztOrdAffInqAns);
	}
	
	/**
	 * 고객서비스 단체 주문 제휴 문의 답변 수정.
	 *
	 * @param csoOrgztOrdAffInqAns the CsoOrgztOrdAffInqAns
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoOrgztOrdAffInqAns(CsoOrgztOrdAffInqAns csoOrgztOrdAffInqAns) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoOrgztOrdAffInqAns", csoOrgztOrdAffInqAns);
	}
	
	/**
	 * 고객서비스 단체 주문 제휴 문의 답변 삭제.
	 *
	 * @param csoOrgztOrdAffInqAns the CsoOrgztOrdAffInqAns
	 * @return the CsoOrgztOrdAffInqAns
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoOrgztOrdAffInqAns(CsoOrgztOrdAffInqAns csoOrgztOrdAffInqAns) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoOrgztOrdAffInqAns", csoOrgztOrdAffInqAns);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

