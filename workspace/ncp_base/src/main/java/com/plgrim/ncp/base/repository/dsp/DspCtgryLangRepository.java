
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryLang;

/**
 * The Class DspCtgryLangRepository.
 */
@Repository
public class DspCtgryLangRepository extends AbstractRepository {
	

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
	 * 전시 카테고리 언어 상세 조회.
	 *
	 * @param dspCtgryLang the DspCtgryLang
	 * @return the DspCtgryLang
	 * @throws SQLException the SQL exception
	 */
	public DspCtgryLang selectDspCtgryLang(DspCtgryLang dspCtgryLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspCtgryLang", dspCtgryLang);
	}
	
	/**
	 * 전시 카테고리 언어 등록.
	 *
	 * @param dspCtgryLang the DspCtgryLang
	 * @throws SQLException the SQL exception
	 */
	public void insertDspCtgryLang(DspCtgryLang dspCtgryLang) {
		getSession1().insert("com.plgrim.ncp.base.insertDspCtgryLang", dspCtgryLang);
	}
	
	/**
	 * 전시 카테고리 언어 수정.
	 *
	 * @param dspCtgryLang the DspCtgryLang
	 * @throws SQLException the SQL exception
	 */
	public int updateDspCtgryLang(DspCtgryLang dspCtgryLang) {
		return getSession1().update("com.plgrim.ncp.base.updateDspCtgryLang", dspCtgryLang);
	}
	
	/**
	 * 전시 카테고리 언어 삭제.
	 *
	 * @param dspCtgryLang the DspCtgryLang
	 * @return the DspCtgryLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspCtgryLang(DspCtgryLang dspCtgryLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspCtgryLang", dspCtgryLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

