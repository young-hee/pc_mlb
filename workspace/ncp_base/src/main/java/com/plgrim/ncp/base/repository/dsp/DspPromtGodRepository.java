
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtGod;

/**
 * The Class DspPromtGodRepository.
 */
@Repository
public class DspPromtGodRepository extends AbstractRepository {
	

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
	 * 전시 기획전 상품 상세 조회.
	 *
	 * @param dspPromtGod the DspPromtGod
	 * @return the DspPromtGod
	 * @throws SQLException the SQL exception
	 */
	public DspPromtGod selectDspPromtGod(DspPromtGod dspPromtGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspPromtGod", dspPromtGod);
	}
	
	/**
	 * 전시 기획전 상품 등록.
	 *
	 * @param dspPromtGod the DspPromtGod
	 * @throws SQLException the SQL exception
	 */
	public void insertDspPromtGod(DspPromtGod dspPromtGod) {
		getSession1().insert("com.plgrim.ncp.base.insertDspPromtGod", dspPromtGod);
	}
	
	/**
	 * 전시 기획전 상품 수정.
	 *
	 * @param dspPromtGod the DspPromtGod
	 * @throws SQLException the SQL exception
	 */
	public int updateDspPromtGod(DspPromtGod dspPromtGod) {
		return getSession1().update("com.plgrim.ncp.base.updateDspPromtGod", dspPromtGod);
	}
	
	/**
	 * 전시 기획전 상품 삭제.
	 *
	 * @param dspPromtGod the DspPromtGod
	 * @return the DspPromtGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspPromtGod(DspPromtGod dspPromtGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspPromtGod", dspPromtGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

