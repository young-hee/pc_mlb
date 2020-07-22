
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtLang;

/**
 * The Class DspPromtLangRepository.
 */
@Repository
public class DspPromtLangRepository extends AbstractRepository {
	

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
	 * 전시 기획전 언어 상세 조회.
	 *
	 * @param dspPromtLang the DspPromtLang
	 * @return the DspPromtLang
	 * @throws SQLException the SQL exception
	 */
	public DspPromtLang selectDspPromtLang(DspPromtLang dspPromtLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspPromtLang", dspPromtLang);
	}
	
	/**
	 * 전시 기획전 언어 등록.
	 *
	 * @param dspPromtLang the DspPromtLang
	 * @throws SQLException the SQL exception
	 */
	public void insertDspPromtLang(DspPromtLang dspPromtLang) {
		getSession1().insert("com.plgrim.ncp.base.insertDspPromtLang", dspPromtLang);
	}
	
	/**
	 * 전시 기획전 언어 수정.
	 *
	 * @param dspPromtLang the DspPromtLang
	 * @throws SQLException the SQL exception
	 */
	public int updateDspPromtLang(DspPromtLang dspPromtLang) {
		return getSession1().update("com.plgrim.ncp.base.updateDspPromtLang", dspPromtLang);
	}
	
	/**
	 * 전시 기획전 언어 삭제.
	 *
	 * @param dspPromtLang the DspPromtLang
	 * @return the DspPromtLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspPromtLang(DspPromtLang dspPromtLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspPromtLang", dspPromtLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

