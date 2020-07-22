
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSet;

/**
 * The Class DspAbTestSetRepository.
 */
@Repository
public class DspAbTestSetRepository extends AbstractRepository {
	

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
	 * 전시 AB 테스트 세트 상세 조회.
	 *
	 * @param dspAbTestSet the DspAbTestSet
	 * @return the DspAbTestSet
	 * @throws SQLException the SQL exception
	 */
	public DspAbTestSet selectDspAbTestSet(DspAbTestSet dspAbTestSet) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspAbTestSet", dspAbTestSet);
	}
	
	/**
	 * 전시 AB 테스트 세트 등록.
	 *
	 * @param dspAbTestSet the DspAbTestSet
	 * @throws SQLException the SQL exception
	 */
	public void insertDspAbTestSet(DspAbTestSet dspAbTestSet) {
		getSession1().insert("com.plgrim.ncp.base.insertDspAbTestSet", dspAbTestSet);
	}
	
	/**
	 * 전시 AB 테스트 세트 수정.
	 *
	 * @param dspAbTestSet the DspAbTestSet
	 * @throws SQLException the SQL exception
	 */
	public int updateDspAbTestSet(DspAbTestSet dspAbTestSet) {
		return getSession1().update("com.plgrim.ncp.base.updateDspAbTestSet", dspAbTestSet);
	}
	
	/**
	 * 전시 AB 테스트 세트 삭제.
	 *
	 * @param dspAbTestSet the DspAbTestSet
	 * @return the DspAbTestSet
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspAbTestSet(DspAbTestSet dspAbTestSet) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspAbTestSet", dspAbTestSet);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

