
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspTmplat;

/**
 * The Class DspTmplatRepository.
 */
@Repository
public class DspTmplatRepository extends AbstractRepository {
	

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
	 * 전시 템플릿 상세 조회.
	 *
	 * @param dspTmplat the DspTmplat
	 * @return the DspTmplat
	 * @throws SQLException the SQL exception
	 */
	public DspTmplat selectDspTmplat(DspTmplat dspTmplat) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspTmplat", dspTmplat);
	}
	
	/**
	 * 전시 템플릿 등록.
	 *
	 * @param dspTmplat the DspTmplat
	 * @throws SQLException the SQL exception
	 */
	public void insertDspTmplat(DspTmplat dspTmplat) {
		getSession1().insert("com.plgrim.ncp.base.insertDspTmplat", dspTmplat);
	}
	
	/**
	 * 전시 템플릿 수정.
	 *
	 * @param dspTmplat the DspTmplat
	 * @throws SQLException the SQL exception
	 */
	public int updateDspTmplat(DspTmplat dspTmplat) {
		return getSession1().update("com.plgrim.ncp.base.updateDspTmplat", dspTmplat);
	}
	
	/**
	 * 전시 템플릿 삭제.
	 *
	 * @param dspTmplat the DspTmplat
	 * @return the DspTmplat
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspTmplat(DspTmplat dspTmplat) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspTmplat", dspTmplat);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

