
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtDspTgt;

/**
 * The Class DspPromtDspTgtRepository.
 */
@Repository
public class DspPromtDspTgtRepository extends AbstractRepository {
	

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
	 * 전시 기획전 전시 대상 상세 조회.
	 *
	 * @param dspPromtDspTgt the DspPromtDspTgt
	 * @return the DspPromtDspTgt
	 * @throws SQLException the SQL exception
	 */
	public DspPromtDspTgt selectDspPromtDspTgt(DspPromtDspTgt dspPromtDspTgt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspPromtDspTgt", dspPromtDspTgt);
	}
	
	/**
	 * 전시 기획전 전시 대상 등록.
	 *
	 * @param dspPromtDspTgt the DspPromtDspTgt
	 * @throws SQLException the SQL exception
	 */
	public void insertDspPromtDspTgt(DspPromtDspTgt dspPromtDspTgt) {
		getSession1().insert("com.plgrim.ncp.base.insertDspPromtDspTgt", dspPromtDspTgt);
	}
	
	/**
	 * 전시 기획전 전시 대상 수정.
	 *
	 * @param dspPromtDspTgt the DspPromtDspTgt
	 * @throws SQLException the SQL exception
	 */
	public int updateDspPromtDspTgt(DspPromtDspTgt dspPromtDspTgt) {
		return getSession1().update("com.plgrim.ncp.base.updateDspPromtDspTgt", dspPromtDspTgt);
	}
	
	/**
	 * 전시 기획전 전시 대상 삭제.
	 *
	 * @param dspPromtDspTgt the DspPromtDspTgt
	 * @return the DspPromtDspTgt
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspPromtDspTgt(DspPromtDspTgt dspPromtDspTgt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspPromtDspTgt", dspPromtDspTgt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

