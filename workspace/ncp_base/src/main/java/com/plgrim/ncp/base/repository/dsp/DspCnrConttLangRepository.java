
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrConttLang;

/**
 * The Class DspCnrConttLangRepository.
 */
@Repository
public class DspCnrConttLangRepository extends AbstractRepository {
	

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
	 * 전시 코너 컨텐츠 언어 상세 조회.
	 *
	 * @param dspCnrConttLang the DspCnrConttLang
	 * @return the DspCnrConttLang
	 * @throws SQLException the SQL exception
	 */
	public DspCnrConttLang selectDspCnrConttLang(DspCnrConttLang dspCnrConttLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspCnrConttLang", dspCnrConttLang);
	}
	
	/**
	 * 전시 코너 컨텐츠 언어 등록.
	 *
	 * @param dspCnrConttLang the DspCnrConttLang
	 * @throws SQLException the SQL exception
	 */
	public void insertDspCnrConttLang(DspCnrConttLang dspCnrConttLang) {
		getSession1().insert("com.plgrim.ncp.base.insertDspCnrConttLang", dspCnrConttLang);
	}
	
	/**
	 * 전시 코너 컨텐츠 언어 수정.
	 *
	 * @param dspCnrConttLang the DspCnrConttLang
	 * @throws SQLException the SQL exception
	 */
	public int updateDspCnrConttLang(DspCnrConttLang dspCnrConttLang) {
		return getSession1().update("com.plgrim.ncp.base.updateDspCnrConttLang", dspCnrConttLang);
	}
	
	/**
	 * 전시 코너 컨텐츠 언어 삭제.
	 *
	 * @param dspCnrConttLang the DspCnrConttLang
	 * @return the DspCnrConttLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspCnrConttLang(DspCnrConttLang dspCnrConttLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspCnrConttLang", dspCnrConttLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

