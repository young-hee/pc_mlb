
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtBrnd;

/**
 * The Class DspPromtBrndRepository.
 */
@Repository
public class DspPromtBrndRepository extends AbstractRepository {
	

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
	 * 전시 기획전 브랜드 상세 조회.
	 *
	 * @param dspPromtBrnd the DspPromtBrnd
	 * @return the DspPromtBrnd
	 * @throws SQLException the SQL exception
	 */
	public DspPromtBrnd selectDspPromtBrnd(DspPromtBrnd dspPromtBrnd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspPromtBrnd", dspPromtBrnd);
	}
	
	/**
	 * 전시 기획전 브랜드 등록.
	 *
	 * @param dspPromtBrnd the DspPromtBrnd
	 * @throws SQLException the SQL exception
	 */
	public void insertDspPromtBrnd(DspPromtBrnd dspPromtBrnd) {
		getSession1().insert("com.plgrim.ncp.base.insertDspPromtBrnd", dspPromtBrnd);
	}
	
	/**
	 * 전시 기획전 브랜드 수정.
	 *
	 * @param dspPromtBrnd the DspPromtBrnd
	 * @throws SQLException the SQL exception
	 */
	public int updateDspPromtBrnd(DspPromtBrnd dspPromtBrnd) {
		return getSession1().update("com.plgrim.ncp.base.updateDspPromtBrnd", dspPromtBrnd);
	}
	
	/**
	 * 전시 기획전 브랜드 삭제.
	 *
	 * @param dspPromtBrnd the DspPromtBrnd
	 * @return the DspPromtBrnd
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspPromtBrnd(DspPromtBrnd dspPromtBrnd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspPromtBrnd", dspPromtBrnd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

