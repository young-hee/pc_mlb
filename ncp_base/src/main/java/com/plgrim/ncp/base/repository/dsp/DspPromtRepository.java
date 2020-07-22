
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;

/**
 * The Class DspPromtRepository.
 */
@Repository
public class DspPromtRepository extends AbstractRepository {
	

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
	 * 전시 기획전 상세 조회.
	 *
	 * @param dspPromt the DspPromt
	 * @return the DspPromt
	 * @throws SQLException the SQL exception
	 */
	public DspPromt selectDspPromt(DspPromt dspPromt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspPromt", dspPromt);
	}
	
	/**
	 * 전시 기획전 등록.
	 *
	 * @param dspPromt the DspPromt
	 * @throws SQLException the SQL exception
	 */
	public void insertDspPromt(DspPromt dspPromt) {
		getSession1().insert("com.plgrim.ncp.base.insertDspPromt", dspPromt);
	}
	
	/**
	 * 전시 기획전 수정.
	 *
	 * @param dspPromt the DspPromt
	 * @throws SQLException the SQL exception
	 */
	public int updateDspPromt(DspPromt dspPromt) {
		return getSession1().update("com.plgrim.ncp.base.updateDspPromt", dspPromt);
	}
	
	/**
	 * 전시 기획전 삭제.
	 *
	 * @param dspPromt the DspPromt
	 * @return the DspPromt
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspPromt(DspPromt dspPromt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspPromt", dspPromt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

