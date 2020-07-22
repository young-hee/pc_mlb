
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
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvCstPlcLangHis;

/**
 * The Class ComOvseaDlvCstPlcLangHisRepository.
 */
@Repository
public class ComOvseaDlvCstPlcLangHisRepository extends AbstractRepository {
	

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
	 * 업체 해외 배송비 정책 언어 이력 상세 조회.
	 *
	 * @param comOvseaDlvCstPlcLangHis the ComOvseaDlvCstPlcLangHis
	 * @return the ComOvseaDlvCstPlcLangHis
	 * @throws SQLException the SQL exception
	 */
	public ComOvseaDlvCstPlcLangHis selectComOvseaDlvCstPlcLangHis(ComOvseaDlvCstPlcLangHis comOvseaDlvCstPlcLangHis) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComOvseaDlvCstPlcLangHis", comOvseaDlvCstPlcLangHis);
	}
	
	/**
	 * 업체 해외 배송비 정책 언어 이력 등록.
	 *
	 * @param comOvseaDlvCstPlcLangHis the ComOvseaDlvCstPlcLangHis
	 * @throws SQLException the SQL exception
	 */
	public void insertComOvseaDlvCstPlcLangHis(ComOvseaDlvCstPlcLangHis comOvseaDlvCstPlcLangHis) {
		getSession1().insert("com.plgrim.ncp.base.insertComOvseaDlvCstPlcLangHis", comOvseaDlvCstPlcLangHis);
	}
	
	/**
	 * 업체 해외 배송비 정책 언어 이력 수정.
	 *
	 * @param comOvseaDlvCstPlcLangHis the ComOvseaDlvCstPlcLangHis
	 * @throws SQLException the SQL exception
	 */
	public int updateComOvseaDlvCstPlcLangHis(ComOvseaDlvCstPlcLangHis comOvseaDlvCstPlcLangHis) {
		return getSession1().update("com.plgrim.ncp.base.updateComOvseaDlvCstPlcLangHis", comOvseaDlvCstPlcLangHis);
	}
	
	/**
	 * 업체 해외 배송비 정책 언어 이력 삭제.
	 *
	 * @param comOvseaDlvCstPlcLangHis the ComOvseaDlvCstPlcLangHis
	 * @return the ComOvseaDlvCstPlcLangHis
	 * @throws SQLException the SQL exception
	 */
	public int deleteComOvseaDlvCstPlcLangHis(ComOvseaDlvCstPlcLangHis comOvseaDlvCstPlcLangHis) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComOvseaDlvCstPlcLangHis", comOvseaDlvCstPlcLangHis);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

