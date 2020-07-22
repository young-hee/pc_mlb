
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
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvCstPlc;

/**
 * The Class ComOvseaDlvCstPlcRepository.
 */
@Repository
public class ComOvseaDlvCstPlcRepository extends AbstractRepository {
	

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
	 * 업체 해외 배송비 정책 상세 조회.
	 *
	 * @param comOvseaDlvCstPlc the ComOvseaDlvCstPlc
	 * @return the ComOvseaDlvCstPlc
	 * @throws SQLException the SQL exception
	 */
	public ComOvseaDlvCstPlc selectComOvseaDlvCstPlc(ComOvseaDlvCstPlc comOvseaDlvCstPlc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComOvseaDlvCstPlc", comOvseaDlvCstPlc);
	}
	
	/**
	 * 업체 해외 배송비 정책 등록.
	 *
	 * @param comOvseaDlvCstPlc the ComOvseaDlvCstPlc
	 * @throws SQLException the SQL exception
	 */
	public void insertComOvseaDlvCstPlc(ComOvseaDlvCstPlc comOvseaDlvCstPlc) {
		getSession1().insert("com.plgrim.ncp.base.insertComOvseaDlvCstPlc", comOvseaDlvCstPlc);
	}
	
	/**
	 * 업체 해외 배송비 정책 수정.
	 *
	 * @param comOvseaDlvCstPlc the ComOvseaDlvCstPlc
	 * @throws SQLException the SQL exception
	 */
	public int updateComOvseaDlvCstPlc(ComOvseaDlvCstPlc comOvseaDlvCstPlc) {
		return getSession1().update("com.plgrim.ncp.base.updateComOvseaDlvCstPlc", comOvseaDlvCstPlc);
	}
	
	/**
	 * 업체 해외 배송비 정책 삭제.
	 *
	 * @param comOvseaDlvCstPlc the ComOvseaDlvCstPlc
	 * @return the ComOvseaDlvCstPlc
	 * @throws SQLException the SQL exception
	 */
	public int deleteComOvseaDlvCstPlc(ComOvseaDlvCstPlc comOvseaDlvCstPlc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComOvseaDlvCstPlc", comOvseaDlvCstPlc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

