
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
package com.plgrim.ncp.base.repository.dsp;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtSprtrLang;

/**
 * The Class DspPromtSprtrLangRepository.
 */
@Repository
public class DspPromtSprtrLangRepository extends AbstractRepository {
	

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
	 * 전시 기획전 구분자 언어 상세 조회.
	 *
	 * @param dspPromtSprtrLang the DspPromtSprtrLang
	 * @return the DspPromtSprtrLang
	 * @throws SQLException the SQL exception
	 */
	public DspPromtSprtrLang selectDspPromtSprtrLang(DspPromtSprtrLang dspPromtSprtrLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspPromtSprtrLang", dspPromtSprtrLang);
	}
	
	/**
	 * 전시 기획전 구분자 언어 등록.
	 *
	 * @param dspPromtSprtrLang the DspPromtSprtrLang
	 * @throws SQLException the SQL exception
	 */
	public void insertDspPromtSprtrLang(DspPromtSprtrLang dspPromtSprtrLang) {
		getSession1().insert("com.plgrim.ncp.base.insertDspPromtSprtrLang", dspPromtSprtrLang);
	}
	
	/**
	 * 전시 기획전 구분자 언어 수정.
	 *
	 * @param dspPromtSprtrLang the DspPromtSprtrLang
	 * @throws SQLException the SQL exception
	 */
	public int updateDspPromtSprtrLang(DspPromtSprtrLang dspPromtSprtrLang) {
		return getSession1().update("com.plgrim.ncp.base.updateDspPromtSprtrLang", dspPromtSprtrLang);
	}
	
	/**
	 * 전시 기획전 구분자 언어 삭제.
	 *
	 * @param dspPromtSprtrLang the DspPromtSprtrLang
	 * @return the DspPromtSprtrLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspPromtSprtrLang(DspPromtSprtrLang dspPromtSprtrLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspPromtSprtrLang", dspPromtSprtrLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

