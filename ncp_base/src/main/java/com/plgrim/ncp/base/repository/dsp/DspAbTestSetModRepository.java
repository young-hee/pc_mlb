
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSetMod;

/**
 * The Class DspAbTestSetModRepository.
 */
@Repository
public class DspAbTestSetModRepository extends AbstractRepository {
	

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
	 * 전시 AB 테스트 세트 변경 상세 조회.
	 *
	 * @param dspAbTestSetMod the DspAbTestSetMod
	 * @return the DspAbTestSetMod
	 * @throws SQLException the SQL exception
	 */
	public DspAbTestSetMod selectDspAbTestSetMod(DspAbTestSetMod dspAbTestSetMod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspAbTestSetMod", dspAbTestSetMod);
	}
	
	/**
	 * 전시 AB 테스트 세트 변경 등록.
	 *
	 * @param dspAbTestSetMod the DspAbTestSetMod
	 * @throws SQLException the SQL exception
	 */
	public void insertDspAbTestSetMod(DspAbTestSetMod dspAbTestSetMod) {
		getSession1().insert("com.plgrim.ncp.base.insertDspAbTestSetMod", dspAbTestSetMod);
	}
	
	/**
	 * 전시 AB 테스트 세트 변경 수정.
	 *
	 * @param dspAbTestSetMod the DspAbTestSetMod
	 * @throws SQLException the SQL exception
	 */
	public int updateDspAbTestSetMod(DspAbTestSetMod dspAbTestSetMod) {
		return getSession1().update("com.plgrim.ncp.base.updateDspAbTestSetMod", dspAbTestSetMod);
	}
	
	/**
	 * 전시 AB 테스트 세트 변경 삭제.
	 *
	 * @param dspAbTestSetMod the DspAbTestSetMod
	 * @return the DspAbTestSetMod
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspAbTestSetMod(DspAbTestSetMod dspAbTestSetMod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspAbTestSetMod", dspAbTestSetMod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

