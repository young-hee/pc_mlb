
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
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcLang;

/**
 * The Class ComDmstcDlvCstPlcLangRepository.
 */
@Repository
public class ComDmstcDlvCstPlcLangRepository extends AbstractRepository {
	

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
	 * 업체 국내 배송비 정책 언어 상세 조회.
	 *
	 * @param comDmstcDlvCstPlcLang the ComDmstcDlvCstPlcLang
	 * @return the ComDmstcDlvCstPlcLang
	 * @throws SQLException the SQL exception
	 */
	public ComDmstcDlvCstPlcLang selectComDmstcDlvCstPlcLang(ComDmstcDlvCstPlcLang comDmstcDlvCstPlcLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComDmstcDlvCstPlcLang", comDmstcDlvCstPlcLang);
	}
	
	/**
	 * 업체 국내 배송비 정책 언어 등록.
	 *
	 * @param comDmstcDlvCstPlcLang the ComDmstcDlvCstPlcLang
	 * @throws SQLException the SQL exception
	 */
	public void insertComDmstcDlvCstPlcLang(ComDmstcDlvCstPlcLang comDmstcDlvCstPlcLang) {
		getSession1().insert("com.plgrim.ncp.base.insertComDmstcDlvCstPlcLang", comDmstcDlvCstPlcLang);
	}
	
	/**
	 * 업체 국내 배송비 정책 언어 수정.
	 *
	 * @param comDmstcDlvCstPlcLang the ComDmstcDlvCstPlcLang
	 * @throws SQLException the SQL exception
	 */
	public int updateComDmstcDlvCstPlcLang(ComDmstcDlvCstPlcLang comDmstcDlvCstPlcLang) {
		return getSession1().update("com.plgrim.ncp.base.updateComDmstcDlvCstPlcLang", comDmstcDlvCstPlcLang);
	}
	
	/**
	 * 업체 국내 배송비 정책 언어 삭제.
	 *
	 * @param comDmstcDlvCstPlcLang the ComDmstcDlvCstPlcLang
	 * @return the ComDmstcDlvCstPlcLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteComDmstcDlvCstPlcLang(ComDmstcDlvCstPlcLang comDmstcDlvCstPlcLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComDmstcDlvCstPlcLang", comDmstcDlvCstPlcLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

