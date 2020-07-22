
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
 * Generator(Generator)		2018-06-11                      
 */
package com.plgrim.ncp.base.repository.cso;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInqAtchmnfl;

/**
 * The Class CsoOrgztOrdAffInqAtchmnflRepository.
 */
@Repository
public class CsoOrgztOrdAffInqAtchmnflRepository extends AbstractRepository {
	

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
	 * 고객서비스 단체 주문 제휴 문의 첨부파일 상세 조회.
	 *
	 * @param csoOrgztOrdAffInqAtchmnfl the CsoOrgztOrdAffInqAtchmnfl
	 * @return the CsoOrgztOrdAffInqAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public CsoOrgztOrdAffInqAtchmnfl selectCsoOrgztOrdAffInqAtchmnfl(CsoOrgztOrdAffInqAtchmnfl csoOrgztOrdAffInqAtchmnfl) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoOrgztOrdAffInqAtchmnfl", csoOrgztOrdAffInqAtchmnfl);
	}
	
	/**
	 * 고객서비스 단체 주문 제휴 문의 첨부파일 등록.
	 *
	 * @param csoOrgztOrdAffInqAtchmnfl the CsoOrgztOrdAffInqAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoOrgztOrdAffInqAtchmnfl(CsoOrgztOrdAffInqAtchmnfl csoOrgztOrdAffInqAtchmnfl) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoOrgztOrdAffInqAtchmnfl", csoOrgztOrdAffInqAtchmnfl);
	}
	
	/**
	 * 고객서비스 단체 주문 제휴 문의 첨부파일 수정.
	 *
	 * @param csoOrgztOrdAffInqAtchmnfl the CsoOrgztOrdAffInqAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoOrgztOrdAffInqAtchmnfl(CsoOrgztOrdAffInqAtchmnfl csoOrgztOrdAffInqAtchmnfl) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoOrgztOrdAffInqAtchmnfl", csoOrgztOrdAffInqAtchmnfl);
	}
	
	/**
	 * 고객서비스 단체 주문 제휴 문의 첨부파일 삭제.
	 *
	 * @param csoOrgztOrdAffInqAtchmnfl the CsoOrgztOrdAffInqAtchmnfl
	 * @return the CsoOrgztOrdAffInqAtchmnfl
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoOrgztOrdAffInqAtchmnfl(CsoOrgztOrdAffInqAtchmnfl csoOrgztOrdAffInqAtchmnfl) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoOrgztOrdAffInqAtchmnfl", csoOrgztOrdAffInqAtchmnfl);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

