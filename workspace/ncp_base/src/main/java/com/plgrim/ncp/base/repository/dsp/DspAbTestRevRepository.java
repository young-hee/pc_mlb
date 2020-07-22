
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestRev;

/**
 * The Class DspAbTestRevRepository.
 */
@Repository
public class DspAbTestRevRepository extends AbstractRepository {
	

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
	 * 전시 AB 테스트 개정 상세 조회.
	 *
	 * @param dspAbTestRev the DspAbTestRev
	 * @return the DspAbTestRev
	 * @throws SQLException the SQL exception
	 */
	public DspAbTestRev selectDspAbTestRev(DspAbTestRev dspAbTestRev) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspAbTestRev", dspAbTestRev);
	}
	
	/**
	 * 전시 AB 테스트 개정 등록.
	 *
	 * @param dspAbTestRev the DspAbTestRev
	 * @throws SQLException the SQL exception
	 */
	public void insertDspAbTestRev(DspAbTestRev dspAbTestRev) {
		getSession1().insert("com.plgrim.ncp.base.insertDspAbTestRev", dspAbTestRev);
	}
	
	/**
	 * 전시 AB 테스트 개정 수정.
	 *
	 * @param dspAbTestRev the DspAbTestRev
	 * @throws SQLException the SQL exception
	 */
	public int updateDspAbTestRev(DspAbTestRev dspAbTestRev) {
		return getSession1().update("com.plgrim.ncp.base.updateDspAbTestRev", dspAbTestRev);
	}
	
	/**
	 * 전시 AB 테스트 개정 삭제.
	 *
	 * @param dspAbTestRev the DspAbTestRev
	 * @return the DspAbTestRev
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspAbTestRev(DspAbTestRev dspAbTestRev) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspAbTestRev", dspAbTestRev);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

