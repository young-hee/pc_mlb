
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
package com.plgrim.ncp.base.repository.com;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvCstPlcHist;

/**
 * The Class ComOvseaDlvCstPlcHistRepository.
 */
@Repository
public class ComOvseaDlvCstPlcHistRepository extends AbstractRepository {
	

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
	 * 업체 해외 배송비 정책 이력 상세 조회.
	 *
	 * @param comOvseaDlvCstPlcHist the ComOvseaDlvCstPlcHist
	 * @return the ComOvseaDlvCstPlcHist
	 * @throws SQLException the SQL exception
	 */
	public ComOvseaDlvCstPlcHist selectComOvseaDlvCstPlcHist(ComOvseaDlvCstPlcHist comOvseaDlvCstPlcHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComOvseaDlvCstPlcHist", comOvseaDlvCstPlcHist);
	}
	
	/**
	 * 업체 해외 배송비 정책 이력 등록.
	 *
	 * @param comOvseaDlvCstPlcHist the ComOvseaDlvCstPlcHist
	 * @throws SQLException the SQL exception
	 */
	public void insertComOvseaDlvCstPlcHist(ComOvseaDlvCstPlcHist comOvseaDlvCstPlcHist) {
		getSession1().insert("com.plgrim.ncp.base.insertComOvseaDlvCstPlcHist", comOvseaDlvCstPlcHist);
	}
	
	/**
	 * 업체 해외 배송비 정책 이력 수정.
	 *
	 * @param comOvseaDlvCstPlcHist the ComOvseaDlvCstPlcHist
	 * @throws SQLException the SQL exception
	 */
	public int updateComOvseaDlvCstPlcHist(ComOvseaDlvCstPlcHist comOvseaDlvCstPlcHist) {
		return getSession1().update("com.plgrim.ncp.base.updateComOvseaDlvCstPlcHist", comOvseaDlvCstPlcHist);
	}
	
	/**
	 * 업체 해외 배송비 정책 이력 삭제.
	 *
	 * @param comOvseaDlvCstPlcHist the ComOvseaDlvCstPlcHist
	 * @return the ComOvseaDlvCstPlcHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteComOvseaDlvCstPlcHist(ComOvseaDlvCstPlcHist comOvseaDlvCstPlcHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComOvseaDlvCstPlcHist", comOvseaDlvCstPlcHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

