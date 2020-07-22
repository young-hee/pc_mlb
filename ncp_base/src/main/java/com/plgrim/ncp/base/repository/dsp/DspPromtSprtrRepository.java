
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtSprtr;

/**
 * The Class DspPromtSprtrRepository.
 */
@Repository
public class DspPromtSprtrRepository extends AbstractRepository {
	

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
	 * 전시 기획전 구분자 상세 조회.
	 *
	 * @param dspPromtSprtr the DspPromtSprtr
	 * @return the DspPromtSprtr
	 * @throws SQLException the SQL exception
	 */
	public DspPromtSprtr selectDspPromtSprtr(DspPromtSprtr dspPromtSprtr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspPromtSprtr", dspPromtSprtr);
	}
	
	/**
	 * 전시 기획전 구분자 등록.
	 *
	 * @param dspPromtSprtr the DspPromtSprtr
	 * @throws SQLException the SQL exception
	 */
	public void insertDspPromtSprtr(DspPromtSprtr dspPromtSprtr) {
		dspPromtSprtr.setRprstSprtrYn("N");
		getSession1().insert("com.plgrim.ncp.base.insertDspPromtSprtr", dspPromtSprtr);
	}
	
	/**
	 * 전시 기획전 구분자 수정.
	 *
	 * @param dspPromtSprtr the DspPromtSprtr
	 * @throws SQLException the SQL exception
	 */
	public int updateDspPromtSprtr(DspPromtSprtr dspPromtSprtr) {
		return getSession1().update("com.plgrim.ncp.base.updateDspPromtSprtr", dspPromtSprtr);
	}
	
	/**
	 * 전시 기획전 구분자 삭제.
	 *
	 * @param dspPromtSprtr the DspPromtSprtr
	 * @return the DspPromtSprtr
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspPromtSprtr(DspPromtSprtr dspPromtSprtr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspPromtSprtr", dspPromtSprtr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

