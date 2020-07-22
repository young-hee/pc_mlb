
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtImgLang;

/**
 * The Class DspPromtImgLangRepository.
 */
@Repository
public class DspPromtImgLangRepository extends AbstractRepository {
	

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
	 * 전시 기획전 이미지 언어 상세 조회.
	 *
	 * @param dspPromtImgLang the DspPromtImgLang
	 * @return the DspPromtImgLang
	 * @throws SQLException the SQL exception
	 */
	public DspPromtImgLang selectDspPromtImgLang(DspPromtImgLang dspPromtImgLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspPromtImgLang", dspPromtImgLang);
	}
	
	/**
	 * 전시 기획전 이미지 언어 등록.
	 *
	 * @param dspPromtImgLang the DspPromtImgLang
	 * @throws SQLException the SQL exception
	 */
	public void insertDspPromtImgLang(DspPromtImgLang dspPromtImgLang) {
		getSession1().insert("com.plgrim.ncp.base.insertDspPromtImgLang", dspPromtImgLang);
	}
	
	/**
	 * 전시 기획전 이미지 언어 수정.
	 *
	 * @param dspPromtImgLang the DspPromtImgLang
	 * @throws SQLException the SQL exception
	 */
	public int updateDspPromtImgLang(DspPromtImgLang dspPromtImgLang) {
		return getSession1().update("com.plgrim.ncp.base.updateDspPromtImgLang", dspPromtImgLang);
	}
	
	/**
	 * 전시 기획전 이미지 언어 삭제.
	 *
	 * @param dspPromtImgLang the DspPromtImgLang
	 * @return the DspPromtImgLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspPromtImgLang(DspPromtImgLang dspPromtImgLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspPromtImgLang", dspPromtImgLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

