
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrndLang;

/**
 * The Class DspStrndLangRepository.
 */
@Repository
public class DspStrndLangRepository extends AbstractRepository {
	

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
	 * 전시 S-TREND 언어 상세 조회.
	 *
	 * @param dspStrndLang the DspStrndLang
	 * @return the DspStrndLang
	 * @throws SQLException the SQL exception
	 */
	public DspStrndLang selectDspStrndLang(DspStrndLang dspStrndLang) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspStrndLang", dspStrndLang);
	}
	
	/**
	 * 전시 S-TREND 언어 등록.
	 *
	 * @param dspStrndLang the DspStrndLang
	 * @throws SQLException the SQL exception
	 */
	public void insertDspStrndLang(DspStrndLang dspStrndLang) {
		getSession1().insert("com.plgrim.ncp.base.insertDspStrndLang", dspStrndLang);
	}
	
	/**
	 * 전시 S-TREND 언어 수정.
	 *
	 * @param dspStrndLang the DspStrndLang
	 * @throws SQLException the SQL exception
	 */
	public int updateDspStrndLang(DspStrndLang dspStrndLang) {
		return getSession1().update("com.plgrim.ncp.base.updateDspStrndLang", dspStrndLang);
	}
	
	/**
	 * 전시 S-TREND 언어 삭제.
	 *
	 * @param dspStrndLang the DspStrndLang
	 * @return the DspStrndLang
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspStrndLang(DspStrndLang dspStrndLang) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspStrndLang", dspStrndLang);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

