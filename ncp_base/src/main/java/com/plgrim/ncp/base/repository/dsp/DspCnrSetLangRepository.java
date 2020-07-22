
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSetLang;

/**
 * The Class DspCnrSetLangRepository.
 */
@Repository
public class DspCnrSetLangRepository extends AbstractRepository {
	

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
	 * 전시 코너 세트 언어 상세 조회.
	 *
	 * @param dspCnrSetLang the DspCnrSetLang
	 * @return the DspCnrSetLang
	 * @throws SQLException the SQL exception
	 */
	public DspCnrSetLang selectDspCnrSetLang(DspCnrSetLang dspCnrSetLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspCnrSetLang", dspCnrSetLang);
	}
	
	/**
	 * 전시 코너 세트 언어 등록.
	 *
	 * @param dspCnrSetLang the DspCnrSetLang
	 * @throws SQLException the SQL exception
	 */
	public void insertDspCnrSetLang(DspCnrSetLang dspCnrSetLang) {
		getSession1().insert("com.plgrim.ncp.base.insertDspCnrSetLang", dspCnrSetLang);
	}
	
	/**
	 * 전시 코너 세트 언어 수정.
	 *
	 * @param dspCnrSetLang the DspCnrSetLang
	 * @throws SQLException the SQL exception
	 */
	public int updateDspCnrSetLang(DspCnrSetLang dspCnrSetLang) {
		return getSession1().update("com.plgrim.ncp.base.updateDspCnrSetLang", dspCnrSetLang);
	}
	
	/**
	 * 전시 코너 세트 언어 삭제.
	 *
	 * @param dspCnrSetLang the DspCnrSetLang
	 * @return the DspCnrSetLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspCnrSetLang(DspCnrSetLang dspCnrSetLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspCnrSetLang", dspCnrSetLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

