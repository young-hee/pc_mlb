
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
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvCstPlcLang;

/**
 * The Class ComOvseaDlvCstPlcLangRepository.
 */
@Repository
public class ComOvseaDlvCstPlcLangRepository extends AbstractRepository {
	

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
	 * 업체 해외 배송비 정책 언어 상세 조회.
	 *
	 * @param comOvseaDlvCstPlcLang the ComOvseaDlvCstPlcLang
	 * @return the ComOvseaDlvCstPlcLang
	 * @throws SQLException the SQL exception
	 */
	public ComOvseaDlvCstPlcLang selectComOvseaDlvCstPlcLang(ComOvseaDlvCstPlcLang comOvseaDlvCstPlcLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComOvseaDlvCstPlcLang", comOvseaDlvCstPlcLang);
	}
	
	/**
	 * 업체 해외 배송비 정책 언어 등록.
	 *
	 * @param comOvseaDlvCstPlcLang the ComOvseaDlvCstPlcLang
	 * @throws SQLException the SQL exception
	 */
	public void insertComOvseaDlvCstPlcLang(ComOvseaDlvCstPlcLang comOvseaDlvCstPlcLang) {
		getSession1().insert("com.plgrim.ncp.base.insertComOvseaDlvCstPlcLang", comOvseaDlvCstPlcLang);
	}
	
	/**
	 * 업체 해외 배송비 정책 언어 수정.
	 *
	 * @param comOvseaDlvCstPlcLang the ComOvseaDlvCstPlcLang
	 * @throws SQLException the SQL exception
	 */
	public int updateComOvseaDlvCstPlcLang(ComOvseaDlvCstPlcLang comOvseaDlvCstPlcLang) {
		return getSession1().update("com.plgrim.ncp.base.updateComOvseaDlvCstPlcLang", comOvseaDlvCstPlcLang);
	}
	
	/**
	 * 업체 해외 배송비 정책 언어 삭제.
	 *
	 * @param comOvseaDlvCstPlcLang the ComOvseaDlvCstPlcLang
	 * @return the ComOvseaDlvCstPlcLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteComOvseaDlvCstPlcLang(ComOvseaDlvCstPlcLang comOvseaDlvCstPlcLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComOvseaDlvCstPlcLang", comOvseaDlvCstPlcLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

