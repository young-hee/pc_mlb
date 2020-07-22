
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTest;

/**
 * The Class DspAbTestRepository.
 */
@Repository
public class DspAbTestRepository extends AbstractRepository {
	

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
	 * 전시 AB 테스트 상세 조회.
	 *
	 * @param dspAbTest the DspAbTest
	 * @return the DspAbTest
	 * @throws SQLException the SQL exception
	 */
	public DspAbTest selectDspAbTest(DspAbTest dspAbTest) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspAbTest", dspAbTest);
	}
	
	/**
	 * 전시 AB 테스트 등록.
	 *
	 * @param dspAbTest the DspAbTest
	 * @throws SQLException the SQL exception
	 */
	public void insertDspAbTest(DspAbTest dspAbTest) {
		getSession1().insert("com.plgrim.ncp.base.insertDspAbTest", dspAbTest);
	}
	
	/**
	 * 전시 AB 테스트 수정.
	 *
	 * @param dspAbTest the DspAbTest
	 * @throws SQLException the SQL exception
	 */
	public int updateDspAbTest(DspAbTest dspAbTest) {
		return getSession1().update("com.plgrim.ncp.base.updateDspAbTest", dspAbTest);
	}
	
	/**
	 * 전시 AB 테스트 삭제.
	 *
	 * @param dspAbTest the DspAbTest
	 * @return the DspAbTest
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspAbTest(DspAbTest dspAbTest) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspAbTest", dspAbTest);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

