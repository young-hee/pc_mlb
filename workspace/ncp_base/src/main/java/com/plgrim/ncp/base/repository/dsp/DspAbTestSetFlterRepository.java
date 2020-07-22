
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSetFlter;

/**
 * The Class DspAbTestSetFlterRepository.
 */
@Repository
public class DspAbTestSetFlterRepository extends AbstractRepository {
	

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
	 * 전시 AB 테스트 세트 필터 상세 조회.
	 *
	 * @param dspAbTestSetFlter the DspAbTestSetFlter
	 * @return the DspAbTestSetFlter
	 * @throws SQLException the SQL exception
	 */
	public DspAbTestSetFlter selectDspAbTestSetFlter(DspAbTestSetFlter dspAbTestSetFlter) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspAbTestSetFlter", dspAbTestSetFlter);
	}
	
	/**
	 * 전시 AB 테스트 세트 필터 등록.
	 *
	 * @param dspAbTestSetFlter the DspAbTestSetFlter
	 * @throws SQLException the SQL exception
	 */
	public void insertDspAbTestSetFlter(DspAbTestSetFlter dspAbTestSetFlter) {
		getSession1().insert("com.plgrim.ncp.base.insertDspAbTestSetFlter", dspAbTestSetFlter);
	}
	
	/**
	 * 전시 AB 테스트 세트 필터 수정.
	 *
	 * @param dspAbTestSetFlter the DspAbTestSetFlter
	 * @throws SQLException the SQL exception
	 */
	public int updateDspAbTestSetFlter(DspAbTestSetFlter dspAbTestSetFlter) {
		return getSession1().update("com.plgrim.ncp.base.updateDspAbTestSetFlter", dspAbTestSetFlter);
	}
	
	/**
	 * 전시 AB 테스트 세트 필터 삭제.
	 *
	 * @param dspAbTestSetFlter the DspAbTestSetFlter
	 * @return the DspAbTestSetFlter
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspAbTestSetFlter(DspAbTestSetFlter dspAbTestSetFlter) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspAbTestSetFlter", dspAbTestSetFlter);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

