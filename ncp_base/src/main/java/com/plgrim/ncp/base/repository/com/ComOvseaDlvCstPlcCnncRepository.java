
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
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvCstPlcCnnc;

/**
 * The Class ComOvseaDlvCstPlcCnncRepository.
 */
@Repository
public class ComOvseaDlvCstPlcCnncRepository extends AbstractRepository {
	

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
	 * 업체 해외 배송비 정책 연결 상세 조회.
	 *
	 * @param comOvseaDlvCstPlcCnnc the ComOvseaDlvCstPlcCnnc
	 * @return the ComOvseaDlvCstPlcCnnc
	 * @throws SQLException the SQL exception
	 */
	public ComOvseaDlvCstPlcCnnc selectComOvseaDlvCstPlcCnnc(ComOvseaDlvCstPlcCnnc comOvseaDlvCstPlcCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComOvseaDlvCstPlcCnnc", comOvseaDlvCstPlcCnnc);
	}
	
	/**
	 * 업체 해외 배송비 정책 연결 등록.
	 *
	 * @param comOvseaDlvCstPlcCnnc the ComOvseaDlvCstPlcCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertComOvseaDlvCstPlcCnnc(ComOvseaDlvCstPlcCnnc comOvseaDlvCstPlcCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertComOvseaDlvCstPlcCnnc", comOvseaDlvCstPlcCnnc);
	}
	
	/**
	 * 업체 해외 배송비 정책 연결 수정.
	 *
	 * @param comOvseaDlvCstPlcCnnc the ComOvseaDlvCstPlcCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateComOvseaDlvCstPlcCnnc(ComOvseaDlvCstPlcCnnc comOvseaDlvCstPlcCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateComOvseaDlvCstPlcCnnc", comOvseaDlvCstPlcCnnc);
	}
	
	/**
	 * 업체 해외 배송비 정책 연결 삭제.
	 *
	 * @param comOvseaDlvCstPlcCnnc the ComOvseaDlvCstPlcCnnc
	 * @return the ComOvseaDlvCstPlcCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteComOvseaDlvCstPlcCnnc(ComOvseaDlvCstPlcCnnc comOvseaDlvCstPlcCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComOvseaDlvCstPlcCnnc", comOvseaDlvCstPlcCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

