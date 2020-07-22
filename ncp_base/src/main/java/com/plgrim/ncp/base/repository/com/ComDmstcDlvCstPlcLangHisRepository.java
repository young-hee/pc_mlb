
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
 * Generator(Generator)		2018-05-29                      
 */
package com.plgrim.ncp.base.repository.com;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcLangHis;

/**
 * The Class ComDmstcDlvCstPlcLangHisRepository.
 */
@Repository
public class ComDmstcDlvCstPlcLangHisRepository extends AbstractRepository {
	

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
	 * 업체 국내 배송비 정책 언어 이력 상세 조회.
	 *
	 * @param comDmstcDlvCstPlcLangHis the ComDmstcDlvCstPlcLangHis
	 * @return the ComDmstcDlvCstPlcLangHis
	 * @throws SQLException the SQL exception
	 */
	public ComDmstcDlvCstPlcLangHis selectComDmstcDlvCstPlcLangHis(ComDmstcDlvCstPlcLangHis comDmstcDlvCstPlcLangHis) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComDmstcDlvCstPlcLangHis", comDmstcDlvCstPlcLangHis);
	}
	
	/**
	 * 업체 국내 배송비 정책 언어 이력 등록.
	 *
	 * @param comDmstcDlvCstPlcLangHis the ComDmstcDlvCstPlcLangHis
	 * @throws SQLException the SQL exception
	 */
	public void insertComDmstcDlvCstPlcLangHis(ComDmstcDlvCstPlcLangHis comDmstcDlvCstPlcLangHis) {
		getSession1().insert("com.plgrim.ncp.base.insertComDmstcDlvCstPlcLangHis", comDmstcDlvCstPlcLangHis);
	}
	
	/**
	 * 업체 국내 배송비 정책 언어 이력 수정.
	 *
	 * @param comDmstcDlvCstPlcLangHis the ComDmstcDlvCstPlcLangHis
	 * @throws SQLException the SQL exception
	 */
	public int updateComDmstcDlvCstPlcLangHis(ComDmstcDlvCstPlcLangHis comDmstcDlvCstPlcLangHis) {
		return getSession1().update("com.plgrim.ncp.base.updateComDmstcDlvCstPlcLangHis", comDmstcDlvCstPlcLangHis);
	}
	
	/**
	 * 업체 국내 배송비 정책 언어 이력 삭제.
	 *
	 * @param comDmstcDlvCstPlcLangHis the ComDmstcDlvCstPlcLangHis
	 * @return the ComDmstcDlvCstPlcLangHis
	 * @throws SQLException the SQL exception
	 */
	public int deleteComDmstcDlvCstPlcLangHis(ComDmstcDlvCstPlcLangHis comDmstcDlvCstPlcLangHis) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComDmstcDlvCstPlcLangHis", comDmstcDlvCstPlcLangHis);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

